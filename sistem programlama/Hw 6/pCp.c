#include <stdio.h>
#include <string.h>
#include <libgen.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <dirent.h>
#include <errno.h>
#include <ctype.h>
#include <sys/file.h>
#include <semaphore.h>
#include <sys/time.h>
#include <time.h>

typedef struct thread_parameter{
    char source[250];
    char dest[250];
}thread_parameter;


pthread_t *thread_id;
int thread_size=0;





sem_t empty,full,m,d;
pthread_mutex_t lock;

int done_flag=0;

int fnum=0,tnum=0;

int number_of_copied_file=0;
int total_byte=0;
struct timeval  tv1, tv2;
int regular=0,special=0;

int *source_buffer;
int *dest_buffer;
char **filenames;
int array_size=0;
char source_control[300];
int buf_size;

void *consumer(void *args);


void sighandler(int sig_num)
{
	signal(SIGINT, sighandler);
  pthread_mutex_lock(&lock);
	printf("All threads cancelled \nAll resource freed\n");
  pthread_mutex_unlock(&lock);
  for(int i=0;thread_size>i;i++){
      sem_post(&full);
  }
  //free( threads.thread_id);
  free(source_buffer);
  free(dest_buffer);

  for(int i=0;buf_size>i;i++)
        free(filenames[i]);

  free(filenames);

  exit(EXIT_SUCCESS);
}


void *producer(void *parameter){

    thread_parameter *p1 = parameter;



    DIR* dir;
    struct dirent *dirEntry;
    struct stat fileStat;
    struct stat st = {0};
    char file_path[250];

    mkdir(p1->dest, 0700);

    if ((dir = opendir(p1->source)) == NULL){
        pthread_mutex_lock(&lock);
        printf("Source directory can not find.Failed");
        pthread_mutex_unlock(&lock);
        closedir(dir);

        exit(EXIT_FAILURE);
    }
    while ((dirEntry=readdir(dir)) != 0) {
        if (strcmp(dirEntry->d_name, ".") == 0 || strcmp(dirEntry->d_name, "..") == 0)
            continue;

        snprintf(file_path, sizeof(file_path), "%s/%s", (char *)(parameter), dirEntry->d_name);
        lstat (file_path, &fileStat);
        if (S_ISDIR(fileStat.st_mode)){


            thread_parameter p2;

            strcpy(p2.source,file_path);
            strcpy(p2.dest,p1->dest);
            strcat(p2.dest,"/");
            strcat(p2.dest,dirEntry->d_name);

            producer(&p2);

        }else if(S_ISLNK(fileStat.st_mode)){


            continue;


        }
         else{




            int infile;
            int outfile;
            char outfilename[PATH_MAX];

          if(S_ISFIFO(fileStat.st_mode)){
            special++;
            infile = -1;
            sprintf(outfilename, "%s/%s", p1->dest, dirEntry->d_name);
            mkfifo(outfilename,0666);
            outfile = -1;
          //  strcpy(filenames[array_size],dirEntry->d_name);
          //  continue;

          }
            else{
              regular++;
            infile = open(file_path, O_RDONLY);
            if(infile == -1){
              pthread_mutex_lock(&lock);
              printf("Producer open file error for read mode\n" );
              pthread_mutex_unlock(&lock);
              close(infile);
                exit(EXIT_FAILURE);
            }
            sprintf(outfilename, "%s/%s", p1->dest, dirEntry->d_name);
            if (stat(p1->dest, &st) == -1) {
                mkdir(p1->dest, 0700);
            }

            outfile = open(outfilename, O_WRONLY | O_TRUNC | O_CREAT,0666);
            if(outfile == -1){
              pthread_mutex_lock(&lock);
              printf("Producer open file error for write mode\n" );
              pthread_mutex_unlock(&lock);
              close(outfile);
            }
          }


                sem_wait(&empty);
                sem_wait(&m);
                source_buffer[array_size]=infile;
                dest_buffer[array_size]=outfile;
                strcpy(filenames[array_size],dirEntry->d_name);
                array_size++;

                sem_post(&m);
                sem_post(&full);
                fnum++;









        }


    }
    if( strcmp(p1->source,source_control) == 0 ){
        done_flag=1;


            sem_wait(&d);



        for(int h=0 ; thread_size > h ; h++){
          sem_post(&full);
        }


    }



    while ((closedir(dir) == -1) && (errno == EINTR));


    return NULL;
}



