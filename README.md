# Bookify
A JavaFX desktop application for bookstore owners to manage inventory, sales, and customers — powered by Java backend and MySQL.
📚 Bookify
A desktop e-commerce and management application built for bookstore owners. Bookify provides a clean, intuitive interface to manage your bookstore's inventory, process sales, and keep track of customers — all in one place.
Built with Java, JavaFX, and MySQL.

✨ Features

📦 Inventory Management — Add, update, and remove books with details like title, author, genre, price, and stock quantity
🛒 Sales Processing — Handle customer purchases and generate order records
👥 Customer Management — Store and manage customer information
📊 Dashboard Overview — Get a quick snapshot of store activity
🗄️ Persistent Storage — All data stored and retrieved from a MySQL database


🛠️ Tech Stack
LayerTechnologyLanguageJavaUI FrameworkJavaFXBackendJava (OOP, MVC architecture)DatabaseMySQLDB ConnectivityJDBC

🚀 Getting Started
Prerequisites

Java JDK 11 or higher
JavaFX SDK
MySQL Server
An IDE 
Installation

Clone the repository

bash   git clone https://github.com/Badawy05/Bookify.git
   cd Bookify

Set up the database

Open MySQL and create a new database:



sql     CREATE DATABASE bookify;

Import the provided schema:

bash     mysql -u root -p bookify < database/bookify_schema.sql

Configure the database connection

Navigate to the DB config file (e.g., src/config/DBConnection.java)
Update your credentials:



java     String URL = ""jdbc:mysql://localhost:3306/Bookify?useSSL=false&allowPublicKeyRetrieval=true"";
     String USER = "root";
     String PASSWORD = "wasd1234";

Run the application

Open the project in your IDE
Add the JavaFX SDK to your project libraries
Run Main.java




📁 Project Structure
Bookify/
├── src/
│   ├── main/
│   │   └── Main.java
│   ├── controllers/
│   ├── models/
│   ├── views/          # FXML files
│   └── config/
│       └── DBConnection.java
├── database/
│   └── bookify_schema.sql
├── resources/
│   └── styles/
└── README.md
