package org.pesi.app.messenger.resource;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.pesi.app.messenger.model.Message;
import org.pesi.app.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {

	MessageService mService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		if (year > 0) {
			return mService.getMessagesForYear(year);
		}
		if (start >= 0 && size >= 0) {
			return mService.getAllMessagesPaginated(start, size);
		}
		return mService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessageWithId(@PathParam("messageId") long messageId) {
		return mService.getMessage(messageId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		message.setCreated(Calendar.getInstance().getTime());
		return mService.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return mService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long messageId) {
		mService.removeMessage(messageId);
	}

	/*
	 * Alternate method to get parameters.
	 * 
	 * @Context is a special annotation that can only be appplied on specific jax-rs
	 * classes. Provides a way to access parameters. 
	 * A third way is to create a bean that has the query param as members, then use that bean in place of the params 
	 *  using the @BeanParam annotation. The params will be accessible using the getters in the bean.
	 */

	@GET
	@Path("/context")
	@Produces(MediaType.TEXT_PLAIN)
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Path: ").append(uriInfo.getPath()).append("\nBase URI ").append(uriInfo.getBaseUri());
		stringBuilder.append("\nCookies: ").append(headers.getCookies());
		return stringBuilder.toString();
	}

}
