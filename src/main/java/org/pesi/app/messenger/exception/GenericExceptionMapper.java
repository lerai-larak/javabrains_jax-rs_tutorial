package org.pesi.app.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.pesi.app.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception exception) {
		ErrorMessage errMessage = new ErrorMessage(exception.getMessage(),500,"http://pesi/docs/api-errors");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errMessage)
				.build();
	}

}
