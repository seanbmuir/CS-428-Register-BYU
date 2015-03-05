package database;

import com.mongodb.DB;
import exceptions.DatabaseException;
import models.Course;
import models.Section;
import models.Student;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
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
	private MongoCollection collection;


	@Before
	public void setUp()
	{
		DB db = TestDatabase.getDB();

		// Start with a clean database before each test
		collection = (new Jongo(db)).getCollection(StudentDAO.getCollectionID());
		TestDatabase.dropCollection(collection);

		dao = new StudentDAO(db);
	}

	@Test
	public void testAddStudent() throws Exception
	{
		Student student = getTestStudent(testStudentID);

		dao.saveStudent(student);

		Student fromDB = dao.getStudent(testStudentID);
		Assert.assertNotEquals("Student is null", null, fromDB);
		Assert.assertEquals("Student ID not the same", testStudentID, student.getStudentId());
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
		// Insert and verify it inserted
		Student student = getTestStudent(testStudentID);
		dao.saveStudent(student);
		Student fromDB = dao.getStudent(testStudentID);
		Assert.assertNotEquals("Student is null", null, fromDB);


		fromDB = null;
		dao.deleteStudent(student);
		try
		{
			fromDB = dao.getStudent(testStudentID);
			Assert.fail("DatabaseException not thrown when it should have");
		}
		catch (DatabaseException e)
		{
			Assert.assertNull("Student wasn't deleted", fromDB);
		}
	}

	@Test
	public void testAddSection() throws Exception
	{
		Student student = getTestStudent(testStudentID);
		dao.saveStudent(student);
		Student fromDB = dao.getStudent(testStudentID);

		Assert.assertFalse(fromDB.getSchedules().contains(new Section())); //TODO finish this
		Section section = new Section();
		String sectionID = "OLAF";
		dao.addSection(section, fromDB);

	}

	@Test
	public void testRemoveSection() throws Exception
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