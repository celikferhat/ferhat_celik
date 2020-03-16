#define __USE_XOPEN
#define _GNU_SOURCE
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <libgen.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <limits.h>
#include <dirent.h>
#include <time.h>
#include<signal.h> 
#include <pthread.h>
#include <arpa/inet.h>


int PORT;
pthread_t *thread_id;
int thread_size=0;
int online_thread_size=0;
char server_directory[400];


void sil(char *dosya_adi,char *direc);

int cfileexists(const char * filename){
    /* try to open file to read */
    FILE *file;
    if ( (file = fopen(filename, "r")) ){
        fclose(file);
        return 1;
    }
    return 0;
}
void removeSubstring(char *s,const char *toremove)
{
  while( (s=strstr(s,toremove)) )
    memmove(s,s+strlen(toremove),1+strlen(s+strlen(toremove)));
}

int mkdirr(const char * path, const mode_t mode, const int fail_on_exist)
{
  int result = 0;
  char * dir = NULL;

  do
  {
    if (NULL == path)
    {
      errno = EINVAL;
      result = -1;
      break;
    }

    if ((dir = strrchr(path, '/')))
    {
      *dir = '\0';
      result = mkdirr(path, mode, fail_on_exist);
      *dir = '/';

      if (result)
      {
       // break;
      }
    }

    if (strlen(path))
    {
      if ((result = mkdir(path, mode)))
      {
        char s[PATH_MAX];
        sprintf(s, "mkdir() failed for '%s'", path);
        //perror(s);

        if ((EEXIST == result) && (0 == fail_on_exist))
        {
          result = 0;
        }
        else
        {
          break;
        }
      }
    }
  } while (0);

  return result;
}

void keeplog(char *line,char *location){
  char log_location[400];
  strcpy(log_location,location);  
  strcat(log_location,"/Log.txt");
  FILE *fp;
  fp = fopen(log_location,"a");
  if(fp == NULL){
    printf("LOG %s\n",strerror(errno) );
    exit(EXIT_FAILURE);
  }
  fprintf(fp, "%s",line);
  fclose(fp);

}



void removeChar(char *str, char garbage) {

    char *src, *dst;
    for (src = dst = str; *src != '\0'; src++) {
        *dst = *src;
        if (*dst != garbage) dst++;
    }
    *dst = '\0';
}

int check_file(char *file_line,char *direc){
  int mybool=0;
  char mystring[400];
  char temp_string[400];
  strcpy(temp_string,direc);
  strcat(temp_string,"/file_list.txt");

  char gelenline[300];
  strcpy(gelenline,file_line);
  int i=0;
  char *token = strtok(gelenline, "|");
  char file[300];
  char date[50];
  while (token != NULL)
  {
        if(i==0){
         strcpy(file,token);
        }
        else if(i == 1){
         strcpy(date,token);
        }


      i++;
      token = strtok(NULL, "|");
  }

 if(cfileexists(temp_string)){
            FILE * fp;
            char * line = NULL;
            size_t len = 0;
            ssize_t read;
            fp = fopen(temp_string, "r");
            if (fp == NULL)
                exit(EXIT_FAILURE);

            while ((read = getline(&line, &len, fp)) != -1) {

              char newline[400],newfile[300];
              int i=0;
              strcpy(newline,line);
              char *token = strtok(newline, "|");
               while (token != NULL)
                {
                      if(i==0){
                       strcpy(newfile,token);
                      }                     
                  i++;
                    token = strtok(NULL, "|");
                }




              if(strcmp(newline,file) == 0){            

                struct tm tm,tm2;
                strptime(date, "%a %b %d %H:%M:%S %Y", &tm);
                time_t client_file_date = mktime(&tm);

                char ch = '|';
                char *ret;
                ret = strrchr(line, ch);
                char* str = malloc(strlen("|Thu Mon 14 15:15:15 2019     ")+1);
                strcpy(str, ret);
                removeChar(str, '|');
                strptime(str, "%a %b %d %H:%M:%S %Y", &tm2);
                time_t server_file_date = mktime(&tm2);
                free(str);
                if(server_file_date >= client_file_date )
                {
                  return 1;
                }else{
                  mybool=1;
                  printf("%s   %s\n",temp_string,line);                  
                  strcpy(mystring,line);                  
                  
                }



              }
                  
            }

            fclose(fp);
            if (line)
                free(line);
              if(mybool == 1){                  
                  removeSubstring(temp_string,"/file_list.txt");                    
                  sil(mystring,temp_string);
                  
                  return 0;
                }

          }
          else{
                FILE * fp = fopen(temp_string, "a");
                  fclose(fp);
            return 0;
          }




return 0;
}



