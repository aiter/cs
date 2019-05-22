#include<unistd.h>
#include<stdlib.h>
#include<curses.h>

int main() {
    initscr();

    move(5,15);
    printw("%s", "Hello world");
    refresh();
    sleep(2);

        int i;
        for(i = 0; i < 100; i++) {
                clear();
                move(5,15);
                printw("%d", i);
                refresh();
                usleep(100000);
        }

    clear();
    move(15,15);
    printw("%s", "Hello World");
    refresh(); 
    sleep(3);
    
    endwin();
    exit(EXIT_SUCCESS);
}
