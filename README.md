# Algorithmic Complexity Calculator (ACC)

## Introduction

Complexity of a code base is a measure of the quality of the code that the programmer writes it help to see vulnerabilities, duplication happen in code and help to reduce the redeclaration of variables in a program. So, since it’s a very import aspect to every programmer to verify the code quality of his/her code base we have made it easy through our solution. Although there are many complexity measure tools exists most of them don not have the ability to calculate the complexity when a folder structure containing list of files is scanned and apart from that our solution show the complexity details of the each and every line in all the files which will help the programmer to identify where he/she can reduce the complexity of the program. When we see from the programmer point of view, he is always occupied with programming most of the time so we have implemented a special feature which will able to track the history of the files that are scanned. So that once the programmer modified and scanned the code base again, he can compare the complexity differences in the code before modifying and after modifying. We have displayed the Code base differences via Bar chart so that programmer can get a clear understanding of the differences.

The key concepts in the our solution that made our solution a unique tool when compared to other products in the market. In our design we are finding total complexities as result of the occurrence of following complexities,

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  a) complexity due to inheritance.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  b) complexity due to Size.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  c) complexity due to Nesting Levels.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  d) complexity due to Control Structures.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  e) complexity due to Recursion.<br />

## System Components

The Algorithmic Complexity Calculator (ACC) contains three major components, ACC scanner, ACC engine and a web client. It currently supports two languages Java and C. 
<p></p><br />

<img src="https://i.ibb.co/cTZNTGG/component-diagram.png" alt="Capture7" border="0">
Figure 1: component diagram
<p></p><br />
ACC scanner does the complexity calculation process. At last the generated output will send as a post API request to ACC engine. The ACC engine inserts the requested values into the database. End user can use the web client to view the current progress and historical records about the scanned projects.

### A.	ACC Scanner

Purely the ACC scanner has implemented in Java oriented technologies. The scanner includes three major services.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Recursive file reader.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Complexity estimator.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Complexity feeding plugin.<br />

Once a project has submitted to the recursive file reader, the service will recursively go through the project folders and read files. While the process reads the code line by line from the beginning. 

The recursive file reader prepares a list and finally passes to the complexity estimator. 

Complexity estimator will estimate the complexity of lines as per the internal algorithm. Algorithm uses source code to generate complexity. All commented lines will be ignored by the process. This process measures four types of complexities and finally combines them. Below are the different types of complexities we are measuring.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a)	Size complexity.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b)	Type and the nesting level of control structures complexity.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c)	Inheritance complexity.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d)	Recursion complexity.<br />

The final complexity value will be generated using these complexities. Finally, all these complexity values parsed to the complexity feeding plugin.

Complexity feeding plugin will generate a JSON request relevant to the passed line data. At last JSON request will be sent to ACC backend.

### B.	ACC Engine

ACC backend engine is developed using Java technology with the spring boot framework. Engine act in two ways, according to the request, it will either return project details or insert request details to the database. ACC engine contains three layers. 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Controller layer.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Service layer.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Repository layer.<br />

Controller layer receives requests coming from ACC scanner, while receiving it will validate the request body and generate an entity object. Then this object will pass to the Service layer for further processing.

Once service layer received the request entity object it will add additional important values such as created time to the entity object. Then it will pass to the repository layer.

When repository layer received an entity object it will insert that object as a JSON document to the configured MongoDB database.

### C.  ACC Web Client

ACC web client is developed using ReactJS technology. The web client provide below features.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Show a comparison about project complexities.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Show project history in graphical view.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	File wise complexity comparison.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;•	Decide project status.<br />

When loading the home screen it will fetch all projects from ACC engine. Then, it will display all projects with relevant project complexity. Users can compare projects in this view.

Inside the project view, it will display a graphical view of project history. In this graph, there is a comparison of the last six analysis results.

Inside the project view, it will display the complexity per file. Also, once a user selects a file, the user can view the line by line complexity.

There is a quality gate that uses the complexity values of all the files. Using an internal algorithm currently, it will display the pass or fail status of a particular project.
<p></p><br />

<img src="https://i.ibb.co/YLVYd49/home.png" alt="Capture7" border="0">
Figure 2: acc client dashboard
<p></p><br />
<img src="https://i.ibb.co/gmfzjX5/files.png" alt="Capture7" border="0">
Figure 3: file complexity
<p></p><br />
<img src="https://i.ibb.co/P17sp9g/file-data.png" alt="Capture7" border="0">
Figure 4: line complexity
<p></p><br />
<img src="https://i.ibb.co/LgBSgZQ/history.png" alt="Capture7" border="0">
Figure 5: analysis history
<p></p><br />

## Complexity Calculation Methodology

### Measuring the complexity of a program statement due to size (Cs) 

➢ A constant value of two is added to ‘Cs’ at the detection of the following:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪  Reference (&) and dereference (*) operators. <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ ‘new’, ‘delete’, ‘throw’, and ‘throws’ key words.    <br />               
Note: The (*) sign used in the declaration of C++ pointer is not a dereference operator. It is just a similar notation that creates a pointer.  
 