void sil(char *dosya_adi,char *direc){

    char temp_string[400],temp_string2[400];
    strcpy(temp_string,direc);
    strcat(temp_string,"/file_list.txt");

    strcpy(temp_string2,direc);
    strcat(temp_string2,"/*mytempfile*.txt");

  FILE * fp,*fp2;
    char * line = NULL;
    size_t len = 0;
    ssize_t readbyte;
    fp = fopen(temp_string, "r");
    if(fp == NULL)
      printf("[SİL Function]%s\n",strerror(errno) );
    fp2 = fopen(temp_string2, "a");
    if (fp == NULL)
       exit(EXIT_FAILURE);
  while ((readbyte = getline(&line, &len, fp)) != -1) {
   
      if( strstr(line,dosya_adi) == 0){
        fprintf(fp2, "%s", line);
      }
      
 
   }
   fclose(fp);
   fclose(fp2);
   free(line);
   remove(temp_string);
   rename(temp_string2,temp_string);

}



int check_file_for_delete(char *file_line,char *direc){
  char line[300];
  char temp_string[400];
  strcpy(temp_string,direc);
  strcat(temp_string,"/client_to_server_list.txt");
  strcpy(line,file_line);
  int i=0;
  char *token = strtok(line, "|");
  char file[300];
  char date[50];
  while (token != NULL)
  {
        if(i==0){
         strcpy(file,token);
        }
        else if(i == 1){
         strcpy(date,token);
        }


      i++;
      token = strtok(NULL, "|");
  }
  if(cfileexists(temp_string)){
             FILE * fp;
            char * line = NULL;
            size_t len = 0;
            ssize_t read;
            fp = fopen(temp_string, "r");
            if (fp == NULL)
                exit(EXIT_FAILURE);
              int flag=0;
            while ((read = getline(&line, &len, fp)) != -1) {

              if(strcmp(file_line,line) == 0)
                  flag=1;
            }
            if(flag==0)
            {
              char pt[300];
              strcpy(pt,direc);
              strcat(pt,file);
              remove(pt);
              sil(file_line,direc);
              keeplog("File deleted:",direc);
              keeplog(file,direc);
              keeplog("\n",direc);
              return 1;
            }

            fclose(fp);
            if (line)
                free(line);




  }else{
   FILE * fp = fopen(temp_string, "a");
   fclose(fp);
  return 0;
 }




return 0;
}





