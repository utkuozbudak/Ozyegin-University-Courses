#include <sys/syscall.h>
#include <stdio.h>
#include <unistd.h>

int main(){

	int gpid;

	gpid = getpid();
	printf("Process id by getpid: %d\n",gpid);

	int spid;
	spid = syscall(SYS_getpid);
	printf("Process id by SYS_getpid: %d\n",spid);


	return 0;
}

