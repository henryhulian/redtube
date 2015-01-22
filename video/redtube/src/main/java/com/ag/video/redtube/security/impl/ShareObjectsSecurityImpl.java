package com.ag.video.redtube.security.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.so.ISharedObject;
import org.red5.server.api.so.ISharedObjectSecurity;

public class ShareObjectsSecurityImpl implements ISharedObjectSecurity{
	
	private static final Log log = LogFactory.getLog(ShareObjectsSecurityImpl.class);

	@Override
	public boolean isCreationAllowed(IScope scope, String name,
			boolean persistent) {
		boolean allowed = false;
		log.info("ShareObjects create security result:"+allowed);
		return allowed;
	}

	@Override
	public boolean isConnectionAllowed(ISharedObject so) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isWriteAllowed(ISharedObject so, String key, Object value) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isDeleteAllowed(ISharedObject so, String key) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isSendAllowed(ISharedObject so, String message,
			List<?> arguments) {
		// TODO Auto-generated method stub
		return true;
	}

}
