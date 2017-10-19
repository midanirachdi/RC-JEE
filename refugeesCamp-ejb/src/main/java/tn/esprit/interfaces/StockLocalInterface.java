package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Stock;

/*
* author: Salim Ben Hassine
*/
@Local
public interface StockLocalInterface {

	boolean add(Stock stock);

	boolean update(Stock stock);

	List<Stock> findAll();

	Stock findById(int id);

	boolean delete(Stock stock);
}

