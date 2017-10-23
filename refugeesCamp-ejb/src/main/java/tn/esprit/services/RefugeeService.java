package tn.esprit.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Refugee;
import tn.esprit.interfaces.RefugeeInterfaceLocal;
import tn.esprit.interfaces.RefugeeInterfaceRemote;
import tn.esprit.utile.SendAmail;

@Stateless
@LocalBean
public class RefugeeService implements RefugeeInterfaceRemote, RefugeeInterfaceLocal {

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	public EntityManager em;

	@Override
	public boolean add(Refugee refugee) {
		try {
			em.persist(refugee); // refugee est gérée par entity manager
			// em.flush(); // forcer l'instruction
			System.out.println("Refugee added successfuly");
			return true;
		} catch (Exception e) {
			System.out.println("Refugee not added ");
			return false;
		}
	}

	@Override
	public boolean update(Refugee refugee) {
		try {
			Refugee r = em.merge(refugee); // merge renvoie l'objet géré et le
											// MAJ
			System.out.println("Refugee updated successfuly");
			return true;
		} catch (Exception e) {
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
		} catch (Exception e) {
			System.out.println("Refugee not deleted ");
			return false;
		}

	}

	@Override
	public List<Refugee> findAll() {
		String requete = "SELECT r FROM Refugee r";
		return em.createQuery(requete, Refugee.class).getResultList();
	}

	@Override
	public Refugee findById(int id) {
		return em.find(Refugee.class, id);
	}

	@Override
	public List<Refugee> findByName(String name) {
		List<Refugee> rl = new ArrayList<Refugee>();
		rl = em.createQuery("Select r from Refugee r where r.firstname =:p").setParameter(0, name).getResultList();
		return rl;
	}

	@Override
	public int countRefugeePerGender(String sex) {
		// TypedQuery<Re> query =(int) em.createQuery("SELECT COUNT(r) FROM
		// Refugee r WHERE r.sex =:p").setParameter(0,sex);
		// int c = query.getSingleResult();
		// return c;
		List<Refugee> rl = new ArrayList<Refugee>();
		rl = em.createQuery("Select r from Refugee r where r.sex =:p").setParameter("p", sex).getResultList();
		return rl.size();
	}
	//
	// @Override
	// public int countRefugeePerAge(int a, int b) {
	// // TODO Auto-generated method stub
	// return 0;
	// }

	@Override
	public List<Refugee> findBestCandidates(String fieldOfWork) {
	
		String requete = "SELECT r " + "FROM Refugee r " + "WHERE r.fieldOfWork LIKE :f "
				+ "ORDER BY lower(r.highestDegree) DESC , lower(r.frenchlanguageLevel) DESC , lower(r.englishlanguageLevel) DESC , r.yearsOfExperience DESC ";

		return em.createQuery(requete, Refugee.class).setParameter("f", fieldOfWork).setMaxResults(3).getResultList();
	}

	@Override
	public boolean sendMail(String jobOfferTitle , String refugeeEmail, int id_jobOffer, int id_refugee) {
		SendAmail s = new SendAmail();
		s.send(jobOfferTitle,refugeeEmail,id_jobOffer,id_refugee);
		return true;
	}



}
