# java-k8s-data


```
mvn compile jib:dockerBuild

# Apply resources
kubectl apply -f k8s-spec/data-configmap.yaml
kubectl apply -f k8s-spec/java-pod.yaml
```
