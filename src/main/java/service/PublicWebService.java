package service;

import database.*;
import exceptions.ForbiddenException;
import models.Department;
import models.Schedule;
import models.Student;
import packages.Courses;
import packages.Departments;
import packages.Schedules;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author: Nick Humrich
 * @date: 1/18/14
 */
public class PublicWebService
{
	private RegistrationStore tempRegistrationStore;
	private RegistrationStore registrationStore;
	private ISemesterDAO semesterDAO;
	private IStudentDAO studentDAO;

	public PublicWebService()
	{
		//ToDo: replace below to read from config and use a factory to choose the store
		semesterDAO = new SemesterDAO(DatabaseRegistrationStore.getDB());
		studentDAO = new StudentDAO(DatabaseRegistrationStore.getDB());
	}

	public Courses getCourses(String sem_id)
	{
		int semesterID = Integer.parseInt(sem_id);
		try{
			return semesterDAO.getSemester(semesterID).getCourses();
		}catch(Exception e){
			//semester does not exist
			return new Courses();//empty set
		}
	}

	public void handleRegistration(String courseInfo, String ticket)
	{
		HttpsURLConnection connection = null;
		try {
			URL url = new URL("https://gamma.byu.edu/ry/ae/prod/registration/cgi/regOfferings.cgi");
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Cookie", "BYU-Web-Session=" + courseInfo);
			int b = courseInfo.getBytes().length;
			connection.setRequestProperty("Content-Length", "" + b);

			connection.setDoInput(true);
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

			wr.writeBytes(courseInfo);
			wr.flush();
			wr.close();

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			//return response.toString();roperties()
			System.out.println(response);


		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Schedules getAllSchedulesForUser(String uid)
	{
		Schedules schedules = new Schedules();
		Student student = studentDAO.getStudent(uid);
		List<Schedule> list = student.getSchedules().getSchedules();
		for (Schedule s : list) {
			s.setId(AuthenticationService.encodeId(Integer.parseInt(s.getId())));
		}

		schedules.setSchedules(list);
		return schedules;
	}


	public Schedule getSchedule(String uid, String sid)
	{
		int scheduleId = AuthenticationService.decodeId(sid);
		int userId = AuthenticationService.decodeId(uid);

		int ownerId = tempRegistrationStore.getOwningUserForSchedule(scheduleId);

		if (ownerId != userId) {
			throw new ForbiddenException("Not allowed to retrieve this schedule");
		}
		Schedule schedule = tempRegistrationStore.getSchedule(scheduleId);
		schedule.setId(sid);
		return schedule;
	}

	public void addSchedule(String uid, Schedule schedule)
	{
		int userId = AuthenticationService.decodeId(uid);

		tempRegistrationStore.addSchedule(userId, schedule);
	}

	public void editSchedule(String uid, String sid, Schedule schedule)
	{
		int userId = AuthenticationService.decodeId(uid);
		int scheduleId = AuthenticationService.decodeId(sid);

		int ownerId = tempRegistrationStore.getOwningUserForSchedule(scheduleId);

		if (ownerId != userId) {
			throw new ForbiddenException("Not allowed to edit this schedule");
		}

		tempRegistrationStore.editSchedule(scheduleId, schedule);
	}

	public void removeSchedule(String uid, String sid)
	{
		int userId = AuthenticationService.decodeId(uid);
		int scheduleId = AuthenticationService.decodeId(sid);

		int ownerId = tempRegistrationStore.getOwningUserForSchedule(scheduleId);

		if (ownerId != userId) {
			throw new ForbiddenException("Not allowed to remove this schedule");
		}

		tempRegistrationStore.removeSchedule(scheduleId);

	}

	public Departments getAllDepartments()
	{
		Departments departments = new Departments();
		List<Department> departmentList = tempRegistrationStore.getAllDepartments();
		departments.setDepartments(departmentList);

		return departments;
	}


}
