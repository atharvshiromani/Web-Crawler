package webcrawler;


import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class PaytmDataUrl extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = -551929838595016744L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		History history=new History();
		
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String starttime = sdf1.format(date);
		history.setStarttime(starttime);
		System.out.println(starttime);
		response.setContentType("text/html");
		String url = request.getParameter("url");
		System.out.println(url);
		try {
			String res=JsonReader1.getPaytmDataFromPage(url);
			System.out.println(res);
			history.setLasturl(res);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date endDate = new Date();
		String endtime = sdf1.format(endDate);
		history.setEndtime(endtime);
		try {
			new DatabaseUtil().saveHistory(history);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(request.getParameter(url)==null) {
			String message = "Great You Have Sucessfully Completed The Process";
			response.sendRedirect("login.jsp?message=" + URLEncoder.encode(message, "UTF-8"));;
			System.out.println("Great You Have Sucessfully Completed The Process");
		
		
		
		
//		request.setAttribute("message", "Great You Have Sucessfully Completed The Process");
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
//		dispatcher.forward(request, response);
	}
		
		else {
			String message = "Sorry some interruption occured!";
			response.sendRedirect("login.jsp?message=" + URLEncoder.encode(message, "UTF-8"));;
		System.out.println("Sorry some interruption occured!");
		return;
		}
		

	}
}