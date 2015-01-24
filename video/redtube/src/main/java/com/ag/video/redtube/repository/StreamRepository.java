package com.ag.video.redtube.repository;

import org.springframework.data.repository.CrudRepository;

import com.ag.video.redtube.entity.StreamResource;

public interface StreamRepository extends CrudRepository<StreamResource	, Long>{

	public StreamResource findStreamResourceByStreamName( String streamName );
	
	public StreamResource findStreamResourceByPublisher( String publisher );
}
