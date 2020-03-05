# BookApiREST
this spring project allows you to manage books via REST APIs (List the books, get/create/update/delete a book)

Requirements

Java - 1.8.x
Maven - 3.3.9
la BDD est H2


Steps to Setup and test :
1. Clone the application

git clone https://github.com/scbushan05/book-api-spring-boot.git

2. Build and run the app using maven

mvn spring-boot:run
The app will start running at http://localhost:8080

3. Explore Rest APIs
The app defines following CRUD APIs.

GET /api/book
POST /api/book
GET /api/book/{bookId}
PUT /api/book/{bookId}
DELETE /api/book/{bookId}
You can test them using postman or any other rest client.

4. Explore DB :

You can access H2 DB Console via : http://localhost:8080/h2-console/
user : sa
password : password

5. launch Unit Test (test the "GET /api/book" API):
mvn test


