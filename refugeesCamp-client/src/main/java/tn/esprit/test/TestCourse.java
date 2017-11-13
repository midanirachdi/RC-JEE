package tn.esprit.test;

import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.entities.Course;
import tn.esprit.interfaces.CourseService;

public class TestCourse {
	
	public static void main(String[] args) throws NamingException {
		
		InitialContext ctx = new InitialContext();
		CourseService proxy1=(CourseService)ctx.lookup("refugeesCamp-ear/refugeesCamp-ejb/CourseImpl!tn.esprit.interfaces.CourseService");
	//////// add/////////////
		Course projet=new Course();
		Date date=new Date();
		projet.setName("angular");
		projet.setStartdate(date);
		projet.setEnddate(date);
		projet.setDescription("na9es");
	    proxy1.addCourse(projet);
	    System.out.println("l'ajout a été effectué lhmd");
	    
	    ///////////// liste
//	    System.out.println("************************affichage**************");
//	    System.out.println(proxy1.listAll());
	  	/*List<Course> list1=new ArrayList<>();
	  	list1=proxy1.listAll();
	  	for(Course projet1:list1)
		{
			System.out.println(projet1.getType());
		}*/
	   /* System.out.println("************************suppresion**************");
	    Course n= (Course)proxy1.findCourseById(2);
		System.out.println(n.getType());
	    proxy1.deleteCourse(n);
		System.out.println("succes");*/
	}
}
