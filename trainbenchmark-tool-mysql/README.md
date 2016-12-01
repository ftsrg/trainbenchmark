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

## Ubuntu 16.04

On Ubuntu 16.04, you can install MySQL without adding third-party repostories. However, in some installations the login does not work: even if you leave the `root` user's password empty during the install, you will *not* be able to login with the `root` user. To fix this, follow [this guide](http://askubuntu.com/a/784347/415610):

```
$ sudo service mysql start
$ sudo mysql -u root
mysql> DROP USER 'root'@'localhost';
mysql> CREATE USER 'root'@'%' IDENTIFIED BY '';
mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
mysql> FLUSH PRIVILEGES;
```
