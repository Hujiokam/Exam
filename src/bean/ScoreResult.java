package bean;

public class ScoreResult {
    private String studentId;
    private String name;
    private String className;
    private String year;
    private String subject;

    private int score1;
    private int score2;
    private int score3;

    // --- getter & setter ---
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public int getScore1() { return score1; }
    public void setScore1(int score1) { this.score1 = score1; }

    public int getScore2() { return score2; }
    public void setScore2(int score2) { this.score2 = score2; }

    public int getScore3() { return score3; }
    public void setScore3(int score3) { this.score3 = score3; }
}