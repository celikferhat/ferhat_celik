#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <string.h>
#include <sys/stat.h>
#include <dirent.h>
#include <errno.h>
#include <ctype.h>
#define READ_END	0
#define WRITE_END	1


void cd(char *path){
    chdir(path);
}


void help(){
  printf("cat [path]\npwd\nlsf\nwc [path]\nbunedu [path]\nbunedu -z [path]\ncd [dirpath]\nexit\n" );

}

void removeSpaces(char *str)
{
    // To keep track of non-space character count
    int count = 0;

    // Traverse the given string. If current character
    // is not space, then place it at index 'count++'
    for (int i = 0; str[i]; i++)
        if (str[i] != ' ')
            str[count++] = str[i]; // here count is
                                   // incremented
    str[count] = '\0';
}


int pipeControl(char *str){
  int flag=0;

    for(int i=0;str[i];i++)
        if(str[i] == '|')
            flag=1;
  return flag;
}


int tofile_control(char *str){
  int flag=0;

    for(int i=0;str[i];i++)
        if(str[i] == '>')
            flag=1;
  return flag;

}


int infile_control(char *str){
  int flag=0;

    for(int i=0;str[i];i++)
        if(str[i] == '<')
            flag=1;
  return flag;

}


void set_in_file(char *str){
  char a[] = "<";
  char fromarg[50];
  char to[50];
  char toarg[50]="";

char *ptr;

ptr = strtok(str,a);
int counter=0;
while (ptr != NULL) {
if(counter == 0){
  strcpy(to,ptr);
}else if(counter == 1){
  strcpy(fromarg,ptr);
}
counter++;


  ptr = strtok(NULL,a);
}

ptr = strtok(to," ");
 counter=0;
while (ptr != NULL) {
if(counter == 0){
  strcpy(to,ptr);
}else if(counter == 1){
  strcpy(toarg,ptr);
}
counter++;


  ptr = strtok(NULL,a);
}

removeSpaces(fromarg);
removeSpaces(to);
removeSpaces(toarg);


strcpy(str,"cat ");
strcat(str,fromarg);
strcat(str,"|");
strcat(str,to);
strcat(str," ");
strcat(str, toarg);



}

void set_to_file(char *str){


char from[50];
char fromarg[50];
char filename[50];
char a[] = ">";
char b[] = " ";
char *ptr;
ptr = strtok(str,a);
int counter=0;
while (ptr != NULL) {
if(counter == 0){
  strcpy(from,ptr);
}else if(counter == 1){
  strcpy(filename,ptr);
}
counter++;


  ptr = strtok(NULL,a);
}

counter = 0;
ptr = strtok(from,b);
while (ptr != NULL) {
if(counter == 0){
  strcpy(from,ptr);
}else if(counter == 1){
  strcpy(fromarg,ptr);
}
counter++;



  ptr = strtok(NULL,a);

}


removeSpaces(from);
removeSpaces(fromarg);
removeSpaces(filename);
strcpy(str,from);
strcat(str," ");
strcat(str,fromarg);
strcat(str,"|tofile ");
strcat(str,filename);



}



void run_with_pipe(int argc,char *argv[]){



if(argc > 1){
  pid_t pid;
  int fd[2];

  pipe(fd);
  pid = fork();

  if(pid==0)
  {
  dup2(fd[WRITE_END], STDOUT_FILENO);
  close(fd[READ_END]);
  close(fd[WRITE_END]);
  if(argc == 2)
      execlp(argv[0], argv[0],  (char*) NULL);
  else if(argc == 3)
      execlp(argv[0], argv[0],argv[1],  (char*) NULL);
  else if(argc == 4)
          execlp(argv[0], argv[0],  (char*) NULL);
  else if(argc == 5)
        execlp(argv[0], argv[0],argv[1],  (char*) NULL);
  fprintf(stderr, "Failed to execute '%s'\n", argv[0]);
  exit(1);
  }
  else
  {
  pid=fork();

  if(pid==0)
  {
      dup2(fd[READ_END], STDIN_FILENO);
      close(fd[WRITE_END]);
      close(fd[READ_END]);
      if(argc == 2)
          execlp(argv[1], argv[1],  (char*) NULL);
      else if(argc == 3)
          execlp(argv[2], argv[2],  (char*) NULL);
      else if(argc == 4)
            execlp(argv[1], argv[1],argv[2],  (char*) NULL);
      else if(argc == 5)
          execlp(argv[2], argv[2],argv[3],  (char*) NULL);

      fprintf(stderr, "Failed to execute '%s'\n", argv[2]);
      exit(1);
  }
  else
  {
      int status;
      close(fd[READ_END]);
      close(fd[WRITE_END]);
      waitpid(pid, &status, 0);
  }
  }
          }
          else{
                  fprintf(stderr, "Usage Msg :. ./a.out ls wc data.txt \n");
                  exit(EXIT_FAILURE);
          }

}




void run(int argc,char *argv[]){

if(argc >= 1){

  int PID=fork();

  if(PID<0){
    printf("%s\n","Fork Error" );
  }else if(PID == 0){

    if(argc == 1)
        execlp(argv[0], argv[0], (char*) NULL);
    else if(argc == 2)
        execlp(argv[0], argv[0],argv[1],  (char*) NULL);
    exit(1);
  }
  else{
    int status;
    waitpid(PID,&status ,0);


  }

}
else{
  printf("Usage program or program [FILE]\n" );
}




}




