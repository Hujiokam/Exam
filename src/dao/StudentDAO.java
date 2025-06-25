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



    // ←ここから下が search() の外にある

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
}