void *consumer(void *args)
{




        sem_wait(&full);
        sem_wait(&m);






    char ch[5]="";
    int ret;
    if(array_size > 0){


        int fdr = source_buffer[array_size-1];
        int fdw = dest_buffer[array_size-1];
        source_buffer[array_size-1]=0;
        pthread_mutex_lock(&lock);
        printf("%s copied.\n",filenames[array_size-1] );
        pthread_mutex_unlock(&lock);
        array_size--;
        sem_post(&m);
        sem_post(&empty);

        if(fdr != 0 && fdr != -1){
        while((ret=read(fdr, ch, 5)) != 0)
            total_byte += write(fdw,ch,5);
        }
        number_of_copied_file++;
        tnum++;

        close(fdr);
        close(fdw);
        if(fnum == tnum  && done_flag == 1){
            sem_post(&d);

        }
    }
    else {
sem_post(&m);
    }




    return NULL;
}







int main(int argc,char *argv[]){
    signal(SIGINT, sighandler);


    if(argc != 5){
        printf("Usage : [ pcp 10 5 souce_path dest_path ]\n" );
        exit(EXIT_FAILURE);
    }



    thread_size=atoi(argv[1]);

  thread_id = calloc(thread_size, sizeof(thread_id));

  buf_size = atoi(argv[2]);

    source_buffer = calloc(buf_size,sizeof(int));
    dest_buffer = calloc(buf_size,sizeof(int));
    filenames = calloc(buf_size,sizeof(char*));
    for (int i = 0; i < buf_size; i++)
        filenames[i] = malloc((250+1) * sizeof(char));


    for(int i=0;buf_size>i;i++){
        source_buffer[i]=-1;
    }



    sem_init(&empty,0,buf_size);
    sem_init(&full,0,0);
    sem_init(&m,0,1);
    sem_init(&d,0,0);


    thread_parameter tp;
    strcpy(tp.source,argv[3]);
    strcpy(tp.dest,argv[4]);

    strcpy(source_control,argv[3]);

    pthread_t producer_thread;
    gettimeofday(&tv1, NULL);
    pthread_create(&producer_thread, NULL, producer, &tp);




while (source_buffer[0] != 0) {



    for (int i = 0; thread_size > i; i++) {
       pthread_create(&thread_id[i], NULL, consumer, NULL);

    }




    for (int i = 0; thread_size > i; i++) {
        pthread_join(thread_id[i], NULL);

    }

    for (int i = 0; thread_size > i; i++) {
        thread_id[i]=0;

    }


}

    pthread_join(producer_thread, NULL);



gettimeofday(&tv2, NULL);
pthread_mutex_lock(&lock);
printf("/////////////////////////\n" );
printf("Number of copied file %d\n",number_of_copied_file );
printf("Time   %f\n",(double) (tv2.tv_usec - tv1.tv_usec) / 1000000 + (double) (tv2.tv_sec - tv1.tv_sec));
printf("Total Bytes: %d\n",total_byte );
printf("%d Regular file\n",regular );
printf("%d Special file\n",special );
pthread_mutex_unlock(&lock);




    sem_destroy(&empty);
    sem_destroy(&full);
    sem_destroy(&m);
    sem_destroy(&d);

free(thread_id);
free(source_buffer);
free(dest_buffer);

for(int i=0;buf_size>i;i++)
      free(filenames[i]);

free(filenames);
    return 0;
}
