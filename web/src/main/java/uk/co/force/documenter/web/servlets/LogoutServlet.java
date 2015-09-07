package uk.co.force.documenter.web.servlets;

import java.io.IOException;

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
		name = "Logout",
		urlPatterns = {"/logout"}
)
public class LogoutServlet extends HttpServlet {

	private Logger logger;
	
	@Override
	public void init() throws ServletException {
		
		logger = LoggerFactory.getLogger(this.getClass());	
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Logging out");
		String accessToken = (String)request.getSession().getAttribute(Constants.ACCESS_TOKEN);
		JSONObject authSession = (JSONObject)request.getSession().getAttribute(Constants.AUTH_SESSION);
		logger.info(authSession.toString());
		try {
			if(accessToken != null && authSession != null) {
				SFRestApi restApi = new SFRestApi();
				CloseableHttpClient client = HttpClients.createDefault();
			
				String url = authSession.getString(Constants.ENV_PARAM) + "/services/oauth2/revoke="+accessToken;
			
				JSONObject logoutResponse = restApi.getJSONObject(authSession.getString(Constants.ACCESS_TOKEN), url, client);
				logger.info("Logout response [{}]", logoutResponse.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}
		request.getSession().removeAttribute(Constants.AUTH_SESSION);
		request.getSession().removeAttribute(Constants.ACCESS_TOKEN);
		
		response.sendRedirect("/");
	}
}
