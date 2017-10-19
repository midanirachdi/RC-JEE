package tn.esprit.resources;
/*
* author: Salim Ben Hassine
*/

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.entities.News;
import tn.esprit.services.NewsService;

@RequestScoped
@Path("/news")
public class NewsRessources {

	@EJB
	NewsService newsService;
	

	public NewsRessources() {
		super();
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddNews(News news) {
		
		if (newsService.add(news)) return Response.status(Status.CREATED).build();
		return Response.status(Status.NOT_FOUND).build();
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllNews() {
		
		List<News> news = new ArrayList<News>();
		news = newsService.findAll();

		if (!news.isEmpty()) return Response.status(Status.OK).entity(news).build();
		
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetNewsById(@PathParam(value = "id") int id) {
		News news = newsService.findById(id);
		if(news!=null)
			return Response.status(Status.OK).entity(news).build();
		return Response.status(404).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteNews(@PathParam(value = "id") int id) {
		
		News news = newsService.findById(id);
		
		if (newsService.delete(news))
			return Response.status(Status.OK).build();
		return Response.status(404).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response UpdateNews(News news) {
		News exNews=newsService.findById(news.getId());
		//Change Tests:
		if(news.getAuthor()!=null) {exNews.setAuthor(news.getAuthor());}
		if(news.getContent()!=null) {exNews.setContent(news.getContent());}
		if(news.getDateOfCreation()!=null) {exNews.setDateOfCreation(news.getDateOfCreation());}
		if(news.getMedias()!=null) {exNews.setMedias(news.getMedias());}
		
		if(newsService.update(exNews))
			return Response.status(Status.OK).build();
		return Response.status(Status.NOT_FOUND).build();
	}

}
