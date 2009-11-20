package yardspoon.requestspy;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SpyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		// Lookup tx to this URL
		
		// Render page with results
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Spy");
	}
}
