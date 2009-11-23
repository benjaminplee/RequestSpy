package yardspoon.requestspy;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SpyServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(SpyServlet.class.getName());
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		String spiedPath = request.getPathInfo();
		log.info("Spying requests for path: " + spiedPath);
		
		PersistenceManager persistenceManager = null;
		Query query = null;
		List<SpiedRequest> results = null;

		try {
			persistenceManager = PMF.get().getPersistenceManager();
			query = persistenceManager.newQuery(SpiedRequest.class);

			query.setFilter("path == spiedPath");
		    query.setOrdering("when desc");
		    query.declareParameters("String spiedPath");

			results = (List<SpiedRequest>) query.execute(spiedPath);
			
	        if (results.iterator().hasNext()) {
	            log.fine("Found " + results.size() + " spied requests for: " + spiedPath);
	        } else {
	            log.fine("No Spied Requests found for: " + spiedPath);
	        }
	    } finally {
	    	if(query != null) { query.closeAll(); }
	    	if(persistenceManager != null) { persistenceManager.close(); }
	    }
		
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/spy.jsp");
	    try {
	    	request.setAttribute("spiedPath", spiedPath);
	    	request.setAttribute("spiedRequests", results);
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			log.warning(e.getMessage());
			
			response.setContentType("text/plain");
			response.getWriter().println("Error retrieving Spy for path: " + spiedPath);
		}
	}
}
