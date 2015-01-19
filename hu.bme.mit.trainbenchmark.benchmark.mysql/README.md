# Train Benchmark MySQL implementation

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark-mysql.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark-mysql)

To run the MySQL implementation, the following steps are required:

1. Install MySQL Server, e.g.
   
	```bash
	sudo apt-get install -y mysql-server
	```

1. During the installation, set the MySQL username to `root` with an empty password.

1. Run the server and connect.

	```bash
	sudo service mysql start
	sudo -u root
	```

1. Create a database.

	```sql
	CREATE DATABASE trainbenchmark;
	```
