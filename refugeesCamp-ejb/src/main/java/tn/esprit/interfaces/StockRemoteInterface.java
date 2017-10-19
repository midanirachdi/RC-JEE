package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Stock;

/*
* author: Salim Ben Hassine
*/
@Remote
public interface StockRemoteInterface {

	boolean add(Stock stock);

	boolean update(Stock stock);

	List<Stock> findAll();

	Stock findById(int id);

	boolean delete(Stock stock);
	
}

