#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>

#define MAX_SERVER 3
#define MAX_CALLCENTER 2

pthread_mutex_t callcenter_mutex[MAX_CALLCENTER];
int server_locked = 0;
int callcenter_locked = 0;

void *connection_handler(void *);

int main(int argc, char *argv[])
{
	for(int i=0; i < MAX_CALLCENTER; ++i)
	{
		pthread_mutex_init(&callcenter_mutex[i] , NULL);
	}

	int socket_desc, client_sock, read_size, *new_sock;
	socklen_t c;
	struct sockaddr_in server, client;
	char client_message[2000];

	//create socket
	socket_desc = socket(AF_INET, SOCK_STREAM, 0);
	if(socket_desc == -1)
	{
		printf("Socket couldn't created\n");
	}

	printf("Server created\n");

	//prepare the sockaddr_in structure
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = htonl(INADDR_ANY);
	server.sin_port = htons( 8888 );

	//bind
	if( bind(socket_desc,(struct sockaddr *)&server, sizeof(server)) < 0)
	{
		//print the error message
		perror("bind failed");
		return 1;
	}

	//Listen
	if( listen(socket_desc , 3) < 0)
	{
		printf("listen error");
	}

	//Accept and incoming connection
	puts("Waiting for incoming connections...");
	c = sizeof(struct sockaddr_in);


	while(1)
 	{
		while(server_locked < MAX_SERVER)
		{

			//accept connection from an incoming client
			client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t *)&c);
			puts("A client accepted to SERVER");
			server_locked = server_locked + 1;
			printf("Current client size in SERVER: %d \n", server_locked);
			printf("Current client size in CALL CENTER: %d \n", callcenter_locked);
			pthread_t client_thread;
			new_sock = malloc(1);
			*new_sock = client_sock;

			while(callcenter_locked >= MAX_CALLCENTER);

			callcenter_locked = callcenter_locked + 1;

			if( pthread_create( &client_thread , NULL , connection_handler , (void*) new_sock) < 0)
			{
				perror("thread couldn't created");
				return 1;
			}
			printf("A client accepted to CALL CENTER \n");
			printf("Current client count in CALL CENTER: %d \n\n", callcenter_locked);


			if(client_sock < 0)
			{
				perror("accept failed");
				return 1;
			}
		}
	}

		return 0;
}

void *connection_handler(void *socket_desc)
{

	int sock = *(int*)socket_desc;
	int read_size, mutex1, mutex2;
	char *message, client_message[2000], *fail_message;
	int mutex = 0;

	for(int i=0; i < MAX_CALLCENTER; ++i)
	{
		if( pthread_mutex_trylock(&callcenter_mutex[i]) == 0)
		{
			mutex = i;
			message = "You are connected to server.";
			write( sock , message , strlen(message));
			break;
		}

	}


	message = " Welcome to the ECHO server";
	write(sock , message , strlen(message));

	while( (read_size = recv(sock , client_message , 2000 , 0)) > 0)
	{
		write(sock , client_message , strlen(client_message));
	}
	if(read_size == 0)
	{
		puts("failed");
	}
	else if(read_size == -1)
	{
		perror("recv failed");
	}

	free(socket_desc);
	pthread_mutex_unlock(&callcenter_mutex[mutex]);
	callcenter_locked = callcenter_locked - 1;
	server_locked = server_locked - 1;

	printf("A client disconnected. Current client count in SERVER: %d \n ", server_locked);
	printf("Current client count in CALL CENTER: %d \n", callcenter_locked);

	return 0;
}
