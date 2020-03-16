// Client side C/C++ program to demonstrate Socket programming
#define __USE_XOPEN
#define _GNU_SOURCE
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <dirent.h>
#include <sys/file.h>
#include <time.h>
#include <libgen.h>
#include <sys/inotify.h>
#include <signal.h>
#include <semaphore.h>
#include <errno.h>
#include <utime.h>

#define EVENT_SIZE  ( sizeof (struct inotify_event) )
#define EVENT_BUF_LEN     ( 1024 * ( EVENT_SIZE + 16 ) )



char IP_ADDR[30];
int PORT;

char deleted_file_name[300];
char client_directory[400];


void removeSubstring(char *s,const char *toremove)
{
  while( (s=strstr(s,toremove)) )
    memmove(s,s+strlen(toremove),1+strlen(s+strlen(toremove)));
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
int cfileexists(const char * filename){
    /* try to open file to read */
    FILE *file;
    if ( (file = fopen(filename, "r")) ){
        fclose(file);
        return 1;
    }
    return 0;
}
void removeChar(char *str, char garbage) {

    char *src, *dst;
    for (src = dst = str; *src != '\0'; src++) {
        *dst = *src;
        if (*dst != garbage) dst++;
    }
    *dst = '\0';
}

int first_check_file(char *file_line,char *direc){
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

              if(strstr(line,file) ){
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


int check_file(char *directory_path,char *base_path){
sleep(1);
  char temp_string[400];
  strcpy(temp_string,client_directory);
  strcat(temp_string,"/file_list.txt");
    DIR* dir;
    struct dirent *dirEntry;
    struct stat fileStat;
    char file_path[300];
    if (!(dir = opendir(directory_path)))
        return -1;

    while ((dirEntry=readdir(dir)) != 0) {

        if (strcmp(dirEntry->d_name, ".") == 0 || strcmp(dirEntry->d_name, "..") == 0)
            continue;
        snprintf(file_path, sizeof(file_path), "%s/%s", directory_path, dirEntry->d_name);

        lstat (file_path, &fileStat);
        if (S_ISDIR(fileStat.st_mode)){

            check_file(file_path,base_path);

        }else{

            char date[80];
            time_t t = fileStat.st_mtime;
			struct tm lt;
			localtime_r(&t, &lt);
			
			strftime(date, sizeof(date), "%c", &lt);
            


            FILE *f = fopen(temp_string, "a");
            if (f == NULL)
            {
                printf("Error opening file!\n");
                exit(1);
            }
            
            
            char temp_file_path[300];
            strcpy(temp_file_path,file_path);
            removeSubstring(temp_file_path,base_path);
            if(strcmp(temp_file_path,"/server_to_client.txt") != 0)
            	fprintf(f, "%s|%s\n", temp_file_path ,date);
            fclose(f);





        }





    }

FILE *f = fopen(temp_string, "a");
fclose(f);
return 0;
}


void add_noti(int wd,int fd,char *path,char *base_path){
	DIR* dir;
    struct dirent *dirEntry;
    struct stat fileStat;
    char file_path[300];
    if (!(dir = opendir(path)))
        exit(EXIT_FAILURE);

    while ((dirEntry=readdir(dir)) != 0) {
    		  if (strcmp(dirEntry->d_name, ".") == 0 || strcmp(dirEntry->d_name, "..") == 0)
            continue;
        snprintf(file_path, sizeof(file_path), "%s/%s", path, dirEntry->d_name);
        lstat (file_path, &fileStat);
        if (S_ISDIR(fileStat.st_mode)){

            add_noti(wd,fd,file_path,base_path);
            wd = inotify_add_watch( fd, file_path, IN_MODIFY | IN_DELETE | IN_MOVED_TO | IN_MOVED_FROM );

        }
    }
}


void noti(char *path){

 int length, i = 0;
  int fd;
  int wd;
  char buffer[EVENT_BUF_LEN];

  /*creating the INOTIFY instance*/
  fd = inotify_init();

  /*checking for error*/
  if ( fd < 0 ) {
    perror( "inotify_init" );
  }

  /*adding the вЂњ/tmpвЂќ directory into watch list. Here, the suggestion is to validate the existence of the directory before adding into monitoring list.*/
  wd = inotify_add_watch( fd, path, IN_CREATE | IN_MODIFY | IN_DELETE | IN_MOVED_TO | IN_MOVED_FROM );


    add_noti(wd,fd,path,path);




  /*read to determine the event change happens on вЂњ/tmpвЂќ directory. Actually this read blocks until the change event occurs*/ 

  length = read( fd, buffer, EVENT_BUF_LEN ); 

  /*checking for error*/
  if ( length < 0 ) {
    perror( "read" );
  }  

  /*actually read return the list of change events happens. Here, read the change event one by one and process it accordingly.*/
  while ( i < length ) {     
        struct inotify_event *event = ( struct inotify_event * ) &buffer[ i ];


     if ( event->len ) {
      
        if ( event->mask & IN_CREATE ) {
        strcpy(deleted_file_name,"/*/%");
        
      }
      
      if ( event->mask & IN_DELETE ) {
        if ( event->mask & IN_ISDIR ) {
          printf( "Directory %s deleted.\n", event->name );
          strcpy(deleted_file_name,"/*/%");
          
        }
        else {
          printf( "File %s deleted.\n", event->name );
          strcpy(deleted_file_name,event->name);
          
        }
      }
      else if ( event->mask & IN_MOVED_TO ) {        
          printf(  "file %s dosya geldi\n", event->name );
          strcpy(deleted_file_name,"/*/%");         
        
      }
      else if ( event->mask & IN_MOVED_FROM ) {  
        if(event->name[0] != '.')                
          printf( "File %s dosya gitti.\n", event->name );
          strcpy(deleted_file_name,event->name);
          
        
      }
      else if ( event->mask & IN_MODIFY ) {        
          printf( "modify %s\n", event->name );
          strcpy(deleted_file_name,"/*/%");         
        
      }
        
        

        


    }
    
    i += EVENT_SIZE + event->len;
  }
  /*removing the вЂњ/tmpвЂќ directory from the watch list.*/
  
 inotify_rm_watch( fd, wd );

  /*closing the INOTIFY instance*/
 
  close( fd );


}




int soket(){

		int sock = 0;
		struct sockaddr_in serv_addr;

		char buffer[1024] = {0};
		if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
		{
				printf("\n Socket creation error \n");
				return -1;
		}

		memset(&serv_addr, '0', sizeof(serv_addr));

		serv_addr.sin_family = AF_INET;
		serv_addr.sin_port = htons(PORT);

		// Convert IPv4 and IPv6 addresses from text to binary form
		if(inet_pton(AF_INET, IP_ADDR, &serv_addr.sin_addr)<=0)
		{
				printf("\nInvalid address/ Address not supported \n");
				return -1;
		}

		if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
		{
				printf("\nConnection Failed \n");
				return -1;
		}

    const char ch = '/';
    char *ret;
    ret = strrchr(client_directory, ch);
    send(sock,ret,20,0);
int first_connection=0;


if(first_connection == 0){
		check_file(client_directory,client_directory);
		printf("first_connection\n");
		char first_s_to_c[400];
		strcpy(first_s_to_c,client_directory);
		strcat(first_s_to_c,"/server_to_client.txt");
		int fdw = open(first_s_to_c, O_WRONLY | O_TRUNC | O_CREAT,0666); 
		if(fdw == -1){
			printf("server to client oluşturulamadı:%s\n",strerror(errno) );
		}
		 while(1) {
		  memset(buffer, 0x00, 1024);
		  int valread = read(sock, buffer, 1024);
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
	    int line_count=0;printf("%s\n",first_s_to_c );
	    fp = fopen(first_s_to_c, "r");
	    if (fp == NULL){
	    		printf("nal %s\n",strerror(errno));
		     exit(EXIT_FAILURE);
		 }
		 while ((readbyte = getline(&line, &len, fp)) != -1) {		 		
		      if(strlen(line) != 0){
		     int ret_val = first_check_file(line,client_directory);
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
			 fp = fopen(first_s_to_c, "r");
			 if (fp == NULL)
			     exit(EXIT_FAILURE);

			  while ((readbyte = getline(&line, &len, fp)) != -1) {			  		    
			      int ret_val = first_check_file(line,client_directory);			      
			       if(ret_val == 0){
			       	printf("line%s\n",line );
			         strcpy(files[k],line);

			         
			         k++;
			       }

			  }
			fclose(fp);
			}

			for(int i=0;line_count>i;i++){

				  char getfile[300];
				  strcpy(getfile,files[i]);
				  	printf("%s\n",files[i] );
				  const char ch = '|';
				  printf("aaaaa\n");
				    char *ret;
				    ret = strrchr(getfile, ch);
				    

				    char tarih[20];
				    strcpy(tarih,ret);    
				    removeSubstring(getfile,tarih);
				    
				  ssize_t gnd = send(sock,getfile,300,0);
				  if(gnd == -1){
				  	printf("first send fail%s\n",strerror(errno) );
				  }
				  
				  char dizin[300];
				  strcpy(dizin,client_directory);
				  strcat(dizin,"/");
				  int string_lenght=strlen(files[i]);
				  for(int j=0;string_lenght-1>j;j++){
				    files[i][j] = files[i][j+1];
				  }
				  files[i][string_lenght-1]='\0';

				  char dir_string[300];
				  strcpy(dir_string,files[i]); 

				   
				  removeSubstring(dir_string,tarih);
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
				  int valread = read(sock, newbuffer, 1024);printf("geldi %d\n",valread);
				  
				  if(strstr(newbuffer,"*deneme*") != 0){
				    close(tmpfd);
				    break;
				  }else{
				  write(tmpfd, newbuffer, valread);
				}

				}
				

				char new_tarih[80];
				strcpy(new_tarih,tarih);
				removeChar(new_tarih,'|');
				struct tm tm;
    			strptime(new_tarih, "%a %b %d %H:%M:%S %Y", &tm);
    			time_t t = mktime(&tm);
    			
    			struct utimbuf ut;
    			ut.modtime = t;
    			utime(dizin,&ut);

				strcpy(first_s_to_c,client_directory);
				strcat(first_s_to_c,"/file_list.txt");
				FILE *f = fopen(first_s_to_c, "a");
				if (f == NULL)
				{
				    printf("Error opening file!\n");
				    exit(1);
				}
				fprintf(f, "/%s",files[i]);
				fclose(f);

				}
				send(sock, "*endoffile*", 300, 0);
				printf("end gönderdi\n");
				if (line)
				     free(line);
				if(line_count>0){
 

				 for(int i=0;line_count>i;i++)
				         free(files[i]);

				  free(files);
				} 

				



				
		first_connection++;
	}







while(1){
		
	
		printf("second\n");
  if(strcmp(deleted_file_name,"/*/%") == 0){
		
  char temp_string[400];
  strcpy(temp_string,client_directory);
  strcat(temp_string,"/file_list.txt");
  	
    remove(temp_string);
		 
		check_file(client_directory,client_directory);

    ssize_t sret = send(sock,deleted_file_name,20,0);
    if(sret == -1){
    	printf("SERVER DOWN [%s]\n",strerror(errno) );
    }
		int sourse_fd = open(temp_string, O_RDONLY);
		if(!sourse_fd) {
				perror("Error : ");
				return 1;
		}
		 
		ssize_t read_len;
    	int counter=0;    
		while(1) {

			memset(buffer, 0x00, 1024);
			read_len = read(sourse_fd, buffer, 1024);
        	if(read_len>0){        
				send(sock, buffer, read_len, 0);
        	}
        else{

          if(counter == 0)
              send(sock, "*nal*", strlen("*nal*"), 0);
        }
				if(read_len == 0) {
						close(sourse_fd);            
						break;
				}
        counter++;
		}

    ssize_t aa = send(sock, "\0", strlen("\0"), 0);
    printf("aa%d\n",aa );

    memset(buffer, 0x00, 1024);
   
    while (1) {
    	
      memset(buffer, 0x00, 300);
      read_len = read(sock, buffer, 300);
      printf("burada\n");      
      printf("[%s]\n",buffer );      
      if(strcmp(buffer,"*endoffile*") != 0){

      char dizin[300];
      strcpy(dizin,client_directory);
      strcat(dizin,buffer);
      int tmpfd = open(dizin, O_RDONLY);
      if(!tmpfd) {
          perror("Error : ");
          return 1;
      }
      printf("acilan dosya %s return %d\n",dizin,tmpfd );
      if(tmpfd < 0)
      	exit(EXIT_FAILURE);
      ssize_t read_lenght;
      while(1) {
  				memset(buffer, 0x00, 1024);
  				read_lenght = read(tmpfd, buffer, 1024);
  				send(sock, buffer, read_lenght, 0);
  				printf("read lenght %d\n",(int)read_lenght );
  				if(read_lenght == 0) {
  					close(tmpfd);
  					printf("kapattım\n");
  						break;
  				}

  		}

      send(sock, "*deneme*", strlen("*deneme*"), 0);

    }



    if(strcmp(buffer,"*endoffile*") == 0){
    	
    	break;
    }

    }
printf("DONGU\n");
    }
    else{
      send(sock, "file_deleted", 20, 0);
      char temp_string[400];
  strcpy(temp_string,client_directory);
  strcat(temp_string,"/file_list.txt");
      remove(temp_string);
  check_file(client_directory,client_directory);



    int sourse_fd = open(temp_string, O_RDONLY);
    if(!sourse_fd) {
        perror("Error : ");
        return 1;
    }

    ssize_t read_len;
    while(1) {

        memset(buffer, 0x00, 1024);
        read_len = read(sourse_fd, buffer, 1024);
        send(sock, buffer, read_len, 0);
        if(read_len == 0) {
            close(sourse_fd);
            break;
        }

    }

    send(sock, "\0", strlen("\0"), 0);








    }
noti(client_directory);
printf("notiden çıktı\n");

}
	return 0;
}


void handle_sigint(int sig) 
{ 
  printf("Caught signal %d\n", sig);
  char temp_string[400];
  strcpy(temp_string,client_directory);
  strcat(temp_string,"/file_list.txt");
  remove(temp_string); 
  exit(EXIT_FAILURE);
} 


int main(int argc, char const *argv[])
{
  signal(SIGINT, handle_sigint);

if(argc == 4){
	char cwd[400];
	getcwd(cwd,sizeof(cwd));
	strcat(cwd,"/");
	strcat(cwd,argv[1]);

	strcpy(IP_ADDR,argv[2]);
	PORT = atoi(argv[3]);
  strcpy(client_directory,cwd);printf("%s\n",client_directory );
  strcpy(deleted_file_name,"/*/%");
  soket();
  }
  else{
  	printf("Wrong usage!\n");
  }
    return 0;
}
