package com.pihen.d3restapi.service.remote;

import com.pihen.d3restapi.service.configuration.Configuration;
import com.pihen.d3restapi.service.remote.exception.D3ServerCommunicationException;

/**
 * A RemoteService give access to Remote entities.
 * 
 * @author NoTiCe
 * 
 */
public interface RemoteService<T extends RemoteEntity> {
	T receiveEntity(Configuration configuration) throws D3ServerCommunicationException;
}
