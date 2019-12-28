package org.pesi.app.messenger.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/*class level is optional for sub resources. This root path is relative to the parent resource 
 * ie. /messages/{messageId}/comments*/
@Path("/") 
 
public class CommentResource {

	@GET
	public String test() {
		return "new sub resource";
	}
	
	@GET
	@Path("/{commentId}")
	public String test2(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return "Message Id: + " + messageId + " Comment Id: " + commentId;
	}
}
