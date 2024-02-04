# Student Social Backend

## Overview
The Student Social Backend is the core of a social networking platform designed for students, enabling them to connect, share, and communicate in a secure and user-friendly environment. This project is built using Spring Boot 3.2.2 and Maven, incorporating MyBatis-Plus for efficient data manipulation and JWT for secure user authentication. It supports essential features like sign-in/sign-up, post creation and commenting, and real-time chat functionalities.

## Features
- **User Authentication**: Implements JWT-based authentication for secure sign-in/sign-up processes.
- **Post Creation and Interaction**: Users can share posts, comment on them, fostering community engagement.
- **Real-time Chat**: A chat system that allows students to communicate with each other in real-time.
- **RESTful API Integration**: Offers a comprehensive set of RESTful APIs that enable seamless communication between the backend and frontend.
- **Data Manipulation**: Utilizes MyBatis-Plus for streamlined data access and manipulation, enhancing overall performance.


## Prerequisites
- JDK 17
- Maven 3.6.3 or newer
- MySQL 8.0 or another compatible database

## Setup and Installation
1. **Clone the repository**
   ```bash
   git clone git@github.com:bymaxchen/student-social-backend.git
   cd student-social-backend

2. **Configure application properties**
Customize src/main/resources/application.yml to set your database and other environment-specific configurations.

3. **Build the project with Maven**
`mvn clean install`

4. **Run the application**
`mvn spring-boot:run`

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgements
+ Spring Boot for providing a robust framework.
+ MyBatis-Plus for simplifying data manipulation.
+ JWT for securing our authentication system.

## Contact
For questions or more information, feel free to contact us at chenzy0620@outlook.com