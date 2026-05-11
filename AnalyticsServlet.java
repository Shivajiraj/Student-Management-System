package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AnalyticsServlet")
public class AnalyticsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_management_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "934600";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Connection con = null;
        try {
            // Load MySQL Driver
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Get all analytics data
            int totalStudents = getTotalStudents(con);
            int totalBranches = getTotalBranches(con);
            String topBranch = getTopBranch(con);
            double avgCgpa = getAverageCGPA(con);
            String branchData = getBranchData(con);
            String studentData = getLatestStudents(con);

            // Build JSON response
            String json = "{" +
                "\"totalStudents\":" + totalStudents + "," +
                "\"totalBranches\":" + totalBranches + "," +
                "\"topBranch\":\"" + topBranch + "\"," +
                "\"avgCgpa\":" + roundToTwo(avgCgpa) + "," +
                branchData + "," +
                studentData +
                "}";

            out.print(json);

        } catch (Exception e) {
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ================= HELPER METHODS =================

    private int getTotalStudents(Connection con) throws Exception {
        int total = 0;
        String query = "SELECT COUNT(*) FROM basic_details";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            total = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return total;
    }

    private int getTotalBranches(Connection con) throws Exception {
        int total = 0;
        String query = "SELECT COUNT(DISTINCT branch) FROM academic_details";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            total = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return total;
    }

    private String getTopBranch(Connection con) throws Exception {
        String topBranch = "None";
        String query = "SELECT branch, COUNT(*) as total FROM academic_details GROUP BY branch ORDER BY total DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            topBranch = rs.getString("branch");
        }
        rs.close();
        ps.close();
        return topBranch;
    }

    private double getAverageCGPA(Connection con) throws Exception {
        double avgCgpa = 0;
        String query = "SELECT AVG(cgpa) FROM academic_details";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            avgCgpa = rs.getDouble(1);
        }
        rs.close();
        ps.close();
        return avgCgpa;
    }

    private String getBranchData(Connection con) throws Exception {
        StringBuilder branchNames = new StringBuilder("[");
        StringBuilder branchCounts = new StringBuilder("[");

        String query = "SELECT branch, COUNT(*) as total FROM academic_details GROUP BY branch";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        boolean first = true;
        while (rs.next()) {
            if (!first) {
                branchNames.append(",");
                branchCounts.append(",");
            }
            branchNames.append("\"").append(rs.getString("branch")).append("\"");
            branchCounts.append(rs.getInt("total"));
            first = false;
        }

        branchNames.append("]");
        branchCounts.append("]");
        rs.close();
        ps.close();

        return "\"branchNames\":" + branchNames + ",\"branchCounts\":" + branchCounts;
    }

    private String getLatestStudents(Connection con) throws Exception {
        StringBuilder students = new StringBuilder("[");

        String query = "SELECT b.student_id, b.full_name, a.branch, a.cgpa " +
                      "FROM basic_details b " +
                      "JOIN academic_details a ON b.student_id = a.student_id " +
                      "ORDER BY b.student_id DESC LIMIT 5";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        boolean first = true;
        while (rs.next()) {
            if (!first) {
                students.append(",");
            }
            students.append("{")
                .append("\"id\":\"").append(rs.getInt("student_id")).append("\",")
                .append("\"name\":\"").append(rs.getString("full_name")).append("\",")
                .append("\"branch\":\"").append(rs.getString("branch")).append("\",")
                .append("\"cgpa\":").append(roundToTwo(rs.getDouble("cgpa")))
                .append("}");
            first = false;
        }

        students.append("]");
        rs.close();
        ps.close();

        return "\"students\":" + students.toString();
    }

    private double roundToTwo(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}