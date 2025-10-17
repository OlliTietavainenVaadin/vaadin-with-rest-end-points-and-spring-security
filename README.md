# An example project of a Spring Boot app providing both REST endpoints and a Vaadin UI

The example has default Vaadin Spring Security setup, but configured to cope with
two REST endpoints. One with public read access and one with secured access to post
messages. The Vaadin side is also secured, and contains further examples how to 
play with the demo.

Import the project to your IDE, run the main class `DemoApplication` and open
http://localhost:8080/my-context in your browser.

## Notes

The app also exposes a Swagger-UI at http://localhost:8080/my-context/swagger-ui.html. 

