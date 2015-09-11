package uk.co.force.documenter.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
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
	private boolean isDev = false;
	
	private String SOBJECT_FILE = "src/test/mockCalls/sObjects.txt";
	private String APEX_MANIFEST_FILE = "src/test/mockCalls/apexManifest.txt";
	
	@Override
	public void init() throws ServletException {
		logger = LoggerFactory.getLogger(this.getClass());
		if(System.getenv("isDev") != null) {
			isDev = Boolean.valueOf(System.getenv("isDev"));
			logger.info("WE ARE RUNNING WITH DEVELOPMENT PARAMS");
		}
	}	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		CloseableHttpClient client = HttpClients.createDefault();
		SFRestApi restApi = new SFRestApi();
		
		JSONObject authSession = (JSONObject)req.getSession().getAttribute(Constants.AUTH_SESSION);
		JSONObject retval = new JSONObject();
		
		if(!isDev) {
			// get sObjects
			String sobjectUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/"+Constants.API_VERSION+"/sobjects";
			JSONObject sobjectGet = restApi.getJSONObject(authSession.getString(Constants.ACCESS_TOKEN), sobjectUrl, client);
			retval.put("sobjects", sobjectGet);
		
			//Files.write(Paths.get("src/test/mockCalls/sObjects.txt"), sobjectGet.toString().getBytes(), StandardOpenOption.CREATE);
		
			// get ApexClasses
			String apexManifestUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/"+Constants.API_VERSION+"/tooling/apexManifest";
			JSONArray apexManifestGet = restApi.getJSONArray(authSession.getString(Constants.ACCESS_TOKEN), apexManifestUrl, client);
			retval.put("apexManifest", apexManifestGet);
			
			//Files.write(Paths.get("src/test/mockCalls/apexManifest.txt"), apexManifestGet.toString().getBytes(), StandardOpenOption.CREATE);
		} else {
			List<String> sobjectLines = Files.readAllLines(Paths.get(SOBJECT_FILE));
			List<String> apexManifestLines = Files.readAllLines(Paths.get(APEX_MANIFEST_FILE));
			retval.put("sobjects", sobjectLines.get(0));
			retval.put("apexManifest", apexManifestLines.get(0));
		}
	
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(retval);
		writer.flush();
		writer.close();
	}
}
