import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Payroll class retrieves, updates, and manipulates a small payroll database.
 * @author Joel Plotnik - 006642110
 */
public class Payroll
{
   private PrintWriter pw;
   private ObjectList employees;
   private ObjectList hiredEmployees;
   private ObjectList firedEmployees;

   /**
    * Overloaded constructor sets all private variables to appropriate values
    * @param pw PrintWriter object to print to text file
    */
   public Payroll(PrintWriter pw)
   {
      this.pw = pw;
      this.employees = new ObjectList();
      this.hiredEmployees = new ObjectList();
      this.firedEmployees = new ObjectList();
   }

   /**
    * Read each line of payfile.txt, hirefile.txt, and firefile.txt.
    * Store data in appropriate ObjectList
    */
   public void readLines()
   {
      try
      {
         /////////////////////// CURRENT EMPLOYEES /////////////////////////////
         // Create a new file object that points to the payfile.txt file
         File emp_file_ptr = new File("payfile.txt");
         Scanner in = new Scanner(emp_file_ptr); // Create new Scanner object

         // While payfile.txt has a line to read
         while (in.hasNext())
         {
            String empData = in.nextLine(); // String to store employee info
            String[] tokens = empData.split("[ ]+");
            Employee empInput = new Employee(tokens[0], tokens[1], tokens[2].charAt(0),
                    Integer.parseInt(tokens[3]), tokens[4].charAt(0), Float.parseFloat(tokens[5]));

            employees.addLast(empInput);
         }
         in.close(); // Close file

         //////////////////////////// NEW HIRES /////////////////////////////////
         // Create a new file object that points to the hirefile.txt file
         File hire_file_ptr = new File("hirefile.txt");
         Scanner in_hire = new Scanner(hire_file_ptr); // Create new Scanner object

         // While hirefile.txt has a line to read
         while (in_hire.hasNext())
         {
            String empData = in_hire.nextLine(); // String to store employee info
            String[] tokens = empData.split("[ ]+");
            Employee empInput = new Employee(tokens[0], tokens[1], tokens[2].charAt(0),
                    Integer.parseInt(tokens[3]), tokens[4].charAt(0), Float.parseFloat(tokens[5]));

            hiredEmployees.addLast(empInput);
         }
         in_hire.close(); // Close file

         ////////////////////////// FIRED EMPLOYEES ///////////////////////////////
         // Create a new file object that points to the hirefile.txt file
         File fire_file_ptr = new File("firefile.txt");
         Scanner in_fire = new Scanner(fire_file_ptr); // Create new Scanner object

         // While firefile.txt has a line to read
         while (in_fire.hasNext())
         {
            String empData = in_fire.nextLine(); // String to store employee info
            String[] tokens = empData.split("[ ]+");
            Employee empInput = new Employee(tokens[0], tokens[1]);

            firedEmployees.addLast(empInput);
         }
         in_fire.close(); // Close file

      }
      catch (FileNotFoundException e)
      {
         // Display error message if file is not found
         System.out.println("File not found");
         System.exit(1);
      }

   }

   /**
    * Output the contents of the info field of each ObjectListNode
    */
   public void contents()
   {
      header(); // Output header to terminal and file

      ObjectListNode empNode = employees.getFirstNode(); // Create a node to hold employee

      // While the node is not pointing t null
      while(empNode != null)
      {
         Employee emp = (Employee) empNode.getInfo(); // Create an employee to store employee data

         // Print headings to terminal
         System.out.println(String.format("%-8s %-8s %8s %8s %8s %15.2f",
                 emp.getFirstName(), emp.getLastName(), emp.getGender(),
                 emp.getTenure(), emp.getRate(), emp.getSalary()));

         // Print headings to file
         pw.println(String.format("%-8s %-8s %8s %8s %8s %15.2f",
                 emp.getFirstName(), emp.getLastName(), emp.getGender(),
                 emp.getTenure(), emp.getRate(), emp.getSalary()));

         empNode = empNode.getNext(); // Get the next node
      }

      System.out.println();
      pw.println();

   }

