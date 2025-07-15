package bean;

import java.io.Serializable;

public class Score implements Serializable {
    private String studentId;    // 学籍番号
    private String name;         // 氏名
    private String className;    // クラス番号（例：131）
    private String subject;      // 科目名（例：国語）
    private String subjectCode;  // 科目コード（例：A02）←追加
    private String times;        // 回数（例：1）
    private String year;
    private int score;           // 得点


    public Score() {}

    // getter/setter
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectCode() {
        return subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTimes() {
        return times;
    }
    public void setTimes(String times) {
        this.times = times;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}