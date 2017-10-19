package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Refugee;

@Local
public interface RefugeeInterfaceLocal {

		boolean add(Refugee refugee);

		boolean update(Refugee refugee);
		
		boolean delete(Refugee refugee);

		List<Refugee> findAll();

		Refugee findById(int id);

		List<Refugee> findByName(String name);
		
		int countRefugeePerGender(String sex);
//		
//		int countRefugeePerAge(int a, int b);
		
}

