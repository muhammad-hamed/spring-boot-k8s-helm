# K8s - Kubectl
* Create spring app from `https://start.spring.io` (mvn, spring web and actuator)
* Create demo controller with only `GET /hello` to return the current machine IP.
* Create the `Dockerfile`
* Build the image in the local registry `docker build -t muhammadhamed/demo .`
* `docker push muhammadhamed/demo`
* `kubectl create namespace demo`
* `kubectl create secret generic regcred --from-file=.dockerconfigjson=/Users/muhammad/.docker/config.json --type=kubernetes.io/dockerconfigjson -n demo`
* `kubectl run demo --image=muhammadhamed/demo:latest --port=8080 -n demo`
* `kubectl expose deployment/demo --type="NodePort" --port 8080 -n demo`
* Using the port showed in the previous command output, try to curl `http://localhost:32042/actuator/health` and `http://localhost:32042/demo` !


# K8s - yml file
* `kubectl delete ns demo`
* `kubectl create namespace demo`
* `kubectl create secret generic regcred --from-file=.dockerconfigjson=/Users/muhammad/.docker/config.json --type=kubernetes.io/dockerconfigjson -n demo`
* `kubectl apply -f k8s/demo-service.yml -n demo`


# K8s - helm
* `brew install kubernetes-helm`
* `helm init`
* `helm create demo`
* `helm upgrade --install demo ./helm/demo/ --namespace demo`