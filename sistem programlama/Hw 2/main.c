/*
 
 -z parametresi için file pointer ve fopen kullandım yalnızca depthFirstApply fonksiyonu içerisinde.
 (Bonus puan için)Maindeki fonsiyonda pdf de yazan şekilde system call kullanarak txt dosyasından size ları çekip topladım.
 
  */




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

int sizepathfun (char *path){


    int file=0;
    if((file=open(path,O_RDONLY)) < -1){ /* error check*/
        close(file);
        return 1;
    }
    struct stat fileStat;
    if(fstat(file,&fileStat) < 0){  /*From file to  point fileStat informations of file   */ /*Negative value means error*/

        close(file);
        return -1;
    }


    int size = (int)fileStat.st_size;

    close(file);

    return size; /*Return file size*/


}


int depthFirstApply (char *path, int pathfun (char *path1) ,int Asize){   /* Asize is a integer pointer and take root folder size*/


    DIR* dir;   /*create directory pointer*/
    struct dirent *dirEntry;  /*Directory entry for reading directory*/
    struct stat fileStat;     /*file stat if path is a file*/
    char _PathN[1000];          /* for copy path */

    int totalSize=0,justDirSize=0;





    if ((dir = opendir(path)) == NULL){
         printf("Directory can not find.Failed");
        closedir(dir);
       
        return -1;
    }


    while ((dirEntry=readdir(dir)) != 0) { /*DFS*/





        if (strcmp(dirEntry->d_name, ".") == 0 || strcmp(dirEntry->d_name, "..") == 0)  /* Ignore Home directories*/
            continue;



        snprintf(_PathN, sizeof(_PathN), "%s/%s", path, dirEntry->d_name);
        lstat (_PathN, &fileStat);



        if (S_ISDIR(fileStat.st_mode)){

            int PID = fork();

            if(PID<0){
                closedir(dir);
                return -1;
            }


            if(PID == 0){
                int dirSize=0;
                closedir(dir);
                dirSize += depthFirstApply(_PathN,sizepathfun,Asize); /*İf dir rec call*/


                int file = open("151044014sizes.txt", O_RDWR | O_APPEND  |O_CREAT, 0666);
                flock(file, LOCK_EX);
                char buff[1000]="";
                char str[20];

				FILE* fp = fdopen(file, "r");
                if(Asize == 1){



                
                char * line = NULL;
                size_t len = 0;


                if (fp == NULL){
                	fclose(fp);

                    exit(EXIT_FAILURE);
                }
                while (getline(&line, &len, fp) != -1) {
                    char parse[] = "|";
                    char parse2[] = "-";

                    //printf("PATH: %s   ID %d\n",line,getpid());

                    char *ptr = strtok(line, parse);
                    char *ptr2;
                    int couter=0;
                    int values[2];
                    while(ptr != NULL)
                    {


                       if(couter == 1){

                           ptr2 = strtok(ptr,parse2);
                           int myctr = 0;

                            while (ptr2 != NULL){




                                char *str = ptr2;

                                while (*str) {

                                    if (isdigit(*str)) {
                                        long val = strtol(str, &str, 10);
                                       // printf("%ld mycounter %d\n", val,myctr);

                                        if(myctr == 0){
                                            values[0]=(int)val;
                                        } else if(myctr == 1){
                                            values[1]=(int)val;
                                        }



                                        myctr++;
                                    } else {
                                        str++;
                                    }

                                }





                                ptr2 = strtok(NULL,parse2);
                            }


                       }
                       couter++;


                        ptr = strtok(NULL, parse);
                    }


                   // printf("\n%d %d",values[0],values[1]);

                   if(values[0] == getpid())
                       dirSize += values[1];

                   	free(ptr);
                   	free(ptr2);

                }

                if (line)
                    free(line);

                

}





                strcpy(buff,"");
                sprintf(str, "%d", getpid());
                strcat(buff,str);

                sprintf(str, " | PPID %d", getppid());
                strcat(buff,str);


                strcat(buff," - Size: ");
                sprintf(str, "%d |  ", dirSize);
                strcat(buff,str);

                strcat(buff,_PathN);




                write(file,buff,strlen(buff));
                write(file,"\n",strlen("\n"));

               // fclose(fp);
                close(file);
                flock(file, LOCK_UN);
               // closedir(dir);
                fclose(fp);
                exit(0);
            } else{

                int status=0;
                wait(&status);
                


            }


        } else if(S_ISLNK(fileStat.st_mode)){

            char sfile[250]="";
            char str[10];
            sprintf(str, "%d |  ", getpid());
            strcat(sfile,str);
            strcat(sfile,"Special file [");
            strcat(sfile,dirEntry->d_name);
            strcat(sfile,"]\n");
            int file = open("151044014sizes.txt", O_RDWR | O_APPEND  |O_CREAT, 0666);
            write(file,sfile,strlen(sfile));
            close(file);


        }
        else{

            totalSize += pathfun(_PathN);



        }




    }
    while ((closedir(dir) == -1) && (errno == EINTR));



    return totalSize; /* Total size */


}


