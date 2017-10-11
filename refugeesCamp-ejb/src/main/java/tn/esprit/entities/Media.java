package tn.esprit.entities;
/*
 * author Salim Ben Hassine
 *
 * 
 */
public class Media {
	
	private int id;
	private String title;
	private String path;
	
	public Media(int id, String title, String path) {
		super();
		this.id = id;
		this.title = title;
		this.path = path;
	}

	public Media() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
