import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Hash class used to allow certain words to be selected from
 * the text, get its hash, and determine whether
 * or not it appears in the hash table.
 * @author Joel Plotnik - 006642110
 */
public class Hash
{
   // Private class variables
   private PrintWriter pw;
   private int tableSize;
   private String[] hashTable;
   private int hashFuncUsed;
   private int resCollisions;
   private int hashFuncCollisions;

   /**
    * Constructor sets default values for Hash private variables
    * @param pw Print Writer
    */
   public Hash(PrintWriter pw)
   {
      this.pw = pw;
      tableSize = 37;
      hashTable = new String[tableSize];
      hashFuncUsed = 0;
      resCollisions = 0;
      hashFuncCollisions = 0;
   }

   /**
    * Get the words to be omitted from getty.txt
    */
   public void getOmit()
   {
      try{
         // Create a new file object that points to omit.txt
         File omit_ptr = new File("omit.txt");
         Scanner inOmit = new Scanner(omit_ptr); // Create new Scanner object

         // Variable to store the hash code
         int hash;

         // While omit.txt has another line
         while(inOmit.hasNextLine())
         {
            String omitWord = inOmit.nextLine(); // Store line/word in omitOwrd
            String[] tokens = omitWord.split("\\s+"); // Place word in token
            hash = getHash(tokens[0]); // Get hash code
            loadHashTable(hash, tokens[0]); // Load element into the hash table
         }

      }
      catch(FileNotFoundException e)
      {
         // Display error message if file is not found
         System.out.println("File not found");
         System.exit(1);
      }
   }

   /**
    * Self contained hash function uses a mathematical calculation to
    * return a value within the range of the given hash table size.
    * @param s String (key)
    * @return int (sum * 83) % tableSize;
    */
   private int getHash(String s)
   {
      int tableSize = 37; // Size of hash table
      int sum = 0; // Variable to store sum of strong chars added together

      // Loop through characters of the string
      for (int i = 0; i < s.length(); i++)
      {
         // Add the characters of the string together
         sum += (int) s.charAt(i);
      }

      // Multiply the sum of the string characters by 83 and return the
      // remainder of the product divided by the size of the hash table
      return (sum * 83) % tableSize;
   }

   /**
    * Load the hash table with words to omit from getty.txt
    * @param hash int
    * @param s String
    */
   private void loadHashTable(int hash, String s)
   {
      // If the hash index value of the hash table is null
      if (hashTable[hash] == null)
      {
         // Load the unimportant word into the hash table at 'hash' location
         hashTable[hash] = s;
         hashFuncUsed++; // Hash function was used
      }
      else
      {
         hashFuncCollisions++; // Hash index had an element

         // While the hash index of the hash table has an element
         while (hashTable[hash] != null)
         {
            hash = (hash + 1) % tableSize; // Move the next hash index position

            if (hashTable[hash] != null)
            {
               resCollisions++; // A resolution collision occurred
            }

            // If we are at the last index of the hash table
            if (hash > tableSize - 1)
            {
               hash = 0; // Move the zero index of the hash table
            }
         }

         // Load the unimportant word into the hash table at 'hash' location
         hashTable[hash] = s;
         hashFuncUsed++; // Hash function was used
      }
   }

