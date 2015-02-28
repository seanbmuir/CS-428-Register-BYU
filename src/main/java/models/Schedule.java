package models;

import java.util.List;

/**
 * @autor: Nick Humrich
 * @date: 1/17/14
 */
public class Schedule
{
	private String id;
	private String name;
	private int semesterID;
	private List<Section> classes;


	public Schedule(int semesterID)
	{
		this.semesterID = semesterID;
	}

	public Schedule(){}

	public String getName()
	{
		return name;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Section> getClasses()
	{
		return classes;
	}

	public void setClasses(List<Section> classes)
	{
		this.classes = classes;
	}

	public int getSemesterID()
	{
		return semesterID;
	}

	public void setSemesterID(int semesterID)
	{
		this.semesterID = semesterID;
	}

	public void addSection(Section section)
	{
		this.classes.add(section);
	}

	public void removeSection(Section section)
	{
		this.classes.remove(section);
	}
}
