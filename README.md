
# SAG demo

  

This project contains a simple REST API implemented with Spring Boot, which calculates a digest from a given text content.

### API specifications
Open API specification: https://app.swaggerhub.com/apis/stephane-sttlab/sag-demo/1.0.0
The API is secured with a "thisisademo" API key, which needs to be passed in a api-key HTTP header.

  

### Maven build and install

Place yourself at the root of the project and run: 

`mvn clean install`

The project is based on Java 11 so make sure to have a JDK that is compatible with that version.

A copy of the generated Spring boot fat jar is placed in the ./docker directory, with the name app.jar

  

### Docker build

The ./docker folder contains a Dockerfile to build the image.

To do so, move to the ./docker folder ans run: 

`sudo docker build -t sagdemo:${version} .`
(set ${version} to the value of your choice, for instance 1.0.0 or latest)

The image is based on openjdk:11-jre-slim and embeds the app.jar file generated by Maven in the previous step.

The Tomcat port is set to 8080 by default.

  

### Push to Docker repository

You can push to the repository of your choice before deploying the image to a Kubernetes cluster.

Just make sure the cluster is authorized to access the repository.

To keep things simple you can push to a public repository like Docker Hub, to which all Kubernetes clusters have access without having anything to do:

Steps to push the image to Docker Hub:

* Create a Docker Hub account if your don't already have one: https://hub.docker.com

* Create an access token: https://docs.docker.com/docker-hub/access-tokens/

* Attach your Docker Hub account to your docker client using this command: 

     `docker login -u ${dockerId} -p ${accessToken}`
(replace ${dockerId} with your docker ID and ${accessToken} with your access token)

* Tag the docker image: 

     `docker tag sagdemo:$version $dockerId/sagdemo:$version`

* Push the image to Docker Hub: 

     `docker push ${dockerId}/sagdemo:${version}`

  

### Kubernetes deployment

The ./deployment folder contains Kubernetes deployment files (in yaml format):
* 01-sagdemo-dep.yaml contains the deployment specifications for the pods
* 02-sagdemo-svc.yaml contains the specifications for the service that is in front of the pods
* 99-sagdemo-ingress.yaml contains the specifications of the ingress gateway that exposes the service to the outside world (on port 80)

    ![Kubernetes Architecture](https://github.com/stephane-sttlab/sagdemo/blob/main/Sagdemo_KubernetesArchitecture.png)
  
To deploy in your Kubernetes cluster:
* Make sure to connect your kubectl client to the cluster in which you want to perform the deployment.
* Install the ngnix ingress controller in your cluster.
* Place yourself in the ./deployment folder and run:

    `kubectl apply -f .`

 * Verify the pods are running by submitting the following command. You should see two sagdemo pods in status "Running"

    `kubectl get pods`

 * Verify the service is also running by submitting the following command. You should see one sagdemo-ingress controller with a public IP address. It can take a few minutes for this IP address to show so you may have to repeat the command a few times.

     `kubectl get ingress`

 * Note the IP address assigned to the ingress controller, you will need it to call the API.