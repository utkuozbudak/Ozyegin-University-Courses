#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "person.h"

int main(){
	printf("Welcome to OzuDB!\n");
	struct person People[5];
	char ch = ' ';

	while(ch!='E')
	{
	printf("Enter your command:\n");
	scanf(" %c",&ch);

	if(ch=='C'||ch=='c')
	{
		printf("Creating new table...\n");
		for(int i=0;i<5;i++)
		{
			People[i].id=0;
			strcpy(People[i].name," ");
			strcpy(People[i].lastname," ");
		}
		printf("Done!\n");

	}
	if(ch=='P' || ch=='p')
	{
		for(int i=0;i<5;i++)
		{
			printf("|%d| |%s| |%s \n", People[i].id,People[i].name,People[i].lastname);
		}

	}
	if(ch=='R' || ch=='r')
	{
		printf("Reading data...\n");
		FILE* stream = fopen("People.csv","r");
		char s[150];
		char token[39];
		while(fgets(s,150,stream))
		{
			char tokenizer[2] =",";
			strcpy(token,strtok(s,tokenizer));
			int id = atoi(token);
			People[id].id=id;

			int x = 0;
			while(x<2)
			{
				strcpy(token,strtok(NULL,tokenizer));
				if(x==0) strcpy(People[id].name,token);
				else if(x==1) strcpy(People[id].lastname,token);
				x++;
			}
		}
		printf("Data is readed successfully.\n");
	}

	if(ch=='A' || ch=='a')
	{
		int nId=0;
		printf("Enter ID:\n");
		scanf("%d",&nId);
		People[nId].id = nId;
		printf("Name:");
		scanf(" %s",People[nId].name);
		printf("Lastname:");
		scanf(" %s",People[nId].lastname);
		printf("User added successfully.\n");
	}

	if(ch=='D' || ch=='d')
	{
		int temp=0;
		printf("Enter the id that you want to delete: ");
		scanf("%d",&temp);
		People[temp].id=0;
		strcpy(People[temp].name," ");
		strcpy(People[temp].lastname," ");
		printf("User is delete successfully.\n");
	}

}
	return 0;

}