   /**
    * Output header to organize employee data
    */
   private void header()
   {
      // Display header info to terminal
      System.out.println("************************************************************");
      System.out.println("********************  PAYROLL DATABASE  ********************");
      System.out.println("************************************************************");
      System.out.println();
      System.out.println(String.format("%-8s %-8s %13s %8s %6s %12s",
              "First", "Last", "Gender", "Tenure", "Rate", "Salary"));
      System.out.println("============================================================");

      // Display header info to file
      pw.println("************************************************************");
      pw.println("********************  PAYROLL DATABASE  ********************");
      pw.println("************************************************************");
      pw.println();
      pw.println(String.format("%-8s %-8s %13s %8s %6s %12s",
              "First", "Last", "Gender", "Tenure", "Rate", "Salary"));
      pw.println("============================================================");

   }

   /**
    * Output number of employees
    */
   public void numEmp()
   {
      // Display number of employees to terminal
      System.out.println("Number of employees: " + employees.size());
      System.out.println();

      // Display number of employees to file
      pw.println("Number of employees: " + employees.size());
      pw.println();
   }

   /**
    * Output the female employees on the payroll
    */
   public void women()
   {
      ObjectListNode empNode = employees.getFirstNode(); // Create a node that points to the first employee
      ObjectList womenList = new ObjectList(); // Create a new list to hold female employees

      // While empNode is not pointing to null
      while (empNode != null)
      {
         Employee women = (Employee) empNode.getInfo(); // Create women employee object to store employee info

         // If the employees gender is female
         if (women.getGender() == 'F')
         {
            womenList.addLast(women); // Add the woman to the list
         }

         empNode = empNode.getNext(); // Get the next node
      }

      // Display to terminal
      System.out.println("Women on payroll:");
      System.out.println(String.format("%-8s %-8s", "First", "Last"));
      System.out.println("================");

      // Display to file
      pw.println("Women on payroll:");
      pw.println(String.format("%-8s %-8s", "First", "Last"));
      pw.println("================");

      ObjectListNode womenNode = womenList.getFirstNode(); // Create a node object to store a women employee

      // When womenNode is not pointing to null
      while(womenNode != null)
      {
         // Store given female employee info in empWomanGetInfo and display info
         Employee empWomanGetInfo = (Employee) womenNode.getInfo();
         System.out.println(String.format("%-8s %-8s", empWomanGetInfo.getFirstName(), empWomanGetInfo.getLastName()));
         pw.println(String.format("%-8s %-8s", empWomanGetInfo.getFirstName(), empWomanGetInfo.getLastName()));
         womenNode = womenNode.getNext();
      }

      System.out.println();
      pw.println();

   }

   /**
    * Output weekly employees who make more than $35,000 per year and have been with the company for five years
    */
   public void bigMoneyAndTime()
   {
      ObjectListNode empNode = employees.getFirstNode(); // Create node to point to first employee in the list
      ObjectList timeAndMoneyList = new ObjectList(); // Create a new list to store richest employee info

      // BigMoneyAndTime variables
      int thirtyFiveK = 35000;
      int tenureFive = 5;
      int weeksInYear = 52;
      char weekly = 'W';

      // While empNode is not pointing to null
      while (empNode != null)
      {
         Employee richEmp = (Employee) empNode.getInfo(); // Create employee to get richest employee info

         // If the employee is weekly AND has been employee over 5 years AND makes more than $35,000 a year
         if ((richEmp.getRate() == weekly) && (richEmp.getTenure() > tenureFive) && ((richEmp.getSalary() * weeksInYear) > thirtyFiveK))
         {
            timeAndMoneyList.addLast(richEmp); // Add employee to the rich list
         }

         empNode = empNode.getNext(); // Get next node
      }

      // Display to terminal
      System.out.println("Highest earning and longest standing employees:");
      System.out.println(String.format("%-8s %-8s %10s", "First", "Last", "Salary"));
      System.out.println("============================");

      // Display to file
      pw.println("Highest earning and longest standing employees:");
      pw.println(String.format("%-8s %-8s %10s", "First", "Last", "Salary"));
      pw.println("============================");

      ObjectListNode richEmpNode = timeAndMoneyList.getFirstNode(); // Create a node object to store rich employee info

      // While richEmpNode is not pointing to null
      while(richEmpNode != null)
      {
         Employee richEmpNodeInfo = (Employee) richEmpNode.getInfo(); // Create an employee to store rich employee info
         System.out.println(String.format("%-8s %-8s %10.2f", richEmpNodeInfo.getFirstName(),
                 richEmpNodeInfo.getLastName(), richEmpNodeInfo.getSalary()));
         pw.println(String.format("%-8s %-8s %10.2f", richEmpNodeInfo.getFirstName(),
                 richEmpNodeInfo.getLastName(), richEmpNodeInfo.getSalary()));
         richEmpNode = richEmpNode.getNext(); // Get next node
      }

      System.out.println();
      pw.println();

   }

