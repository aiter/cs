all: lseek2

CC = gcc

INCLUDE = .

CFLAGS = -g -Wall -ansi

#CFLAGS = -O -Wall -ansi
MYLIB = mylib.a

lseek2: lseek2.o $(MYLIB)
	$(CC) -o $@ $@.o $(MYLIB)

$(MYLIB): $(MYLIB)(apue.o)
#$(MYLIB): $(MYLIB)(apue.o) $(MYLIB)(apue_sys.o)

lseek2.o: lseek2.c apue.h
apue.o: apue.c apue.h
#apue_sys.o: apue_sys.c apue.h

clean:
	-rm lseek2.o apue.o mylib.a

