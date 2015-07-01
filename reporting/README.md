Train Benchmark reporting tools
===============================

## Dependencies

Clone the [MONDO-SAM](https://github.com/FTSRG/mondo-sam) repository next to the Trianbenchmark directory. Follow the [instructions](https://github.com/FTSRG/mondo-sam/blob/master/README.md) and install the required dependencies.

## Convert the Results

It is possible to convert the measurement results from JSON to CSV with the following script:
 ```bash
 cd reporting
 ./convert_results.sh
 ```

## Interactive Reporting

In order to use the interactive interface in MONDO-SAM, run the following:
 `./interactive.sh`
 
For further information, read the [instructions](https://github.com/FTSRG/mondo-sam/blob/master/README.md).

## Reporting 2

Adjust the `reporting/config.json` file and run the `reporting/report.py` script. Read about the possible configuration values in the [wiki page](https://github.com/FTSRG/mondo-sam/wiki/Reporting).
