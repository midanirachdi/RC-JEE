package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.News;

/*
* author: Salim Ben Hassine
*/
@Remote
public interface NewsRemoteInterface {

	boolean add(News news);

	boolean update(News news);

	List<News> findAll();

	News findById(int id);

	boolean delete(News news);
}

