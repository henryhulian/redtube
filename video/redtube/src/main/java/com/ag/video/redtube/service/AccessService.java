package com.ag.video.redtube.service;

import com.ag.video.redtube.entity.StreamResource;

public interface AccessService {

	public StreamResource createStream( String publisher);
	
	public boolean checkAccessKey(String name);

	public StreamResource findStreamResourceByStreamName(String name);
	
	public StreamResource findStreamResourceByPublisher(String publisher);
	
	public StreamResource authorizeSubscriber(String subscriber , String publisher );

	public void startAccessTime(Long id);
	
}
