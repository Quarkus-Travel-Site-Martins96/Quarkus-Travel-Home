cd src/main/angular/home-ng
npm i
ng build --prod
cd ../../../..
./mvnw package -DskipTests
