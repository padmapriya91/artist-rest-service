# artist-rest-service
To Get Artist Information

# Build the code
mvn clean install

# Run the code
mvn spring-boot:run

# Edge cases Covered
1. No artist is present with the passed artist id.
2. No events are present for the passed artist id.
3. No mapping venue is present from the venue mentioned in the event.

# Dependencies Included
1. Web Dependency - Required for REST API
2. Lombok Dependency - Required to avoid boilerplate code.
3. Dev Tools Dependency - To automatically restart the server when changes are done.
4. Junit and Mockito - For Unit Tests

#Request URL
http://localhost:8080/artist/21

#Response Structure
{
    "name": "HRH Prog",
    "id": 21,
    "imgSrc": "//some-base-url/hrh-prog.jpg",
    "url": "/hrh-prog-tickets/artist/21",
    "rank": 1,
    "events": [
        {
            "title": "Fusion Prog",
            "venue": "O2 Academy Sheffield,Sheffield"
        },
        {
            "title": "A festival Live",
            "venue": "O2 Academy Brixton,London"
        },
        {
            "title": "Huge Live",
            "venue": "O2 Academy Sheffield,Sheffield"
        }
    ]
}