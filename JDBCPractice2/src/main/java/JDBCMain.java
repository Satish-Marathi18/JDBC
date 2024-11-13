import java.sql.Connection;
import java.sql.SQLException;

public class JDBCMain {
    public static void main(String[] args) {
        JDBCConnection jdbc = new JDBCConnection();
//        try(Connection con = jdbc.getConnection()) {
//            System.out.println("Connected to database "+con);
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//        jdbc.createTable();
//        String profilePicture= "C:\\Users\\SATISH\\Videos\\WhatsApp Video\\VID-20200117-WA0013.mp4";
//        Student s1 = new Student("4MK20CS037","Satish Marathi",8,22,"Male",profilePicture);
//        jdbc.createStudent(s1);

        jdbc.getStudent("4MK20CS037");
    }
}
