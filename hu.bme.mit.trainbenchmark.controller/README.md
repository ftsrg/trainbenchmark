#Train Benchmark Controller

### Overview
Trainbenchmark-controller is responsible for providing a configuration for Train Benchmark and also support management for building the actual projects, generating the models and also running the benchmark tests. The scripts are written in Python programming language and every process is executed on the base of a `config.json` file. To alter the default configuration of Train Benchmark, just modify `config.json` file. You can find more information about the configuration parameters [below](#Configuration).

### Requirements
Apart from the fundamental requirements like git, maven etc. (described [here](https://github.com/FTSRG/trainbenchmark-core/blob/master/README.md)) that is also necessary to possess a Python 3 interpreter. In the case of using virtual environment, python3.4 interpreter is necessary. Find further information about virtual environments [here](#pyvenv). All modules were written and tested on Linux Ubuntu operating system, so there is no guarantee yet that the scripts can be perfectly used on Windows.
For executing scripts, there are no restrictions for the actual working directories, which means you can run the modules from any path.
If any trainbenchmark projects have dependencies to each other, then that must be written in the `config/dependencies.json` file.
A part of the file can be seen here:
  ```
  "sesame": {
    "name": "rdf",
    "url": "https://github.com/FTSRG/trainbenchmark-rdf.git",
    "branch": "master",
    "depth": "50",
    "folder": "trainbenchmark-rdf"
  }
  ```
The example above shows the required dependency of trainbenchmark-sesame. Url means the remote repository's availability, branch and depth are git based parameters and the folder is the name of the directory that will be created on our local space. The latter is important since the Controller will search the packages under the given directory.

### Installation guide
At first, clone the trainbenchmark-controller repository to your local folder. It is advisable to clone to the same directory where you store the other trainbenchmark projects, since the controller searches them under the same parent folder. In the other case, if the controller did not find any of the certain projects, it would download them automatically. To clone the trainbenchmark-controller, execute the following command:
* `git clone https://github.com/FTSRG/trainbenchmark-controller.git`

Then it is necessary to install the required external modules for Python. Two options exist. The first one is installing packages globally and the second one is installing everything into a virtual environment.

#### Install globally

In this scenario every necessary external package will be installed globally to the system (e.g. pip, third-party modules). In this case just execute the following script from the `trainbenchmark-controller/init/` directory:
* `./initialize.py`

Note that root password is required for the successful deployment. As a result, the required third-party python modules will be installed and the Controller can be used already which described in details [here](#Usage)

####<a name="pyvenv"></a>Install virtual environment (optional)

If you wouldn't like to deploy every external package globally on your system, then use a virtual environment. In this case run the following script from the `trainbenchmark-controller/init/` directory
* `./initialize_venv.py`

After this, the python binary and third-party modules will be copied to the 'trainbenchmark-controller/tb-env' directory. Since you are not using the python interpreter globally in this case but locally, you have to activate the virtual environment with the next command:
* `source tb-env/bin/activate`

After this operation you are able to use the Controller as described [here](#Usage).
To turn off the environment, execute the `deactivate` command. However, every time, when a new terminal window is opened, the virtual environment must be activated again. That means you have to write the same activation command to be able to use the environment.

### <a name="Configuration"></a>Configuration
Every configuration parameter which matters is stored in the `config/config.json` file. A typical structure of it can be seen here:
 
```
{
  "scenarios": [
    "User",
    "XForm"
  ],
  "MAVEN_OPTS": {
    "Xmx": "512m",
    "XX:MaxPermSize": "256m"
  },
  "JAVA_OPTS": {
    "xmx": "1g",
    "maxPermSize": "256m"
  },
  "minSize": 1,
  "maxSize": 4,
  "workspacePath": ".",
  "measurements": 5,
  "queries": [
    "PosLength",
    "RouteSensor",
    "SignalNeighbor",
    "SwitchSensor"
  ],
  "tools": [
    "sesame"
  ],
  "format": "rdf"
}
```
The parameters above can contain the following values:
 * scenarios: `Batch`, `User`, `XForm` or these combinations
 * the Xmx and maxPermSize attributes must match to this regular expression: `^[0-9]+[m,g]`. For example: 
  ```
  Correct values:
  128m
  1g
  1024m
  
  Invalid values:
  128M
  1G
  1024
  ```

 * minSize: cannot be less than 1
 * maxSize: must be higher than minSize  
   Additional condition with min- and maxSize: cannot be the range between them arbitrary: at least one power of 2 number must fit to the defined set. For example:

  ```
  Correct values:
  min:1 max:4
  min:2 max:2
  
  Invalid values:
  min:0 max:4
  min:9 max:15
  ```
 * measurements: must be higher than 0
 * workspacePath: shall be equal to a path of an existing directory. The "." value is also acceptable, which refers to the same level of hierarchy of directories where the trainbenchmark-controller is located. For example if this parameter was equal with `./example`, then an `example` folder should be exist next to the trainbenchmark-controller directory.
 * queries: must be equal at least one of these values:
  * `PosLength`
  * `RouteSensor`
  * `SignalNeighbor`
  * `SwitchSensor`
  * `SegmentLength`
  * `SwitchNodes`
  * `RouteEntry`
  * `RouteRouteDefinition`
  * `TrackElementSensor`  
   Note that every possible combination of the queries is permitted.
 * tools: the possible values of tools are depend on the given format. For instance:
  * format: `rdf`
    * tools:
      * `sesame`
      * `fourstore`  
       And these possible combinations.
  * format: `graph`
    * tools:
      * `neo4j`
  * format: `emf`
    * tools:
      * `drools5`
      * `drools610`
      * `java`
      * `emfincquery`
      * `eclipseocl`  
       And these possible combinations.
  * format: `sql`
    * tools:
      * `mysql`  

   Note that you cannot use more than one format at the same time, that is only possible with tools. But you cannot mix tools which connect to different formats. For example:

   ```
   //correct configuration
   "tools": [
     "sesame",
     "fourstore"
   ],
   "format": "rdf"  

   //invalid configuration
   "tools": [
     "sesame",
     "neo4j"
   ],
   "format": "rdf", "graph"  
   ```

### Modules
The most important python modules of trainbenchmark-controller are the followings:
 * **build.py**: The module is responsible for resolving the dependencies between repositories, furthermore build the projects with Maven. Every operation is executed after the `config/config.json` file. With the optional arguments, there is also an opportunity to generate the models and run the benchmark measurements too.
  Arguments:
   * `-g`, `--generate`: generate the models too
   * `-b`, `--benchmark`: run benchmark after build
   * `-t`, `--tools`: build the tools
   * `-f`, `--format`: build the format
   * `-c`, `--core`: build the core  
   Actually by executing `./build.py -tfc` script you receive the same effect like running `./build.py`, since it builds everything basically.
 * **generate.py**: Generates the certain models, based on the `config/config.json` file.
 * **benchmark.py**: This module is responsible for running the benchmark measurements.  

Important fact that all modules work with the certain tools and formats, which are given in `config/config.json`.

### <a name="Usage"></a>Usage
After the step of cloning the repository and install the required modules furthermore adjust the configuration file, you are able to build the projects by running the following script from the `trainbenchmark-controller/src/controller` directory, like this:
`./build.py`

This will clone the actual trainbenchmark repositories (if necessary) and run the maven build. But be careful, as some of the tools cannot be built without the generated models, as the JUNIT tests require for them (for example sesame). To avoid this conflict, it is worthwhile to execute the script at the first time like this:
 
```
./build.py --generate
#or the shorter version
./build.py -g
 ```
You are able to build only some parts of the Train Benchmark:

```
./build --tools

//OR
./build.py --tools --format
```
For more information read the module's help page: `./build.py -h`
There is another opportunity to generate models distinctly via the `generate.py` module. Execute it from the `trainbenchmark-controller/src/controller` folder:
```
./generate.py
```
The generated models will be created under the `trainbenchmark-models` directory next to the controller's folder.

Eventually you can run the benchmark:
```
./benchmark.py
```
 
In order to obtain exactly the same procedure by one script, run the `build.py` module with optional parameters:
```
./build.py --generate --benchmark
//OR
./build.py -g -b
//OR
./build.py -gb
```