   /**
    * Output a description of the hash function
    * Output the hash table including array indices of each item in the hash table
    * Output the load factor of the hash table
    */
   public void outputHashTableInfo()
   {
      System.out.println();
      System.out.println();
      System.out.println("*****************************************************************************************");
      System.out.println("***************************   Hash Function Description   *******************************");
      System.out.println("*****************************************************************************************");
      System.out.println("The hash function used is self contained and uses a mathematical calculation to return\n" +
              "a value within the range of the given hash table size. First the size of the hash table\n" +
              "is defined and a sum variable is created to store the sum of the characters of the string\n" +
              "being passed into the function. After trying many prime numbers, I chose the lowest prime\n" +
              "number I could find that would give me a total of 8 collisions which was 83. Although, 157\n" +
              "or 823 also produce 8 collisions. I multiply the sum by 83 and return the remainder of the\n" +
              "product divided by the size of the hash table.");
      pw.println();
      pw.println();
      pw.println("*****************************************************************************************");
      pw.println("***************************   Hash Function Description   *******************************");
      pw.println("*****************************************************************************************");
      pw.println("The hash function used is self contained and uses a mathematical calculation to return\n" +
              "a value within the range of the given hash table size. First the size of the hash table\n" +
              "is defined and a sum variable is created to store the sum of the characters of the string\n" +
              "being passed into the function. After trying many prime numbers, I chose the lowest prime\n" +
              "number I could find that would give me a total of 8 collisions which was 83. Although, 157\n" +
              "or 823 also produce 8 collisions. I multiply the sum by 83 and return the remainder of the\n" +
              "product divided by the size of the hash table.");

      // Output Hash table to terminal and csis.txt
      System.out.println();
      System.out.println();
      System.out.println("****************************");
      System.out.println("******   HASH TABLE   ******");
      System.out.println("****************************");
      System.out.println(String.format("%-8s %-8s", "Index", "Key"));
      System.out.println("===================");

      pw.println();
      pw.println();
      pw.println("****************************");
      pw.println("******   HASH TABLE   ******");
      pw.println("****************************");
      pw.println(String.format("%-8s %-8s", "Index", "Key"));
      pw.println("===================");

      // While i is less than the size of the hash table, increment i
      for(int i = 0; i < tableSize; i++)
      {
         // If hash table at index i has an element
         if(hashTable[i] != null)
         {
            System.out.println(String.format("%-8s %-8s", i, hashTable[i]));
            pw.println(String.format("%-8s %-8s", i, hashTable[i]));
         }
         else if (hashTable[i] == null) // Print --- for null item
         {
            System.out.println(String.format("%-8s %-8s", i, "---"));
            pw.println(String.format("%-8s %-8s", i, "---"));
         }
      }

      // Calculate load factor
      float loadFactor = (float) (100 * hashFuncUsed) / tableSize;

      // Print hash collisions, resolution collisions, total collisions,
      // and load factor to the terminal and csis.txt
      System.out.println();
      System.out.println("=============================");
      System.out.println("Hash Function Collisions: " + hashFuncCollisions);
      System.out.println("Resolution Collisions: " + resCollisions);
      System.out.println("Total Collisions: " + (hashFuncCollisions + resCollisions) );
      System.out.println();
      System.out.println(String.format("Load Factor: %.2f%%", loadFactor));
      System.out.println("=============================");
      pw.println();
      pw.println("=============================");
      pw.println("Hash Function Collisions: " + hashFuncCollisions);
      pw.println("Resolution Collisions: " + resCollisions);
      pw.println("Total Collisions: " + (hashFuncCollisions + resCollisions) );
      pw.println();
      pw.println(String.format("Load Factor: %.2f%%", loadFactor));
      pw.println("=============================");

   }

   /**
    *  Compare token from getty.txt with the unimportant words contained
    *  in the hash table.
    * @param s String
    * @return boolean
    */
   public boolean compareToOmit(String s)
   {
      int hash = getHash(s); // Get hash code for the string token
      int tempHash = hash; // Track index as
      boolean loopAround = false; // Track hash index

      // If the element within the hash table index matches the string token
      if ((hashTable[hash] != null) && hashTable[hash].equals(s))
      {
         return true;
      }
      else if (hashTable[hash] == null) // If there is no element in the hash table at 'hash' index
      {
         return false;
      }
      else
      {
         // While the element at hash table index 'hash' is not equal to the string token
         // and while at 'hash' index there is not a word stored
         while ((hashTable[hash] != null) && !(hashTable[hash].equals(s)))
         {
            hash++; // Move to next hash table array index

            // If 'hash' index  has reached the end of the hash table
            if (hash == tableSize)
            {
               hash = 0; // Move to index zero of the hash table array
               loopAround = true; // Zero index of the hash table has been reached
            }

            // If the word stored in the hash table index is equal to the string token
            if ((hashTable[hash] != null) && (hashTable[hash].equals(s)))
            {
               return true;
            }

            // If we have cycled through every index of the hash table and found no matches
            if(loopAround && (tempHash == hash))
            {
               return false;
            }
         }

         return false;
      }
   }
}
