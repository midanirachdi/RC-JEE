package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.News;

/*
* author: Salim Ben Hassine
*/
@Local
public interface NewsLocalInterface {

	boolean add(News news);

	boolean update(News news);

	List<News> findAll();

	News findById(int id);

	boolean delete(News news);
	
	List<News> findByCountry(String country);

}

