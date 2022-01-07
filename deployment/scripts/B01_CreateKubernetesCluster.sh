GCP_PROJECT=logical-essence-121619
CLUSTER_NAME=sagdemo
ZONE=europe-west6-c

# Set the Google compute zone in which the cluster is to be created
gcloud config set compute/zone $ZONE

# Create the cluster (here we create a cluster with two zones)
gcloud container clusters create $CLUSTER_NAME --num-nodes=2

# Change the kubectl client config to point to the newly created cluster
gcloud container clusters get-credentials $CLUSTER_NAME --zone $ZONE --project $GCP_PROJECT

# Verify that kubectl points to the new cluster 
kubectl get nodes
