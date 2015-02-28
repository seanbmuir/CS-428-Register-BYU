package database;

import models.Course;
import models.Section;
import models.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOTest
{
	private StudentDAO dao;

	@Before
	public void setUp()
	{
		dao = new StudentDAO(DatabaseRegistrationStore.getDB());
	}

	@Test
	public void testAddStudent() throws Exception
	{
		String studentID = "reggiebyu";
		Student student = getTestStudent(studentID);

		dao.saveStudent(student);

		Student fromDB = dao.getStudent(studentID);
		Assert.assertNotEquals("Student is null", null, fromDB);
		Assert.assertEquals("Student ID not the same", studentID, student.getStudentId());
	}
	
	@Test
	public void testStudentDoesNotExist() throws Exception
	{
		String studentID = "____fakeUser_____";
		Student fromDB = null;
		try{
			fromDB = dao.getStudent(studentID);
			Assert.fail();//should not get to this line of code
		}catch(Exception e){
			Assert.assertNull(fromDB);
		}
		Assert.assertNull(fromDB);
	}

	@Test
	public void testDeleteStudent() throws Exception
	{

	}

	@Test
	public void testAddSection() throws Exception
	{

	}

	@Test
	public void testRemoveSection() throws Exception
	{

	}

	@Test
	public void testGetStudent() throws Exception
	{

	}

	@Test
	public void testAddCourses() throws Exception
	{

	}

	@Test
	public void testRemoveCourses() throws Exception
	{

	}

	private Student getTestStudent(String studentID)
	{
		Student student = new Student();
		student.setStudentId(studentID);
		List<Course> courses = new ArrayList<>();
		Course course = new Course();
		course.setCourseID("1234");
		course.setSections(new ArrayList<Section>());
		courses.add(course);
		student.setPlannedCourses(courses);
		student.setStudentId(studentID);
		return student;
	}
}