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
    
* Execute the following command from the `/hu.bme.mit.trainbenchmark.reporting/src` directory:

     `Rscript generate_diagrams.R` 
    
    Or:
    
     `Rscript generate_series.R`
     
## Interactive reporting
* Apart from the required packages above, another necessity the Shiny R package. To install it, use the following commands: 
    
     ```
     cd hu.bme.mit.trainbenchmark.reporting/src/shiny/
     sudo R -f install.R
     ```
* Important prerequisite to convert the result files to an acceptable CSV format. In this case, use the converter Python script:
 
    `./hu.bme.mit.trainbenchmark.controller/src/controller/convert_results.py`

* Finally, the application can be started: 
    
      `R -f run.R`
     
     
