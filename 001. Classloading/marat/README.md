run
 mvn clean compile
cd to classloading-main
run something like:
 mvn exec:java -e -Djava.system.class.loader=com.epam.marat.classloading.MyClassLoader
  -Dexec.args="d://testclclasses"
note - we expect classes are in the same folders as their package