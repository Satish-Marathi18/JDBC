import java.io.*;
import java.sql.*;

public class JDBCConnection {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "Satish@123";
        return DriverManager.getConnection(url, username,password);
    }

    public void createTable() {
        String query = "create table student(usn varchar(10) primary key, name varchar(20) not null, sem int(1) not null,age int(3) not null,gender varchar(10) not null, profile_picture longblob)";
        try(Connection con = getConnection();
            Statement stmt = con.createStatement();
        ){
            System.out.println("Table created "+stmt.executeUpdate(query)+" rows affected");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void createStudent(Student s) {
        String query = "insert into student(usn,name,sem,age,gender,profile_picture,file_name) values (?,?,?,?,?,?,?)";
        try(Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query)){
            File f = new File(s.getProfilePicture());
            FileInputStream inputStream = new FileInputStream(f);

            pstmt.setString(1,s.getUsn());
            pstmt.setString(2,s.getName());
            pstmt.setInt(3,s.getSem());
            pstmt.setInt(4,s.getAge());
            pstmt.setString(5,s.getGender());
            pstmt.setBinaryStream(6,inputStream,f.length());
            pstmt.setString(7,f.getName());
            System.out.println("Student created "+pstmt.executeUpdate()+" rows affected");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getStudent(String USN) {
        String query = "select * from student where usn = ?";
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1,USN);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String usn = rs.getString("usn");
                String name = rs.getString("name");
                int sem = rs.getInt("sem");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                InputStream is = rs.getBinaryStream("profile_picture");
                String fileName = rs.getString("file_name");

                String path = "C:\\satish\\BridgeLabz\\JDBCPractice2\\src\\main\\resources"+fileName;
                FileOutputStream fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];

                int bytesRead = -1;
                while((bytesRead = is.read(buffer)) != -1){
                    fos.write(buffer,0,bytesRead);
                }
                System.out.println("USN: "+usn);
                System.out.println("Name: "+name);
                System.out.println("Sem: "+sem);
                System.out.println("Age: "+age);
                System.out.println("Gender: "+gender);
                System.out.println("Profilre pictured copied in the path: "+path);
            }

        }catch(SQLException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
