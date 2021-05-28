docker build -t sanjoy/tags:latest -f Dockerfile .

docker run -p 8089:8080 --name tag sanjoy/tags:latest
