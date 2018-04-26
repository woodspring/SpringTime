package woodspring.springtime.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springtime.model.CBTPhoto;
import woodspring.springtime.service.SpringImageService;

@RestController
@RequestMapping(value = "/spring")
public class SpringImageController {
	
	@Autowired
	SpringImageService imageService;
	
	@GetMapping("/imageInfo/{id}")
	public ResponseEntity<?> getImangeInfo(@PathVariable("id") String itemId) {
		//String itemId = Integer.valueOf(id).toString();
		CBTPhoto thePhoto = imageService.getSpringPhoto(itemId);
		ResponseEntity<CBTPhoto> responseEntity = new ResponseEntity<>(thePhoto, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(
	  value = "/image/{id}",
	  produces = MediaType.IMAGE_JPEG_VALUE
	)
	public ResponseEntity<byte[]> getImageWithMediaType(@PathVariable("id") String itemId )  {
		//String itemId = Integer.valueOf(id).toString();
			    byte[] retBytes = imageService.getImageOnly(itemId);
			    
			    return ResponseEntity
		                .ok()
		                .contentType(MediaType.IMAGE_JPEG)
		                .body(retBytes);
	}
}
