public class Osoby  implements java.io.Serializable{
private int Id;
private String Name,Surnname,StudentIndex;
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getSurnname() {
        return Surnname;
    }

    public String getStudentIndex() {
        return StudentIndex;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurnname(String surnname) {
        Surnname = surnname;
    }

    public void setStudentIndex(String studentIndex) {
        StudentIndex = studentIndex;
    }
}
