public class Student {
    private String usn;
    private String name;
    private int sem;
    private int age;
    private String gender;
    private String profilePicture;

    public Student(String usn, String name, int sem, int age, String gender, String profilePicture) {
        this.usn = usn;
        this.name = name;
        this.sem = sem;
        this.age = age;
        this.gender = gender;
        this.profilePicture = profilePicture;
    }

    public String getUsn() {
        return usn;
    }

    public String getName() {
        return name;
    }

    public int getSem() {
        return sem;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