void *cs_thread(void *args){

int valread;
char buffer[1024] = {0};

int new_socket = *((int *) args);
free(args);
char new_dir_name[20];
read(new_socket, new_dir_name, 20);

char thread_c_dir[400];
strcpy(thread_c_dir,server_directory);
strcat(thread_c_dir,new_dir_name);
mkdir(thread_c_dir,0777);
keeplog("Client connected.Directory name:",thread_c_dir);
keeplog(new_dir_name,thread_c_dir);
keeplog("\n",thread_c_dir);


int first_connection=0;


while(1){
  if(first_connection == 0){
    printf("First connection\n");
    char flist[400];
    strcpy(flist,thread_c_dir);
    strcat(flist,"/file_list.txt");

    int fdfilelist = open(flist,O_RDONLY);printf("fdfilelist%d\n",fdfilelist );
    if(fdfilelist == -1) {
        FILE * fp = fopen(flist, "a");
                  fclose(fp);
                  send(new_socket, "*nal*", strlen("*nal*"), 0);
        first_connection++;
        continue;
    }
      ssize_t read_len;
      int counter=0;

      while(1) {

      memset(buffer, 0x00, 1024);
      read_len = read(fdfilelist, buffer, 1024);printf("readlen%d\n",(int)read_len );
          if(read_len>0){        
        send(new_socket, buffer, read_len, 0);
          }
        else{

          if(counter == 0){
              send(new_socket, "*nal*", strlen("*nal*"), 0);

            }
        }
        if(read_len == 0) {
            close(fdfilelist);            
            break;
        }
        counter++;
    }
    printf("aaçtı\n");
    send(new_socket, "\0", strlen("\0"), 0);
    memset(buffer, 0x00, 1024);
    while (1) {
      
      memset(buffer, 0x00, 300);
      read_len = read(new_socket, buffer, 300);printf("burada\n");
      printf("[%s]\n",buffer );
      if(strcmp(buffer,"*endoffile*") != 0){

      char dizin[300];
      strcpy(dizin,thread_c_dir);
      strcat(dizin,buffer);
      int tmpfd = open(dizin, O_RDONLY);
      if(!tmpfd) {
          perror("Error : ");
          exit(EXIT_FAILURE);
      }
      printf("acilan dosya %s return %d\n",dizin,tmpfd );
      if(tmpfd < 0)
        exit(EXIT_FAILURE);
      ssize_t read_lenght;
      while(1) {
          memset(buffer, 0x00, 1024);
          read_lenght = read(tmpfd, buffer, 1024);
          send(new_socket, buffer, read_lenght, 0);
          printf("read lenght %d\n",(int)read_lenght );
          if(read_lenght == 0) {
            close(tmpfd);
            printf("kapattım\n");
              break;
          }

      }
       send(new_socket, "*deneme*", strlen("*deneme*"), 0);
      keeplog("Sended file:",thread_c_dir);
      keeplog(dizin,thread_c_dir);
      keeplog("\n",thread_c_dir);

    }



    if(strcmp(buffer,"*endoffile*") == 0){
      
      break;
    }

    }



    printf("\nilk bağlantı bitti\n");
    first_connection++;

  }
  else{
    fflush(stdout);
  printf("LOOP\n");
    char check_delete_file[20];
    int read_return = read(new_socket, check_delete_file, 20);
    if(read_return == 0){
      close(new_socket);
      online_thread_size--;
      printf("baglantı koptu\n");
      keeplog("Client connection ended\n",thread_c_dir);
      return NULL;
    }
        

printf("%s\n",check_delete_file );

    if(strstr(check_delete_file,"file_deleted") != 0){
    
    char temp_string[400];
    strcpy(temp_string,thread_c_dir);
    strcat(temp_string,"/client_to_server_list.txt");  
    remove(temp_string);
    int fdw = open(temp_string, O_WRONLY | O_TRUNC | O_CREAT,0666);

  while(1) {
  memset(buffer, 0x00, 1024);
  valread = read(new_socket, buffer, 1024);
  write(fdw, buffer, valread);

  if(strstr(buffer,"\0") != 0) {
        close(fdw);
        break;
  }
  
}

/*karşılaştır
*/
  FILE * fp;
  char * line = NULL;
  size_t len = 0;
  ssize_t readbyte; 
  strcpy(temp_string,thread_c_dir);
  strcat(temp_string,"/file_list.txt");  
  fp = fopen(temp_string, "r");
  if (fp == NULL)
     exit(EXIT_FAILURE);
 while ((readbyte = getline(&line, &len, fp)) != -1) {

     check_file_for_delete(line,thread_c_dir);   
 
 }

 
 fclose(fp);

  strcpy(check_delete_file,"");

    }else if(strstr(check_delete_file,"/*/%") != 0){

    char temp_string[400];
    strcpy(temp_string,thread_c_dir);
    strcat(temp_string,"/client_to_server_list.txt");
    remove(temp_string);
    int fdw = open(temp_string, O_WRONLY | O_TRUNC | O_CREAT,0666);
printf("abcdefe\n");
  while(1) {
  memset(buffer, 0x00, 1024);
  valread = read(new_socket, buffer, 1024);
  if(strstr(buffer,"*nal*") != 0){
      close(fdw);
      break;
    }
  write(fdw, buffer, valread);

  if(strstr(buffer,"\0") != 0) {
        close(fdw);
        break;
  }
  
}



  FILE * fp;
  char * line = NULL;
  size_t len = 0;
  ssize_t readbyte;
  char **files;
  int line_count=0;

  fp = fopen(temp_string, "r");
 if (fp == NULL)
     exit(EXIT_FAILURE);
 while ((readbyte = getline(&line, &len, fp)) != -1) {

      if(strlen(line) != 0){
     int ret_val = check_file(line,thread_c_dir);
     if(ret_val == 0){       
       line_count++;
     }
   }
 }
 fclose(fp);




if(line_count>0){
 files = calloc(line_count,sizeof(char*));
    for (int i = 0; i < line_count; i++)
        files[i] = malloc((300+1) * sizeof(char));



  int k=0;
 fp = fopen(temp_string, "r");
 if (fp == NULL)
     exit(EXIT_FAILURE);

  while ((readbyte = getline(&line, &len, fp)) != -1) {     
      int ret_val = check_file(line,thread_c_dir);
       if(ret_val == 0){
         strcpy(files[k],line);         
         k++;
       }

  }
fclose(fp);
}


printf("%d\n",line_count );
/*clientte olup serverda olmayan dosyalar var ==> files*/
for(int i=0;line_count>i;i++){

  char getfile[300];
  strcpy(getfile,files[i]);
  const char ch = '|';
  printf("aaaaa\n");
    char *ret;
    ret = strrchr(getfile, ch);


    char tarih[20];
    strcpy(tarih,ret);    
    removeSubstring(getfile,tarih);
    
  send(new_socket,getfile,300,0);
  
  char dizin[300];
  strcpy(dizin,thread_c_dir);
  strcat(dizin,"/");
  int string_lenght=strlen(files[i]);
  for(int j=0;string_lenght-1>j;j++){
    files[i][j] = files[i][j+1];
  }
  files[i][string_lenght-1]='\0';

  char dir_string[300];
  strcpy(dir_string,files[i]); 

   char new_file[300];
  removeSubstring(dir_string,tarih);
  strcpy(new_file,dir_string);
  char bname[100];
  strcpy(bname,basename(dir_string));
  removeSubstring(dir_string,bname);  
  strcat(dizin,dir_string);  
  mkdirr(dizin,0777,0);
  strcat(dizin,bname);

  if(strcmp(basename(files[i]) , "*endoffile*") == 0)
        continue;

  int tmpfd = open(dizin, O_WRONLY | O_TRUNC | O_CREAT,0666);
  char newbuffer[1024];

  while(1) {
  memset(newbuffer, 0x00, 1024);
  valread = read(new_socket, newbuffer, 1024);  
  if(strstr(newbuffer,"*deneme*") != 0){
    close(tmpfd);
    break;
  }else{
  write(tmpfd, newbuffer, valread);}

}


printf("new file%s\n",new_file);
keeplog("New file:",thread_c_dir);
keeplog(new_file,thread_c_dir);
keeplog("\n",thread_c_dir);

strcpy(temp_string,thread_c_dir);
strcat(temp_string,"/file_list.txt");
FILE *f = fopen(temp_string, "a");
if (f == NULL)
{
    printf("Error opening file!\n");
    exit(1);
}
fprintf(f, "/%s",files[i]);
fclose(f);

}

 int send_return = send(new_socket, "*endoffile*", strlen("*endoffile*"), 0);
 printf("send return %d\n",send_return );
 if(send_return == -1)
    strerror(errno);
printf("end gönderdi\n");

if (line)
     free(line);
if(line_count>0){
 

 for(int i=0;line_count>i;i++)
         free(files[i]);

  free(files);
}
strcpy(check_delete_file,"");
}

}
}





return NULL;
}





