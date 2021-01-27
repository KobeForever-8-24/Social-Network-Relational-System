// --== CS400 File Header Information ==--
// Name: Brandon Kuhl
// Email: bkuhl@wisc.edu
// Team: CG
// Role: Test Engineer
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDatabase {
	
	private Database db;
	
	/**
	 * Create database and read in text file.
	 */
	@BeforeEach
	public void setup() {
		db = new Database();
	}
	
	/**
	 * Test addPerson() and removePerson() methods
	 */
	@Test
	void testAddAndRemovePerson() {
		Person aaliyah = new Person("Aaliyah Adama", 68, "Poet");

		//Add test
		db.addPerson(aaliyah);
		if (!db.containsPerson("Aaliyah Adama")) {
			fail("Failed to add person.");
		}
		//Remove test
		db.removePersonByName("Aaliyah Adama");
		if (db.containsPerson("Aaliyah Adama")) {
			fail("Failed to remove person.");
		} 
	}
	
	/**
	 * Test updatePerson() method
	 */
	@Test
	void testUpdatePerson() {
		
		// add person
		Person jose = new Person("Jose Gutierrez", 34, "Nuclear Technician");
		db.addPerson(jose);
		
		// change age and profession of person, then attempt to update
		jose = new Person("Jose Gutierrez", 100, "Blast Furnace Technician");
		db.updatePerson(jose);
		// get person from database
		Person updatedJose = db.getPerson("Jose Gutierrez");
		// test profession update
		if (!updatedJose.getProfession().equals("Blast Furnace Technician")) {
			fail("Failed to update profession.");
		}
		// test age update
		if (updatedJose.getAge() != 100) {
			fail("Failed to update age.");
		}
	}
	
	/**
	 * test addRelationship(), updateRelationship(), and removeRelationship() methods
	 */
	@Test
	void testRelationshipMethods() {
		
		// test containsRelationship
		if (db.containsRelationship("Alys Fountain", "Jayce Ward") == false) {
			fail("Failed to show existence of relationship.");
		}
		
		// test add relationship
		Person aaliyah = new Person("Aaliyah Adama", 68, "Poet");
		Person deep = new Person("Deependra Shrestha", 51, "Soccer Player");
		db.addPerson(aaliyah);
		db.addPerson(deep);
		db.addRelationship("Aaliyah Adama", "Deependra Shrestha", 3);
		if (db.containsRelationship("Aaliyah Adama", "Deependra Shrestha") != true
			|| db.containsRelationship("Deependra Shrestha", "Aaliyah Adama") != true) {
			fail("Failed to add relationship.");
		}
		
		// test update relationship
		db.updateRelationship("Aaliyah Adama", "Deependra Shrestha", 1);
		if (db.getRelationshipWeight("Aaliyah Adama", "Deependra Shrestha") != 1) {
			fail("Failed to update relationship weight.");
		}
		
		// test remove relationship
		db.removeRelationship("Aaliyah Adama", "Deependra Shrestha");
		if (db.containsRelationship("Aaliyah Adama", "Deependra Shrestha") != false) {
			fail("Failed to remove relationship.");
		}
	}
	
	/**
	 * test getShortestPath() method
	 */
	@Test
	void testGetShortestPath() {
		// add three people and relationships against which to test
		Person steven = new Person("Steven Walter", 51, "Musician");
		Person betsy = new Person("Betsy Riley", 22, "Plasterer");
		Person jose = new Person("Jose Gutierrez", 34, "Nuclear Technician");
		db.addPerson(steven);
		db.addPerson(betsy);
		db.addPerson(jose);
		db.addRelationship("Steven Walter", "Betsy Riley", 2);
		db.addRelationship("Betsy Riley", "Jose Gutierrez", 1);
		// ArrayList against which to test. Should have steven, betsy, and jose in order.
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(steven);
		list.add(betsy);
		list.add(jose);
		// test return value of getShortestPath against ArrayList list
		if (!db.getShortestPath("Steven Walter", "Jose Gutierrez").equals(list) ) {
			fail("Failed to get shortest path.");
		}
	}

	/**
	 * Test getPathCost() method
	 */
	@Test
	void testGetPathCost() {
		// add people and relationships
		Person steven = new Person("Steven Walter", 51, "Musician");
		Person betsy = new Person("Betsy Riley", 22, "Plasterer");
		Person jose = new Person("Jose Gutierrez", 34, "Nuclear Technician");
		db.addPerson(steven);
		db.addPerson(betsy);
		db.addPerson(jose);
		db.addRelationship("Steven Walter", "Betsy Riley", 2);
		db.addRelationship("Betsy Riley", "Jose Gutierrez", 1);
		// path cost should be 3 (2 + 1)
		if (db.getPathCost("Steven Walter", "Jose Gutierrez") != 3) {
			fail("Path cost should equal 3. Instead, it equals " + db.getPathCost("Steven Walter", "Jose Gutierrez"));
		}
	}
	
	/**
	 * Test getNumberPerson(), getNumberRelationship() methods before and after adding
	 * person and relationship
	 */
	@Test
	void testNumberMethods() {		
		// test method for determining number of people in database
		if (db.getNumberPerson() != 95) {
			fail("getNumberPerson should return 95. It returns " + db.getNumberPerson());
		}
		// test method for determining number of relationships in database
		if (db.getNumberRelationship() != 424) {
			fail("getNumberPerson should return 424. It returns " + db.getNumberRelationship());
		}
		
		// test method for determining number of people in database after adding person
		Person jose = new Person("Jose Gutierrez", 34, "Nuclear Technician");
		db.addPerson(jose);
		if (db.getNumberPerson() != 96) {
			fail("getNumberPerson should return 96 after adding person. It returns " + db.getNumberPerson());
		}
		// test method for determining number of relationships in database after adding relationship
		Person betsy = new Person("Betsy Riley", 22, "Plasterer");
		db.addPerson(betsy);
		db.addRelationship("Betsy Riley", "Jose Gutierrez", 7);
		if (db.getNumberRelationship() != 426) {
			fail("getNumberPerson should return 426 after adding relationship (between two people). It returns " + db.getNumberRelationship());
		}
	}
	
	/**
	 * test containsPerson() and containsRelationship() methods
	 */
	@Test
	void testContainsMethods() {
		// test containsPerson() from read in data
		if (!db.containsPerson("Umaiza Boyer")) {
			fail("containsPerson() method failed.");
		}
		
		// test containsRelationship() from read in data
		if (!db.containsRelationship("Umaiza Boyer", "Lilly Reynolds")) {
			fail("containsRelationship() method failed.");
		}
		
	}

	/**
	 * Test clearDatabase() method
	 */
	@Test
	void testClearDatabase() {
		db.clearDatabase();
		if (db.containsPerson("Umaiza Boyer")) {
			fail("clearDatabase() method failed.");
		}
	}
	


	
	
	
	
}
