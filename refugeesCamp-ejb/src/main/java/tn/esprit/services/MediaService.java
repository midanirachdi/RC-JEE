package tn.esprit.services;
/*
* author: Salim Ben Hassine
*/


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Admin;
import tn.esprit.entities.Media;
import tn.esprit.entities.News;
import tn.esprit.utile.MD5Encrypter;

@Stateless
@LocalBean
public class MediaService {
	

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;
	private final String UPLOADED_FILE_PATH = System.getProperty("jboss.server.data.dir")+"\\media\\";
	
	public boolean testFileType(String filename) {
	    int i = filename.lastIndexOf('.');
	    String extension = filename.substring(i);
	    extension=extension.toLowerCase();
	    if(extension.equals(".jpg")||extension.equals(".jpeg")||extension.equals(".png")||extension.equals(".gif")||extension.equals(".bmp")) return true;
	    else return false;
	}
	
	public String getMediaName(String originalName)
	{
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
	    String strDate = sdfDate.format(now);
	    strDate=strDate+ThreadLocalRandom.current().nextInt(2, 14752 + 1);
	    strDate=MD5Encrypter.crypt(strDate);
	    String extension = "";

	    int i = originalName.lastIndexOf('.');
	     extension = originalName.substring(i);
	    
		return strDate+extension;
	}
	
	
	public String getUPLOADED_FILE_PATH() {
		return UPLOADED_FILE_PATH;
	}
	
	public Media addMedia(String path,Admin admin) {
		Media media=new Media();
		String req="Select n from News n where n.id=:p";
		media.setPath(path);
		/*List<News> ln=new ArrayList<News>();
		if(media.getNews().size()!=0) {ln.addAll(media.getNews());}
		ln.add(news);
		media.setNews(ln);*/
		media.setAdmin(admin);
		media.setTitle("Untitled-"+media.getId());
		if (media != null) {
			em.persist(media);
			
		}
		return media;
	}
}