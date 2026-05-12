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

@SuppressWarnings("serial")

@WebServlet("/DeleteStudent")

public class DeleteStudent extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String student_id = request.getParameter("id");

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
            	    "jdbc:mysql://trolley.proxy.rlwy.net:20416/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
            	    "root",
            	    "roasjGBKmitUikschrYkKXEvRavKOTsH"
            	);

			// Delete Academic Details First

			PreparedStatement ps1 = con.prepareStatement(

					"DELETE FROM academic_details WHERE student_id=?");

			ps1.setString(1, student_id);

			ps1.executeUpdate();

			// Delete Basic Details

			PreparedStatement ps2 = con.prepareStatement(

					"DELETE FROM basic_details WHERE student_id=?");

			ps2.setString(1, student_id);

			ps2.executeUpdate();

			ps1.close();
			ps2.close();

			con.close();

			// Refresh Automatically

			response.sendRedirect("ViewStudents");

		} catch(Exception e) {

			e.printStackTrace();
		}
	}
}
