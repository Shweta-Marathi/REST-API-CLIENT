# REST-API-CLIENT
COMPANY: CODTECH IT SOLUTIONS

NAME: SHWETA RAMA MARATHI

INTERN ID: CT04DY1153

DOMAIN: JAVA PROGRAMMING

DURATION: 4 WEEKS

MENTOR: NEELA SANTOSH

DESCRIPTION:
This task involves building a simple Java REST client that fetches real-time weather information from the internet using the Open-Meteo API. The program is designed to request weather data for selected Indian cities (like Mumbai, Delhi, Hyderabad, Bengaluru, Pune, and Chennai), parse the JSON response, and display key details such as temperature, wind speed, wind direction, and whether it is day or night. The task is implemented in Visual Studio Code (VS Code) with Maven as the build tool, ensuring proper dependency management.

Tools Used
Java Development Kit (JDK 17):
The program requires JDK 11 or later because it uses the java.net.http package, which was introduced in Java 11. The project is configured to use JDK 17 through the pom.xml file.
Visual Studio Code (VS Code):
VS Code is used as the coding platform. With extensions like Java Extension Pack and Maven for Java, it provides syntax highlighting, error detection, debugging, and integrated terminal support.
Maven:
Maven is a popular Java build automation tool. It is used here to manage project structure and dependencies. The project’s pom.xml specifies the Gson library for JSON parsing. Maven also makes it easy to compile, package, and run the application.
Gson Library:
To parse JSON responses from the weather API, the Google Gson library is used. It simplifies converting raw JSON into Java objects (JsonObject) and extracting values.

Platform
The project is developed on VS Code with Maven, running on JDK 17. The advantage of this setup is:
Cross-platform execution (Windows, Linux, macOS).
Automatic dependency handling via Maven.
Lightweight development environment (VS Code) compared to heavier IDEs.

Applications
This task has multiple real-world applications:
Weather Apps: The logic can be extended into desktop or mobile weather applications.
IoT Devices: Smart displays or assistants can use this client to show live weather.
Educational Use: Demonstrates REST API consumption, JSON parsing, and modern Java features.
Automation: Can be part of scripts for weather-based decision making, such as logistics or farming operations.
Data Logging: The client can be modified to periodically fetch weather data and store it for analysis.
