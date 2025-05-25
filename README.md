#  OnTrack – Task management

This project is part of SIT707 – Software Quality and Testing. It implements a simplified version of an **Task management** feature for the OnTrack platform.

---

##  Story task management system
1.	As a user, I want to Update and delete my sent messages to correct my mistakes, including spelling errors, incomplete messages, and outdated information.
2.	As a user, I want to search for messages so I can quickly find the conversation I need. It will help me find what I need without wasting my time.
3.	As a student, I want to view all my conversations with my lecturer related to my assignment so that I can track my progress, and it will help me find what I need without wasting my time.
4.	As a lecturer, he has the ability to update and delete assignment deadlines, which gives him access to modify the assignment timeline and delete the outdated assignments.
5.	As a student, I can see the assignment information, so it will be easy to finish my task on time.
6.	As a lecturer, he can create a new assignment that students can complete to gain knowledge and marks.

---

##  Technology Stack

- **Language:** Java
- **Build Tool:** Maven
- **Testing:** JUnit 5
- **CI/CD:** GitHub Actions
- **hibernate:** Database

---

##  How to Run

```bash
# Run tests
mvn clean test

# Package the project
mvn clean install

```

In here we are using github action to automate continues integration.
