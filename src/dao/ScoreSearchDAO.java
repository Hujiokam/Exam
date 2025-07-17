package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ScoreResult;

public class ScoreSearchDAO {
    private static final String URL = "jdbc:h2:tcp://localhost/~/java-kadai";
    private static final String USER = "sa";
    private static final String PASS = "";

    // 科目別検索（1〜3回の得点をまとめる）
    public List<ScoreResult> searchBySubject(String year, String classNum, String subjectCode) {
        List<ScoreResult> list = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return list;
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("s.NO AS student_id, ");
        sql.append("s.NAME, ");
        sql.append("s.CLASS_NUM AS class_num, ");
        sql.append("s.ENT_YEAR AS ent_year, ");
        sql.append("sub.NAME AS subject_name, ");
        sql.append("t.NO AS times, ");
        sql.append("t.POINT AS score ");
        sql.append("FROM STUDENT s ");
        sql.append("LEFT JOIN TEST t ON s.NO = t.STUDENT_NO ");
        sql.append("LEFT JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD ");
        sql.append("WHERE 1=1 ");

        List<Object> params = new ArrayList<>();
        if (year != null && !year.isEmpty()) {
            sql.append("AND s.ENT_YEAR = ? ");
            params.add(year);
        }
        if (classNum != null && !classNum.isEmpty()) {
            sql.append("AND s.CLASS_NUM = ? ");
            params.add(classNum);
        }
        if (subjectCode != null && !subjectCode.isEmpty()) {
            sql.append("AND t.SUBJECT_CD = ? ");
            params.add(subjectCode);
        }

        sql.append("ORDER BY s.NO, t.NO");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            String currentStudentId = null;
            ScoreResult current = null;

            while (rs.next()) {
                String studentId = rs.getString("student_id");

                if (!studentId.equals(currentStudentId)) {
                    current = new ScoreResult();
                    current.setStudentId(studentId);
                    current.setName(rs.getString("name"));
                    current.setClassName(rs.getString("class_num"));
                    current.setYear(rs.getString("ent_year"));
                    current.setSubject(rs.getString("subject_name"));
                    list.add(current);
                    currentStudentId = studentId;
                }

                String times = rs.getString("times");
                int score = rs.getInt("score");

                if ("1".equals(times)) current.setScore1(score);
                else if ("2".equals(times)) current.setScore2(score);
                else if ("3".equals(times)) current.setScore3(score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 学生番号による検索（科目コード・科目名・回数・点数をまとめて取得）
    public List<ScoreResult> searchByStudentId(String studentId) {
        List<ScoreResult> list = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return list;
        }

        String sql =
            "SELECT s.NO AS student_id, s.NAME, s.CLASS_NUM AS class_num, " +
            "s.ENT_YEAR AS ent_year, sub.NAME AS subject_name, sub.CD AS subject_code, " +
            "t.NO AS times, t.POINT AS score " +
            "FROM STUDENT s " +
            "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO " +
            "LEFT JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD " +
            "WHERE s.NO = ? " +
            "ORDER BY sub.CD, t.NO";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId.trim());
            ResultSet rs = ps.executeQuery();

            String currentSubject = null;
            ScoreResult current = null;

            while (rs.next()) {
                String subject = rs.getString("subject_name");
                if (subject == null) subject = "-";

                String subjectCode = rs.getString("subject_code");

                if (!subject.equals(currentSubject)) {
                    current = new ScoreResult();
                    current.setStudentId(rs.getString("student_id"));
                    current.setName(rs.getString("name"));
                    current.setClassName(rs.getString("class_num"));
                    current.setYear(rs.getString("ent_year"));
                    current.setSubject(subject);
                    current.setSubjectCode(subjectCode);
                    list.add(current);
                    currentSubject = subject;
                }

                String times = rs.getString("times");
                int score = rs.getInt("score");

                if ("1".equals(times)) current.setScore1(score);
                else if ("2".equals(times)) current.setScore2(score);
                else if ("3".equals(times)) current.setScore3(score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}