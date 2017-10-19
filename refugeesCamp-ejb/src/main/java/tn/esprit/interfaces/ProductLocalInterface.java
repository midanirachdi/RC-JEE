package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Product;

/*
* author: Salim Ben Hassine
*/
@Local
public interface ProductLocalInterface {
	boolean add(Product product);

	boolean update(Product product);

	List<Product> findAll();

	Product findById(int id);

	boolean delete(Product product);
}