void server(){

  int server_fd, new_socket;
  struct sockaddr_in address;
  int opt = 1;
  int addrlen = sizeof(address);
  


  // Creating socket file descriptor
  if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
  {
      perror("socket failed");
      exit(EXIT_FAILURE);
  }


  // Forcefully attaching socket to the port 8080
  if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT,&opt, sizeof(opt)))
  {
      perror("setsockopt");
      exit(EXIT_FAILURE);
  }
  address.sin_family = AF_INET;
  address.sin_addr.s_addr = INADDR_ANY;
  /* if(inet_pton(AF_INET, "192.168.1.118", &address.sin_addr)<=0)  
    { 
        printf("\nInvalid address/ Address not supported \n"); 
        exit(EXIT_FAILURE); 
    } */
  address.sin_port = htons( PORT );

  // Forcefully attaching socket to the port 8080
  if (bind(server_fd, (struct sockaddr *)&address,sizeof(address))<0)
  {
      perror("bind failed");
      exit(EXIT_FAILURE);
  }

  if (listen(server_fd, 10) < 0)     //kuyrukta bekleyecek baglantı sayısı
  {
      perror("listen");
      exit(EXIT_FAILURE);
  }


  while(1){
    if(online_thread_size < thread_size){
  if ((new_socket = accept(server_fd, (struct sockaddr *)&address,(socklen_t*)&addrlen))<0)
  {
      perror("accept");
      exit(EXIT_FAILURE);
  }



int *arg = malloc(sizeof(*arg));
  *arg = new_socket;
  pthread_create(&thread_id[online_thread_size], 0, cs_thread, arg);
  pthread_detach(thread_id[online_thread_size]);
  online_thread_size++;
}
}







}






void handle_sigint(int sig) 
{ 
  printf("Caught signal %d\n", sig);
  free(thread_id); 
  exit(EXIT_FAILURE);
} 

int main(int argc, char const *argv[])
{
  signal(SIGINT, handle_sigint);
if(argc == 4){
  PORT = atoi(argv[3]);
  thread_size = atoi(argv[2]);
  thread_id = calloc(thread_size, sizeof(thread_id));
  strcpy(server_directory,argv[1]);
  server();
  free(thread_id);
}
else 
  printf("Wrong usage\n");
    return 0;
}
