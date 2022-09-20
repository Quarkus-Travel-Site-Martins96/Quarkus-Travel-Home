cd src/main/angular/home-ng
npm i
ng build --configuration=development
cd ../../../..
./mvnw quarkus:dev -DskipTests
