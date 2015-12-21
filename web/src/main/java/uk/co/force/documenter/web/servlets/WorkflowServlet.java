package uk.co.force.documenter.web.servlets;

import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import uk.co.force.documenter.common.Constants;

@WebServlet(
		name = "Workflows",
		urlPatterns = {"/app/workflows"}
)
public class WorkflowServlet extends BaseServlet {

	@Override
	protected JSONObject execute() {
		
		JSONObject retval = new JSONObject();
		
		// get Workflow Process
		String processUrl = authSession.getString(Constants.INSTANCE_URL) +"/services/data/v35.0/process/rules";
		JSONObject processGet = restApi.getJSONObject(authSession.getString(Constants.ACCESS_TOKEN), processUrl, client);
		logger.info(processGet.toString());
		retval.put("processes", processGet);
		
		return retval;
	}
}
