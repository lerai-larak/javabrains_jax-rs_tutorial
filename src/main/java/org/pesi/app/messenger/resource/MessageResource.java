package org.pesi.app.messenger.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.pesi.app.messenger.model.Message;
import org.pesi.app.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService mService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages() {
		return mService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	public Message getMessageWithId(@PathParam("messageId")long messageId) {
		return mService.getMessage(messageId);
	}
}
