package uk.co.force.documenter.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.force.documenter.common.Constants;
import uk.co.force.documenter.common.SFRestApi;

@WebServlet(
		name = "Overview",
		urlPatterns = {"/app/overview"}
)
public class OverviewServlet extends HttpServlet {

	private Logger logger;
	
	@Override
	public void init() throws ServletException {
		logger = LoggerFactory.getLogger(this.getClass());
	}	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		CloseableHttpClient client = HttpClients.createDefault();
		SFRestApi restApi = new SFRestApi();
		
		JSONObject authSession = (JSONObject)req.getSession().getAttribute(Constants.AUTH_SESSION);
		JSONObject retval = new JSONObject();
		
		// get sObjects
		String sobjectUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/"+Constants.API_VERSION+"/sobjects";
		retval.put("sobjects", restApi.getJSONObject(authSession.getString(Constants.ACCESS_TOKEN), sobjectUrl, client));
	
		// get ApexClasses
		String apexManifestUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/"+Constants.API_VERSION+"/tooling/apexManifest";
		retval.put("apexManifest", restApi.getJSONArray(authSession.getString(Constants.ACCESS_TOKEN), apexManifestUrl, client));
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(retval);
		writer.flush();
		writer.close();
	}
}
