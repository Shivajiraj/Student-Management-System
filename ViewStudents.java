package p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
@WebServlet("/ViewStudents")

public class ViewStudents extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/student_management_system",
					"root",
					"934600");

			String sql =
					"SELECT b.student_id, b.full_name, b.dob, b.email, " +
					"b.phone, b.gender, " +
					"a.branch, a.year_of_study, a.cgpa " +
					"FROM basic_details b " +
					"LEFT JOIN academic_details a " +
					"ON b.student_id = a.student_id";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			out.println("<!DOCTYPE html>");

			out.println("<html>");

			out.println("<head>");

			out.println("<title>Student Records</title>");

			out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap' rel='stylesheet'>");

			out.println("<style>");

			out.println("*{margin:0;padding:0;box-sizing:border-box;font-family:Poppins,sans-serif;}");

			out.println("body{background:#0b1120;padding:40px;color:white;}");

			out.println(".container{width:95%;margin:auto;}");

			out.println(".top-bar{display:flex;justify-content:space-between;align-items:center;margin-bottom:35px;}");

			out.println("h1{font-size:42px;color:#60a5fa;font-weight:700;}");

			out.println(".home-btn{background:linear-gradient(135deg,#2563eb,#3b82f6);padding:14px 24px;color:white;text-decoration:none;border-radius:12px;font-weight:600;transition:0.3s;}");

			out.println(".home-btn:hover{transform:translateY(-4px);box-shadow:0px 10px 20px rgba(37,99,235,0.4);}");

			out.println(".table-card{background:#111827;padding:25px;border-radius:25px;box-shadow:0px 15px 35px rgba(0,0,0,0.35);overflow:auto;}");

			out.println("table{width:100%;border-collapse:collapse;background:white;border-radius:20px;overflow:hidden;color:#111827;}");

			out.println("th{background:#2563eb;color:white;padding:20px;font-size:15px;text-transform:uppercase;}");

			out.println("td{padding:18px;text-align:center;border-bottom:1px solid #e5e7eb;font-size:15px;}");

			out.println("tr:hover{background:#f8fafc;transition:0.3s;}");

			out.println(".action-box{display:flex;justify-content:center;}");

			out.println(".delete-btn{text-decoration:none;background:#ef4444;color:white;padding:10px 16px;border-radius:10px;font-size:14px;font-weight:600;transition:0.3s;}");

			out.println(".delete-btn:hover{background:#dc2626;transform:scale(1.05);}");

			out.println(".empty{padding:40px;text-align:center;font-size:20px;color:#94a3b8;}");

			out.println("@media(max-width:1100px){table{min-width:1100px;}}");

			out.println("</style>");

			out.println("</head>");

			out.println("<body>");

			out.println("<div class='container'>");

			out.println("<div class='top-bar'>");

			out.println("<h1>Student Records</h1>");

			out.println("<a href='home.html' class='home-btn'>⬅ Back Home</a>");

			out.println("</div>");

			out.println("<div class='table-card'>");

			out.println("<table>");

			out.println("<tr>");

			out.println("<th>Student ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>DOB</th>");
			out.println("<th>Email</th>");
			out.println("<th>Phone</th>");
			out.println("<th>Gender</th>");
			out.println("<th>Branch</th>");
			out.println("<th>Year</th>");
			out.println("<th>CGPA</th>");
			out.println("<th>Actions</th>");

			out.println("</tr>");

			boolean hasData = false;

			while (rs.next()) {

				hasData = true;

				String studentId = rs.getString("student_id");

				out.println("<tr>");

				out.println("<td>" + studentId + "</td>");

				out.println("<td>" + rs.getString("full_name") + "</td>");

				out.println("<td>" + rs.getString("dob") + "</td>");

				out.println("<td>" + rs.getString("email") + "</td>");

				out.println("<td>" + rs.getString("phone") + "</td>");

				out.println("<td>" + rs.getString("gender") + "</td>");

				out.println("<td>" + rs.getString("branch") + "</td>");

				out.println("<td>" + rs.getString("year_of_study") + "</td>");

				out.println("<td>" + rs.getString("cgpa") + "</td>");

				out.println("<td>");

				out.println("<div class='action-box'>");

				out.println("<a class='delete-btn' href='DeleteStudent?id="
						+ studentId
						+ "' onclick=\"return confirm('Delete this student?')\">Delete</a>");

				out.println("</div>");

				out.println("</td>");

				out.println("</tr>");
			}

			if (!hasData) {

				out.println("<tr>");

				out.println("<td colspan='10' class='empty'>No Student Records Found</td>");

				out.println("</tr>");
			}

			out.println("</table>");

			out.println("</div>");

			out.println("</div>");

			out.println("</body>");

			out.println("</html>");

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {

			out.println(e);
		}
	}
}