int main(int argc, char *argv[]) {

    int Asize=0;
    int flag=0;

    if(argc == 1){
        printf("\n incorrect function call \n"
               "Must be : buNeDu  path \n"
               "or"
               "\n buNeDu -z path \n");
    } else if(argc == 2){
        Asize=0;
        flag=1;

    }
    else if(argc == 3){
            if (strcmp(argv[1],"-z") == 0) {
                Asize = 1;
                flag = 1;

            }else{
                printf("\n incorrect function call \n"
                       "Must be : buNeDu  path \n"
                       "or"
                       "\n buNeDu -z path \n");
            }

        }
    else{
        printf("\n incorrect function call \n"
               "Must be : buNeDu  path \n"
               "or"
               "\n buNeDu -z path \n");
    }


        if(flag==1) {

            int ctr = 0;
            int PID = fork();

            if (PID < 0) {
                printf("\nFailed main fork\n");
                return -1;
            }
            if (PID == 0) {

                int a;

                if(Asize == 0){
                    a = depthFirstApply(argv[1], sizepathfun, Asize);
                }else if(Asize == 1){
                    a = depthFirstApply(argv[2], sizepathfun, Asize);

                }



                int z = 0;



                /* -z size*/

                char buffer[600] = "";
                char reading[5] = "";
                int processes[20];
                int i;
                for (i = 0; i < 20; i++) {
                    processes[i] = 0;
                }

                int file = open("151044014sizes.txt", O_RDONLY, 0666);
                while (read(file, reading, 1) >
                       0) { /* nbytes 1 olmak zorunda her satır aynı tipte veri içermiyor special filelar var*/

                    strcat(buffer, reading);

                    if (strcmp(reading, "\n") == 0) {
                        printf("\n%s", buffer);
                        char parse[] = " ";


                        char *ptr = strtok(buffer, parse);

                        int counter = 0;
                        while (ptr != NULL) {
                            if (counter == 0) {
                                int bool = 0;

                                for (i = 0; i < 20; i++) {
                                    if (processes[i] == atoi(ptr))
                                        bool = 1;
                                }

                                if (bool == 0) {
                                    processes[ctr] = atoi(ptr);
                                    ctr++;
                                }


                            }


                            if (counter == 6)
                                z += atoi(ptr);
                            counter++;
                            ptr = strtok(NULL, parse);
                        }


                        strcpy(buffer, "");
                    }


                }


                close(file);
                if (Asize == 1)
                    printf("\n%d | PPID: %d | Size: %d %s", getpid(),getppid(), a + z, argv[2]);
                else if (Asize == 0)
                    printf("\n%d | PPID: %d | Size: %d %s", getpid(),getppid(),a, argv[1]);


                printf("\n%d Child created", ctr+1);
            exit(0);
            }
            if (PID > 0) {
                int status = 0;
                wait(&status);

                printf("by main process %d\n",  getpid());


            }

        }
            
            
            
            
           










        return 0;





}
