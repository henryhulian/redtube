package com.ag.video.redtube;

import java.util.*;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.so.ISharedObjectSecurity;
import org.red5.server.api.stream.IBroadcastStream;
import org.red5.server.api.stream.IServerStream;
import org.red5.server.api.stream.IStreamPlaybackSecurity;
import org.red5.server.api.stream.IStreamPublishSecurity;
import org.red5.server.api.stream.ISubscriberStream;
import org.springframework.beans.factory.annotation.Autowired;

import com.ag.video.redtube.entity.User;
import com.ag.video.redtube.repository.UserRepository;
import com.ag.video.redtube.service.security.ConnectionSecurityService;

public class Application extends ApplicationAdapter {

	private IScope appScope;

	private IServerStream serverStream;
	
	@Autowired
	private ConnectionSecurityService connectionSecurityService;
	
	@Autowired
	private IStreamPlaybackSecurity streamPlaybackSecurityService;
	
	@Autowired
	private IStreamPublishSecurity streamPublishSecurityService;
	
	@Autowired
	private ISharedObjectSecurity sharedObjectSecurityService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean appStart(IScope app) {
		super.appStart(app);
		registerStreamPlaybackSecurity(streamPlaybackSecurityService);
		registerStreamPublishSecurity(streamPublishSecurityService);
		registerSharedObjectSecurity(sharedObjectSecurityService);
		log.info("redtube appStart");
		appScope = app;
		return true;
	}
	
	@Override
	public void streamBroadcastStart(IBroadcastStream stream) {
		log.info("strart broadcast"+stream.getName()+" publish name:"+stream.getPublishedName());
		// TODO Auto-generated method stub
		super.streamBroadcastStart(stream);
	}
	
	@Override
	public void streamSubscriberStart(ISubscriberStream stream) {
		log.info("subscrib start stream:"+stream.getName()+" publish name:"+stream.getBroadcastStreamPublishName());
		log.info("subscrib start:"+stream.getConnection().getSessionId());
		super.streamSubscriberStart(stream);
	}
	
	

	@Override
	public boolean appConnect(IConnection conn, Object[] params) {
		log.info("redtube appConnect sessionId:"+conn.getSessionId());
		IScope appScope = conn.getScope();
		log.info("App connect called for scope: {}", appScope.getName());
		
		userRepository.save(new User());
		
		// getting client parameters
		Map<String, Object> properties = conn.getConnectParams();
		for (Map.Entry<String, Object> e : properties.entrySet()) {
			log.info("Connection property: {} = {}", e.getKey(), e.getValue());
		}
		
		// connection security 
		if( !connectionSecurityService.isConnectionAllowed(conn) ){
			rejectClient();
			return false;
		}
	
		return super.appConnect(conn, params);
	}

	@Override
	public void appDisconnect(IConnection conn) {
		log.info("redtube appDisconnect");
		if (appScope == conn.getScope() && serverStream != null) {
			serverStream.close();
		}
		super.appDisconnect(conn);
	}

	public ConnectionSecurityService getConnectionSecurityService() {
		return connectionSecurityService;
	}

	public void setConnectionSecurityService(ConnectionSecurityService connectionSecurityService) {
		this.connectionSecurityService = connectionSecurityService;
	}
}
