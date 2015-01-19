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
    
    ## Usage
    
    Execute the following command from the `trainbenchmark-reporting/hu.bme.mit.trainbenchmark.reporting` directory:
     * `Rscript generate_diagrams.R`
