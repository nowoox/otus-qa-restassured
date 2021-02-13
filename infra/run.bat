cd C:\temp

cm_windows_386.exe selenoid start
cm_windows_386.exe selenoid-ui start

docker pull selenoid/vnc_chrome:86.0
docker pull selenoid/vnc_firefox:81.0

docker ps

cd C:\Users\gpbu8196\IdeaProjects\otus-qa-restassured & mvn clean test

cm.exe selenoid-ui stop
cm.exe selenoid stop