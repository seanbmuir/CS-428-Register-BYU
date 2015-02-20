package parser.catalog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import models.*;
import packages.Courses;

public class CatalogParser_NEW {
	
	public static void main(String[] args)
	{
		try {
			System.out.println(parseCourses("UPDATE_DATABASE.txt").toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Courses parseCourses(String filePath) throws IOException
	{
		Scanner scan = new Scanner(new File(filePath)).useDelimiter("#");
		String tmp;
		int currentIndex = 0;
		Section currentSection = new Section();
		Course currentCourse = new Course();
		Courses allCourses = new Courses();
		boolean firstTime = true;
		while(scan.hasNext())
		{
			tmp = scan.next().trim();
			if(tmp.isEmpty())
			{
				if(currentIndex == 14 || currentIndex == 8)//This check is for when there are no notes/outcomes or no professor Listed
					currentIndex++;
				continue;
			}
			if(currentIndex == 6 && (tmp.equals("L") || tmp.equals("N")))
				continue; // These show up I After section number L means Lab section, N i think is study abroad
				
			
			switch (currentIndex)
			{
				case 0:
//					System.out.println("Course Number Unique: " + tmp);
					if(tmp != currentCourse.getCourseID())
					{
						if(!firstTime)
						{
							allCourses.addCourse(currentCourse);
							currentCourse = new Course();
							currentCourse.setCourseID(tmp);
							currentSection.setCourseID(tmp);
						}
						else
						{
							firstTime = false;
							currentCourse.setCourseID(tmp);
							currentSection.setCourseID(tmp);
						}
							
					}
					break;
				case 1:
//					System.out.println("Title Code: " + tmp);
					currentCourse.setNewTitleCode(tmp);
					break;
				case 2:
//					System.out.println("Dept Code: " + tmp);
					currentCourse.setDepartment(tmp);
					break;
				case 3:
//					System.out.println("Credit Type?: " + tmp);
					currentCourse.setRegistrationType(tmp);
					break;
				case 4:
//					System.out.println("Course Number: " + tmp);
					currentCourse.setCourseNumber(tmp);
					break;
				case 5:
//					System.out.println("Section Number: " + tmp);
					currentSection.setSectionID(tmp);
					break;
				case 6:
//					System.out.println("DAY or NIGHT: " + tmp);
					currentSection.setSectionType(tmp);
					break;
				case 7:
//					System.out.println("Course Title: " + tmp);
					currentCourse.setCourseName(tmp);	
					break;
				case 8:
//					System.out.println("Prof Name: " + tmp);
					currentSection.setProfessor(tmp);
					break;
				case 9:
//					System.out.println("Number of Credits: " + tmp);
					currentSection.setCredits(tmp);
					break;
				case 10:
//					System.out.println("Days Taught: " + tmp);
					currentSection.setDaysTaught(tmp.split("\n"));
					break;
				case 11:
//					System.out.println("Start Time/s: " + tmp);
					currentSection.setStartTimes(tmp.split("\n"));
					break;
				case 12:
//					System.out.println("End Time/s: " + tmp);
					currentSection.setEndTimes(tmp.split("\n"));
					break;
				case 13:
//					System.out.println("Location/s: " + tmp);
					currentSection.setLocations(tmp.split("\n"));
					break;
				case 14:
//					System.out.println("Notes: " + tmp);
					currentCourse.setOutcomes(Arrays.asList(tmp.split("\n")));
					break;
				case 15:
//					System.out.println("How full is the class: " + tmp);
					String[] seats = tmp.split("\\s*/\\s* ");
					currentSection.setSeatsAvailable(seats[0]);
					currentSection.setTotalSeats(seats[1]);
					break;
				case 16:
//					System.out.println("Weight List: " + tmp);
					currentSection.setWaitList(tmp);
					break;
				default:
					System.err.println("SHOULDN'T BE HERE");
			}
			currentIndex++;		
			if(currentIndex > 16)
			{
				currentIndex = 0;
				currentCourse.addSection(currentSection);
				currentSection = new Section();
				
			}
//			System.out.println(currentIndex);
				
			
			
		}
		
		scan.close();
		return allCourses;
	}

}