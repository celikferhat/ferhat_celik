#define _GNU_SOURCE
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <time.h>
#include <stdlib.h>
#include <fcntl.h>
#include <err.h>
#include <signal.h>
#include <sys/types.h>
#define PIPE_BUF 1048576



#define READ_END	0
#define WRITE_END	1
#define BUFFSIZE 20
#define err(mess) { fprintf(stderr,"Error: %s.", mess); exit(1); }
#define client_buffer_size 20
pid_t process_1,process_2,process_3,process_4;
int number_of_process=0;
int p1n=0,p2n=0,p3n=0,p4n=0;
FILE *logfile;
struct timeval  tv1, tv2;
void banka(int sec);

static void ALARMhandler(int sig)
{
    printf("Banka sonlandı! \n");
    unlink("151044014_main_fifo");
    char number[10];
    sprintf(number, "%d", p1n+p2n+p3n+p4n);
    number[ strlen(number) ] = '\0';
    if(logfile != NULL){
    fprintf(logfile, "\n%s müşteriye hizmet verdik.", number);
    fprintf(logfile, "\nprocess1 %d\nprocess2 %d\nprocess3 %d\nprocess4 %d müşteriye hizmet sundu.", p1n,p2n,p3n,p4n);
    fclose(logfile);
  }
    kill(process_1,SIGKILL);
    kill(process_2,SIGKILL);
    kill(process_3,SIGKILL);
    kill(process_4,SIGKILL);


    exit(EXIT_SUCCESS);
}
void server(int sec){

    signal(SIGALRM, ALARMhandler);
    alarm(sec);

  banka(sec);

    exit(EXIT_SUCCESS);


}




void delay(int number_of_seconds)
{
    int milli_seconds = 1000 * number_of_seconds;
    clock_t start_time = clock();
    while (clock() < start_time + milli_seconds)
    {

    }
}



