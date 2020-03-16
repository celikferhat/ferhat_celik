#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

void cat(int rfd){
    char reading[5] = "";


    int wfd;

	wfd = fileno(stdout);
    if(rfd < 0){
        printf("File not found.\n");
        exit(EXIT_FAILURE);
    }


    int sz;
    while ((sz = (int)read(rfd, reading,1 )) > 0) {


        write(wfd, reading, (size_t)sz);

    }





}


int main(int argc, char *argv[]){
   int fd;
    if(argc == 2){
      fd = open(argv[1],O_RDONLY);
      cat(fd);
    }else{

    fd = fileno(stdin);
    cat(fd);
  }

    return 0;
}
