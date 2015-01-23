package com.ag.video.redtube.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StreamResource {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 用户名
	 */
	private String publisher;
	
	/**
	 * 流命名
	 */
	private String streamName;
	
	/**
	 * 流命名
	 */
	private String accessKey;
	
	/**
	 * 可以访问此资源的用户名列表
	 */
	private String subscriberList;
	
	/**
	 * 订阅者访问起始时间
	 */
	private Timestamp accessStartTime;
	
	/**
	 * 订阅者访问结束时间
	 */
	private Timestamp accessEndTime;
	
	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public Timestamp getAccessStartTime() {
		return accessStartTime;
	}

	public void setAccessStartTime(Timestamp accessStartTime) {
		this.accessStartTime = accessStartTime;
	}

	public Timestamp getAccessEndTime() {
		return accessEndTime;
	}

	public void setAccessEndTime(Timestamp accessEndTime) {
		this.accessEndTime = accessEndTime;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(String subscriberList) {
		this.subscriberList = subscriberList;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	
}
