package woodspring.springtime.service;

import woodspring.springtime.model.CBTPhoto;

public interface SpringImageService {
	
	CBTPhoto getSpringPhoto(String itemId);
	byte[] getImageOnly(String itemId);
	

}
