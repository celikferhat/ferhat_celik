#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <time.h>
#include <err.h>
#include <errno.h>

#define BUFFSIZE 512
#define err(mess) { fprintf(stderr,"Error: %s.", mess); exit(1); }


void sighandler(int sig_num)
{
	signal(SIGINT, sighandler);
	printf("Müşteri %d parasını alamadı :(\n",getpid());
  char str[12];
  sprintf(str, "%d", getpid());
  unlink(str);
  exit(EXIT_SUCCESS);
}

void customer_create(int customer_number){


int i;
for(i=0;customer_number>i;i++){

  if(fork()==0){
    signal(SIGINT, sighandler);
    int fd,clientFD;

    umask(0);
    char str[12];
    sprintf(str, "%d", getpid());
    mkfifo(str, 0666);

    if ( (fd = open("151044014_main_fifo", O_WRONLY | O_NONBLOCK )) < 0){
      unlink(str);
      printf("Müşteri %d parasını alamadı :(\n", getpid());
      exit(EXIT_FAILURE);
    }

    int ret_w;
    if( (ret_w=write(fd,str,20))<20){
      printf("Müşteri %d parasını alamadı :(\n",getpid());
      unlink(str);
      exit(EXIT_FAILURE);
    }
		
    close(fd);



    if ( (clientFD = open(str, O_RDONLY )) < 0)
        err("open")

   char buffer[512];

   if(read(clientFD,buffer,20) < 20){
     unlink(str);
     kill(0,SIGINT);
     exit(EXIT_FAILURE);
   }



	 char delim[] = "|";
	 char para[10];
	 int k=0;
	 char *ptr = strtok(buffer, delim);
	 		while(ptr != NULL){
	 			if(k==1)
	 					strcpy(para,ptr);

	 		k++;
	 		ptr = strtok(NULL, delim);
	 		}
   printf("Müşteri %d  %s lira aldı :)\n",getpid(),para );
   fflush(stdout);
   unlink(str);
    exit(0);
  }




}

for(i=0;customer_number>i;i++)
    wait(NULL);


}
void delay(int number_of_seconds)
{
    int milli_seconds = 1000 * number_of_seconds;
    clock_t start_time = clock();
    while (clock() < start_time + milli_seconds)
    {

    }
}


int main(int argc,char *argv[]){

customer_create(atoi(argv[1]));
int i;
for(i=0;atoi(argv[1])>i;i++)
	wait(NULL);

printf("\n\n");
   return 0;
}
