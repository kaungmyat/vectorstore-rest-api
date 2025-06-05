
@echo off
set "JAVA_HOME=C:\Program Files\Java\jdk-21.0.6"
set "PATH=C:\Program Files\Java\jdk-21.0.6\bin;C:\path\to\apache-maven-3.9.8\bin;%PATH%"
mvn %*
