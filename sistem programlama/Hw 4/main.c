
#define _GNU_SOURCE
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
#include <sys/file.h>





int sizepathfun (char *mypath){



    int file=0;
    if((file=open(mypath,O_RDONLY)) < -1){ /* error check*/
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

    return size;


}



void insertionSort(int arr[], int n)
{
    int i, key, j;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;

        /* Move elements of arr[0..i-1], that are
          greater than key, to one position ahead
          of their current position */
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}






int depthFirstApply (char *path, int pathfun (char *path1) ,int Asize){


    DIR* dir;
    struct dirent *dirEntry;
    struct stat fileStat;
    char dirPath[1000];
    int zpipe[2];
    int totalSize=0;
    char mypath[250];
    pid_t pid;


    if (pipe(zpipe)==-1)
    {
       fprintf(stderr, "Pipe Failed" );
       return 1;
     }



    strcpy(mypath,path);


    if ((dir = opendir(mypath)) == NULL){
        printf("Directory can not find.Failed");
        closedir(dir);

        return -1;
    }


    while ((dirEntry=readdir(dir)) != 0) {

        if (strcmp(dirEntry->d_name, ".") == 0 || strcmp(dirEntry->d_name, "..") == 0)  /* Ignore Home directories*/
            continue;

        snprintf(dirPath, sizeof(dirPath), "%s/%s", mypath, dirEntry->d_name);

        lstat (dirPath, &fileStat);


        if (S_ISDIR(fileStat.st_mode)){



         if(pipe(zpipe) == -1)
         {
             perror( "pipe Failed" );
             continue;
         }

         pid = fork();

         if(pid < 0)
         {
             perror("fork failed");
             exit(1);
         }

         if(pid == 0)
         {

           //child code
           int dirSize=0;
           closedir(dir);
           dirSize += depthFirstApply(dirPath,sizepathfun,Asize);


             char buff[100];

             close(zpipe[0]);
             sprintf(buff, "%d %d %s",(int)getpid(),dirSize,dirPath);

             write(zpipe[1], buff,strlen(buff)+1);
close(zpipe[1]);



             FILE* fd=fopen("151044014sizes","w");
             fprintf(fd, "%s\n", buff);
             fclose(fd);

              exit(0);
         }

         else{//parent

               char buff[100];
             close(zpipe[1]);
             if ( read( zpipe[0], buff, sizeof(buff)) <= 0) //read from pipe
             {
                 perror( "read failed" );
                 exit( EXIT_FAILURE );
             }
             close(zpipe[0]);



             //printf("%s\n", buff);


             if(Asize == 1){

             char parse[] = " ";
             char *ptr = strtok(buff, parse);
             int counter = 0;
             while (ptr != NULL) {
               if(counter==1){
                 totalSize += atoi(ptr);
               }



               counter++;
               ptr = strtok(NULL, parse);
             }

           }




         }

        } else if(S_ISLNK(fileStat.st_mode)){

           printf("%d %d Special File [%s]\n",(int)getpid(),-1,dirEntry->d_name);



        }
        else{


            totalSize += pathfun(dirPath);



        }




    }
    while ((closedir(dir) == -1) && (errno == EINTR));



    return totalSize;


}


int main(int argc, char *argv[]) {
  int Asize=0;
  char path[150];
  int size=0,flag=0;

  if(argc == 1){
          printf("\n incorrect function call \n"
                 "Must be : buNeDu  path \n"
                 "or"
                 "\n buNeDu -z path \n");
                 return -1;
      } else if(argc == 2){
        Asize=0;
        flag=1;
        strcpy(path,argv[1]);

    }else if(argc == 3){
        if (strcmp(argv[1],"-z") == 0) {
               Asize = 1;
               flag = 1;

           }else{
               printf("\n incorrect function call \n"
                      "Must be : buNeDu  path \n"
                      "or"
                      "\n buNeDu -z path \n");


                      return -1;
           }




    }else{
      printf("\n incorrect function call \n"
             "Must be : buNeDu  path \n"
             "or"
             "\n buNeDu -z path \n");

             return -1;

    }






if(flag == 1){
  unlink("151044014sizes");
    mkfifo("151044014sizes",0666);

              if(Asize == 0){
                  size = depthFirstApply(argv[1], sizepathfun, Asize);
              }else if(Asize == 1){
                  size = depthFirstApply(argv[2], sizepathfun, Asize);

              }


        FILE *fp=fopen("151044014sizes","r");
        char buff[500];

        char lines[100][250];


        char * line = NULL;
        size_t len = 0;
        int chcounter=0;
        while (getline(&line, &len, fp) != -1){
                strcpy(lines[chcounter],line);
                chcounter++;
              }
        fclose(fp);
        free(line);

        unlink("151044014sizes");
        int i,j;
        int arr[chcounter];

        char parse[] = " ";
              for(i=0;chcounter>i;i++){
                  arr[i]= strlen(lines[i]);
                  }

              insertionSort(arr,chcounter);

              for(i=chcounter;i>0;i--){

                    for(j=0;chcounter>j;j++){

                        if(arr[i] == strlen(lines[j]))
                            printf("%s",lines[j] );

                    }

              }


        printf("%d %d %s\n",getpid() , size ,path );
        printf("%d Child created by main process %d\n",chcounter,(int)getpid());


}














    return 0;
}
