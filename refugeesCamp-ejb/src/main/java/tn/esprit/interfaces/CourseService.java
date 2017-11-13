package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.Course;

@Remote
public interface CourseService {
	
	public void addCourse(Course c);
	public void deleteCourse(Course c);
	public void updateCourse(Course c);
	public Course  findCourseById(int id);
	public List<Course> listAll();
	public List<Course> getCourseByName(String c);
	public List<Course> getCourseByDis(DistrictChef c);


}
