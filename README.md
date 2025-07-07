## Original requirements

I don't feel like I can share the full details. But this was supposed to be RESTful service which supports authentication of my choice.

Some endpoints are public, some require authentication.

Some data are allowed to be hard coded (the service doesn't allow one to add movies or users for example).

It's a service for rating movies where users can rate movies and modify their ratings.

## How to build and run

```
./mvnw clean install -DskipTests # you won't be able to run tests until you run docker compose up
docker build -t skyinterview/service .
docker compose up
```

You can omit '-DskipTests' after you have built the docker image and started mysql

Please note that I had to restart the service when I did `docker compose up`
for the first time because mysql took longer to start than the service itself.

## How to test

All passwords are 'password'.

To see all available users, look into V1__Init_Database.sql

```
# Rate as tony@sky.com
curl --location --request PUT 'http://localhost:8080/movies/3/rate?rating=2' \
--user tony@sky.com:password

# Rate as frank@sky.com
curl --location --request PUT 'http://localhost:8080/movies/3/rate?rating=2' \
--user frank@sky.com:password

# Get Top rated movies
curl --location 'http://localhost:8080/movies?sortBy=averageRatingDesc'

# Get all movies
curl --location 'http://localhost:8080/movies'

# Get movie by id
curl --location 'http://localhost:8080/movies/3'

# Delete rating
curl --location --request DELETE 'http://localhost:8080/movies/3/rate' \
--user frank@sky.com:password

```

## Known shortcomings

I didn't implement any logging or metrics.

Integration tests are in SkyInterviewApplicationTests but they just verify that the application starts 
and that the database is initialized correctly.

Unit tests test the very basic scenarios. I would have implemented tests updating the rating as well as
edge case scenarios like invalid inputs (rating = 100) or users trying to rate movies that don't exist.

There is no formal API specification like OpenAPI. Production service should definitely have one.

The database table names 'user' and 'users' are a bit confusing. User is the app entity. 
"users" is a spring security table which holds the encrypted user passwords.
I'd definitely refactor this before going to production - probably by prefixing the
spring security table names.

There is no interface for editing the movies or users but that wasn't a part of the requirements.

For larger amount of movies, we should implement pagination.