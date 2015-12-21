package uk.co.force.documenter.web.servlets;

import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import uk.co.force.documenter.common.Constants;

@WebServlet(
		name = "ChatterUsers",
		urlPatterns = {"/app/chatterUsers"}
)

public class ChatterUsersServlet extends BaseServlet {

	@Override
	protected JSONObject execute() {
		JSONObject retval = new JSONObject();

		// chatter users
		String chatterUsersUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/v35.0/chatter/users";
		JSONObject chatterUsersGet = restApi.getJSONObject(authSession.getString(Constants.ACCESS_TOKEN), chatterUsersUrl, client);
		logger.info(chatterUsersGet.toString());
		retval.put("chatterUsers", chatterUsersGet);	
		
		return retval;
	}
	

}
