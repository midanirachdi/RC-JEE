package tn.esprit.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.Course;
import tn.esprit.interfaces.CourseService;

@Stateless
@LocalBean
public class CourseImpl implements CourseService{

	@PersistenceContext   // vih +entites persistance  ...injectih v entity manager
	EntityManager em;     //gestionnaire entites

	@Override
	public boolean addCourse(Course c) {
		// TODO Auto-generated method stub
		/*em.persist(c);
		em.flush();
		System.out.println("Course ajouté avec succes");
*/
		if (c != null) {
			Date date_debut = c.getStartdate();
			Date date_fin = c.getEnddate();

			if (date_debut.before(date_fin) == true) {
				em.persist(c);
				return true;
			}
			return false;
		}
		return false;
		
	}

	@Override
	public void deleteCourse(Course c) {
		// TODO Auto-generated method stub
		em.remove(em.merge(c));

	}

	@Override
	public boolean updateCourse(Course c) {
		// TODO Auto-generated method stub
		//em.merge(c);	
		if (c != null) {
			Date date_debut = c.getStartdate();
			Date date_fin = c.getEnddate();

			if (date_debut.before(date_fin) == true) {
				em.merge(c);
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public Course findCourseById(int id) {
		// TODO Auto-generated method stub
		return em.find(Course.class,id);
	}

	@Override
	public List<Course> listAll() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT c FROM Course c",Course.class).getResultList();
	}

	@Override
	public List<Course> getCourseByName(String c) {
		// TODO Auto-generated method stub
		Query req = em.createQuery
				("select c from Course c where c.name like :x ");
			req.setParameter("x", "%" +c+ "%");
			List resultList = req.getResultList();
			return resultList;
	}

	@Override
	public List<Course> getCourseByDis(DistrictChef d) {
		Query req = em.createQuery
				("select c from Course c where c.dischef = :x ");
			req.setParameter("x",d);
			List resultList = req.getResultList();
			return resultList;
	}
	
	

}
