# Train Benchmark analysis and visualisation

## Dependencies

* Install R: the `r-base`, `r-base-dev` packages are required. The code is tested with R 3.0 (you can check your version with the `R --version` command).

    ```bash
    sudo apt-get install -y r-base r-base-dev
    ```

* Grant access rights to the current user to the R `site-library` directory. If you wish to change the `site-library`, check the R documentation or this guide: <http://www.r-bloggers.com/installing-r-packages/>.
 
    ```bash
    sudo chown -R $USER:$USER /usr/local/lib/R/site-library/
    ```

* Install the required R packages by issuing the following command:

    ```bash
    cd hu.bme.mit.trainbenchmark.analyze/
    R -f install.R
    ```

* If you wish to edit the R files, it is strongly recommended to install [RStudio](http://www.rstudio.com/ide/download/desktop).

**Note:** The visualisation scripts will not work in Cygwin. If you wish to use them in Windows, it is recommended to use RStudio.

## Steps to generate diagrams automatically

* Go to the root directory of this repository.
* Merge the measured data into `results.txt`. The file contains one header line and multiple data records belonging to (optionally) multiple series.
* Execute the `scripts/visualize.sh` script.
* Inspect the generated `plotLines.html` and `plotLines-user.html` reports.
* If the you want the report to be zipped in a single file, execute `packHTML.sh` that generates `tbReport.zip`.
