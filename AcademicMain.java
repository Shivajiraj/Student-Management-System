package p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/AcademicMain")

public class AcademicMain extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String student_id = request.getParameter("student_id");
		String branch = request.getParameter("branch");
		String year = request.getParameter("year");
		String section = request.getParameter("section");
		String cgpa = request.getParameter("cgpa");
		String admission_year = request.getParameter("admission_year");
		String roll_number = request.getParameter("roll_number");
		String attendance = request.getParameter("attendance");


		if (student_id == null || student_id.trim().isEmpty()) {

			response.sendRedirect("academic.html?status=error");
			return;
		}

		try {


			Class.forName("com.mysql.cj.jdbc.Driver");


			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/student_management_system",
					"root",
					"934600");

			PreparedStatement ps = con.prepareStatement(

					"INSERT INTO academic_details " +
					"(student_id, branch, year_of_study, section, cgpa) " +
					"VALUES (?, ?, ?, ?, ?)");


			ps.setString(1, student_id.trim());
			ps.setString(2, branch);
			ps.setString(3, year);
			ps.setString(4, section);

			ps.setDouble(5,
			cgpa != null && !cgpa.isEmpty()
			? Double.parseDouble(cgpa)
			: 0.0);


			int i = ps.executeUpdate();

			if(i > 0){

				response.sendRedirect("home.html?status=success");

			}
			else{

				response.sendRedirect("academic.html?status=error");

			}

			ps.close();
			con.close();
		} catch (Exception e) {

			e.printStackTrace();

			response.sendRedirect("academic.html?status=error");
		}
	}
}