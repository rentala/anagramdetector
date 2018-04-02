# Anagram Finder

#### Build Instructions ####

Maven is a prerequisite.
* Navigate to root directory and run the following command

```
sudo mvn package

```

Note: The build is dependent on the passing of unit test cases. So be sure to have a dictionary.txt file placed in the root directory.


* A .jar file will be compiled to the target directory

* To execute the first version, run the following command from root with the dictionary file being the first argument

```
java -jar target/myapp-1.0-jar-with-dependencies.jar ./dictionary.txt

```


* To execute the second version, run the following command from root with the dictionary file being the first argument and a second argument. It just needs a second argument as an indication to run the second version.

```
java -jar target/myapp-1.0-jar-with-dependencies.jar ./dictionary.txt large

```


* To run the unit tests, excute this command:

```
sudo mvn test
```

##### Initial project and history : #####

https://github.com/rentala/AnagramFinder



#### Initial Approach: ####

I loaded up the .txt file into a HashMap with the 'key' entry being the sorted value of the anagram and the 'value' entry being a group of anagrams that sort to the 'key' entry value.
For example,  'opst' => [ 'post', 'spot', 'stop', 'tops'].


##### Pros: #####
All retrieval calls take less than 1ms

##### Cons: #####
Loading the dictionary into a HashMap does take some time initially.
For large values, a single server could eventually run out of RAM



##### Second Version: #####

For the second part, I've thought about "if it is run against a dictionary with over 100,000 words including longer words"  and I did some googling and math.

Oxford English Dictionary contains full entries for 171,476 words. If we round it up to 200,000 words and assume that each word contains an average of 10 characters and each char being 2 bytes, we would need RAM of 4MB which isn't a lot and the initial approach would scale to the calculated load.

However, if we still wanted to make sure that it ran on "possibly part of some larger server infrastructure", sharding would be the approach. 
The second part, follows a similar approach as that of the initial version, which is having a mapping of sorted word to list of anagrams mapping but writes these to disk and groups all of them alphabetically. For example, a sample structure would be:

/Index
          /o
             os.csv
          /a
             abc.csv => { 
             
##### Pros: #####
Retrieval calls take 1-3ms 
Scalable for very large data as RAM utilization is a fraction of what is used up by the first approach
##### Cons: #####
Loading the dictionary into a file structure takes some time initially.
Allows for sharding by alphabetical order across servers. Example: server 1 contains { a- m } server 2 contains { n = z }
