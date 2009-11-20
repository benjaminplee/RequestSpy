package yardspoon.requestspy;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RequestServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Record tx by path
		
		respondWithDate(response);
	}

	private void respondWithDate(HttpServletResponse response)
			throws IOException {
		response.setContentType("text/plain");
		response.getWriter().println(new Date());
	}
}
