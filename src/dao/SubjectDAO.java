package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDAO extends DAO {
    public List<Subject> getAllSubjects() throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection con = getConnection();

        String sql = "SELECT CD, NAME FROM SUBJECT ORDER BY CD";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject s = new Subject();
            s.setCode(rs.getString("CD"));
            s.setName(rs.getString("NAME"));
            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

 // 既存の科目コードを検索
    public Subject find(String cd) {
        Subject subject = null;
        String sql = "SELECT * FROM subject WHERE cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cd);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setCode(rs.getString("cd"));
                subject.setName(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    // 新規登録
    public void insert(Subject subject) {
        String sql = "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("INSERT実行: " + subject.getCode() + ", " + subject.getName() + ", " + subject.getSchoolCode());

            ps.setString(1, subject.getCode());
            ps.setString(2, subject.getName());
            ps.setString(3, subject.getSchoolCode());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //科目削除
    public void delete(String subjectCd) {
        String sql = "DELETE FROM subject WHERE cd = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subjectCd);
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
