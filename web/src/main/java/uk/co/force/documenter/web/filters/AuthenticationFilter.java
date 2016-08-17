package uk.co.force.documenter.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.force.documenter.common.Constants;

@WebFilter(
		filterName="AuthenticationFilter",
		displayName="Authentication Filter",
		urlPatterns={"/app/*"})
public class AuthenticationFilter implements Filter {

	private Logger logger;
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		logger.info("Trying to figure out if request is authenticated [{}]", request.getRequestURL());
		JSONObject authResponse = (JSONObject)request.getSession().getAttribute(Constants.AUTH_SESSION);
		if(authResponse != null && authResponse.getString(Constants.ACCESS_TOKEN) != null) {
			logger.debug("Have found an access token and authentication session");
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			logger.debug("Un-authenticated session");
			((HttpServletResponse)servletResponse).sendRedirect("/");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		logger = LoggerFactory.getLogger(this.getClass());
	}

}
