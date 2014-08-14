package com.pihen.d3restapi.service.remote.exception;

import java.io.IOException;

import com.pihen.d3restapi.service.remote.RemoteEntity;

/**
 * Represents an exception coming from the Battle.net servers. May contain an
 * ErrorEntity describing the problem.
 * 
 * When receiving this exception, the communication was ended successfully, but
 * the result is an error.
 * 
 * @author NoTiCe
 * 
 */
@SuppressWarnings("serial")
public class D3ServerCommunicationException extends IOException {

	private RemoteEntity errorEntity;

	public D3ServerCommunicationException()
	{
		super();
	}
	
	
	public D3ServerCommunicationException(RemoteEntity errorEntity) {
		this.errorEntity = errorEntity;
	}

	/**
	 * Gives back the error entity.
	 * 
	 * @return The error entity, may be null.
	 */
	public RemoteEntity getErrorEntity() {
		return errorEntity;
	}
}
