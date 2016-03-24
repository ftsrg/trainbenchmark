library(arules)

data(iris)
x <- iris[,4]
hist(x, breaks=20, main="Data")

def.par <- par(no.readonly = TRUE) # save default
layout(mat=rbind(1:2,3:4))

# ### convert continuous variables into categories (there are 3 types of flowers)
# ### default is equal interval width
# table(discretize(x, categories=3))
# hist(x, breaks=20, main="Equal Interval length")
# abline(v=discretize(x, categories=3, onlycuts=TRUE), col="red")
# 
# ### equal frequency
# table(discretize(x, "frequency", categories=3))
# 
# hist(x, breaks=20, main="Equal Frequency")
# abline(v=discretize(x, method="frequency", categories=3, onlycuts=TRUE),col="red")
# 
# ### k-means clustering 
# table(discretize(x, "cluster", categories=3))
# hist(x, breaks=20, main="K-Means")
# abline(v=discretize(x, method="cluster", categories=3, onlycuts=TRUE), col="red")


### user-specified
x = c(1.2, 1.6, 2.4)
table(discretize(x, "fixed", categories = c(-Inf,.8,1.6,Inf)))
table(discretize(x, "fixed", categories = c(-Inf,.8, Inf), labels=c("small", "large"))) 
hist(x, breaks=20, main="Fixed")
abline(v=discretize(x, method="fixed", categories = c(-Inf,.8,Inf), onlycuts=TRUE), col="red")

par(def.par)  # reset to default

### prepare the iris data set for association rule mining
for(i in 1:4) iris[,i] <- discretize(iris[,i],  "frequency", categories=3)

trans <- as(iris, "transactions")
inspect(head(trans, 1))

as(head(trans, 3),"matrix")
