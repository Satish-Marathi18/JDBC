import java.io.*;
import java.sql.*;

public class JDBCExample {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employees";
        String userName= "root";
        String password = "Satish@123";
        return DriverManager.getConnection(url,userName,password);
    }

    public void createTable() {
        String query = "create table emp(id varchar(6) primary key,name varchar(15) not null,salary int(6))";
        try(Connection con =getConnection();
            Statement stmt = con.createStatement();){
            int affected = stmt.executeUpdate(query);
            System.out.println(affected+" rows affected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createEmployee(Employee emp) {
        String query = "insert into emp (id,name, salary,emp_image,file_name) values (?,?,?,?,?)";
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(query);){
            File f = new File(emp.getEmp_image());
            FileInputStream inputStream = new FileInputStream(f);

            stmt.setInt(1, emp.getId());
            stmt.setString(2, emp.getName());
            stmt.setDouble(3, emp.getSalary());
            stmt.setBinaryStream(4, inputStream, (int) f.length());
            stmt.setString(5, f.getName());

            System.out.println("Employee created successfully "+stmt.executeUpdate()+" rows affected");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getEmployees(){
        String query = "select * from emp";
        try(Connection con = getConnection();
        Statement stmt = con.createStatement();){
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               double salary = rs.getDouble("salary");
               InputStream inputStream = rs.getBinaryStream("emp_image");
               String fileName = rs.getString("file_name");

               String path = "C:\\satish\\BridgeLabz\\JDBCPractice1\\src\\main\\resources\\";
                FileOutputStream outputStream = new FileOutputStream(path+fileName);
                byte[] buffer = new byte[1024];
                int bytesRead = -1;
                while((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                System.out.println("Id:"+id+"   Name:"+name+"   Salary:"+salary);
                System.out.println("Image "+fileName+" is stored inside "+path);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(int id) {
        String query = "delete from emp where id = ?";
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(query);) {
            stmt.setInt(1, id);
            System.out.println("Employee deleted successfully "+stmt.executeUpdate()+" rows affected");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
