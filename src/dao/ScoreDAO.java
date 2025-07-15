package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Score;

public class ScoreDAO {
    private static final String URL = "jdbc:h2:tcp://localhost/~/java-kadai";
    private static final String USER = "sa";
    private static final String PASS = "";

    // 🔍 検索メソッド
    public List<Score> search(String year, String classNum, String subjectCode, String times) {
        List<Score> list = new ArrayList<>();

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
        sql.append("t.POINT AS score, ");
        sql.append("t.NO AS times ");
        sql.append("FROM TEST t ");
        sql.append("JOIN STUDENT s ON t.STUDENT_NO = s.NO ");
        sql.append("JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD ");
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
        if (times != null && !times.isEmpty()) {
            sql.append("AND t.NO = ? ");
            params.add(times);
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Score s = new Score();
                s.setStudentId(rs.getString("student_id"));
                s.setName(rs.getString("name"));
                s.setClassName(rs.getString("class_num"));
                s.setYear(rs.getString("ent_year"));
                s.setSubject(rs.getString("subject_name"));
                s.setScore(rs.getInt("score"));
                s.setTimes(rs.getString("times"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 📝 登録（更新）メソッド
    public void register(List<Score> scoreList, String subjectCode, String times) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String sql = "UPDATE TEST SET POINT = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Score score : scoreList) {
                ps.setInt(1, score.getScore());
                ps.setString(2, score.getStudentId());
                ps.setString(3, subjectCode);
                ps.setString(4, times);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}