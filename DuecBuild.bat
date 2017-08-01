rem Batch script to execute ant build 

IF NOT DEFINED ANT_HOME set ANT_HOME=C:\Program Files\apache-ant-1.9.6
IF NOT DEFINED JAVA_HOME set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_25
set PATH=%PATH%;%ANT_HOME%\bin;%JAVA_HOME%\bin

IF NOT DEFINED MAJORVERSION set MAJORVERSION=1
IF NOT DEFINED MINORVERSION set MINORVERSION=2
IF NOT DEFINED BUILD_NUMBER  set BUILD_NUMBER=1

call ant -f buildconfig.xml std
echo $default target run successfully 
::call ant -f . -Dnb.internal.action.name=javadoc javadoc
::call ant -f . -Dnb.internal.action.name=test -Dignore.failing.tests=true test
call ant -f buildconfig.xml test
