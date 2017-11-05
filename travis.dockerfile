FROM gradle

USER root

RUN apt-get update

RUN apt-get -qq update && apt-get install -y mysql-server sqlite3 r-base r-base-dev

COPY trainbenchmark-reporting/install.R /install.R

RUN Rscript /install.R

CMD ["gradle", "initScript", "build", "-x", ":trainbenchmark-generator-sql:test", "-x", ":trainbenchmark-tool-mysql:test", "-x", ":trainbenchmark-tool-sqlite:test", "--stacktrace", "--continue"]

