package com.ag.video.redtube;

import java.util.*;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.stream.IBroadcastStream;
import org.red5.server.api.stream.IServerStream;
import org.red5.server.api.stream.ISubscriberStream;

public class Application extends ApplicationAdapter {

	private IScope appScope;

	private IServerStream serverStream;

	@Override
	public boolean appStart(IScope app) {
		super.appStart(app);
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
		
		// getting client parameters
		Map<String, Object> properties = conn.getConnectParams();
		for (Map.Entry<String, Object> e : properties.entrySet()) {
			log.info("Connection property: {} = {}", e.getKey(), e.getValue());
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
}
