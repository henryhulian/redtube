package com.ag.video.redtube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ag.video.redtube.entity.StreamResource;
import com.ag.video.redtube.service.AccessService;


@RestController
@RequestMapping("/accessController")
public class AccessController {
	
	@Autowired
	private AccessService accessService;

	@RequestMapping(value="/publishAccessUrl",method=RequestMethod.GET)
	public String getPublishAccessUrl( @RequestParam String publisher ){
		
		StreamResource streamResource = accessService.createStream(publisher);
		
		return streamResource.getStreamName();
	}
}
