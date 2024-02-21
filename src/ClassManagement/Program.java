package ClassManagement;
import ClassManagement.DB;
import ClassManagement.Menu;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args) throws SQLException {
        // Variable containing the connection string
        String connectionString = "jdbc:mysql://localhost:3306/studentdb?user=root&password=Edimundo2";

        // Send data between different parts of the program
        DB database = new DB(connectionString);
        StudentsCRUD crud = new StudentsCRUD(database);
        Menu menu = new Menu(crud);

        try {
            database.CreatedDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        menu.ShowMenu();
    }
}

