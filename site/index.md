---
layout: default
---

* [Publications](https://github.com/FTSRG/publication-pages/wiki#train-benchmark)
* [GitHub repository](https://github.com/FTSRG/trainbenchmark)
* [SOSYM paper](http://link.springer.com/article/10.1007/s10270-016-0571-8): The Train Benchmark: cross-technology performance evaluation of continuous model queries

### Generated models

{% for file in site.static_files %}
  {% if file.extname == ".ttl" %}
     * [{{ file.path }}]({{ site.baseurl }}{{ file.path }})
  {% endif %}
{% endfor %}

### Supplementary material for the submitted SOSYM paper

#### Latest benchmark results

* [Benchmark results for the individual queries](results/results-individual-runtime.csv)
* [Benchmark results for the query mixes (memory)](results/results-mix-memory.csv)
* [Benchmark results for the query mixes (runtime)](results/results-mix-runtime.csv)

#### Plots

* Batch scenario
  * [Read phase](diagrams/runtime-Batch-Read.pdf)
  * [Check phase](diagrams/runtime-Batch-Check.pdf)
  * [Read and Check phase](diagrams/runtime-Batch-Read-and-Check.pdf)
* Inject scenario
  * [Read phase](diagrams/runtime-Inject-Read.pdf)
  * [Check phase](diagrams/runtime-Inject-Check.pdf)
  * [Read and Check phase](diagrams/runtime-Inject-Read-and-Check.pdf)
  * [Transformation phase](diagrams/runtime-Inject-Transformation.pdf)
  * [Recheck phase](diagrams/runtime-Inject-Recheck.pdf)
  * [Transformation and Recheck phase](diagrams/runtime-Inject-Transformation-and-Recheck.pdf)
* Repair scenario
  * [Read phase](diagrams/runtime-Repair-Read.pdf)
  * [Check phase](diagrams/runtime-Repair-Check.pdf)
  * [Read and Check phase](diagrams/runtime-Repair-Read-and-Check.pdf)
  * [Transformation phase](diagrams/runtime-Repair-Transformation.pdf)
  * [Recheck phase](diagrams/runtime-Repair-Recheck.pdf)
  * [Transformation and Recheck phase](diagrams/runtime-Repair-Transformation-and-Recheck.pdf)
