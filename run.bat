set projectLocation=C:\Testing\DockerJenkins
pushd %projectLocation%
##call mvn -f %projectLocation%\pom.xml clean install -U   --> If <suiteXmlFiles>testng.xml</suiteXmlFiles> in pom.xml
mvn clean test -Dsurefire.suiteXmlFiles=%projectLocation%\testng.xml
pause