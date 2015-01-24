package com.ag.video.redtube.service.impl;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ag.video.redtube.entity.StreamResource;
import com.ag.video.redtube.repository.StreamRepository;
import com.ag.video.redtube.service.AccessService;
import com.ag.video.redtube.util.AESUtil;

@Service
@Transactional
public class AccessServiceImpl implements AccessService{
	
	private static final Log log = LogFactory.getLog(AccessServiceImpl.class);
	
	private String key = "wXf;7-*!i)&d7TCM";
	
	@Autowired
	private StreamRepository streamRepository;


	@Override
	public StreamResource createStream(String publisher) {
		
		StreamResource stream = new StreamResource();
		stream.setPublisher(publisher);
		stream.setCreateTime(new Timestamp(System.currentTimeMillis()));
		try {
			stream.setStreamName(URLEncoder.encode(AESUtil.encrypt(publisher+"&"+stream.getCreateTime().getTime(), key),"UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		streamRepository.save(stream);
		
		return stream;
	}


	@Override
	public boolean checkAccessKey(String name) {
		
		try {
			AESUtil.decrypt(URLDecoder.decode(name,"UTF-8"), this.key);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

	@Override
	public StreamResource findStreamResourceByStreamName(String name) {
		return streamRepository.findStreamResourceByStreamName(name);
	}
	

	@Override
	public StreamResource authorizeSubscriber(String subscriber,
			String publisher) {
		
		StreamResource streamResource = findStreamResourceByPublisher(publisher);
		streamResource.setSubscriberList(streamResource.getSubscriberList()+subscriber+"&");
		streamRepository.save(streamResource);
		
		return streamResource;
	}
	

	@Override
	public StreamResource findStreamResourceByPublisher(String publisher) {

		return streamRepository.findStreamResourceByPublisher(publisher);
	}


	@Override
	public void startAccessTime(Long id) {
		
		StreamResource streamResource = streamRepository.findOne(id);
		streamResource.setAccessStartTime(new Timestamp(System.currentTimeMillis()));
		streamRepository.save(streamResource);
		log.debug(streamResource.getSubscriberList());
		
	}



	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


}