➢ A constant value of one is added to ‘Cs’ at the detection of the following:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Arithmetic operators       → { +     -      *        /       %  ++     -- } <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Relation operators           → { ==      !=      >     <     >=  <= } <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Logical operators            →  { &&     ||      ! } <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Bitwise operators            →  { |      ^      ~   << >>     >>>     <<< } <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Miscellaneous operators →  { ,    -> .      :: } <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Assignment operators     →  {+=    -=    *=    /=   =   >>>=    |=  &=   %=   <<=   >>=   ^= } <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Key words                       → {void, double, int, float, string, printf,  println, cout, cin, ‘if’, ‘for’, ‘while’, ‘do-while’, ‘switch’, ‘case’ etc.} <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Manipulators                   → {‘endl’,  ‘ \n’, etc. } <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Text inside a pair of double quotes → {Eg:  “The greatest is” } <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Class, method, object, variable, and array names <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▪ Numeric values (numbers) Note: Key words such as public, static, else, try and return are not considered under the size complexity factor. 

### Measuring the complexity of a program statement due to type of control structures (Ctc) 

➢ For a program statement with a conditional control structure such as an ‘if’ condition,  a weight of one is assigned for the ‘if’ condition and for each logical (‘&&’ and ‘||’) or bitwise (‘&’ and ‘|’) operator that is used to combine two or more conditions. 

➢ For a program statement with an iterative control structure such as a ‘for’, ‘while’, or ‘do-while’ loop,  a weight of two is assigned for the ‘for’, ‘while’, or ‘dowhile’ loop and for each logical (‘&&’ and ‘||’) or bitwise (‘&’ and ‘|’) operator that is used to combine two or more conditions. 

➢ A weight of one is assigned for a program statement with a ‘catch’ statement. 

➢ A weight of n is assigned for a program statement with a ‘switch’ statement with n number of cases. 

➢ A weight of zero is assigned for all the other program statements in a program. 

### Measuring the complexity of a program statement due to nesting of control structures (Cnc)

➢ A weight of zero is added for program statements which does not contain any level of nesting. 

➢ A weight of one is added for program statements which are at the outer most level of nesting. 

➢ A weight of two is added for program statements which are at the next inner level of nesting. 

➢ Similarly, the weight allocated for the program statements is increased by one for each level of nesting. 

### Measuring the complexity of a program statement due to inheritance (Ci)

➢ The complexity of all the program statements which belongs to a class is assigned the same weight that the class has due to its inheritance: 

##### Complexity of a program statement of a class due to inheritance (Ci) = Complexity of the class due to its inheritance (CCi) 
 
➢ Complexity of a class due to inheritance (CCi) is computed as follows: 

##### Complexity of a class due to its inheritance (CCi) = Number of ancestor classes of the class + 1 

### Measuring total complexity of a program statement   

➢ First, compute total weight (TW) of a program statement as follows: 

##### TW = Ctc + Cnc + Ci 
 
➢ Next, compute the complexity of a program statement (Cps) as follows: 

##### Cps = Cs * TW 

### Measuring the complexity introduced due to recursion (Cr)   

➢ Double the Cps values derived for each program statement that belongs to a recursive method. 

### Measuring complexity of a program (Cp)  

➢ The complexity of a program (Cp) which consists of a recursive method is computed as follows: 

##### Cp  = Addition of ‘Cps’ values derived for the program statements which does not belong to a recursive method + Addition of ‘Cr’ values derived for the program statements that belongs to a recursive method 
 
➢ The complexity of a program (Cp) which does not consist of a recursive method is computed as follows: 

##### Cp = Addition of the ‘Cps’ values of all the program statements in a program 

## Unique Features

We’ve developed these components separately so that the ACC scanner can be used as continues inspection plugging which can be plugged into continues deployment systems to scan the code before deploying to a server. 

Also, this ACC scanner can be compiled into a separate jar file which can later be developed into an eclipse plugin which shows complexity in real-time. It will help developers to write fewer complex codes while they write coding.

## Deployment

1) Go to the folder "ACC" and build the java project using ```mvn clean install```. Then run the jar file and start the backend service using the command ```java -jar acc-0.0.1-SNAPSHOT.jar```.

2) Go to the folder "ACCClient" and open the console and enter the below commands to start the react client.
```
> npm install
> npm run dev
```
3) Go to the folder "ACCScanner" build the java project using ```mvn clean install```. Run the Scaner using the following command in the command line console and give the paths of the files you want to analyse with a unique project key. 
NOTE: When running the ACCScanner the path should contain the given config folder.

```
java -DprojectKey={Unique Project Key} -DsourcePath={Path to the project source folder} -jar {ACCScanner jar file name}
eg:- java -DprojectKey=ACC -DsourcePath=E:\Development\algorithmic-complexity-calculator -jar core-1.0-SNAPSHOT-jar-with-dependencies.jar
```

4) Go to the url http://localhost:1234/home in your browser.
	
#### Developed by Team VIKING RAIDERS;
* Ranmal Dewage
* Tenusha Guruge
* Vimukthi Rajapaksha
* Aravinda Kulasooriya


## Copyright

(C) 2019 Ranmal Dewage (ranmal.b.dewage@gmail.com)
<br>
[ranmaldewage.wordpress.com](https://ranmaldewage.wordpress.com)
