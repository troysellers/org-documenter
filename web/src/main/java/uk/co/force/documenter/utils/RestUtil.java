package uk.co.force.documenter.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestUtil {
	private Logger logger;
	
	public RestUtil() {
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public JSONObject getJSONObject(String accessToken, String url, HttpClient client) {
		
		JSONObject jObj = new JSONObject();
		
		RequestBuilder builder = RequestBuilder.create(HttpGet.METHOD_NAME);
		builder.addHeader("content-type","application/x-www-form-urlencoded");
		builder.addHeader("Authorization: ", "OAuth " + accessToken);
		
		builder.setUri(url);
		try {
			HttpResponse response = client.execute(builder.build());
			
			logger.info(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			
			jObj = new JSONObject(new JSONTokener(entity.getContent()));
			logger.info(jObj.toString());
			EntityUtils.consume(entity);

		} catch (ClientProtocolException pe) {
			
		} catch (IOException ioe) {
			
		}
		return jObj;
	}
	
	public JSONArray getJSONArray(String accessToken, String url, HttpClient client) {
		
		JSONArray jArray = new JSONArray();
		
		RequestBuilder builder = RequestBuilder.create(HttpGet.METHOD_NAME);
		builder.addHeader("content-type","application/x-www-form-urlencoded");
		builder.addHeader("Authorization: ", "OAuth " + accessToken);
		
		builder.setUri(url);
		try {
			HttpResponse response = client.execute(builder.build());
			
			logger.info(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			
			jArray = new JSONArray(new JSONTokener(entity.getContent()));
			EntityUtils.consume(entity);
			
		} catch (ClientProtocolException pe) {
			
		} catch (IOException ioe) {
			
		}
		return jArray;
	}
}
