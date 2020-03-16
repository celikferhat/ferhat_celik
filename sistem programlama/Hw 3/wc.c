#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
int wc(int fd) {

char line[5]="";
int counter=0;
while (read(fd, line, 1) > 0) {
  if(line[0] == '\n')
      counter++;
}
close(fd);
return counter;
}

int main(int argc, char *argv[]) {
  if(argc == 2){
    int fd = open(argv[1],O_RDONLY);
    printf("%d\n",wc(fd) );
  }else{
  int fd;
  fd = fileno(stdin);
  printf("%d\n",wc(fd) );
}
  return 0;
}
