package com.ag.video.redtube.service.security.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.stream.IStreamPublishSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ag.video.redtube.entity.StreamResource;
import com.ag.video.redtube.service.AccessService;

@Service
public class PulishSecurityImpl implements IStreamPublishSecurity {

	private static final Log log = LogFactory.getLog(PulishSecurityImpl.class);

	@Autowired
	private AccessService accessService;

	@Override
	public boolean isPublishAllowed(IScope scope, String name, String mode) {
		
		// getting client parameters
//		Map<String, Object> properties = conn.getConnectParams();
//		for (Map.Entry<String, Object> e : properties.entrySet()) {
//			log.info("Connection property: {"+e.getKey()+"} = {"+e.getValue()+"}");
//		}

		try {
			
			if( !accessService.checkAccessKey(name) ){
				log.warn(" decrypt failed: "+name);
				return false;
			}
			
			StreamResource streamResource = accessService.findStreamResourceByStreamName(name);

			if( streamResource == null ){
				log.warn("stream name:"+name+" can not found!");
				return false;
			}
		} catch (Exception e) {
			log.error(e);
			return false;
		}
		
		
		return true;
	}

}
