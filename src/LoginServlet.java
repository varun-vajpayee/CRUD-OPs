
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		out.println("<title>Welcome To Kendo Validation</title>");
		out.println("<link rel=\"stylesheet\" href=\"Css/kendo.default.min.css\" />");
		out.println("<link rel=\"stylesheet\" href=\"Css/kendo.default.mobile.min.css\" />");
		out.println("<link rel=\"stylesheet\" href=\"Css/kendo.common.min.css\" />");
		out.println("<link rel=\"stylesheet\" href=\"Css/all.css\" />");

		// STORING DATA FROM THE HTML FORM
		String Name = request.getParameter("name");
		String Password = request.getParameter("password");
		String PhoneNumber = request.getParameter("telephone");

		out.print("Hello" + " " + Name);

		// PRINTING DATA ON THE CONSOLE FROM THE HTML FORM
		System.out.println(Name);
		System.out.println(Password);
		System.out.println(PhoneNumber);

		try {
			// CONNECTION TO DATABASE
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/Details", "postgres",
					"root")) {

				if (conn != null) {
					System.out.println("Connected to the database!");
					Statement stmnt = null;
					stmnt = conn.createStatement();

					// INSERTING DATA INTO THE DATABASE
					System.out.println("INSERTING DATA INTO THE DATATABASE");

					java.sql.PreparedStatement pst = null;
					String sql = "INSERT INTO public.\"RegistrationDetails\"(\"Name\",\"Password\",\"PhoneNumber\") VALUES (?, ?, ?)";
					pst = conn.prepareStatement(sql);
					pst.setString(1, Name);
					pst.setString(2, Password);
					pst.setString(3, PhoneNumber);
					pst.executeUpdate();
					System.out.println("DONE INSERTION");

					// UPDATE DATA INTO DATABASE
					// String sql2 = "UPDATE public.\"RegistrationDetails\" SET \"Name\" = 'Pablo
					// Escobar Gaviria ' WHERE \"Password\" = 'thgyhgtyhg13' ";
					// stmnt = conn.createStatement();
					// stmnt.execute(sql2);
					//
					// // DELETE DATA FROM DATABASE
					// String sql3 = "DELETE FROM public.\"RegistrationDetails\" WHERE \"Name\" =
					// 'vKira' ";
					// stmnt = conn.createStatement();
					// stmnt.execute(sql3);

					System.out.println("DONE ....!");

				} else

				{
					System.out.println("Failed to make connection!");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		out.println("<title>Welcome To Kendo Validation</title>");
		out.println("<link rel=\"stylesheet\" href=\"Css/kendo.default.min.css\" />");
		out.println("<link rel=\"stylesheet\" href=\"Css/kendo.default.mobile.min.css\" />");
		out.println("<link rel=\"stylesheet\" href=\"Css/kendo.common.min.css\" />");
		out.println("<link rel=\"stylesheet\" href=\"Css/all.css\" />");

		// STORING DATA FROM THE HTML FORM
		String Name = request.getParameter("name");

		try {
			// CONNECTION TO DATABASE
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/Details", "postgres",
					"root")) {

				if (conn != null) {
					System.out.println("Connected to the database!");
					Statement stmnt = null;
					stmnt = conn.createStatement();

					// READING DATA FROM THE DATABASE
					response.setContentType("text/html");

					System.out.println("READING DATA FROM THE DATATABASE");
					ResultSet rs = stmnt.executeQuery("SELECT * FROM public.\"RegistrationDetails\"");
					while (rs.next()) {
						out.println(rs.getString(1));
						out.println(rs.getString(2));
						out.println(rs.getString(3));
						System.out.println(" ");
						System.out.println(rs.getString(1));
						System.out.println(" ");
						System.out.println(rs.getString(2));
						System.out.println(" ");
						System.out.println(rs.getString(3));
						System.out.println(" ");

					}
					out.println();
					out.println("<div class=\"k-grid k-widget k-display-block\" style=\"height: 550px;\">");
					out.println(
							"<div class=\"k-grid-header\" style=\"padding-right: 17px;\"><div class=\"k-grid-header-wrap k-auto-scrollable\">");

					out.println(
							"<table align=\"center\" width=50% style=\"font-family:'Exo', sans-serif;border: 4px solid gray;color:#000000;\">");
					out.println("<tr><th>Name</th>" + "<th>Password</th>" + "<th>Phone Number</th>" + "</tr>");
					while (rs.next()) {

						out.println("<tr><td align=\"center\" width=\"10%\" >" + "<b>" + rs.getString(1) + "</b>"
								+ "</td>");
						out.println("<td align=\"center\" width=\"25%\">" + "<b>" + rs.getString(2) + "</b>" + "</td>"
								+ "<td align=\"center\" width=\"25%\">" + "<b>" + rs.getString(3) + "</b>" + "</td>"
								+ "</tr>");
						out.println(rs.getString(1));
						System.out.println(rs.getString(1));
						System.out.println(rs.getString(2));
						System.out.println(rs.getString(3));

					}

				} else

				{
					System.out.println("Failed to make connection!");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
