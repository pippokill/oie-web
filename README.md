# Wiki Open IE Search Engine

This is the web application for quering the triples index created by WikiOIE: https://github.com/pippokill/WikiOIE

This project was created from https://start.vaadin.com.

## Run and Debug

### Running the application from the command line.
To run from the command line, use `mvn` and open http://localhost:8080 in your browser.

### Running and debugging the application from the IDE
- Locate the Application.java class in the Project view. It is in the src folder, under the main package's root.
- Right-click on the Application class
- Run the Application class in debugging mode.

After the application has started, you can view it at http://localhost:8080/ in your browser. 
You can now also attach breakpoints in code for debugging purposes, by clicking next to a line number in any source file.

## Project structure

- `MainView.java` in `src/main/java` contains the navigation setup. It uses [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.

## Vaadin Info

[vaadin.com](https://vaadin.com) has lots of material to help you get you started:

- Follow the tutorials in [vaadin.com/tutorials](https://vaadin.com/tutorials). Especially [vaadin.com/tutorials/getting-started-with-flow](https://vaadin.com/tutorials/getting-started-with-flow) is good for getting a grasp of the basic Vaadin concepts.
- Read the documentation in [vaadin.com/docs](https://vaadin.com/docs).
- For a bigger Vaadin application example, check out the Full Stack App starter from [vaadin.com/start](https://vaadin.com/start).
