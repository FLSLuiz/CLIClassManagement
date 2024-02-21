package ClassManagement;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class DB
{
    private final String connectionString;

    public DB(String connectionString)
    {
        this.connectionString = connectionString;
    }

    public Connection GetConnection() throws SQLException
    {
        return DriverManager.getConnection(connectionString);
    }
    public void CreatedDB() throws SQLException
    {
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement())
        {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS studentdb ("
                    + "id_student INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Name VARCHAR(125) NOT NULL, "
                    + "PhoneNr VARCHAR(10) NOT NULL, "
                    + "BirthDate VARCHAR(10) NOT NULL, "
                    + "FinalGrade FLOAT NOT NULL)");
        }
    }
}