   /**
    * Give a raise to all employee that make either less than $10/hr or $50/wk
    */
   public void giveRaise()
   {
      ObjectListNode empNode = employees.getFirstNode(); // Create node to store first employee
      ObjectList hourlyList = new ObjectList(); // Create a list to hold hourly employees
      ObjectList weeklyList = new ObjectList(); // Create a list to hold weekly employees

      // giveRaise variables
      double hrRaise = 0.75;
      double tenDollars = 10.00;
      double wkRaise = 50.00;
      double thirtyFiveHun = 350.00;
      char hourly = 'H';
      char weekly = 'W';

      // While empNode is not pointing to null
      while (empNode != null)
      {
         Employee emp = (Employee) empNode.getInfo(); // Create employee to store employee info

         // If employee rate is hourly and employee earns less than $10
         if ((emp.getRate() == hourly) && (emp.getSalary() < tenDollars))
         {
            emp.setSalary(emp.getSalary() + hrRaise); // Give employee a raise
            hourlyList.addLast(emp); // Add employee to hourly list
         }
         // If employee rate is weekly and employee earns less than $350.00
         else if ((emp.getRate() == weekly) && (emp.getSalary() < thirtyFiveHun))
         {
            emp.setSalary((emp.getSalary() + wkRaise)); // Give employee a raise
            weeklyList.addLast(emp); // Add employee to weekly list
         }

         empNode = empNode.getNext(); // Get next node
      }

      // Display to terminal
      System.out.println("Employees receiving a raise:");
      System.out.println(String.format("%-8s %-8s %10s", "First", "Last", "Salary"));
      System.out.println("============================");

      // Display to file
      pw.println("Employees receiving a raise:");
      pw.println(String.format("%-8s %-8s %10s", "First", "Last", "Salary"));
      pw.println("============================");

      ObjectListNode hourlyListNode = hourlyList.getFirstNode(); // Create node to hold hourly employee

      // While hourlyListNode is not pointing to null
      while(hourlyListNode != null)
      {
         Employee hourlyEmp = (Employee) hourlyListNode.getInfo(); // Get hourly employee info
         System.out.println(String.format("%-8s %-8s %10.2f", hourlyEmp.getFirstName(),
                 hourlyEmp.getLastName(), hourlyEmp.getSalary()));
         pw.println(String.format("%-8s %-8s %10.2f", hourlyEmp.getFirstName(),
                 hourlyEmp.getLastName(), hourlyEmp.getSalary()));
         hourlyListNode = hourlyListNode.getNext(); // Get next node
      }

      ObjectListNode weeklyListNode = weeklyList.getFirstNode(); // Create a node to hold weekly employee

      // While weeklyListNode is not pointing to null
      while(weeklyListNode != null)
      {
         Employee weeklyEmp = (Employee) weeklyListNode.getInfo(); // Get weekly employee info
         System.out.println(String.format("%-8s %-8s %10.2f", weeklyEmp.getFirstName(),
                 weeklyEmp.getLastName(), weeklyEmp.getSalary()));
         pw.println(String.format("%-8s %-8s %10.2f", weeklyEmp.getFirstName(),
                 weeklyEmp.getLastName(), weeklyEmp.getSalary()));
         weeklyListNode = weeklyListNode.getNext(); // Get next node
      }

      System.out.println();
      pw.println();
   }

