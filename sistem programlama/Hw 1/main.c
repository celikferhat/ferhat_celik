#include<stdio.h>
#include<fcntl.h>
#include <unistd.h>
#include <dirent.h>
#include <sys/stat.h>
#include <string.h>

/* Size pathfun return file size which given path*/
int sizepathfun (char *path){


    int file=0;
    if((file=open(path,O_RDONLY)) < -1){ /* error check*/

        return 1;
    }
    struct stat fileStat;
    if(fstat(file,&fileStat) < 0){  /*From file to  point fileStat informations of file   */ /*Negative value means error*/


        return -1;
    }




return (int)fileStat.st_size; /*Return file size*/


}


/*DFS on directory*/
int depthFirstApply (char *path, int pathfun (char *path1) ,int *Asize){   /* Asize is a integer pointer and take root folder size*/


    DIR* dir;   /*create directory pointer*/
    struct dirent *dirEntry;  /*Directory entry for reading directory*/
    struct stat fileStat;     /*file stat if path is a file*/
    char _PathN[1000];          /* for copy path */

    int totalSize=0,justDirSize=0;



    dir = opendir(path);

    if (!(dir = opendir(path)))
        return -1;


    while ((dirEntry=readdir(dir)) != 0) { /*DFS*/

        int dirSize=0;



        if (strcmp(dirEntry->d_name, ".") == 0 || strcmp(dirEntry->d_name, "..") == 0)  /* Ignore Home directories*/
            continue;



        snprintf(_PathN, sizeof(_PathN), "%s/%s", path, dirEntry->d_name);
        lstat (_PathN, &fileStat);



        if (S_ISDIR(fileStat.st_mode)){


            dirSize += depthFirstApply(_PathN,sizepathfun,Asize); /*İf dir rec call*/
            totalSize += dirSize;
            
            printf("\nSize:%d   %s",dirSize,_PathN); /*Directories Size*/



        } else if(S_ISLNK(fileStat.st_mode)){
            printf("\nSpecial file [ %s ]",dirEntry->d_name);  /* Link files */
        }else{

            totalSize += pathfun(_PathN);
            justDirSize += pathfun(_PathN);

        }




    }
    closedir(dir);

        *Asize = justDirSize; /* -z argumant*/

        return totalSize; /* Total size */









}







int main(int argc, char *argv[]) {


    if(argc == 1){
        printf("\n incorrect function call \n"
               "Must be : buNeDu  path \n"
               "or"
               "\n buNeDu -z path \n");
    } else if(argc == 2){
int sizeA=0;
         int a =  depthFirstApply(argv[1],sizepathfun,&sizeA);

        printf("\nSize:%d %s\n",a,argv[1]);

    } else if(argc == 3){
        if (strcmp(argv[1],"-z") == 0){

            int sizeA=0;
            int a =  depthFirstApply(argv[2],sizepathfun,&sizeA);

            printf("\nSize:%d %s\n",sizeA,argv[2]);

        }

    }
    else{
        printf("\n incorrect function call \n"
               "Must be : buNeDu  path \n"
               "or"
               "\n buNeDu -z path \n");
    }



  // int a =  depthFirstApply("/home/ferhat/Masaüstü/A",sizepathfun);

   //printf("\n\n%d",a);

 // printf("\n%d",sizepathfun("/home/ferhat/Masaüstü/A/Hw1_v2.pdf"));


    return 0;
}