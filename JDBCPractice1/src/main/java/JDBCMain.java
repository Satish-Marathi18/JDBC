import java.sql.Connection;
import java.sql.SQLException;

public class JDBCMain {
    public static void main(String[] args) {
        JDBCExample jdbc = new JDBCExample();
//        try {
//            Connection con = jdbc.getConnection();
//            System.out.println("Connected to database");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        jdbc.createTable();
//        jdbc.createEmployee(new Employee(1,"Satish Marathi",50000.0, "C:\\Users\\SATISH\\Pictures\\Screenshots\\Screenshot 2024-01-13 213054.png"));
        jdbc.getEmployees();
//        jdbc.deleteEmployee(1);
    }
}
