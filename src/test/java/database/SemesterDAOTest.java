package database;

import models.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SemesterDAOTest {
	private SemesterDAO dao;

	@Before
	public void setUp()
	{
		dao = new SemesterDAO(DatabaseRegistrationStore.getDB());
	}

	@Test
	public void testGetFakeSemester() throws Exception
	{
		int semesterId = 20157;
		Semester s = null;
		try{
			s = dao.getSemester(semesterId);
			Assert.fail();
		}
		catch(Exception e){
			Assert.assertEquals("Semester is null", null, s);
		}
		Assert.assertEquals("Semester is null", null, s);
	}
	
	@Test
	public void saveFakeSemester() throws Exception
	{
		int semesterId = 20158;
		String fakeSemester = "Fake Semester";
		Semester s = new Semester();
		s.setID(semesterId);
		s.setName(fakeSemester);
		dao.addSemester(s);
		Semester s2 = dao.getSemester(semesterId);
		Assert.assertEquals("Semester id is the same", s.getID(), s2.getID());
		Assert.assertEquals("Semester name is the same", s.getName(),s2.getName());
	}
	
	@Test
	public void getF2015Semester() throws Exception
	{
		int semesterId = 20155;
		Semester s = dao.getSemester(semesterId);
		Assert.assertEquals("Semester id is the same", s.getID(), semesterId);
	}
}
