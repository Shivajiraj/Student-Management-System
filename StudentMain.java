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

/**
 * Servlet implementation class StudentMain
 */
@WebServlet("/StudentMain")
public class StudentMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StudentMain() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();

		String student_id = request.getParameter("student_id");
		String full_name = request.getParameter("full_name");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system","root","934600");
			
			PreparedStatement ps = con.prepareStatement(

					"INSERT INTO basic_details "
					+ "(student_id, full_name, dob, email, phone, gender, address) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)"

				);
			
			ps.setString(1, student_id);
			ps.setString(2, full_name);
			ps.setString(3, dob);
			ps.setString(4, email);
			ps.setString(5, phone);
			ps.setString(6, gender);
			ps.setString(7, address);

			int i = ps.executeUpdate();

			if(i > 0){

				response.sendRedirect("academic.html");

			}
			else{

				out.println("Data Not Inserted");

			}

			
			ps.close();
			con.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
