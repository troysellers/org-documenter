package uk.co.force.documenter.web.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.force.documenter.common.Constants;

@WebServlet(
		name = "ApexClassServlet",
		urlPatterns = {"/app/apexClasses"}
)
public class ApexClassServlet extends BaseServlet {
	
	
	protected JSONObject execute() {
		
		JSONObject retval = new JSONObject();
		
		// get ApexClasses
		String apexManifestUrl = authSession.getString(Constants.INSTANCE_URL) + "/services/data/"+Constants.API_VERSION+"/tooling/apexManifest";
		JSONArray apexManifestGet = restApi.getJSONArray(authSession.getString(Constants.ACCESS_TOKEN), apexManifestUrl, client);
		logger.info(apexManifestGet.toString());
		List<JSONObject> apexClasses = new ArrayList<JSONObject>();
		
		
		for(int i=0 ; i< apexManifestGet.length() ; i++) {
			JSONObject jObj = apexManifestGet.getJSONObject(i);
			String objId = jObj.getString("id");
			String apexObjectGetURL = authSession.getString(Constants.INSTANCE_URL) + "/services/data/"+Constants.API_VERSION+"/tooling/sobjects/";
			if(jObj.getString("type").equals("CLASS")) {
				apexObjectGetURL += "ApexClass";
			} else if(jObj.getString("type").equals("TRIGGER")) {
				apexObjectGetURL += "ApexTrigger";
			} else {
				retval.put("error","There is an unexpected TYPE ["+jObj.getString("type")+"] returned from the ApexManifest call on the tooling API");
				break;
			}
			apexObjectGetURL += "/" + objId;
			logger.info(apexObjectGetURL);
			JSONObject temp = restApi.getJSONObject(authSession.getString(Constants.ACCESS_TOKEN), apexObjectGetURL, client);
			logger.info(temp.toString());
			apexClasses.add(temp);
		}
		
		logger.info("We have [{}] temps", apexClasses.size());
		
		retval.put("apexClasses", apexClasses);
		
		return retval;
	}
}
