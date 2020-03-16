#include <stdio.h>
#include <unistd.h>


void pwd(){
    char cwd[255];
    getcwd(cwd, sizeof(cwd));
    printf("%s\n",cwd);
}


int main(){

    pwd();
    return 0;
}
