@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-8
set PATH=%JAVA_HOME%\bin;%PATH%
gradlew runClient --rerun-tasks
pause