FROM gradle

USER root

RUN apt-get -qq update && apt-get install -y mysql-server sqlite3 r-base r-base-dev

COPY trainbenchmark-reporting/install.R /install.R

RUN Rscript /install.R

