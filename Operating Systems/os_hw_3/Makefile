CC=gcc
BINARY = create server client

LIB=-pthread
INCLUDE=

%: %.c
	$(CC) -o $@ $< $(LIB)

all: $(BINARY)

clean:
	rm -f *.o $(BINARY)

# type the command "make client myserver"
