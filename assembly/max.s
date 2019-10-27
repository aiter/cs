 .section .data
data_items:
 .long 3,67,34,222,45,75,54,34,44,33,22,11,66,0

 .section .text
 .globl _start
_start:
 movl $0, %edi  #move 0 into the index register(edi)
 movl data_items(,%edi,4), %eax  #load the first byte of data to eax
 movl %eax, %ebx  #since this is the first itm, %eax is the biggest to ebx

start_loop:
 cmpl $0, %eax #eax==0,then go to loop_exit
 je loop_exit
 incl %edi    #index++
 movl data_items(,%edi,4), %eax #load next data to eax
 cmpl %ebx, %eax  #compare 
 jle start_loop

 movl %eax, %ebx
 jmp start_loop

loop_exit:
 #movl $4, %edx
 movl %ebx, %ecx
 movl $1, %ebx
 movl $4, %eax
 int $0x80

 movl $1, %eax
 int $0x80
