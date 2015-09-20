# Train Benchmark MySQL implementation

Install MySQL server:
```
sudo apt-get install mysql-server
```

Set the root password to empty.

Edit the `/etc/mysql/my.cnf`:

```
[mysqld]
max_heap_table_size=1G
```
