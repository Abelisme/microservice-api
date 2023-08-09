cd ../../..

# Build all the modules
mvn clean package -DskipTests

# Deploy
docker-compose up --build -d
