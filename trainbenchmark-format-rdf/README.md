#### Visualization script

For visualizing RDF models, you can use the `graphviz` and `rapper` tools:

```bash
sudo apt-get install -y rapper graphviz
```

Usage:

```bash
MODEL=railway-repair-1
rapper -i turtle $MODEL.ttl -o dot | dot -Tpdf > $MODEL.pdf
```

You may use `fdp`, `neato`, `twopi` or `circo` instead of `dot`.