void gtushell(char cwd[]) {

printf("\n     GTUSHELL     \n\n" );

int array_counter=0;
char array[100][100];


while(1){
  int back_flag=0;

printf(">" );



  char str[100];
  char from[50],fromarg[50];
  char to[50],toarg[50];
  scanf("%[^\n]%*c", str);
  char *back_com=strchr(str,'!');


  if(strcmp(str,"help")==0){
    help();
    continue;
  }

  if(strcmp(str,"exit") == 0){
    exit(1);
  }


  else if(back_com != NULL){
    memmove(back_com, back_com+1, strlen(back_com));
    int num = atoi(back_com);
    if((array_counter - num) >= 0 )
        strcpy(str,array[array_counter - num]);
    back_flag = 1;
  }

if(back_flag == 0){

  strcpy( array[array_counter],str );
  array_counter++;
}



if(tofile_control(str)){

  set_to_file(str);

}

if(infile_control(str)){
  set_in_file(str);
  
}


  if(!pipeControl(str)){
      char parse[] = " ";
      int counter=0;
      char *ptr = strtok(str, parse);
      while(ptr != NULL){
        if(counter==0){
          strcpy(from,ptr);
        }
        else if(counter == 1){
          strcpy(fromarg,ptr);
        }
        counter++;
        ptr = strtok(NULL, parse);
      }

      removeSpaces(from);
      removeSpaces(fromarg);

      if(counter > 1){
        char n_from[70];
        strcpy(n_from,cwd);
        strcat(n_from,"/");
        strcat(n_from,from);
        removeSpaces(from);
        removeSpaces(n_from);
        if(strcmp(from,"cd") == 0){

          cd(fromarg);

        }else{
        char *argv[]={n_from,fromarg};
        run(2, argv);
      }
      }else{
        char n_from[70];
        strcpy(n_from,cwd);
        strcat(n_from,"/");
        strcat(n_from,from);
        removeSpaces(n_from);
        char *argv[]={n_from};
        run(1,argv);
      }





  }else{



  char parse[] = "|";
  char parse2[] = " ";
  char *ptr = strtok(str, parse);
  char *ptr2,*ptr3;
  int ctr=0,argctr=0,toctr=0;

  while(ptr != NULL){

    if(ctr == 0){
        strcpy(from,ptr);
        //from[strlen(from)]='\0';

      }


    else if(ctr == 1){

          strcpy(to,ptr);
          //to[strlen(to)]='\0';

  }

          ctr++;

    ptr = strtok(NULL, parse);
  }


  free(ptr);

  ptr2 = strtok(from,parse2);
  while (ptr2 != NULL) {

      if(argctr == 0){
        strcpy(from,ptr2);
      //  from[strlen(from)]='\0';
      }
      else if(argctr == 1){
        strcpy(fromarg,ptr2);
      //  fromarg[strlen(fromarg)]='\0';
      }
      argctr++;

    ptr2 = strtok(NULL,parse2);
  }

ptr3 = strtok(to,parse2);
while (ptr3 != NULL) {

    if(toctr == 0){
      strcpy(to,ptr3);
    //  from[strlen(from)]='\0';
    }
    else if(toctr == 1){
      strcpy(toarg,ptr3);
    //  fromarg[strlen(fromarg)]='\0';
    }
    toctr++;

  ptr3 = strtok(NULL,parse2);
}


  free(ptr2);
  free(ptr3);
  removeSpaces(from);
  removeSpaces(fromarg);
  removeSpaces(to);
  removeSpaces(toarg);



if(argctr == 2 & toctr != 2){

char n_from[70];
strcpy(n_from,cwd);
strcat(n_from,"/");
char n_to[70];
strcpy(n_to,cwd);
strcat(n_to,"/");
strcat(n_from,from);
strcat(n_to, to);
removeSpaces(n_from);
removeSpaces(n_to);
  char *argv[] = {n_from,fromarg,n_to};
  run_with_pipe(3,argv);
}else if(toctr==2 & argctr != 2){
  char n_from[70];
  strcpy(n_from,cwd);
  strcat(n_from,"/");
  char n_to[70];
  strcpy(n_to,cwd);
  strcat(n_to,"/");
  strcat(n_from,from);
  strcat(n_to, to);
  removeSpaces(n_from);
  removeSpaces(n_to);

    char *argv[] = {n_from,n_to,toarg};
    run_with_pipe(4,argv);
}
else if(argctr == 2 & toctr == 2){
  char n_from[70];
  strcpy(n_from,cwd);
  strcat(n_from,"/");
  char n_to[70];
  strcpy(n_to,cwd);
  strcat(n_to,"/");
  strcat(n_from,from);
  strcat(n_to, to);
  removeSpaces(n_from);
  removeSpaces(n_to);

  char *argv[] = {n_from,fromarg,n_to,toarg};
  run_with_pipe(5,argv);
}

else{
  char n_from[70];
  strcpy(n_from,cwd);
  strcat(n_from,"/");
  char n_to[70];
  strcpy(n_to,cwd);
  strcat(n_to,"/");
  strcat(n_from,from);
  strcat(n_to, to);
  removeSpaces(n_from);
  removeSpaces(n_to);
  char *argv[]={n_from,n_to};
  run_with_pipe(2,argv);
}


}



}


}



void sighandler(int sig_num)
{
	// Reset handler to catch SIGTSTP next time
	signal(SIGTERM, sighandler);
	printf("SIGTERM Signal Detected\n");
  exit(EXIT_SUCCESS);
}

int main(int argc, char *argv[]) {
  signal(SIGTERM, sighandler);
  char cwd[500];
  getcwd(cwd, sizeof(cwd));
  removeSpaces(cwd);
  gtushell(cwd);

  return 0;
}
