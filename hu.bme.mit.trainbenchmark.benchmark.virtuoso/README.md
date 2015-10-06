# Train Benchmark Virtuoso implementation

## Install on Ubuntu

Install Virtuoso with the following command (on Ubuntu-based systems):

```bash
sudo apt-get install virtuoso-server
```

Subsequently, adjust both the username and password of root to `dba`.

## Install Virtuoso From Source

To install the newest version of Virtuoso, download it from [here](http://virtuoso.openlinksw.com/download/) with a corresponding licence as well. Decompress the tar file (regarding Linux), and place the licence file in the same directory where the `install.sh` script is located. There is a chance that the *libaio* library is needed, in this case, execute the following:
 * `sudo apt-get install libaio1 libaio-dev`

Finnaly, run the `install.sh` script, and use the default username and password, `dba`. For further information: read the [manual](http://docs.openlinksw.com/pdf/virtdocs.pdf).

Some additional package dependencies are listed [here](https://github.com/openlink/virtuoso-opensource).
