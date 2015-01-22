package com.ag.video.redtube.security;

import org.red5.server.api.IConnection;

public interface ConnectionSecurityService {

	public boolean isConnectionAllowed( IConnection connection );
	
}
