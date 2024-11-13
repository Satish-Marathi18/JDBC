public class Employee {
    private int id;
    private String name;
    private double salary;
    private String emp_image;

    public Employee(int id, String name, double salary, String emp_image) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.emp_image = emp_image;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public String getEmp_image() {
        return emp_image;
    }
}
