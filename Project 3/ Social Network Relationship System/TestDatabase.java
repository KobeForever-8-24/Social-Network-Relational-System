// --== CS400 File Header Information ==--
// Name: Isaac Colbert
// Email: icolbert@wisc.edu
// Team: CG
// Role: Test Engineer 1
// TA: Yeping Wang
// Lecturer: FLorian Heimerl
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.LinkedList;

public class TestDatabase {
  private Database database;

  @BeforeEach
  public void setup() {
    database = new Database();
  }

  @Test
  public void testContainsMethods() {
    Person person1 = new Person("person1", 20, "job1");
    Person person2 = new Person("person2", 21, "job2");
    Person person3 = new Person("person3", 22, "job3");
    Person person4 = new Person("person4", 23, "job4");
    // Add people
    database.addPerson(person1);
    database.addPerson(person2);
    database.addPerson(person3);
    database.addPerson(person4);
    // Add relationships
    database.addRelationship("person1", "person2", 3);
    database.addRelationship("person3", "person4", 5);
    database.addRelationship("person1", "person3", 1);
    database.addRelationship("person4", "person2", 7);
    // Test containsPerson
    if (!database.containsPerson("person1")) {
      fail("Did not add person to database.");
    }
    if (database.containsPerson("person10")) {
      fail("Should not contain this person.");
    }
    // Test containsRelationship
    if (!database.containsRelationship("person1", "person2")) {
      fail("Does not correctly identfity relationship.");
    }
    if (database.containsRelationship("person1", "person4")) {
      fail("Should not contain this relationship");
    }
  }

  @Test
  public void testAddAndRemovePerson() {
    assertFalse(database.containsPerson("Jim Johnson"));
    // Test add person
    if (!database.addPerson(new Person("Jim Johnson", 46, "Actor")) 
        || !database.containsPerson("Jim Johnson")) {
      fail("Did not correctly add person.");
    }
    // Test remove person
    if (!database.removePersonByName("Jim Johnson") || database.containsPerson("Jim Johnson")) {
      fail("Did not correctly remove person.");
    }
  }
  
  @Test
  public void testUpdatePerson() {
    Person newPerson = new Person("newPerson", 22, "newJob");
    // Valid input test
    database.addPerson(newPerson);
    if(!database.updatePerson(new Person("newPerson", 77, "updatedJob"))) {
      fail("Did not correctly update person.");
    }
    Person updatedPerson = database.getPerson("newPerson");
    if(!updatedPerson.getName().equals("newPerson") || updatedPerson.getAge() != 77
        || !updatedPerson.getProfession().equals("updatedJob")) {
      fail("Updated fields were not as expected.");
    }
    // Invalid input test
    if (database.updatePerson(new Person("NotAPerson", 11, "job"))) {
      fail("Update method did not handle invalid input");
    }
  }
  
  @Test
  public void testRelationshipMethods() {
    Person person1 = new Person("person1", 20, "job1");
    Person person2 = new Person("person2", 21, "job2");
    Person person3 = new Person("person3", 22, "job3");
    Person person4 = new Person("person4", 23, "job4");
    // Add people
    database.addPerson(person1);
    database.addPerson(person2);
    database.addPerson(person3);
    database.addPerson(person4);
    // Add relationships
    database.addRelationship("person1", "person2", 3);
    database.addRelationship("person3", "person4", 5);
    database.addRelationship("person1", "person3", 1);
    database.addRelationship("person4", "person2", 7);
    // Add a couple more people
    Person newPerson1 = new Person("newPerson1", 25, "newJob1");
    Person newPerson2 = new Person("newPerson2", 25, "newJob2");
    database.addPerson(newPerson1);
    database.addPerson(newPerson2);
    // Test addRelationship
    if (database.containsRelationship("newPerson1", "newPerson2")) {
      fail("Should not yet contain relationship.");
    }
    if (!database.addRelationship("newPerson1", "newPerson2", 4) 
        || !database.containsRelationship("newPerson1", "newPerson2") 
        || database.getPathCost("newPerson1", "newPerson2") != 4) {
      fail("Did not correctly add relationship");
    }
    // Test removeRelationship
    if (!database.removeRelationship("newPerson1", "newPerson2") 
        || database.containsRelationship("newPerson1", "newPerson2")) {
      fail("Did not correctly remove relationship.");
    }
    // Test updateRelationship, valid input
    if (!database.updateRelationship("person1", "person2", 6) 
        || database.getPathCost("person1", "person2") != 6) {
      fail("Did not correctly update relationship.");
    }
    // invalid input
    if (database.updateRelationship("NotAPerson", "NotAnotherPerson", 2)) {
      fail("Should not have been able to update relationship");
    }
  }
  
