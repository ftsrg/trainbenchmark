# Train Benchmark MySQL implementation

Add the following to `/etc/apt/sources.list` (e.g. `sudo nano /etc/apt/sources.list`):

```
deb http://repo.mysql.com/apt/ubuntu/ trusty mysql-5.7
deb-src http://repo.mysql.com/apt/ubuntu/ trusty mysql-5.7
```

Install/update MySQL server.

```bash
sudo apt-get update
sudo apt-get install -y mysql-server
```
