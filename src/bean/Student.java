package bean;

public class Student {
    private String no;
    private String name;
    private String entYear;
    private String classNum;
    private boolean isAttend;

    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEntYear() {
        return entYear;
    }
    public void setEntYear(String entYear) {
        this.entYear = entYear;
    }

    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public boolean isAttend() {
        return isAttend;
    }
    public void setAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }
}
