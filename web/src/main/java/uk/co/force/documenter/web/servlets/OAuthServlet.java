package uk.co.force.documenter.web.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.force.documenter.common.Constants;

@WebServlet(
		name = "OAuth",
		urlPatterns = {"/oauth", "/oauth/*"},
		initParams = {
			//clientId is 'Consumer Key', clientSecret is 'Consumer Secret' 
			@WebInitParam(name = "clientId", value="3MVG9Rd3qC6oMalVL_ZWet4ueYzdRKSsAR2MTYVXhS7baMsQsh.xAAfs.uaeawxHxypyh7ON256A0UXkDZ4Sc"),
			@WebInitParam(name="clientSecret", value="4538587340034276718"),
			// must match exactly Callback URL
			@WebInitParam(name="redirectUri", value="http://localhost:8080/oauth/_callback"),
		}
)
public class OAuthServlet extends HttpServlet {

	private Logger logger;
	private String clientId = null;
	private String clientSecret = null;
	private String redirectUri = null;
	private String authUrl = null;
	private String tokenUrl = null;
	
	@Override
	public void init() throws ServletException {
		
		logger = LoggerFactory.getLogger(this.getClass());
		
		clientId = this.getInitParameter("clientId");
		clientSecret = this.getInitParameter("clientSecret");
		redirectUri = this.getInitParameter("redirectUri");
		
		logger.info("Initialised with ID [{}] Secret [{}] Redirect [{}]", clientId, clientSecret, redirectUri);	
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getRequestURI().endsWith("_callback")) {
			
			String code = request.getParameter(Constants.CODE);
			String environment = "https://"+URLDecoder.decode(request.getParameter(Constants.STATE),"UTF-8");
			tokenUrl = environment + "/services/oauth2/token";
			CloseableHttpClient client = HttpClients.createDefault();
			try {
				
				RequestBuilder postBuilder = RequestBuilder.create(HttpPost.METHOD_NAME);
				
				postBuilder.addParameter(Constants.CODE, code);
				postBuilder.addParameter(Constants.GRANT_TYPE, "authorization_code");
				postBuilder.addParameter(Constants.CLIENT_ID, clientId);
				postBuilder.addParameter(Constants.CLIENT_SECRET, clientSecret);
				postBuilder.addParameter(Constants.REDIRECT_URI, redirectUri);
				
				postBuilder.setUri(tokenUrl);
	
				logger.info("Send to token URL [{}]",tokenUrl);

				CloseableHttpResponse res = client.execute(postBuilder.build());
				logger.info("STATUS [{}]", res.getStatusLine());
				
				HttpEntity entity = res.getEntity();
				JSONObject authSession = new JSONObject(new JSONTokener(entity.getContent()));
				authSession.put(Constants.ENV_PARAM, environment);
				EntityUtils.consume(entity);
				
				// set authSession
				request.getSession().setAttribute(Constants.AUTH_SESSION, authSession);
				request.getSession().setAttribute(Constants.ACCESS_TOKEN, authSession.getString(Constants.ACCESS_TOKEN));
				// set session timeout for 20 minutes
				request.getSession().setMaxInactiveInterval(20*60);
				response.sendRedirect("/web/home.jsp");
				
			} finally {
				client.close();
			}
		} else {
			response.sendRedirect("/"); // if we receive a different GET request
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String accessToken = (String)request.getSession().getAttribute(Constants.ACCESS_TOKEN);
		JSONObject authSession = (JSONObject)request.getSession().getAttribute(Constants.AUTH_SESSION);
		logger.info("OAuth Servlet request URL [{}] [{}]", request.getRequestURL(), accessToken);
		
		if(accessToken == null || authSession == null) {

			String environment =  request.getParameter(Constants.ENV_PARAM); // get SF env from request
			logger.info("Environment {}", environment);
		
			/* This is the Web Server OAuth Authentication Flow
			 * https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/intro_understanding_web_server_oauth_flow.htm
			 * 
			 * optional param State added to keep login environment for us
			 */
			authUrl = "https://" + environment + "/services/oauth2/authorize?"
						+ Constants.RESPONSE_TYPE + "=code&"
						+ Constants.CLIENT_ID + "=" + clientId 
						+ "&" + Constants.REDIRECT_URI + "=" + URLEncoder.encode(redirectUri,"UTF-8") 
						+ "&" + Constants.STATE + "=" + URLEncoder.encode(environment, "UTF-8");
			
			logger.info("Redirecting [{}]", authUrl);
			response.sendRedirect(authUrl);
			return;

		} else {
			response.sendRedirect("/web/home.jsp");
		}
	}

}
