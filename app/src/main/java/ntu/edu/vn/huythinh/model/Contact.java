package ntu.edu.vn.huythinh.model;

public class Contact {
    String name;
    String dob;
    String number;
    String from;

    public Contact() {
    }

    public Contact(String name, String dob, String number, String from) {
        this.name = name;
        this.dob = dob;
        this.number = number;
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
