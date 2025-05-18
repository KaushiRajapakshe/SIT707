#  OnTrack – Extension Request System

This project is part of SIT707 – Software Quality and Testing. It implements a simplified version of an **Extension Request Management** feature for the OnTrack platform, allowing students to submit assignment deadline extension requests and lecturers to review them.

---

##  Critical Observation of Existing OnTrack Functions

### 1. **OnTrack Class**
- **Input:** Users (students and lecturers), tasks, messages, extension requests
- **Output:** Task statuses, extension request statuses, notifications, approvals

### 2. **PersonManager Class**
- **Input:** Person type (student, lecturer), name
- **Output:** Filtered list of persons by type, validated user sessions

### 3. **TaskManager Class**
- **Input:** Task details (name, due date, trimester)
- **Output:** Task list per trimester, task deadlines, status tracking

### 4. **ChatManager Class**
- **Input:** Chat messages related to tasks between students and lecturers
- **Output:** Chronological list of communication for each task

---

##  Customer Requirement Story

> As a student using OnTrack, I want to be able to request assignment deadline extensions by providing a reason and preferred new submission date. This feature supports students facing unexpected challenges such as:
>
> - Medical issues
> - Mental health problems
> - Technical failures (e.g., laptop breakdown)
> - Workload overlap (e.g., internship or multiple exams)
> - Family emergencies
> - Accommodation or visa issues

### Benefits:
- Lecturers can reduce student stress.
- Students are treated fairly in tough situations.
- Auto-approval for medical reasons.
- Lecturers are notified and can approve manually when needed.

---

##  Planned Features and Functionality

###  Extension Request Management

| Function                             | Description |
|--------------------------------------|-------------|
| **Submit Extension Request**         | Student submits request with reason and desired new date |
| **User Validation**                  | Validate requester is a registered student |
| **Check Submission Deadline**        | Ensure request is not after current due date |
| **Validate Request Details**         | All fields must be complete and valid |
| **Validate New Submission Date**     | New date must be after the current deadline |
| **Validate Extension Reason**        | Only predefined reasons are accepted |
| **Auto-Approval Logic**              | Auto-approve if reason is medical |
| **Lecturer Review and Approval**     | Lecturer reviews and approves/rejects manually |
| **Send Email Notification**          | Notify student of the decision |
| **Review Submitted Requests**        | Maintain and display request history |

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
