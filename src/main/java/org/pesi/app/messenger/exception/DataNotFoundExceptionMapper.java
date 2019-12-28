   package org.pesi.app.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.pesi.app.messenger.model.ErrorMessage;

@Provider //Registers the class with JAX-RS
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errMessage = new ErrorMessage(exception.getMessage(),404,"http://pesi/docs/api-errors");
		return Response.status(Status.NOT_FOUND)
				.entity(errMessage)
				.build();
	}

}
