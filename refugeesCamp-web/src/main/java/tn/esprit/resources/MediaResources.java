package tn.esprit.resources;

import java.io.File;

import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tn.esprit.authorization.AllowTo;
import tn.esprit.entities.Media;
import tn.esprit.services.MediaService;


/*
* author: Salim Ben Hassine
*/
@RequestScoped
@Path("/news/{id}/media")
public class MediaResources {

	@EJB
	MediaService mediaServ;
	@Context
	UriInfo uri;
	@PathParam(value= "id")
	int idnews;
	
	public MediaResources() {
		super();
	}


	public MediaService getMediaServ() {
		return mediaServ;
	}


	public void setMediaServ(MediaService mediaServ) {
		this.mediaServ = mediaServ;
	}


	public UriInfo getUri() {
		return uri;
	}


	public void setUri(UriInfo uri) {
		this.uri = uri;
	}


	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@AllowTo(roles = { "Admin" })
	public Response addMedia(@Context HttpServletRequest req) throws Exception {
		 StringBuilder files = new StringBuilder();
		 FileItemFactory factory = new DiskFileItemFactory();
		 ServletFileUpload upload = new ServletFileUpload(factory);
		 
		 List<FileItem> items = upload.parseRequest(req);
		 Iterator<FileItem> iter = items.iterator();
		 String newName="";
		while (iter.hasNext()) {
		 FileItem item = (FileItem) iter.next();
		 
		if(!mediaServ.testFileType(item.getName())) {
			return Response.status(404).build();
		}
		  newName=mediaServ.getMediaName(item.getName());
		 
		files.append(item.getName()).append(",");
		 File fullFile = new File(newName);
		 if (fullFile.length()>1048576)return Response.status(404).build();
		 
		 File savedFile = new File(mediaServ.getUPLOADED_FILE_PATH(), fullFile.getName());
		 item.write(savedFile);
		 
		 
		 }
		String link=uri.getAbsolutePath().toString();
		if(link.charAt(link.length()-1)!='/')link=link+"/";
		
		Media media=mediaServ.addMedia(link+newName, idnews);
		if(media!=null)
			return Response.status(Status.OK).entity(media).build();
		return Response.status(404).build();
		}
		
	@GET
	@Produces(MediaType.TEXT_HTML)
	@AllowTo(roles = { "Admin" })
	public String uploadInterface(@PathParam(value= "id")int id)
	{
		return "<form action=\"\" method=\"post\" enctype=\"multipart/form-data\">\r\n" + 
				"\r\n" +"<p>\r\n" + "<br>Select a file : <input type=\"file\" name=\"uploadedFile\" size=\"50\" />\r\n" + 
				"</p>\r\n\r\n" + "<input type=\"submit\" value=\"Upload\" />\r\n"+"</form>\r\n";
	}
	
 	@GET  
	    @Path("/{name}")  
	    @Produces({"image/png", "image/jpg"})  
	    public Response getFile(@PathParam(value="name") String name) {  
	        File file = new File(mediaServ.getUPLOADED_FILE_PATH()+name);  
	        return Response.ok(file).build();
	   
	    }  
	 	
}

