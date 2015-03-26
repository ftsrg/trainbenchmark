# Train Benchmark MemSQL implementation

## Requirements

 * 64-bit Linux-based operating system
 * MySQL client tool
 * g++ compiler
 
## Usage

To prevent conflicts with running MySQL services, change the default listening port of MemSQL. In this case modify the `memsql.conf` file. The recommended value is `3307`.
Another opportunity is that to adjust the port number when start the server:
 * `./memsqld -P 3307`
 
Connect to the MemSQL server by MySQL client tool:
 
* `mysql -u root -h 127.0.0.1 -P 3307 --prompt="memsql> "`

The username is `root` and the password is empty. Finally, create the `trainbenchmark` database:

* `memsql> CREATE DATABASE trainbenchmark;`


