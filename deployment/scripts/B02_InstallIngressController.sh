# Add the ingress nginx repo to the helm config
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx

# Update helm to load the content of the newly added repo
helm repo update

# Install nginx ingress from the repo
helm install nginx-ingress ingress-nginx/ingress-nginx

# Verify the installation
kubectl get service nginx-ingress-ingress-nginx-controller