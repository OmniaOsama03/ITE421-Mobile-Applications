package omnia.adu.ac.ae.ite421_assignment2_1084595;

public class Staff
{
    private int id;
    private String fullName;
    private String department;
    private String position;
    private double salary;

    //Constructor
    public Staff(int id, String fullName, String department, String position, double salary)
    {
        this.id = id;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }

    //Getters for the private fields
    public int getId()
    {
        return id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getDepartment()
    {
        return department;
    }

    public String getPosition()
    {
        return position;
    }

    public double getSalary()
    {
        return salary;
    }
}
