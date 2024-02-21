package ClassManagement;
import ClassManagement.DB;
import ClassManagement.Students;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class StudentsCRUD
{
    public final DB _database;
    public StudentsCRUD(DB database)
    {
        _database = database;
    }

    public void Add(Students students) throws SQLException
    {
        try(Connection connection = _database.GetConnection())
        {
            String query = "INSERT INTO studentdb (id_student, Name, PhoneNr, BirthDate, FinalGrade)" + "VALUES (?, ?, ?, ?, ?)";
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                preparedStatement.setInt(1, students.getId_Student());
                preparedStatement.setString(2, students.getName());
                preparedStatement.setString(3, students.getPhoneNr());
                preparedStatement.setString(4, students.getBirthDate());
                preparedStatement.setFloat(5, students.getFinalGrade());
                preparedStatement.executeUpdate();
                connection.commit();
            }
            catch (SQLException ex)
            {
                connection.rollback();
                throw ex;
            }
        }
    }
    public void Remove(int studentId) throws SQLException {
        try (Connection connection = _database.GetConnection()) {
            connection.setAutoCommit(false);
            String query = "DELETE FROM studentdb WHERE id_student = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            }
        }
    }
    public void Update(Students students) throws SQLException
    {
        try (Connection connection = _database.GetConnection())
        {
            connection.setAutoCommit(false);
            String query = "UPDATE studentdb SET Name = ?, PhoneNr = ?, BirthDate = ?, FinalGrade = ? " +
                    "WHERE id_student = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                preparedStatement.setString(1, students.getName());
                preparedStatement.setString(2, students.getPhoneNr());
                preparedStatement.setString(3, students.getBirthDate());
                preparedStatement.setFloat(4, students.getFinalGrade());
                preparedStatement.setInt(5, students.getId_Student());
                preparedStatement.executeUpdate();
                connection.commit();
            }
            catch (SQLException ex)
            {
                connection.rollback();
                throw ex;
            }
        }
    }
    public List<Students> GetAll() throws SQLException {
        List<Students> studentsList = new ArrayList<>();
        try (Connection connection = _database.GetConnection()) {
            String query = "SELECT * FROM studentdb";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Students student = new Students();
                        student.setId_Student(resultSet.getInt("id_student"));
                        student.setName(resultSet.getString("Name"));
                        student.setPhoneNr(resultSet.getString("PhoneNr"));
                        student.setBirthDate(resultSet.getString("BirthDate"));
                        student.setFinalGrade(resultSet.getFloat("FinalGrade"));
                        studentsList.add(student);
                    }
                }
            }
        }
        return studentsList;
    }

    public Students GetById(int id_student) throws SQLException {
        Students students = new Students();
        try (Connection connection = _database.GetConnection()) {
            String query = "SELECT * FROM studentdb WHERE id_student = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id_student);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next())
                    {
                        students.setId_Student(resultSet.getInt("id_student"));
                        students.setName(resultSet.getString("Name"));
                        students.setPhoneNr(resultSet.getString("PhoneNr"));
                        students.setBirthDate(resultSet.getString("BirthDate"));
                        students.setFinalGrade(resultSet.getFloat("FinalGrade"));
                    }
                }
            }
        }
        return students;
    }

    public double ClassAvg () throws SQLException
    {
        List<Students> students = GetAll();
        return students.stream().mapToDouble(Students::getFinalGrade).average().orElse(0f);
    }

    public List<Students> GetHighestGrade() throws SQLException
    {
        List<Students> students = GetAll();
        float highestGrade = students.stream().map(Students::getFinalGrade).max(Float::compare).orElse(0f);
        return students.stream().filter(s -> s.getFinalGrade() == highestGrade).toList();
    }

    public List<Students> GetLowestGrade() throws SQLException
    {
        List<Students> students = GetAll();
        float lowestGrade = students.stream().map(Students::getFinalGrade).min(Float::compare).orElse(0f);
        return students.stream().filter(s -> s.getFinalGrade() == lowestGrade).toList();
    }
}
