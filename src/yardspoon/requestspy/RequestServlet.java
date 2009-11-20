package yardspoon.requestspy;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RequestServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(RequestServlet.class.getName());
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.info("Spying request to " + request.getPathInfo());
		
		SpiedRequest spiedRequest = new SpiedRequest();
		spiedRequest.setCaller(request.getRemoteAddr());
		spiedRequest.setMethod(request.getMethod());
		spiedRequest.setPath(request.getPathInfo());
		spiedRequest.setWhen(new Date());
		
		log.fine("Saving spied request: " + spiedRequest.toString());
		
		PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
		
		try {
			persistenceManager.makePersistent(spiedRequest);
		} finally {
			persistenceManager.close();
		}
		
		log.fine("Responding to spied request");
		
		respondWithDate(response);
	}

	private void respondWithDate(HttpServletResponse response)
			throws IOException {
		response.setContentType("text/plain");
		response.getWriter().println(new Date());
	}
}
