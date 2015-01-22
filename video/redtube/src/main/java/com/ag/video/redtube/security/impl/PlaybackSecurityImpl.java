package com.ag.video.redtube.security.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.stream.IStreamPlaybackSecurity;

public class PlaybackSecurityImpl implements IStreamPlaybackSecurity {
	
	private static final Log log = LogFactory.getLog(PlaybackSecurityImpl.class);
	
    @Override
    public boolean isPlaybackAllowed(IScope scope, String name, int start, int length, boolean flushPlaylist) {
        //start out denied
        boolean allowed = false;
        //get the current connection
        IConnection conn = Red5.getConnectionLocal();
        //token to use for auth
        Long token = -1L;
        if (conn.hasAttribute("token")) {
            //get a 'token' we stored on their connection from elsewhere
            token = conn.getLongAttribute("token");
            //validate the token in some way
            if (token > 0L) {
                allowed = true;
            }
        }
        //return allowed or denied state
        log.info("Playback security result:"+allowed);
        return allowed;
    }
}
