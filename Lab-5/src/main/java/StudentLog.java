import javax.persistence.*;
public class StudentLog {
    private int Id;
    private String zad1, zad2, zad3, zad4, zad5, zad6;
    StudentLog(){}
    StudentLog(int id,String zad1,String zad2,String zad3,String zad4,String zad5, String zad6){
        this.Id =id;
        this.zad1 = zad1;
        this.zad2 = zad2;
        this.zad3 = zad3;
        this.zad4 = zad4;
        this.zad5 = zad5;
        this.zad6 = zad6;

    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setZad1(String zad1) {
        this.zad1 = zad1;
    }

    public String getZad1() {
        return zad1;
    }

    public void setZad2(String zad2) {
        this.zad2 = zad2;
    }

    public String getZad2() {
        return zad2;
    }

    public void setZad3(String zad3) {
        this.zad3 = zad3;
    }

    public String getZad3() {
        return zad3;
    }

    public void setZad4(String zad4) {
        this.zad4 = zad4;
    }

    public String getZad4() {
        return zad4;
    }

    public void setZad5(String zad5) {
        this.zad5 = zad5;
    }

    public String getZad5() {
        return zad5;
    }

    public void setZad6(String zad6) {
        this.zad6 = zad6;
    }

    public String getZad6() {
        return zad6;
    }
}

