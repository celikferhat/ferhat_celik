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






void lsf(char *currentpath){

    DIR* dir;
    struct dirent *dirEntry;
    struct stat fileStat;
    char files[500];

    if ((dir = opendir(currentpath)) == NULL){
        printf("Directory can not find.Failed");
        closedir(dir);

        exit(EXIT_FAILURE);
    }

    printf("%-30s %8s %8s %8s\n", "Name", "Type", "Size","Acces Rights");
    while ((dirEntry=readdir(dir)) != 0) {


        snprintf(files, sizeof(files), "%s/%s", currentpath, dirEntry->d_name);
        lstat (files, &fileStat);


        if(S_ISREG(fileStat.st_mode)){

        printf("%-30s %8s %8d ",dirEntry->d_name,"R",(int)fileStat.st_size);

            printf( (S_ISDIR(fileStat.st_mode)) ? "d" : "-");
            printf( (fileStat.st_mode & S_IRUSR) ? "r" : "-");
            printf( (fileStat.st_mode & S_IWUSR) ? "w" : "-");
            printf( (fileStat.st_mode & S_IXUSR) ? "x" : "-");
            printf( (fileStat.st_mode & S_IRGRP) ? "r" : "-");
            printf( (fileStat.st_mode & S_IWGRP) ? "w" : "-");
            printf( (fileStat.st_mode & S_IXGRP) ? "x" : "-");
            printf( (fileStat.st_mode & S_IROTH) ? "r" : "-");
            printf( (fileStat.st_mode & S_IWOTH) ? "w" : "-");
            printf( (fileStat.st_mode & S_IXOTH) ? "x" : "-");

        printf("\n");

        } else if(S_ISLNK(fileStat.st_mode)){

            printf("%-30s %8s %8d\n",dirEntry->d_name,"S",0);


        }



    }



closedir(dir);

}







int main() {

    char cwd[500];
    getcwd(cwd, sizeof(cwd));
    lsf(cwd);
    return 0;
}