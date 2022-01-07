APP_NAME=sagdemo
APP_VERSION=0.0.10
DOCKER_ID=sttlab

# Build the docker image
docker build -t $APP_NAME:$APP_VERSION ../../docker

# Tag the image before pushing it to Docker Hub
docker tag $APP_NAME:$APP_VERSION $DOCKER_ID/$APP_NAME:$APP_VERSION

# Login to Docker Hub (it's going to ask for the Docker key)
docker login -u $DOCKER_ID

# Push to Docker Hub
docker push $DOCKER_ID/$APP_NAME:$APP_VERSION
