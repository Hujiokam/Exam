package bean;

public class ScoreView {
  private String studentNo;
  private String name;
  private String entYear;
  private String classNum;
  private String score1;
  private String score2;
  private String subjectName;
  private String subjectCd;

  public String getStudentNo() { return studentNo; }
  public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getEntYear() { return entYear; }
  public void setEntYear(String entYear) { this.entYear = entYear; }

  public String getClassNum() { return classNum; }
  public void setClassNum(String classNum) { this.classNum = classNum; }

  public String getScore1() { return score1; }
  public void setScore1(String score1) { this.score1 = score1; }

  public String getScore2() { return score2; }
  public void setScore2(String score2) { this.score2 = score2; }

  public String getSubjectName() {
	  return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getSubjectCd() {
    return subjectCd;
  }

  public void setSubjectCd(String subjectCd) {
    this.subjectCd = subjectCd;
  }
}
