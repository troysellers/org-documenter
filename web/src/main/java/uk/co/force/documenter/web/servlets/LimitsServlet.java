package uk.co.force.documenter.web.servlets;

import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import uk.co.force.documenter.common.Constants;

@WebServlet(
		name = "LimitsServlet",
		urlPatterns = {"/app/limits"}
)
public class LimitsServlet extends BaseServlet {

	@Override
	protected JSONObject execute() {
		
		JSONObject retval = new JSONObject();
		
		// limits
		String limitsUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/v35.0/limits";
		JSONObject limitsGet = restApi.getJSONObject(authSession.getString(Constants.ACCESS_TOKEN), limitsUrl, client);
		logger.info(limitsGet.toString());
		retval.put("limits", limitsGet);
		
		return retval;
	}
	
	
}
