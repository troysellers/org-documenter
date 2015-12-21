package uk.co.force.documenter.web.servlets;

import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

@WebServlet(
		name = "Overview",
		urlPatterns = {"/app/overview"}
)
public class OverviewServlet extends BaseServlet {


	protected JSONObject execute() {
		
		JSONObject retval = new JSONObject();
		
		retval.put("SimpleMessage", "We really need to think about what the overview should be");
		
		return retval;
	}
}
