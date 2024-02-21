# CLASS MANAGEMENT APP

#### Description:

`Class Management App` is an app  made with Java created to manage students data. It provides the user the capability to perform CRUD operations
(Create, Read, Update, Delete), the menu interface is terminal based.

1. **Students.java**:

   - Defines the `Students` class, representing student entities.
   - Holds data such as `id_student`, `Name`, `PhoneNr`, `BirthDate`, and `Nota_final`.

2. **StudentsCRUD.java**:

   - Handles CRUD operations related to the `Students` class.

3. **DB.java**:

   - Connects to the MySQL database and ensures the "Students" (students) table is set-up

5. **Menu.java**:

   - Manages the user interface of the application.
   - Provides methods to guide users through adding, removing, updating, displaying, and searching students, as well as viewing class averages and students with highest/lowest grades.

6. **Program.java**:
   - Starting point of the application.
   - Initializes the database connection, CRUD operations, and the user interface.
