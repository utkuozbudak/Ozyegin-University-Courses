all: prog01 prog02

prog01: hello.c
	gcc -o prog01 hello.c

prog02: driver.c
	gcc -o prog02 driver.c
