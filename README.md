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
