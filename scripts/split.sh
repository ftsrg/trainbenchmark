#!/bin/bash
model="CIF_ALL_FULL_DAILY-toc-full.json"
maxLines=$(wc -l $model | cut -d' ' -f1)
N=20
defaultName="schedules"
range=$((maxLines / N))
readonly defaultName
readonly N
readonly maxLines
readonly range

echo "Split the data to $N fragments. The number of lines is: $maxLines"

for (( i=0; i<$N; i++ ))
do
	start=$((1 + range * i))
	if [[ i -eq $N-1 ]]
	then
		# skip the last line, since that contains superfluous information
		end=$((maxLines - 1))
	else
		end=$((start + range - 1))
	fi
	# split the model to smaller parts
	sed -n ''$start','$end'p' "$model" > ${defaultName}${i}temp
	# insert '[' to the beginning and '{}]' at the end of the file, plus, replace '}}' strings with }},
	sed '0,/{/s/^/[/' ${defaultName}${i}temp | sed '$a{}]' | sed 's/}}$/}},/' > ${defaultName}${i}.json
	rm ${defaultName}${i}temp
	echo "$defaultName$i created"
done


