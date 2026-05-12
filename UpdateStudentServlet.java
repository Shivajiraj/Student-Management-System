package p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String studentId =
        request.getParameter("student_id");

        String fullName =
        request.getParameter("full_name");

        String email =
        request.getParameter("email");

        String phone =
        request.getParameter("phone");

        String gender =
        request.getParameter("gender");

        String branch =
        request.getParameter("branch");

        String year =
        request.getParameter("year_of_study");

        String section =
        request.getParameter("section");

        String cgpa =
        request.getParameter("cgpa");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

           Connection con = DriverManager.getConnection(
            	    "jdbc:mysql://trolley.proxy.rlwy.net:20416/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
            	    "root",
            	    "roasjGBKmitUikschrYkKXEvRavKOTsH"
            	);

            // UPDATE BASIC DETAILS

            String q1 =
            "UPDATE basic_details SET " +
            "full_name=?, email=?, " +
            "phone=?, gender=? " +
            "WHERE student_id=?";

            PreparedStatement ps1 =
            con.prepareStatement(q1);

            ps1.setString(1, fullName);
            ps1.setString(2, email);
            ps1.setString(3, phone);
            ps1.setString(4, gender);
            ps1.setString(5, studentId);

            ps1.executeUpdate();

            // UPDATE ACADEMIC DETAILS

            String q2 =
            "UPDATE academic_details SET " +
            "branch=?, year_of_study=?, " +
            "section=?, cgpa=? " +
            "WHERE student_id=?";

            PreparedStatement ps2 =
            con.prepareStatement(q2);

            ps2.setString(1, branch);
            ps2.setString(2, year);
            ps2.setString(3, section);
            ps2.setString(4, cgpa);
            ps2.setString(5, studentId);

            ps2.executeUpdate();

            response.sendRedirect(
            "ViewStudents");

            con.close();

        } catch(Exception e){

            e.printStackTrace();
        }
    }
}
