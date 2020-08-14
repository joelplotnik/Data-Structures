/**
 * Employee class stores information about an employee
 * @author Joel Plotnik - 006642110
 */
public class Employee implements Comparable
{
   private String firstName;
   private String lastName;
   private char gender;
   private int tenure;
   private char rate;
   private double salary;

   /**
    * Constructor
    */
   public Employee()
   {
      firstName = "";
      lastName = "";
      gender = ' ';
      tenure = 0;
      rate = ' ';
      salary = 0;
   }

   /**
    * Overloaded Constructor
    * @param firstName String = firstName
    * @param lastName String = lastName
    * @param gender char = gender
    * @param tenure int = tenure
    * @param rate double = rate
    * @param salary double = salary
    */
   public Employee(String firstName, String lastName, char gender, int tenure, char rate, double salary) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.gender = gender;
      this.tenure = tenure;
      this.rate = rate;
      this.salary = salary;
   }

   /**
    * Overloaded Constructor for firefile.txt
    * @param firstName String firstName
    * @param lastName String lastName
    */
   public Employee(String firstName, String lastName)
   {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   /**
    * Get first name of employee
    * @return String firstName
    */
   public String getFirstName()
   {
      return firstName;
   }

   /**
    * Set first name of employee
    * @param firstName String
    */
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   /**
    * Get last name of employee
    * @return String lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * Set last name of employee
    * @param lastName String
    */
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   /**
    * Get gender of employee
    * @return char gender
    */
   public char getGender() {
      return gender;
   }

   /**
    * Set gender of employee
    * @param gender char
    */
   public void setGender(char gender) {
      this.gender = gender;
   }

   /**
    * Get tenure of employee
    * @return int tenure
    */
   public int getTenure() {
      return tenure;
   }

   /**
    * Set tenure of employee
    * @param tenure int
    */
   public void setTenure(int tenure) {
      this.tenure = tenure;
   }

   /**
    * Get rate of employee
    * @return double rate
    */
   public char getRate() {
      return rate;
   }

   /**
    * Set rate of employee
    * @param rate double
    */
   public void setRate(char rate) {
      this.rate = rate;
   }

   /**
    * Get employee salary
    * @return double salary
    */
   public double getSalary() {
      return salary;
   }

   /**
    * Set employee Salary
    * @param salary double
    */
   public void setSalary(double salary) {
      this.salary = salary;
   }

   /**
    * Compares one Employee to another Employee
    * @param o Object to be compared
    * @return int negative or positive depending on comparison
    */
   public int compareTo (Object o)
   {
      Employee e = (Employee) o;
      return lastName.compareTo(e.getLastName());
   }
}
