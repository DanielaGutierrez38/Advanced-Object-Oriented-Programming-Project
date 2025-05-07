# Space Debris Web App

## Overview
This is a Java Spring Boot web application designed to manage and analyze data on space debris. It provides role-based access for Scientists, Space Agency Representatives, and Administrators.

## Features
- **Scientist**
  - Track Objects in Space
  - Assess Orbit Status
- **Space Agency Representative**
  - Analyze Long-term Impact
  - Generate Density Reports
- **Administrator**
  - Create, Manage, and Delete Users

## Prerequisites
- Java 17 or later
- Maven 3.9 or later

## Setup Instructions
1. Clone the repository or download the source files.
2. Navigate to the project directory.
3. Run the following commands:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Open your browser and go to `http://localhost:8080`.

## Notes
- All required dependencies are managed via `pom.xml`.
- No `requirements.txt` is needed as this is not a Python project.

## Author
Developed by Caitlin Gregory and Daniela Gutierrez.

## How To:
1. Download Maven Binary ZIP
a) Go to:
👉 https://maven.apache.org/download.cgi

b) Download:
apache-maven-3.9.9-bin.zip

c) Open terminal and run:
cd ~/Downloads
unzip apache-maven-3.9.9-bin.zip
sudo mv apache-maven-3.9.9 /opt/maven

2. Set Environment Variables
a) In terminal:
nano ~/.zprofile

b) In the nano editor, paste the following lines:
export M2_HOME=/opt/maven/apache-maven-3.9.9
export PATH=$M2_HOME/bin:$PATH

c) Save and exit nano:
Press Ctrl + O then Enter
Then Ctrl + X

d) Apply changes:
source ~/.zprofile

3. Confirm Installation
mvn -v
You should see Maven version and Java info.

4. Run the Project
a) Navigate to your project folder (where pom.xml is):
cd /path/to/your/project-folder

b) Build the project:
mvn clean install

c) Run the web application:
mvn spring-boot:run

5. Access the Website
Open your browser and go to:
http://localhost:8080

