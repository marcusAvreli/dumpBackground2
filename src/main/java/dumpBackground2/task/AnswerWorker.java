package dumpBackground2.task;

import java.util.List;

import javax.swing.SwingWorker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import dumpBackground2.App;
import dumpBackground2.baseevents.BaseEventsReceiver;
import dumpBackground2.baseevents.event.TokenReceivedEvent;
import dumpBackground2.baseevents.event.refresh.RefreshAffiliationEvent;
import dumpBackground2.model.base.entity.Token;
import dumpBackground2.project.Context;
import dumpBackground2.tools.Util;
import sailpoint.integration.Base64;

public class AnswerWorker extends SwingWorker<Boolean, String>
{
	private static final Logger logger = LoggerFactory.getLogger(AnswerWorker.class);
	private String username;
	private String password;
	private String baseUrl = Context.getInstance().getBaseUrl();
	public AnswerWorker(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	
    protected Boolean doInBackground() throws Exception
    {
        // Do a time-consuming task.
        Thread.sleep(1000);
        System.out.println("start login");
        Client client = ClientBuilder.newClient();
		//String clientID = "ectAJlZCfeb1AvhGgozgj7dkYl7gDVLa";
		//String clientSecret = "m34XrqOjqDjz9HSx";
        
        String clientID = username;
        String clientSecret = password;
		String tokenURL = baseUrl+"/oauth2/generateToken";
		String grantType = "client_credentials";
		MultivaluedMap<String, String> formData = new MultivaluedHashMap<String,String>();

		formData.add("grant_type", grantType);

		String secret = "Basic " + Base64.encodeBytes(new String(clientID + ":" + clientSecret).getBytes()); 
		// we	// should 	// use
																												// Base64
		boolean isOk = true;																										// encode
																												// to
		while(isOk) {																								// encode
																												// client
																												// id
																												// and
																												// client
																												// secret
		Response response = (Response) client.target(tokenURL). // token URL to get access token
				request("application/json"). // JSON Request Type
				accept("application/json"). // Response access type - application/scim+json
				header("Authorization", secret) // Authorization header goes here
				.post(Entity.json(null)); // body with grant type

		String token = response.readEntity(String.class); // reading response as string format
		System.out.println("token:"+token);
		JSONObject jsonObject = new JSONObject(token);
		String accessToken = "Bearer " + jsonObject.getString("access_token");
		publish(accessToken);
		
		String expiresInString = jsonObject.getString("expires_in");
		if(Util.isNotNullOrEmpty(expiresInString)) {
			Context.getInstance().notifyKnowledgeBaseObsever(true);
		}
		Thread.sleep(Integer.valueOf(expiresInString)*1000);
	     

		}
				
        System.out.println("after sleep do in backgound");
        return true;
    }

    protected void done()
    {
    	logger.info("done");
    	System.out.println("execute done");
        try
        {
            //JOptionPane.showMessageDialog(f, get());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    // Can safely update the GUI from this method.
    protected void process(List<String> chunks) {
     // Here we receive the values that we publish().
     // They may come grouped in chunks.
     String accessToken = chunks.get(chunks.size()-1);
     Token token = new Token();
     token.setAccessToken(accessToken);
     Context.getInstance().setAccessToken(token);
    // BaseEventsReceiver.getInstance().addEvent( new TokenReceivedEvent());

     //countLabel1.setText(Integer.toString(mostRecentValue));
    }
}