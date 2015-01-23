package com.ag.video.redtube.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.ag.video.redtube.entity.StreamResource;
import com.ag.video.redtube.repository.StreamRepository;
import com.ag.video.redtube.service.AccessService;
import com.ag.video.redtube.util.AESUtil;

public class AccessServiceImpl implements AccessService{
	
	private String key = "wXf;7-*!i)&d7TCM";
	
	@Autowired
	private StreamRepository streamRepository;


	@Override
	public StreamResource createStream(String publisher) {
		
		StreamResource stream = new StreamResource();
		stream.setPublisher(publisher);
		stream.setStreamName(publisher+System.currentTimeMillis());
		stream.setCreateTime(new Timestamp(System.currentTimeMillis()));
		try {
			stream.setAccessKey(AESUtil.decrypt(stream.getId()+":"+stream.getPublisher()+":"+stream.getStreamName(), key));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		streamRepository.save(stream);
		
		return stream;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}

}
