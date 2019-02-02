import org.apache.solr.common.SolrDocumentList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		SolrDocumentList doclist = (SolrDocumentList) Solr.solr();
		for (int i = 0; i < doclist.getNumFound(); i++) {
			response.getWriter().println("id:" + doclist.get(i).get("id") + "---title:" + doclist.get(i).get("title"));
		}
	}
}
