// Name: Isaac Colbert
// Email: icolbert@wisc.edu
// Team: CG
// Role: Test Engineer 1
// TA: Yeping Wang
// Lecturer: FLorian Heimerl
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;

/**
 * Test suite for RelationshipDatabase.java
 * <p>
 * @author Isaac Colbert
 *
 */
public class TestRelationshipDatabase {
  private RelationshipDatabase rDatabase;
  
  /**
   * Sets up for each test.
   */
  @BeforeEach
  public void setup() {
    rDatabase = new RelationshipDatabase();
    // Add people to database
    rDatabase.addPerson("person1", 20, "Job1");
    rDatabase.addPerson("person2", 21, "Job2");
    rDatabase.addPerson("person3", 22, "Job3");
    rDatabase.addPerson("person4", 23, "Job4");
    // Add relationships to database
    rDatabase.addsRelationship("person1", "person2", 3);
    rDatabase.addsRelationship("person3", "person4", 5);
    rDatabase.addsRelationship("person1", "person3", 1);
    rDatabase.addsRelationship("person4", "person2", 7);
  }
  
  /**
   * Tests the getPerson() method.
   */
  @Test
  public void testGetPerson() {
    Person returnedPerson = rDatabase.getPerson("person1");
    // Test for a person in the database
    if (!returnedPerson.getName().equals("person1") || returnedPerson.getAge() != 20
        || !returnedPerson.getProfession().equals("Job1")) {
      fail("Fields from returned person were not as expected. Expected: "
          + "person1, 20, Job1. Returned: " + returnedPerson.getName() + ", " 
          + returnedPerson.getAge() + ", " + returnedPerson.getProfession());
    }
    // Test for a person not in the database
    returnedPerson = rDatabase.getPerson("NotAPerson");
    if (returnedPerson != null) {
      fail("Should have returned a null reference.");
    }
  }
  
  /**
   * Tests the addPerson() and hasPerson() methods.
   */
  @Test
  public void testAddAndHasPerson() {
    // Test has person
    // Person in database
    if (!rDatabase.hasPerson("person1")) {
      fail("person1 should be in the database.");
    }
    // Person not in database
    if (rDatabase.hasPerson("NotAPerson")) {
      fail("NotAPerson should not be in database.");
    }
    
    // Test add person
    if (!rDatabase.addPerson("newPerson", 25, "newJob") || !rDatabase.hasPerson("newPerson")) {
      fail("newPerson was not correctly added.");
    }
    Person returnedPerson = rDatabase.getPerson("newPerson");
    if (returnedPerson == null || !returnedPerson.getName().equals("newPerson") 
        || returnedPerson.getAge() != 25 || !returnedPerson.getProfession().equals("newJob")) {
      fail("Fields of the newPerson were not as expected.");
    }
    // Test add person with bad input
    if (rDatabase.addPerson("person1", 100, "weirdJob")) {
      fail("Should not have added a person already in database.");
    }
  }
  
  /**
   * Tests the updatePerson() method.
   */
  @Test
  public void testUpdatePerson() {
    // Test update age with valid input
    if (!rDatabase.updateAge("person1", 30) || rDatabase.getPerson("person1").getAge() != 30) {
      fail("Update age did not function as expected. Expected 30, returned: " 
          + rDatabase.getPerson("person1").getAge());
    }
    // Test update age with invalid input
    if (rDatabase.updateAge("NotAPerson", 31)) {
      fail("Should not have successfully updated age of a person not in the database.");
    }
    // Test update profession with valid input
    if (!rDatabase.updateProfession("person1", "updatedJob1") 
        || !rDatabase.getPerson("person1").getProfession().equals("updatedJob1")) {
      fail("Update profession did not function as expected. Expected updatedJob1, returned: "
          + rDatabase.getPerson("person1").getProfession().equals("updatedJob1"));
    }
    // Test update profession with invalid input
    if (rDatabase.updateProfession("NotAPerson", "newJob")) {
      fail("Should not have successfully updated profession of a person not in the database.");
    }
  }
  
  /**
   * Tests the removePerson() method.
   */
  @Test
  public void testRemovePerson() {
    // Test remove person with valid input
    if (!rDatabase.removePerson("person1") || rDatabase.hasPerson("person1")) {
      fail("person1 was not successfully removed.");
    }
    // Test remove person with invalid input
    if (rDatabase.removePerson("NotAPerson")) {
      fail("Should not have successfully removed a person not in the database.");
    }
  }
  
  /**
   * Tests the getPath() method
   */
  @Test
  public void testGetPath() {
    ArrayList<Person> path 
      = (ArrayList<Person>)rDatabase.getRelationshipPath("person1", "person4");
    // Construct what the path should be
    ArrayList<Person> correctPath = new ArrayList<Person>();
    correctPath.add(rDatabase.getPerson("person1"));
    correctPath.add(rDatabase.getPerson("person3"));
    correctPath.add(rDatabase.getPerson("person4"));
    // Check if the paths match
    if (!path.equals(correctPath)) {
      System.out.println(path.toString());
      System.out.println(correctPath.toString());
      fail("Path returned was not as expected.");
    }
  }
  
  /**
   * Tests addsRelationship(), removeRelationship(), and containsRelationship()
   */
  @Test
  public void testRelationships() {
    // Test containsRelationship
    if (!rDatabase.containsRelationship("person1", "person2")) {
      fail("Contains relationship didn't return true for existing relationship.");
    }
    // Test addsRelationship valid input
    if (rDatabase.containsRelationship("person1", "person4")) {
      fail("There should not be a relationship between person1 and person4 yet.");
    }
    if (!rDatabase.addsRelationship("person1", "person4", 4) 
        || !rDatabase.containsRelationship("person1", "person4")) {
      fail("Valid relationship was not successfully added.");
    }
    if (rDatabase.getRelationshipWeight("person1", "person4") != 4) {
      fail("The weight of the added relationship is not as expected.");
    }
    // Test addsRelationship invalid input
    if (rDatabase.addsRelationship("person2", "person3", -6)) {
      fail("Should not have added a relationship with a negative weight.");
    }
    // Test removeRelationship with valid input
    if (!rDatabase.removeRelationship("person1", "person4") 
        || rDatabase.containsRelationship("person1", "person4")) {
      fail("Valid relationship was not successfully removed.");
    }
    // Test removeRelationship with invalid input
    if (rDatabase.removeRelationship("person2", "person3")) {
      fail("Should not have successfully removed a relationship that doesn't exist.");
    }
  }
  
  /**
   * Tests getNumberOfPersons() and getNumberOfRelationships() methods.
   */
  @Test
  public void testNumberMethods() {
    // Check initial number of people (+ 4 added in @BeforeEach)
    if(rDatabase.getNumberOfPersons() != 99) {
      fail("Database should initially have 99 people. Returned: " + rDatabase.getNumberOfPersons());
    }
    // Check initial number of relationships (+8 added in @BeforeEach)
    if (rDatabase.getNumberOfRelationships() != 432) {
      fail("Database should initially have 432 realtionships. Returned: "
          + rDatabase.getNumberOfRelationships());
    }
  }
}
