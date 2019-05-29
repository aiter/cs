How to Configure and Build a Linux Kernel
[links](https://www.cs.montana.edu/~chandrima.sarkar/AdvancedOS/CSCI560_Proj_main/index.html#H)

Step 1 : Get the necessary requirements for Building and Using the Kernel

Only three packages that are absoulutely necessary for successfully building a kernel:

Compiler : To build the kernel, the gcc C compiler must be used. Most Linux distributions have a package entitiled gcc that should be installed. If you wish to download the compiler and build it yourself, you can find it at http://gcc.gnu.org.As of the 2.6.18 kernel release, the 3.2 version of gcc is the oldest that can properly build a working kernel.  
$ gcc –version

Linker : The C compiler, gcc, does not do all of the compiling on its own. It needs an additional set of tools known as binutils to do the linking and assembling of source files. The binutils package also contains useful utilities that can manipulate object files in lots of useful ways, such as to view the contents of a library. As of the 2.6.18 kernel release, the 2.12 release of binutils is the oldest that can successfully link the kernel.
$ ld –v

Make:   make is a tool that walks the kernel source tree to determine which files need to be compiled, and then calls the compiler and other build tools to do the work in building the kernel. As of the 2.6.18 kernel release, the 3.79.1 release of make is the oldest that can properly build the kernel. It is recommended that you install the latest stable  version of make.
$ make –version

Other packages :
util-linux , Filesystem-Specific Tools  util packages – JFS , ReiserFS , XFS , Quota , NFS, Udev , process tools – procps package , PCMCIA tools

 

Step 2 : Retrieving the Kernel Source

Visit http://kernel.org/ and download the latest source code. It is also possible to download the kernel source from the command line, using the wget or curl utilities, both ofwhich should come with your Linux distribution.

$ wget http://www.kernel.org/pub/linux/kernel/v2.6/linux-2.6.17.8.tar.gz

or

$ curl http://www.kernel.org/pub/linux/kernel/v2.6/linux-2.6.17.8.tar.gz \ -o linux-2.6.17.8.tar.gz

 

Step 3 : Configuring and Building

The most basic method of configuring a kernel is to use the make config method :

$ cd linux-2.6.17.8

$ make config

default configuration Option :

$ make defconfig

Modifying the configuration :The menuconfig way of configuring a kernel is a console-based program that offers a way to move around the kernel configuration using the arrow keys on the
keyboard. There are three ways of doing so -

menuconfig - Text based color menus, radiolists & dialogs. This option also useful on remote server if you wanna compile kernel remotely.
xconfig - X windows (Qt) based configuration tool, works best under KDE desktop
gconfig - X windows (Gtk) based configuration tool, works best under Gnome
$ make menuconfig

 

Step 4: Compiling/Building The Kernel

$ make

Start compiling to kernel modules:

$ make modules

Install kernel modules (become a root user, use su command):

$ sudo make modules_install

After the modules have been successfully installed, the main kernel image must be
installed:

$ make install

It will install three files into /boot directory as well as modification to your kernel grub configuration file:

System.map-2.617.8
config-2.617.8
vmlinuz-2.617.8
Create an initrd image
Type the following command at a shell prompt:

$ cd /boot

$ mkinitrd -o initrd.image -2.617.8

Step 5: Installing and Booting from a Kernel

Modifying the Bootloader for the New kernel

There are two common Linux kernel bootloaders: GRUB and LILO.To determine which bootloader your system uses, look in the /boot/ directory. If there is a grub subdirectory:
$ ls -F /boot | grep grub

will give 
grub/

If this directory is not present, look for the presence of the /etc/lilo.conf file:
$ ls /etc/lilo.conf

should give

/etc/lilo.conf

If this is present, you are using the LILO program to boot with.

To let GRUB know that a new kernel is present, all you need to do is modify the /boot/grub/menu.lst file. For full details on the structure of this file, and all of the different options available, please see the GRUB info pages:

$ info grub

or

$ man lilo

Step 6 : Modify grub configuration files - /boot/grub/menu.lst

$ update -grub

The last step is to Reboot computer and boot into your new kernel

Enjoy !
