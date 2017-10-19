package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Product;

/*
* author: Salim Ben Hassine
*/
@Remote
public interface ProductRemoteInterface {
	boolean add(Product product);

	boolean update(Product product);

	List<Product> findAll();

	Product findById(int id);

	boolean delete(Product product);
}

