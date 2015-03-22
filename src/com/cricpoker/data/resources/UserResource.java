package com.cricpoker.data.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.cricpoker.data.access.UserDao;
import com.cricpoker.data.objects.User;

public class UserResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  String id;
  public UserResource(UriInfo uriInfo, Request request, String id) {
    this.uriInfo = uriInfo;
    this.request = request;
    this.id = id;
  }
  
  //Application integration     
  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public User getTodo() {
	  User todo = UserDao.instance.getModel().get(id);
    if(todo==null)
      throw new RuntimeException("Get: Todo with " + id +  " not found");
    return todo;
  }
  
  // for the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public User getTodoHTML() {
	  User todo = UserDao.instance.getModel().get(id);
    if(todo==null)
      throw new RuntimeException("Get: Todo with " + id +  " not found");
    return todo;
  }
  
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public Response putTodo(JAXBElement<User> todo) {
	  User c = todo.getValue();
    return putAndGetResponse(c);
  }
  
  @DELETE
  public void deleteTodo() {
	  User c = UserDao.instance.getModel().remove(id);
    if(c==null)
      throw new RuntimeException("Delete: Todo with " + id +  " not found");
  }
  
  private Response putAndGetResponse(User todo) {
    Response res;
    if(UserDao.instance.getModel().containsKey(todo.getId())) {
      res = Response.noContent().build();
    } else {
      res = Response.created(uriInfo.getAbsolutePath()).build();
    }
    UserDao.instance.getModel().put(todo.getId(), todo);
    return res;
  }
  
  

} 