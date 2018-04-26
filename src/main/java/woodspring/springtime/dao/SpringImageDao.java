package woodspring.springtime.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.google.gson.Gson;

import woodspring.springtime.model.CBTPhoto;
import woodspring.springtime.util.SpringConstant;

@Component
public class SpringImageDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CBTPhoto getImage( String imageId) {
		String urlStr = SpringConstant.IMAGE_URL+"/magic/"+ imageId;
		CBTPhoto thePhoto = new CBTPhoto();
		URL imageUrl;
		try {
			imageUrl = new URL(urlStr);
			HttpsURLConnection connect = (HttpsURLConnection) imageUrl.openConnection();
	        InputStreamReader isr = new InputStreamReader(connect.getInputStream());
	        BufferedReader br = new BufferedReader(isr);
	        String inputLine = br.readLine() ;
	        Gson gson = new Gson();
	        thePhoto = gson.fromJson( inputLine,  CBTPhoto.class);
	        br.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("MalformedURLException "+ urlStr +" exception:" + e.getMessage());
			//e.printStackTrace();
			thePhoto = null;
		} catch (IOException e) {
			logger.info("IOException:"+ e.getMessage() + " localMsg:"+ e.getLocalizedMessage()+" url:"+urlStr+" item:"+ imageId );
			//e.printStackTrace();
			thePhoto = null;
		}
		
		return thePhoto;
	}
	
	public byte[] getPhotoOnly( String photoUrl) {
		//String tmpUrl = "https://unsplash.it/500?image=12";
		URL imageUrl;
		byte[] theImage = null;
		try {
			imageUrl = new URL(photoUrl);
			HttpsURLConnection connect = (HttpsURLConnection) imageUrl.openConnection();
			theImage = StreamUtils.copyToByteArray( connect.getInputStream());
			logger.info(" getPhotoOnly, photoUrl:["+photoUrl +"]");
		} catch (IOException e) {
			
			logger.info(" IOException"+ e.getMessage() + " photoUrl:"+photoUrl);
			//e.printStackTrace();
		}
				
		return theImage;
	}

}
