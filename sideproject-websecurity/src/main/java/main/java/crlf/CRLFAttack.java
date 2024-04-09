package main.java.crlf;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/search")
public class CRLFAttack extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/html");       
        // Get the query parameter
        String query = request.getParameter("query");
        System.out.println(query);
        // Check for CRLF characters in the query parameter
        if (query != null && (query.contains("%0d") || query.contains("%0a"))) {
        	System.out.println(query);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        } else {
            // Proceed with normal processing
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Search Results</h2>");
            out.println("<p>Query: " + query + "</p>");
            out.println("</body></html>");
        }
    }
}
