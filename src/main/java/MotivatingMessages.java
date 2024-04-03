import java.sql.*;

public class MotivatingMessages {
public static void main(String[] args) throws SQLException {
    try {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/motivating_messages", "root", "kadekopsid644088");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from motivating_messages");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("message_content"));
        }

    } catch (Exception e){
        e.printStackTrace();
    }
}

}

