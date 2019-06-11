### server
```c
    fd = socket(addr->sa_family, type, 0);

	bind(fd, addr, alen); //socklen_t alen

	listen(fd, qlen);
```

### client
```c
	fd = socket(domain, type, protocol);

	connect(fd, addr, alen);
```
