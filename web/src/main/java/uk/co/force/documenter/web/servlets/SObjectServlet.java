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
		name = "SOBjectServlet",
		urlPatterns = {"/app/sobjects"}
)
public class SObjectServlet extends BaseServlet {

	@Override
	protected JSONObject execute() {
		
		JSONObject retval = new JSONObject();
		
		String sobjectUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/"+Constants.API_VERSION+"/sobjects";
		JSONObject sobjectGet = restApi.getJSONObject(authSession.getString(Constants.ACCESS_TOKEN), sobjectUrl, client);
		logger.info(sobjectGet.toString());
		retval.put("sobjects", sobjectGet);
		
		return retval;
	}

	
}
