# Train Benchmark MemSQL implementation

## Requirements

 * 64-bit Linux-based operating system
 * MySQL client tool
 * g++ compiler
 
## Usage

To prevent conflicts with running MySQL services, change the default listening port of MemSQL. In this case modify the `memsql.conf` file. The recommended value is `3307`.
Another opportunity is that to adjust the port number when start the server:
 * `./memsqld -P 3307`
 
The Train Benchmark implementation also connects to port `3307`.

An initial step is to create a `trainbenchmark` database. At first, connect to the MemSQL server by MySQL client tool:
 
* `mysql -u root -h 127.0.0.1 -P 3307 --prompt="memsql> "`

The username is `root` and the password is empty. Finally, create the `trainbenchmark` database:

* `memsql> CREATE DATABASE trainbenchmark;`

After that, the implementation can be already used.

In any case to stop the server, use to following:
* `sudo killall memsqld`

