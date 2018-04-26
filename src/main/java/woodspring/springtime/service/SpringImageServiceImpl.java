package woodspring.springtime.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import woodspring.springtime.dao.SpringImageDao;
import woodspring.springtime.model.CBTPhoto;

@Service
public class SpringImageServiceImpl implements SpringImageService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SpringImageDao springImage;

	@Override
	public CBTPhoto getSpringPhoto(String itemId) {
		logger.info(" getSpringPhoto, start itemId" + itemId);
		CBTPhoto cbtPhoto = springImage.getImage(itemId);
		if ( cbtPhoto != null && cbtPhoto.getImageUrl() != null && cbtPhoto.getImageUrl().length() > 0) {
			//cbtPhoto.setImage( springImage.getPhotoOnly( cbtPhoto.getImageUrl()));
		}
		logger.info("getSpringPhoto, id:"+ itemId+"  cbtPhoto:"+ ((cbtPhoto==null) ? "null": cbtPhoto.toString()));
		return cbtPhoto;
	}

	@Override
	public byte[] getImageOnly(String itemId) {
		CBTPhoto cbtPhoto = springImage.getImage(itemId);
		byte[] theImage = null;
		if ( cbtPhoto != null && cbtPhoto.getImageUrl() != null && cbtPhoto.getImageUrl().length() > 0) {
			theImage = springImage.getPhotoOnly( cbtPhoto.getImageUrl());
		} else {
			ClassPathResource imgFile = new ClassPathResource("static/image/NONE.JPG");
			try {
				theImage = StreamUtils.copyToByteArray(imgFile.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		}
		logger.info("getImageOnly, id:"+ itemId+"  imageSize:"+ ((theImage == null) ? "0" : theImage.length));
		return theImage;
	}

}
