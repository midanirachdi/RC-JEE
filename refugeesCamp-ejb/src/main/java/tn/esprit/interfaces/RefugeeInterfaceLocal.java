package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Refugee;

@Local
public interface RefugeeInterfaceLocal {

		void add(Refugee refugee);

		void update(Refugee refugee);
		
		void delete(Refugee refugee);

		List<Refugee> findAll();

		Refugee findById(int id);

		List<Refugee> findByName(String name);
		
		int countRefugeePerSex(String sex);
//		
//		int countRefugeePerAge(int a, int b);
		
}

