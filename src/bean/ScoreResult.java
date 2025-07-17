package bean;

public class ScoreResult {
    private String studentId;
    private String name;
    private String className;
    private String year;
    private String subject;

    private String subjectCode; // ←追加
    private String subjectName; // ←追加（科目名）

    private Integer score1;
    private Integer score2;
    private Integer score3;

    private Integer times;      // ←必要であれば追加（試験回数）
    private Integer point;      // ←必要であれば追加（得点）

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

    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }

    public String getSubjectName() { return subjectName; }  // ←追加
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public Integer getScore1() { return score1; }
    public void setScore1(Integer score1) { this.score1 = score1; }

    public Integer getScore2() { return score2; }
    public void setScore2(Integer score2) { this.score2 = score2; }

    public Integer getScore3() { return score3; }
    public void setScore3(Integer score3) { this.score3 = score3; }

    public Integer getTimes() { return times; }
    public void setTimes(Integer times) { this.times = times; }

    public Integer getPoint() { return point; }
    public void setPoint(Integer point) { this.point = point; }
}