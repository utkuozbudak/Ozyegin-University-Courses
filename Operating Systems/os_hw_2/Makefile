# O. UTKU ÖZBUDAK, S012136 #
# command "make kitchen" builds #
# "./kitchen" runs the program(in my computer) #
# command "make clean" cleans #

CC=gcc
BINARY=create kitchen meal

LIB=-pthread
INCLUDE=

%: %.c
	$(CC) -o $@ $< $(LIB)

all: $(BINARY)

clean:
	rm -f *.o $(BINARY)
