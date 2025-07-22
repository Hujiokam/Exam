package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDAO extends DAO {

    public List<Student> search(String year, String classNum, String status) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND ");
        sql.append("FROM STUDENT WHERE 1=1 ");

        boolean isInitial = (year == null && classNum == null && status == null);

        if (!isInitial) {
            if (year != null && !year.equals("---")) {
                sql.append("AND ENT_YEAR = ? ");
            }
            if (classNum != null && !classNum.equals("---")) {
                sql.append("AND CLASS_NUM = ? ");
            }

            // 在学中条件（チェックあり: 1、チェックなし: 0）
            if (status != null) {
                sql.append("AND IS_ATTEND = 1 ");
            } else {
                sql.append("AND IS_ATTEND = 0 ");
            }
        }

        PreparedStatement st = con.prepareStatement(sql.toString());

        int idx = 1;
        if (!isInitial) {
            if (year != null && !year.equals("---")) {
                st.setString(idx++, year);
            }
            if (classNum != null && !classNum.equals("---")) {
                st.setString(idx++, classNum);
            }
        }

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Student s = new Student();
            s.setNo(rs.getString("NO"));
            s.setName(rs.getString("NAME"));
            s.setEntYear(rs.getString("ENT_YEAR"));
            s.setClassNum(rs.getString("CLASS_NUM"));
            s.setAttend(rs.getInt("IS_ATTEND") == 1);
            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 以下、他のメソッドも問題なし（必要に応じて編集）

    public List<String> getYears() throws Exception {
        List<String> years = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT DISTINCT ENT_YEAR FROM STUDENT ORDER BY ENT_YEAR";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            years.add(rs.getString("ENT_YEAR"));
        }
        rs.close();
        st.close();
        con.close();
        return years;
    }

    public List<String> getClasses() throws Exception {
        List<String> classes = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT DISTINCT CLASS_NUM FROM STUDENT ORDER BY CLASS_NUM";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            classes.add(rs.getString("CLASS_NUM"));
        }
        rs.close();
        st.close();
        con.close();
        return classes;
    }

    public void delete(String studentNo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("DELETE FROM STUDENT WHERE NO = ?");
            stmt.setString(1, studentNo);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, stmt, null);
        }
    }

    public Student find(String studentNo) {
        Student student = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE NO = ?");
            stmt.setString(1, studentNo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setNo(rs.getString("NO"));
                student.setName(rs.getString("NAME"));
                student.setEntYear(rs.getString("ENT_YEAR"));
                student.setClassNum(rs.getString("CLASS_NUM"));
                student.setAttend(rs.getInt("IS_ATTEND") == 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, stmt, rs);
        }

        return student;
    }
}
