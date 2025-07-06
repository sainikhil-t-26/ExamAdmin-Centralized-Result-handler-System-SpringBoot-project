# ResultSphere-Centralized-Results-Exam-System(Spring Boot, Thymeleaf, MySQL)
This is a centralized Spring Boot web application developed by Sai Nikhil T of MLRIT as a prototype to efficiently manage and streamline the assessments conducted by the Data Science department for Campus Recruitment Training programs at MLRIT.
Developed as a prototype by Sai Nikhil T of MLRIT, this system provides distinct functionalities for Admins, Teachers, and Students, ensuring a smooth end-to-end assessment lifecycle.

📌 Features
✅ Admin Module

Add, edit, and delete student records.

Bulk import student records via Excel.

Manage teacher records (add, edit, delete).

✅ Teacher Module

Enter and update marks of students for various subjects.

Bulk upload marks through CSV files.

✅ Student Module

View individual assessment results.

✅ General

Pagination and search to easily navigate student records.

Export data to Excel for offline analysis.

🛠 Tech Stack
Layer	Technology
Backend	Spring Boot (Java)
Frontend	Thymeleaf, HTML, CSS
Database	MySQL
File Handling	Apache POI (Excel), OpenCSV (CSV)
ORM	Spring Data JPA

✨USE-CASE Diagram of the project

![Examhandler](https://github.com/user-attachments/assets/2eae992b-b9b0-47ca-8084-9ff3857109a2)

✨Project File Structure

![filestructure](https://github.com/user-attachments/assets/06952b0a-83de-4a1b-b588-b7a220beaaee)

✨Screenshots of Virtual-Guide of the Project 
![indexpage](https://github.com/user-attachments/assets/56b8abee-bc38-422a-86bf-54391cb5f10e)

Admin Home

![Adminpage](https://github.com/user-attachments/assets/a2b6d006-f895-43b2-abf9-154d02f1750d)


![addstu](https://github.com/user-attachments/assets/58658b75-0ad5-4b26-8f7f-6837cb289ddd)

![allstu](https://github.com/user-attachments/assets/0545a0cc-d0dc-44df-9e2c-54815360cfba)

 Teacher Page
 ![teacherhome](https://github.com/user-attachments/assets/87bc8652-89b4-4284-b2fa-2c81ba39dcae)
 
Results page
![Resultspage](https://github.com/user-attachments/assets/facc69f6-40db-4349-9ea9-b05cf387ee12)

⚙️ Setup

1️⃣ Clone the Repository

git clone https://github.com/yourusername/ResultSphere.git

cd ResultSphere

2️⃣ Configure MySQL

-->Create a database called resultsphere.

CREATE DATABASE resultsphere;

Update src/main/resources/application.properties with your DB credentials:

-->application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/resultsphere

spring.datasource.username=root

spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update

3️⃣ Build & Run

mvn clean install

mvn spring-boot:run

Visit: http://localhost:8080

This is the complete guide to develop the project.
For any queries 
contact: tsainikhil1@gmail.com
