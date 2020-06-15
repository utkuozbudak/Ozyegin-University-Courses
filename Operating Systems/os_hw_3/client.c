#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <time.h>

int main(int argc , char *argv[])
{
	int sock;
	struct sockaddr_in server;
	char message[1000] , server_reply[1000], echo_message[1000];

	//Create socket
	sock = socket(AF_INET , SOCK_STREAM , 0);
	if(sock == -1)
	{
		printf("Could not create socket");
	}

	server.sin_addr.s_addr = inet_addr("127.0.0.1");
	server.sin_family = AF_INET;
	server.sin_port = htons( 8888 );

	//Connect to remote server
	if(connect(sock, (struct sockaddr *)&server, sizeof(server)) < 0)
	{
		perror("connect failed");
		return 1;
	}


	printf("Type something in order to try to connect to the server: ");
	scanf("%s", message);

	if(send(sock, message, strlen(message), 0) < 0)
	{
		puts("Send failed");
		return 1;
	}
	//Recieve a reply from server
	if( recv(sock, server_reply, 1000, 0) < 0)
	{
		puts("recv failed");
	}
	puts("Server response: ");
	puts(server_reply);
	bzero(message, 1000);
	bzero(server_reply, 1000);

	printf("Enter a message for echo: ");
	scanf("%s", message);

	if(send(sock, echo_message , strlen(echo_message), 0) < 0)
	{
		puts("send failed");
		return 1;
	}
	if( recv(sock, server_reply, 1000, 0) < 0)
	{
		puts("recv failed");
	}
	puts("Server response: ");
	puts(server_reply);
	bzero(echo_message, 1000);
	bzero(server_reply, 1000);

	sleep(10);
	close(sock);
	printf("Disconnected \n");
	return 0;

}
