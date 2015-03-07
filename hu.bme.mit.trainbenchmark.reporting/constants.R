resultsPath <- "../results/csv/results.csv"
configPath <- "./config.json"

# y-axis scaling
scaleDivisor <- 10**6

# output folder
rootPath <- c("../diagrams/")

# plot settings
pointSize = 3
lineSize = 1
yLabel <- "Time (ms)"
yAxis <- "Log10"

labels <- list("1" = "1\n Thomas Hardy", 
               "2" = "2\n Charles Dickens", 
               "4" = "4\n P \n O \n E", 
               "8" = "8\n Fyodor \n Dostoyevsky",
               "16"= "16\n Leo \n Tolstoy",
               "32"= "32\n Robert \n Merle",
               "64" = "64\n Ernest Hemingway",
               "128" = "128\n Raskolnikov",
               "256" = "256\n Levin",
               "512" = "512\n Ivan Karamazov"
               )


