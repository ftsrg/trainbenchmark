### Running the benchmark

To run the benchmark, set the appropriate formats and tools in the `trainbenchmark-scripts/src` directory's `GeneratorScript.groovy` and `BenchmarkScript.groovy` scripts, respectively. Then run the following command:

```bash
./gradlew generate benchmark
```

:warning: Note that if you "cancel" the benchmark with the <kbd>Ctrl</kbd> + <kbd>C</kbd> keys, it will only stop the Gradle process running the benchmark. The JVM under benchmark will continue to run until completion (and will not timeout, as the timeout is governed by the benchmark script). Hence, in these cases, you should check for running `java` processes manually.
