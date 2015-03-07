Train Benchmark reporting tools
===============================

## Dependencies

* Install R: the `r-base`, `r-base-dev` packages are required. The code is tested with R 3.0 (you can check your version with the `R --version` command).

    ```bash
    sudo apt-get install -y r-base r-base-dev
    ```

* Install the required R packages by issuing the following command:

    ```bash
    cd hu.bme.mit.trainbenchmark.reporting/
    sudo R -f install.R
    ```
* As a consequence, the `ggplot2`, `jsonlite`, `plyr` R packages will be deployed.

## Usage
    
* At first, convert the benchmark results by executing the following script from the `/hu.bme.mit.trainbenchmark.controller/src/controller` library:
    
     `./convert_results.py`

* Then adjust the configuration parameters in the `/hu.bme.mit.trainbenchmark.reporting/src/config.json` file.
* Finally execute the following command from the same directory:

     `Rscript generate_diagrams.R` 
    
### Configuration

* An example of the configuration file can be seen below:

     ```
{
  "Dimensions": {
    "X_Dimensions": {
      "Size": true,
      "Iteration": false
    },
    "Groups": {
      "Case": true,
      "Tool": false
    }
  },
  "Summarize_Functions": [
    {
      "Phases": [
        "Read",
        "Check",
        "ReCheck"
      ]
    },
    {
      "Phases": [
        "LHS",
        "RHS"
      ]
    }
  ],
  "Extension": "png"
}
     ```
* The first function: `Read+Check+ReCheck`
* The second one: `LHS+RHS`
 
* The dimension and group parameters can be changed independently from each other, which means for example every variable can be `true` at the same time. As a consequent, more diagrams will be generated.


## Interactive reporting
* Apart from the required packages above, another necessity is the Shiny R package. To install it, use the following commands: 
    
     ```
     cd hu.bme.mit.trainbenchmark.reporting/src/shiny/
     sudo R -f install.R
     ```
* Important prerequisite to convert the result files to an acceptable CSV format. In this case, use the converter Python script again:
 
    `./hu.bme.mit.trainbenchmark.controller/src/controller/convert_results.py`

* Finally, the application can be started: 
    
      `R -f run.R`
     
     
