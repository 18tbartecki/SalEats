package Assignment4;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;


public class YelpService extends DefaultApi20 {
	
	private static final String API_HOST = "api.yelp.com";
	private static final String SEARCH_PATH = "/v3/businesses/search";
	
	private static final String API_KEY = "dXI8FYupCJcQKUxo_e5KxGVJGo0__5FIsfzgiCsQoyiUeqlmk1a4r1uYxy4li7Q7Y_1vbKX9hvjbQ6zJQFP8gdPXdvBTVuG3ea8znoDwbwgu2dMAtiEMmsRfqUl4XnYx";
	
	
	//Must be implemented as they are abstract
	public String getAccessTokenEndpoint() {
        return "";
    }
    protected String getAuthorizationBaseUrl() {
        return "";
    }
    
    //Queries the Yelp API to find nearby restaurants
    public String findRestaurant(String name, double latitude, double longitude, String sort_by)
    {
    	String body = "";
    	
    	try
    	{
	    	YelpService instance = new YelpService();
	    	
	    	OAuth20Service service = new ServiceBuilder(API_KEY).build(instance);
	    	
	    	OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST+ SEARCH_PATH);
	    	//Add necessary parameters to the search and execute it
	    	request.addQuerystringParameter("term", name);
	    	request.addQuerystringParameter("latitude", ""+ latitude);
	        request.addQuerystringParameter("longitude", ""+ longitude);
	        request.addQuerystringParameter("limit", "" + 10);
	        request.addQuerystringParameter("sort_by", sort_by);
	        
	        service.signRequest(API_KEY, request);
        
			Response response = service.execute(request);
			body = response.getBody();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//Return data to server
    	return body;
    }
}







