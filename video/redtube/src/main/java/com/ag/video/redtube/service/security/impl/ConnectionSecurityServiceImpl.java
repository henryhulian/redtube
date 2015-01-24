package com.ag.video.redtube.service.security.impl;

import org.red5.server.api.IConnection;
import org.springframework.stereotype.Service;

import com.ag.video.redtube.service.security.ConnectionSecurityService;


@Service
public class ConnectionSecurityServiceImpl implements ConnectionSecurityService{

	@Override
	public boolean isConnectionAllowed(IConnection connection) {
		// TODO Auto-generated method stub
		return true;
	}

}
