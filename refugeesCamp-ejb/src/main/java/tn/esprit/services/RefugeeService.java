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

@Stateless
@LocalBean
public class RefugeeService implements RefugeeInterfaceRemote,RefugeeInterfaceLocal{

	@PersistenceContext(unitName = "refugeesCamp-ejb")
	public EntityManager em;
	
	@Override
	public void add(Refugee refugee) {
		em.persist(refugee); // refugee est gérée par entity manager 
		// em.flush(); // forcer l'instruction
		System.out.println("Refugee added successfuly");
		
	}

	@Override
	public void update(Refugee refugee) {
//		int result = em.createQuery("update Refugee r set r.dateOfBirth =: p0 , r.firstname =: p1 , r.lastName =: p2 , r.nationality =: p3 , r.sex =: p4 where r.id =:p5")
//			.setParameter(0,refugee.getDateOfBirth()).setParameter(1,refugee.getFirstname()).setParameter(2,refugee.getLastName())
//			.setParameter(3,refugee.getNationality()).setParameter(4,refugee.getSex()).setParameter(5,id).executeUpdate();
		Refugee r = em.merge(refugee); // merge renvoie l'objet géré et le MAJ 
		System.out.println("Refugee updated successfuly");
		
	}

	@Override
	public void delete(Refugee refugee) {
		em.remove(em.merge(refugee));
		System.out.println("Refugee deleted successfuly");
		
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
	public int countRefugeePerSex(String sex) {
//		TypedQuery<int> query =(int) em.createQuery("SELECT COUNT(r) FROM Refugee r WHERE r.sex =:p").setParameter(0,sex);
//		int c = query.getSingleResult();
		List<Refugee> rl =new ArrayList<Refugee>();
		rl = em.createQuery("Select r from Refugee r where r.sex =:p").setParameter("p",sex).getResultList();
		return rl.size();
	}
//
//	@Override
//	public int countRefugeePerAge(int a, int b) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
