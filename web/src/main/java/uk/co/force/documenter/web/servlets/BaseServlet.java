package uk.co.force.documenter.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
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

public abstract class BaseServlet extends HttpServlet {
	
	protected Logger logger;
	protected JSONObject authSession;
	protected SFRestApi restApi;
	protected CloseableHttpClient client;
	
	@Override
	public void init() throws ServletException {
		logger = LoggerFactory.getLogger(this.getClass());
	}	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		client = HttpClients.createDefault();
		restApi = new SFRestApi();
		
		authSession = (JSONObject)req.getSession().getAttribute(Constants.AUTH_SESSION);
		logger.info(authSession.toString());
		
		JSONObject retval = execute();
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(retval);
		writer.flush();
		writer.close();
	}
	
	abstract protected JSONObject execute();
}
