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

Run these commands:

```
$ sudo service mysql start
$ sudo mysql -u root
```

Issue the following instructions in the MySQL console:

```sql
DROP USER 'root'@'localhost';
CREATE USER 'root'@'%' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;
```

## Ubuntu 18.04

Getting permissions right on Ubuntu 18.04 can be tricky -- by default, `mysqldump` is only able to write in the `/tmp` directory. A possible workaround is to use that directory for benchmarking but you might want to fully use the `models/` directory in this repository. In this case, you'll need to tweak a few settings. Tips:

* If you need quick feedback during this process, create a directory such as `~/mytmp/subdir` and use the following dump command:

    ```
    $ mysqldump -u root --skip-dump-date --skip-comments --default-character-set=utf8 --fields-terminated-by=, --tab=~/mytmp/subdir trainbenchmark
    ```

* You can check `dmesg` to see the reason of the permission error.

There are two major error messages:

1. **Error message:**

    ```console
    The MySQL server is running with the --secure-file-priv option so it cannot execute this statement
    ```

    **Solution:**

    ```bash
    $ sudo vim /etc/mysql/mysql.conf.d/mysqld.cnf
    ```
    
    ```ini
    # add the following line to the end of the file
    secure_file_priv=""
    ```

    Restart MySQL:

    ```bash
    $ sudo service mysql restart
    ```

1. **Error message:**

    ```
    Can't create/write to file '.../models/railway-batch-1-csv/Region.txt' (Errcode: 13 - Permission denied) when executing 'SELECT INTO OUTFILE'
    ```

    **Solution:**

    As suggested [on Stack Overflow](https://stackoverflow.com/questions/2783313/how-can-i-get-around-mysql-errcode-13-with-select-into-outfile), you'll need to configure AppArmor:

    ```
    $ sudo vim /etc/apparmor.d/usr.sbin.mysqld
    ```

    Add to the end of the file:

    ```console
      ...
      /path/to/trainbenchmark/models r,
      /path/to/trainbenchmark/models/** rw,
    }
    ```

    Restart AppArmor:

    ```bash
    $ sudo /etc/init.d/apparmor reload
    ```

`mysqldump` should work now.