void banka(int sec){





    /*creating pipes*/
    int pipe_1[2],pipe_2[2],pipe_3[2],pipe_4[2];
    int pipe_1_2[2],pipe_2_2[2],pipe_3_2[2],pipe_4_2[2];

    if (pipe(pipe_1)==-1)
    {
        fprintf(stderr, "Pipe Failed" );
        exit(EXIT_FAILURE);
    }
    if (pipe(pipe_2)==-1)
    {
        fprintf(stderr, "Pipe Failed" );
        exit(EXIT_FAILURE);
    }
    if (pipe(pipe_3)==-1)
    {
        fprintf(stderr, "Pipe Failed" );
        exit(EXIT_FAILURE);
    }
    if (pipe(pipe_4)==-1)
    {
        fprintf(stderr, "Pipe Failed" );
        exit(EXIT_FAILURE);
    }
    if (pipe(pipe_1_2)==-1)
    {
        fprintf(stderr, "Pipe Failed" );
        exit(EXIT_FAILURE);
    }
    if (pipe(pipe_2_2)==-1)
    {
        fprintf(stderr, "Pipe Failed" );
        exit(EXIT_FAILURE);
    }
    if (pipe(pipe_3_2)==-1)
    {
        fprintf(stderr, "Pipe Failed" );
        exit(EXIT_FAILURE);
    }
    if (pipe(pipe_4_2)==-1)
    {
        fprintf(stderr, "Pipe Failed" );
        exit(EXIT_FAILURE);
    }




    /**********************/






    process_1 = fork();
    if(process_1 == 0){
        /*process 1*/

        int nread;
        char buf[BUFFSIZE];


        close(pipe_1[WRITE_END]);
        close(pipe_1_2[READ_END]);

        while (1) {


            nread = (int)read(pipe_1[READ_END], buf, (size_t)BUFFSIZE);
            switch (nread) {
                case -1:

                    if (errno == EAGAIN) {
                        printf("(pipe empty)\n");
                        break;
                    }

                    else {
                        perror("read");
                        exit(4);
                    }
                case 0:
                    printf("[1]End of conversation\n");
                    close(pipe_1[0]);
                    exit(0);
                default:
                    printf("child 1 [%s]\n", buf);
                    int clientFD = open(buf,O_WRONLY);
                    if(clientFD == -1){
                        fprintf(stderr, "Open error: %s",buf );
                        continue;
                    }
                    srand(time(NULL) ^ atoi(buf));
                    int r = rand()%100;
                    char number[20];
                    sprintf(number, "%s|%d",buf, r);
                    delay(1500);
                    if(write(clientFD,number,client_buffer_size) != client_buffer_size)
                        fprintf(stderr, "error writing fifo: %s",buf );
                    if(close(clientFD) == -1)
                        fprintf(stderr, "close error");
                    write(pipe_1_2[WRITE_END],number,BUFFSIZE);

            }
        }
    }
    else if(process_1 > 0){

        process_2 = fork();

        if(process_2 == 0){
            /*process 2*/

            int nread;
            char buf[BUFFSIZE];

            close(pipe_2[WRITE_END]);
            close(pipe_2_2[READ_END]);

            while (1) {


                nread = (int)read(pipe_2[READ_END], buf, (size_t)BUFFSIZE);
                switch (nread) {
                    case -1:


                        if (errno == EAGAIN) {
                            printf("(pipe empty)\n");
                            break;
                        }

                        else {
                            perror("read");
                            exit(4);
                        }


                    case 0:
                        printf("[2]End of conversation\n");

                        // read link
                        close(pipe_2[0]);

                        exit(0);
                    default:


                        printf("child 2 [%s]\n", buf);
                        int clientFD = open(buf,O_WRONLY);
                        if(clientFD == -1){
                            fprintf(stderr, "Open error: %s",buf );
                            continue;
                        }
                        srand(time(NULL) ^ atoi(buf));
                        int r = rand()%100;
                        char number[20];
                        sprintf(number, "%s|%d",buf, r);
                        delay(1500);
                        if(write(clientFD,number,client_buffer_size) != client_buffer_size)
                            fprintf(stderr, "error writing fifo: %s",buf );
                        if(close(clientFD) == -1)
                            fprintf(stderr, "close error");
                        write(pipe_2_2[WRITE_END],number,BUFFSIZE);
                }
            }
        }
        else if(process_2 > 0){

            process_3 = fork();

            if(process_3 == 0){
                /*process 3*/

                int nread;
                char buf[BUFFSIZE];


                close(pipe_3[WRITE_END]);
                close(pipe_3_2[READ_END]);

                while (1) {


                    nread = (int)read(pipe_3[READ_END], buf, (size_t)BUFFSIZE);
                    switch (nread) {
                        case -1:


                            if (errno == EAGAIN) {
                                printf("(pipe empty)\n");
                                break;
                            }

                            else {
                                perror("read");
                                exit(4);
                            }


                        case 0:
                            printf("[3]End of conversation\n");

                            // read link
                            close(pipe_3[0]);

                            exit(0);
                        default:


                            printf("child 3 [%s]\n", buf);
                            int clientFD = open(buf,O_WRONLY);
                            if(clientFD == -1){
                                fprintf(stderr, "Open error: %s",buf );
                                continue;
                            }
                            srand(time(NULL) ^ atoi(buf));
                            int r = rand()%100;
                            char number[20];
                            sprintf(number, "%s|%d",buf, r);
                            delay(1500);
                            if(write(clientFD,number,client_buffer_size) != client_buffer_size)
                                fprintf(stderr, "error writing fifo: %s",buf );
                            if(close(clientFD) == -1)
                                fprintf(stderr, "close error");
                            write(pipe_3_2[WRITE_END],number,BUFFSIZE);
                    }
                }
            }
            else if(process_3 > 0){

                process_4 = fork();

                if(process_4 == 0){
                    /*process 4*/

                    int nread;
                    char buf[BUFFSIZE];


                    close(pipe_4[WRITE_END]);
                    close(pipe_4_2[READ_END]);

                    while (1) {


                        nread = (int)read(pipe_4[READ_END], buf, (size_t)BUFFSIZE);
                        switch (nread) {
                            case -1:



                                if (errno == EAGAIN) {
                                    printf("(pipe empty)\n");
                                    break;
                                }

                                else {
                                    perror("read");
                                    exit(4);
                                }


                            case 0:
                                printf("[4]End of conversation\n");

                                // read link
                                close(pipe_4[0]);

                                exit(0);
                            default:


                                printf("child 4 [%s]\n", buf);
                                int clientFD = open(buf,O_WRONLY);
                                if(clientFD == -1){
                                    fprintf(stderr, "Open error: %s",buf );
                                    continue;
                                }
                                srand(time(NULL) ^ atoi(buf));
                                int r = rand()%100;
                                char number[20];
                                sprintf(number, "%s|%d",buf, r);
                                delay(1500);
                                if(write(clientFD,number,client_buffer_size) != client_buffer_size)
                                    fprintf(stderr, "error writing fifo: %s",buf );
                                if(close(clientFD) == -1)
                                    fprintf(stderr, "close error");
                                write(pipe_4_2[WRITE_END],number,BUFFSIZE);

                        }
                    }

                }
                else if(process_4 > 0) {

                    /*parent process*/

                    close(pipe_1[READ_END]);
                    close(pipe_2[READ_END]);
                    close(pipe_3[READ_END]);
                    close(pipe_4[READ_END]);
                    close(pipe_1_2[WRITE_END]);
                    close(pipe_2_2[WRITE_END]);
                    close(pipe_3_2[WRITE_END]);
                    close(pipe_4_2[WRITE_END]);


                    int fd;
                    ssize_t n;
                    char buf[BUFFSIZE];

                    if ((fd = open("151044014_main_fifo", O_RDONLY | O_NONBLOCK)) < 0) err("open")
                    fcntl(fd,F_SETPIPE_SZ, PIPE_BUF );
                    int i = 0;

                    unlink("Logfile.txt");
                    logfile = fopen("Logfile.txt", "w");
                    char logtmp[250]="";

                    time_t t = time(NULL);
                    struct tm tm = *localtime(&t);


                    char str[80];
                    sprintf(str, "%d %d %d tarihinde işlem başladı.Bankamız %d saniye hizmet verecek.\n", tm.tm_year + 1900,tm.tm_mon+1,tm.tm_mday,sec);
                    strcat(logtmp,str);
                    strcat(logtmp,"clientpid    ");
                    strcat(logtmp,"processNO    ");
                    strcat(logtmp,"Para    ");
                    strcat(logtmp,"islem bitis zamani\n");
                    strcat(logtmp,"---------    ---------    ----    ----------\n");
                    fprintf(logfile, "%s", logtmp);


                    for(;;){



                  n = read(fd, buf, BUFFSIZE);
                  if(n == -1){

                    if (errno == EAGAIN) {
                        continue;
                      }

                  }

                  else if(n>0){

                        i = i % 4;

                        if (i == 0) {

                            write(pipe_1[WRITE_END], buf, BUFFSIZE);

                        } else if (i == 1) {
                            write(pipe_2[WRITE_END], buf, BUFFSIZE);

                        } else if (i == 2) {
                            write(pipe_3[WRITE_END], buf, BUFFSIZE);

                        } else if (i == 3) {
                            write(pipe_4[WRITE_END], buf, BUFFSIZE);

                        }

                        i++;


                    }


                    int k=0;char *ptr;char delim[] = "|";char pid[10];
                    char para[10];

                    char buff1[20];int flag=1;
                    if (fcntl(pipe_1_2[READ_END], F_SETFL, O_NONBLOCK) == -1) {
                      fprintf(stderr, "Call to fcntl failed.\n"); exit(1);
                    }
                    int r = read(pipe_1_2[READ_END], buff1, BUFFSIZE);
                    if(r == -1){
                    if (errno == EAGAIN) {
                        flag=0;
                      }
                    }
                      if(flag==1){
                    gettimeofday(&tv2, NULL);

                    k=0;
                    ptr = strtok(buff1, delim);
                        while(ptr != NULL){
                          if(k==0)
                              strcpy(pid,ptr);
                          else if(k==1)
                              strcpy(para,ptr);

                        k++;
                        ptr = strtok(NULL, delim);
                        }

                    fprintf(logfile, "%s        process1      %s      %f\n", pid,para,(double) (tv2.tv_usec - tv1.tv_usec) / 1000000 + (double) (tv2.tv_sec - tv1.tv_sec));
                    p1n++;

                    }




                    char buff2[20];int flag2=1;
                    fcntl(pipe_2_2[READ_END], F_SETFL, O_NONBLOCK);
                    int r2 = read(pipe_2_2[READ_END], buff2, BUFFSIZE);
                    if(r2 == -1){
                    if (errno == EAGAIN) {
                        flag2=0;
                      }
                    }
                      if(flag2==1){
                    gettimeofday(&tv2, NULL);
                    k=0;
                    ptr = strtok(buff2, delim);
                        while(ptr != NULL){
                          if(k==0)
                              strcpy(pid,ptr);
                          else if(k==1)
                              strcpy(para,ptr);
                        k++;
                        ptr = strtok(NULL, delim);
                        }

                    fprintf(logfile, "%s        process2      %s      %f\n", pid,para,(double) (tv2.tv_usec - tv1.tv_usec) / 1000000 + (double) (tv2.tv_sec - tv1.tv_sec));
                    p2n++;
                  }


                    char buff3[20];int flag3=1;
                    fcntl(pipe_3_2[READ_END], F_SETFL, O_NONBLOCK);
                    int r3=read(pipe_3_2[READ_END], buff3, BUFFSIZE);
                    if(r3 == -1){
                      if (errno == EAGAIN) {
                        flag3=0;
                      }
                    }
                      if(flag3==1){
                    gettimeofday(&tv2, NULL);
                    k=0;
                    ptr = strtok(buff3, delim);
                        while(ptr != NULL){
                          if(k==0)
                              strcpy(pid,ptr);
                          else if(k==1)
                              strcpy(para,ptr);
                        k++;
                        ptr = strtok(NULL, delim);
                        }

                    fprintf(logfile, "%s        process3      %s      %f\n", pid,para,(double) (tv2.tv_usec - tv1.tv_usec) / 1000000 + (double) (tv2.tv_sec - tv1.tv_sec));
                    p3n++;
                  }



                    char buff4[20];int flag4=1;
                    fcntl(pipe_4_2[READ_END], F_SETFL, O_NONBLOCK);
                    int r4 = read(pipe_4_2[READ_END], buff4, BUFFSIZE);
                    if(r4 == -1){
                    if (errno == EAGAIN) {
                        flag4=0;
                      }
                    }
                      if(flag4==1){
                     gettimeofday(&tv2, NULL);
                     k=0;
                     ptr = strtok(buff4, delim);
                         while(ptr != NULL){
                           if(k==0)
                               strcpy(pid,ptr);
                           else if(k==1)
                               strcpy(para,ptr);
                          k++;
                         ptr = strtok(NULL, delim);
                         }

                     fprintf(logfile, "%s        process4      %s      %f\n", pid,para,(double) (tv2.tv_usec - tv1.tv_usec) / 1000000 + (double) (tv2.tv_sec - tv1.tv_sec));
                    p4n++;
                  }




                }
                    close(fd);
                    fclose(logfile);





                    printf("parent\n");
                    exit(EXIT_SUCCESS);
                }



            }


        }


    }










}






int main(int argc,char *argv[]) {

    gettimeofday(&tv1, NULL);

    if (mkfifo("151044014_main_fifo",0666) == -1) {

        if (errno != EEXIST) {
            fprintf(stderr, "[%ld]:failed to create named pipe %s: %s\n", (long)getpid(), argv[1], strerror(errno));
            return 1;
        }
    }



    int sec=0;
    if(argc == 2){
      sec = atoi(argv[1]);}

    server(sec);
    //banka();




    return 0;
}
