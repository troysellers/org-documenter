package uk.co.force.documenter.web.servlets;

import javax.servlet.annotation.WebServlet;

import org.json.JSONArray;
import org.json.JSONObject;

import uk.co.force.documenter.common.Constants;

@WebServlet(
		name = "TabServlet",
		urlPatterns = {"/app/tabs"}
)
public class TabServlet extends BaseServlet{

	@Override
	protected JSONObject execute() {
		
		JSONObject retval = new JSONObject();
		
		// get TAbs
		String tabUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/"+Constants.API_VERSION + Constants.TABS;
		JSONArray tabGet = restApi.getJSONArray(authSession.getString(Constants.ACCESS_TOKEN), tabUrl, client);
		logger.info(tabGet.toString());
		retval.put("tabs", tabGet);
		
		return retval;
	}
	
	
}
