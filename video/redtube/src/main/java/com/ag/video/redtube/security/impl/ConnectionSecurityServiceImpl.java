package com.ag.video.redtube.security.impl;

import org.red5.server.api.IConnection;
import org.springframework.stereotype.Service;

import com.ag.video.redtube.security.ConnectionSecurityService;

@Service
public class ConnectionSecurityServiceImpl implements ConnectionSecurityService{

	@Override
	public boolean isConnectionAllowed(IConnection connection) {
		// TODO Auto-generated method stub
		return false;
	}

}