  @Test
  public void testGetShortestPath() {
    Person person1 = new Person("person1", 20, "job1");
    Person person2 = new Person("person2", 21, "job2");
    Person person3 = new Person("person3", 22, "job3");
    Person person4 = new Person("person4", 23, "job4");
    // Add people
    database.addPerson(person1);
    database.addPerson(person2);
    database.addPerson(person3);
    database.addPerson(person4);
    // Add relationships
    database.addRelationship("person1", "person2", 3);
    database.addRelationship("person3", "person4", 5);
    database.addRelationship("person1", "person3", 1);
    database.addRelationship("person4", "person2", 7);
    // First test for get shortest path
    ArrayList<Person> path = (ArrayList<Person>)database.getShortestPath("person3", "person2");
    ArrayList<Person> correctPath = new ArrayList<>(); 
    correctPath.add(person3);
    correctPath.add(person1);
    correctPath.add(person2);
    if (!path.equals(correctPath)) {
      fail("Returned path is not as expected.");
    }
    // Second Test
    correctPath.clear();
    correctPath.add(person1);
    correctPath.add(person3);
    correctPath.add(person4);
    path = (ArrayList<Person>)database.getShortestPath("person1", "person4");
    if (!path.equals(correctPath)) {
      fail("Returned path is not as expected.");
    }
  }
  
  @Test
  public void testGetPathCost() {
    Person person1 = new Person("person1", 20, "job1");
    Person person2 = new Person("person2", 21, "job2");
    Person person3 = new Person("person3", 22, "job3");
    Person person4 = new Person("person4", 23, "job4");
    // Add people
    database.addPerson(person1);
    database.addPerson(person2);
    database.addPerson(person3);
    database.addPerson(person4);
    // Add relationships
    database.addRelationship("person1", "person2", 3);
    database.addRelationship("person3", "person4", 5);
    database.addRelationship("person1", "person3", 1);
    database.addRelationship("person4", "person2", 7);
    // Test getPathCost
    if (database.getPathCost("person1", "person2") != 3 
        || database.getPathCost("person2", "person4") != 7
        || database.getPathCost("person1", "person3") != 1
        || database.getPathCost("person3", "person4") != 5) {
      fail("Path costs returned were not as expected. Returned values should be 3, 7, 1, 5. "
          + "Returned: " + database.getPathCost("person1", "person2") 
          + database.getPathCost("person2", "person4")
          + database.getPathCost("person1", "person3")
          + database.getPathCost("person3", "person4"));
    }

  }
  
  @Test
  public void testNumberMethods() {
    // Test initial getNumberPerson
    if (database.getNumberPerson() != 95) {
      fail("There should be 95 people. Returned: " + database.getNumberPerson());
    }
    // Test initial getNumberRelationship
    if (database.getNumberRelationship() != 424) {
      fail("There should be 424 relationships. Returned: " + database.getNumberRelationship());
    }
    // Add some people and relationships
    Person person1 = new Person("person1", 20, "job1");
    Person person2 = new Person("person2", 21, "job2");
    Person person3 = new Person("person3", 22, "job3");
    Person person4 = new Person("person4", 23, "job4");
    // Add people
    database.addPerson(person1);
    database.addPerson(person2);
    database.addPerson(person3);
    database.addPerson(person4);
    // Add relationships
    database.addRelationship("person1", "person2", 3);
    database.addRelationship("person3", "person4", 5);
    database.addRelationship("person1", "person3", 1);
    database.addRelationship("person4", "person2", 7);
    // Test new getNumberPerson
    if (database.getNumberPerson() != 99) {
      fail("There should be 99 people. Returned: " + database.getNumberPerson());
    }
    // Test new getNumberRelationships
    if (database.getNumberRelationship() != 432) {
      fail("There should be 432 relationships. Returned: " + database.getNumberRelationship());
    }
  }
  
  @Test
  public void testClearDatabase() {
    // clear database
    database.clearDatabase();
    if (database.getNumberPerson() != 0 || database.getNumberRelationship() != 0) {
      fail("There should be no items in the database after clear.");
    }
  }

}
