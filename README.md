## The Project
The project consist on a very basic Rock-Paper-Scissors simulator (https://en.wikipedia.org/wiki/Rock-paper-scissors).


Once executed, the program will ask you for two things:
* **Mode of play**: The mode of the simulation. It will determine how the second player behaves (the first one will always play in random-mode), which can be:
  * **Fair**: Plays in Random-mode
  * **Unfair**: Always chooses Rock.
  * **Remote**: Plays in random but the choice is asked via an HTTP request. For this, a the server will start automatically in localhost.
  
> For the remote mode, the spring-logs are displayed. It is not very visual friendly but it ensures, at least, that the server is being executed :)


* **Output Destination**: Where to output the result of the simulation. The options are:
  * **Console**: It will print the result into the console at the end of the simulation.
  * **File**: The result will be persisted to the file named: **GIG_SIM_RESULT_FILE.txt** in the *user's home*.

> The generated file will override any previously existing file with that name (so you can open it in your preferred editor and reload the contents within each execution without closing it)


## The technologies

The main tech-stack is: **Java 8 + Lombok**

Additionally, some tools are being used:

* Spring Boot - Only in the "remote" mode of play.


## Try-it yourself

#### Tests

You can launch the test with a simple command. From the root folder of the project run:
```
$ ./mvnw test
```


#### Execution

You can try the project executing the application. To execute it there are several ways:

* **Build-it yourself**: You can build the project with ease, for it you must open a console in the root folder of the project and execute:
```
$ ./mvnw clean compile exec:java
```
> (This order will automatically build and execute the server.)

* **Execute the [provided jar](https://github.com/NemesisMate/gigtrial/releases/)**
```
java -jar <JAR_NAME>.jar
```