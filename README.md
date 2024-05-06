# Details of changes


1. Delete .idea folder - **Never commit these files to Git**
2. Delete .iml file - **Never commit this file to Git**

3. Try running Main class - can of error - Yahoo!!
4. `SelectAndNonSelectApp1.java` - `java: package com.pwskills.utility does not exist`. Lets go to this package and see this class does exist. Then what's the problem?
5. The first line of this file is problem - `package main.java.com.pwskills.utility;` change to `package com.pwskills.utility;` Now pretty much most of the error is gone, baring few. Note `src/main/java` is a convention follwed by Java for Maven/Gradle projects these folders are not part of your package. 


6. `DBUtilConnectionPool.java` error - `java: package com.mysql.cj.jdbc does not exist`. This is super easy. You do not have the mysql jdbc driver as dependency in your pom.xml. so go to mvnrepository.com and find the driver and add it as shown. 
7. `TestImageApiApp1.java` error - `java: package org.apache.commons.io does not exist` - Again same problem. Missing dependency commons-io. Add it as shown. 

