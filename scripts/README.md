### Scripts

The framework provides a set of scripts for building the projects, generating the instance models and running the benchmark. The configuration is stored in the `config.json` file. To alter the default configuration, just edit this file. Find more information [here](https://github.com/FTSRG/trainbenchmark/wiki/Configuration).

### Requirements

* Git
* Maven 3
* Python 3 (tested with 3.4)

In the case of using virtual environment a Python 3.4 interpreter is necessary. For further information about virtual environments, read [here](#install-virtual-environment-optional).

### Installation guide

It is necessary to install the required external modules for Python. Two options exist. The first one is installing packages globally and the second one is installing everything into a virtual environment.

Install `pip3`:

```bash
sudo apt-get install python3-pip
```

#### Install globally

In this scenario every necessary external package will be installed globally to the system (e.g. pip, third-party modules). In this case just execute the following script from the `scripts/init/` directory:

```bash
sudo ./initialize.py
```

As a result, the required third-party python modules will be installed and the Controller can be used already.

#### Install virtual environment (optional)

If deploying every external package globally on the system wouldn't be satisfying or feasible, then use a virtual environment. In this case run the following script from the `scripts/init/` directory

```bash
./initialize_venv.py
```

After this the python binary and third-party modules will be copied to the `./tb-env` folder which is also created automatically. Since the Python interpreter is not used globally, but locally, it must be activated every time:

```bash
source tb-env/bin/activate
```

After this operation the Controller can be already used.
To turn off the environment, execute the `deactivate` command. However, every time when a new terminal window is opened, the virtual environment must be activated again. That means it is a necessity to write the same activation command again to be able to use the environment.

### Usage

The `scripts` directory contains the `run.py` script which is used for the following purposes:
* `run.py -b` -- builds the projects
* `run.py -b -s` -- builds the projects without testing
* `run.py -g` -- generates the instance models
* `run.py -m` -- runs the benchmark
* `run.py -h` -- displays the help