   /**
    * Sort employees into alphabetical order according to last/first name
    */
   public void sortLast()
   {
      ObjectListNode emp = employees.getFirstNode(); // Create node to store first employee
      ObjectList empSortedList = new ObjectList(); // Create list to store sorted employees

      // While emp is not pointing to null
      while (emp != null)
      {
         Employee temp = (Employee) employees.removeFirst(); // Remove the first employee in the list
         empSortedList.insert(temp); // Insert employee into correct position in list

         emp = employees.getFirstNode(); // Get the next first employee...
      }

      employees = empSortedList.copyList(); // Copy sorted list into the employee list

      // Display to terminal
      System.out.println("Employees sorted alphabetically according to their last/first name:");
      System.out.println(String.format("%-8s %-8s %10s", "First", "Last", "Salary"));
      System.out.println("============================");

      // Display to file
      pw.println("Employees sorted alphabetically according to their last/first name:");
      pw.println(String.format("%-8s %-8s %10s", "First", "Last", "Salary"));
      pw.println("============================");

      ObjectListNode empNode = employees.getFirstNode(); // Create node to store employee

      // While empNode is not pointing to null
      while(empNode != null)
      {
         Employee empNodeInfo = (Employee) empNode.getInfo(); // Create employee to store employee info
         System.out.println(String.format("%-8s %-8s %10.2f", empNodeInfo.getFirstName(),
                 empNodeInfo.getLastName(), empNodeInfo.getSalary()));
         pw.println(String.format("%-8s %-8s %10.2f", empNodeInfo.getFirstName(),
                 empNodeInfo.getLastName(), empNodeInfo.getSalary()));
         empNode = empNode.getNext(); // Get next node
      }

      System.out.println();
      pw.println();
   }

   /**
    * Add new employees to the payroll system
    */
   public void hire()
   {
      ObjectListNode emp = hiredEmployees.getFirstNode(); // Create node to store hired employee from file

      // While emp is not pointing to null
      while(emp != null)
      {
         Employee temp = (Employee) emp.getInfo(); // Create temporary employee
         employees.insert(temp); // Insert the employee into correct position in the employee list

         emp = emp.getNext(); // Get the next node
      }

      // Display to terminal
      System.out.println("Updated payroll with recently HIRED employees:");
      System.out.println(String.format("%-8s %-8s", "First", "Last"));
      System.out.println("================");

      // Display to file
      pw.println("Updated payroll with recently HIRED employees:");
      pw.println(String.format("%-8s %-8s", "First", "Last"));
      pw.println("================");

      ObjectListNode newEmpNode = employees.getFirstNode(); // Create a new node pointing to the first employee in the list

      // While newEmpNode is not pointing to null
      while(newEmpNode != null)
      {
         Employee newEmpNodeInfo = (Employee) newEmpNode.getInfo(); // Create employee to store employee information
         System.out.println(String.format("%-8s %-8s", newEmpNodeInfo.getFirstName(), newEmpNodeInfo.getLastName()));
         pw.println(String.format("%-8s %-8s", newEmpNodeInfo.getFirstName(), newEmpNodeInfo.getLastName()));
         newEmpNode = newEmpNode.getNext(); // Get next node
      }

      System.out.println();
      pw.println();

   }

   /**
    * Remove employees from the payroll system
    */
   public void fire()
   {
      ObjectListNode emp = firedEmployees.getFirstNode(); // Create a new node that points to the first employee to be fired

      // While emp is not pointing to null
      while(emp != null)
      {
         Employee temp = (Employee) emp.getInfo(); // Get fired employee info and store in temp
         employees.remove(temp); // Remove employee from employee list

         emp = emp.getNext(); // Get next node
      }

      // Display to terminal
      System.out.println("Updated payroll with recently FIRED employees:");
      System.out.println(String.format("%-8s %-8s", "First", "Last"));
      System.out.println("================");

      // Display to file
      pw.println("Updated payroll with recently FIRED employees:");
      pw.println(String.format("%-8s %-8s", "First", "Last"));
      pw.println("================");

      ObjectListNode newEmpNode = employees.getFirstNode(); // Create new node to point to first employee in list

      // While newEmpNode is not pointing to null
      while(newEmpNode != null)
      {
         Employee newEmpNodeInfo = (Employee) newEmpNode.getInfo(); // Create new employee to store employee information
         System.out.println(String.format("%-8s %-8s", newEmpNodeInfo.getFirstName(), newEmpNodeInfo.getLastName()));
         pw.println(String.format("%-8s %-8s", newEmpNodeInfo.getFirstName(), newEmpNodeInfo.getLastName()));
         newEmpNode = newEmpNode.getNext(); // Get next node
      }

      System.out.println();
      pw.println();
   }

}
