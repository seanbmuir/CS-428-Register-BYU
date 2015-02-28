package database;

import models.Course;
import models.Section;
import models.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import packages.Courses;

import java.util.ArrayList;

public class StudentDAOTest
{
	private final String testCourseID = "1234";
	private final String testStudentID = "reggiebyu";
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
	public void testAddCourse() throws Exception
	{
		String courseID = "98765";
		Course course = getTestCourse(courseID);
		Student student = getTestStudent(testStudentID);
		dao.addCourse(course, student);

		student = dao.getStudent(testStudentID);

		Courses courses = student.getPlannedCourses();
		Assert.assertTrue("Course not added", courses.contains(course));
	}

	@Test
	public void testRemoveCourse() throws Exception
	{
		dao.saveStudent(getTestStudent(testStudentID));
		dao.removeCourse(getTestCourse(testCourseID), getTestStudent(testStudentID));

		Student student = dao.getStudent(testStudentID);
		Assert.assertEquals("Planned course did not get removed", 0, student.getPlannedCourses().size());

	}

	private Student getTestStudent(String studentID)
	{
		Student student = new Student();
		student.setStudentId(studentID);

		Courses courses = new Courses();
		Course course = getTestCourse(testCourseID);
		courses.addCourse(course);

		student.setPlannedCourses(courses);
		student.setStudentId(studentID);
		return student;
	}

	private Course getTestCourse(String courseID)
	{
		Course course = new Course();
		course.setCourseID(courseID);
		course.setSections(new ArrayList<Section>());
		return course;
	}
}