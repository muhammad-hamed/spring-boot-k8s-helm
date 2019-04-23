# Spring boot service deployment on Kubernetes

We will deploy the Spring boot Service into the Kubernetes using
1. Kubectl comand line.
2. Kubernetes yaml files.
3. Helm package manager. 

The spring boot service is running on port **8080**. it's only has one controller `GET /hello` which print greeting and the ip address. 

## K8s - Kubectl
* Create spring app from `https://start.spring.io` (mvn, spring web and actuator)
* Create demo controller with only `GET /hello` to return the current machine IP.
* Create the `Dockerfile`
* Build the image in the local registry 
``` shell 
docker build -t muhammadhamed/demo .
```
* Push the image to docker hub repository 
```shell 
docker push muhammadhamed/demo
```

* Create a namespace for this project , this will help us to clear all the resources by just deleting the namespace 
 ```shell
 kubectl create namespace demo
 ```
* To let the kubernetes get image from your repository you have to add your secret 
```shell
kubectl create secret generic regcred --from-file=.dockerconfigjson=/Users/muhammad/.docker/config.json --type=kubernetes.io/dockerconfigjson -n demo
```
* Here we go, let deploy our image, this will create a kubernetes deployment using the image we've built for our service
```shell
kubectl run demo --image=muhammadhamed/demo:latest --port=8080 -n demo
```
* We need to make it available to the world so we will create a service
```shell 
kubectl expose deployment/demo --type="NodePort" --port 8080 -n demo
```
* Using the port showed in the previous command output, try to 
``` shell 
curl http://localhost:port/actuator/health
curl http://localhost:port/hello
``` 


## K8s - yml file
*  We will clear every thins and we will create a new name space
``` shell 
kubectl delete ns demo
```
* 
``` shell 
kubectl create namespace demo
````
* 
``` shell 
kubectl create secret generic regcred --from-file=.dockerconfigjson=/Users/muhammad/.docker/config.json --type=kubernetes.io/dockerconfigjson -n demo
```
* All of the the required resource aee create in a yaml file which will create the deployment and the service.
``` shell 
kubectl apply -f k8s/demo-service.yml -n demo
```
* Using the port showed in the previous command output, try to 
``` shell 
curl http://localhost:port/actuator/health
curl http://localhost:port/hello
``` 


## K8s - helm
* First of all we need to clear every thing and we will create a new namespace.
* And the we will install helm
```shell 
brew install kubernetes-helm
```
* Then we will create a helm template for our projectm this will create a set of filesm part of them is the Kubernetes deplyment.
```shell 
helm init 
helm create demo
```
* Then we will update thos template with the image tag, ports, etc. Then we will install our project
*) on this command we use upgrade which in the first time will install it. )*
```shell 
helm upgrade --install demo ./helm/demo/ --namespace demo
```

* Using the port showed in the previous command output, try to 
``` shell 
curl http://localhost:port/actuator/health
curl http://localhost:port/hello
``` 


Thanks a lot :) 