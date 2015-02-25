package models;

/**
 * @author: Nick Humrich
 * @date: 1/17/14
 */
public class Semester {

  private String name;
    private int id;

  public String getName() {
    return name;
  }

  public void setName(String name, int id) {
    this.name = name;
      this.id = id;
  }
    
    public int getID()
    {
        return id;
    }
    
    public void setID(int id){
        this.id = id;
    }
}
