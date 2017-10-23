package tn.esprit.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Refugee;
import tn.esprit.interfaces.RefugeeInterfaceLocal;
import tn.esprit.interfaces.RefugeeInterfaceRemote;

@Stateless
@LocalBean
public class RefugeeService implements RefugeeInterfaceRemote,RefugeeInterfaceLocal{

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	public EntityManager em;
	
	@Override
	public boolean add(Refugee refugee) {
		try {
		em.persist(refugee); // refugee est gérée par entity manager 
		em.flush(); // forcer l'instruction
		System.out.println("Refugee added successfuly");
		return true;
		} catch (Exception e){
			System.out.println("Refugee not added ");
			return false;
		}
	}

	@Override
	public boolean update(Refugee refugee) {
		try {
		Refugee r = em.merge(refugee); // merge renvoie l'objet géré et le MAJ 
		System.out.println("Refugee updated successfuly");
		return true;
		}catch (Exception e){
			System.out.println("Refugee not updated ");
			return false;
			}
		}
		

	@Override
	public boolean delete(Refugee refugee) {
		try {
		em.remove(em.merge(refugee));
		System.out.println("Refugee deleted successfuly");
		return true;
		}catch (Exception e) {
			System.out.println("Refugee not deleted ");
			return false;
		}
		
	}

	@Override
	public List<Refugee> findAll() {
		String requete = "SELECT r FROM Refugee r";
		return em.createQuery(requete,Refugee.class).getResultList();
	}

	@Override
	public Refugee findById(int id) {
		return em.find(Refugee.class, id);
	}

	@Override
	public List<Refugee> findByName(String name) {
		List<Refugee> rl =new ArrayList<Refugee>();
		rl = em.createQuery("Select r from Refugee r where r.firstname =:p").setParameter(0,name).getResultList();
		return rl; 
	}

	@Override
	public int countRefugeePerGender(String sex) {
//		TypedQuery<int> query = em.createQuery("SELECT COUNT(r) in c FROM Refugee r WHERE r.sex =:p").setParameter(0,sex);
//		int c = query.getSingleResult();
//		return c;
		List<Refugee> rl =new ArrayList<Refugee>();
		rl = em.createQuery("Select r from Refugee r where r.sex =:p").setParameter("p",sex).getResultList();
		return rl.size();
	}

	@Override
	public List<Integer> countRefugeePerAge() {
		List<Integer> statAge = new ArrayList<Integer>();
		int bebe = 0,enfant =0,ado =0,adulte =0,agee =0;
		List<Refugee> l = new ArrayList<Refugee>();
		l=findAll();
		for (Refugee r : l) {
			if (r.getDateOfBirth() != null) {
				
				if (getAge(r.getDateOfBirth()) >=0 && getAge(r.getDateOfBirth()) <=2 ) {
					bebe++;
				}else if (getAge(r.getDateOfBirth())>2 && getAge(r.getDateOfBirth()) <= 12) {
					enfant++;
				}else if (getAge(r.getDateOfBirth())>12 && getAge(r.getDateOfBirth()) <= 18) {
					ado++;
				}else if (getAge(r.getDateOfBirth())>18 && getAge(r.getDateOfBirth())<=70) {
					adulte++;
				}else {
					agee++;
				}
		    }   
		}
			statAge.add(bebe);
			statAge.add(enfant);
			statAge.add(ado);
			statAge.add(adulte);
			statAge.add(agee);
	return statAge;
	}
	
	public int getAge(Date d) {
	int age =  (Calendar.getInstance().get(Calendar.YEAR))-(d.getYear()+1900);
	return age;
    }
}
