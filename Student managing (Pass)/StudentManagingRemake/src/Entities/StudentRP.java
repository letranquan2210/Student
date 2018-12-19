package Entities;

public class StudentRP extends Student {

    private int totalJava;
    private int totalNet;
    private int totalC;

    public StudentRP() {
    }

    public int getTotalJava() {
        return totalJava;
    }

    public void setTotalJava(int totalJava) {
        this.totalJava = totalJava;
    }

    public int getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(int totalNet) {
        this.totalNet = totalNet;
    }

    public int getTotalC() {
        return totalC;
    }

    public void setTotalC(int totalC) {
        this.totalC = totalC;
    }
}
