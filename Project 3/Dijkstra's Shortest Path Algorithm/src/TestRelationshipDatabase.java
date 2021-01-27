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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class TestRelationshipDatabase {
	
	private RelationshipDatabase db;
	
	/**
	 * Just creates a new database with text file read in.
	 */
	@BeforeEach
	public void setup() {
		db = new RelationshipDatabase();
	}
	
	/**
	 * Test getPerson() method
	 */
	@Test
	void testGetPerson() {
		db.addPerson("Jose Gutierrez", 34, "Nuclear Technician");
		if (!db.getPerson("Jose Gutierrez").getName().equals("Jose Gutierrez")) {
			fail("getPerson() method failed to recover person's name. (Will also fail if addPerson() method fails.)");
		}
		if (db.getPerson("Jose Gutierrez").getAge() != 34) {
			fail("getPerson() method failed to get person's age. (Will also fail if addPerson() method fails.)");
		}
		if (!db.getPerson("Jose Gutierrez").getProfession().equals("Nuclear Technician")) {
			fail("getPerson() method failed to get person's profession. (Will also fail if addPerson() method fails.)");
		}
	}

	/**
	 * Test addPerson() and hasPerson() methods
	 */
	@Test
	void testAddAndHasPerson() {
		db.addPerson("Jose Gutierrez", 34, "Nuclear Technician");
		if (!db.hasPerson("Jose Gutierrez")) {
			fail("Either addPerson() or hasPerson() method failed.");
		}
	}
	
	/**
	 * Test updatePerson() method
	 */
	@Test
	void testUpdatePerson() {
		db.addPerson("Jose Gutierrez", 34, "Nuclear Technician");
		db.updateAge("Jose Gutierrez", 101);
		db.updateProfession("Jose Gutierrez", "Drywall Installer");
		if (db.getPerson("Jose Gutierrez").getAge() != 101) {
			fail("updateAge() failed.");
		}
		if (!db.getPerson("Jose Gutierrez").getProfession().equals("Drywall Installer")) {
			fail("updateProfession() failed.");
		}
	}
	
	/**
	 * Test removePerson() method
	 */
	@Test
	void testRemovePerson() {
		db.removePerson("Augustus Sharples");
		if (db.hasPerson("Augustus Sharples")) {
			fail("removePerson() method failed.");
		}
	}
	
	/**
	 * Test getRelationshipPath() method
	 */
	@Test
	void testGetPath() {
		// add three people and relationships against which to test
		db.addPerson("Steven Walter", 51, "Musician");
		db.addPerson("Betsy Riley", 22, "Plasterer");
		db.addPerson("Jose Gutierrez", 34, "Nuclear Technician");
		db.addsRelationship("Steven Walter", "Betsy Riley", 2);
		db.addsRelationship("Betsy Riley", "Jose Gutierrez", 1);
		// ArrayList against which to test. Should have steven, betsy, and jose in order.
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(db.getPerson("Steven Walter"));
		list.add(db.getPerson("Betsy Riley"));
		list.add(db.getPerson("Jose Gutierrez"));
		// test return value of getShortestPath against ArrayList list
		if (!db.getRelationshipPath("Steven Walter", "Jose Gutierrez").equals(list) ) {
			fail("Failed to get relationship path.");
		}
	}
	
	/**
	 * Test addRelationship(), removeRelationship(), and containsRelationship() methods
	 */
	@Test
	void testRelationships() {
		db.addPerson("Jose Gutierrez", 34, "Nuclear Technician");
		db.addPerson("Akemi Yamamoto", 69, "Boxer");
		db.addsRelationship("Akemi Yamamoto", "Jose Gutierrez", 1);
		if (!db.containsRelationship("Jose Gutierrez", "Akemi Yamamoto")) {
			fail("addPerson(), addsRelationship(), or containsRelationship() failed.");
		}
		db.removeRelationship("Akemi Yamamoto", "Jose Gutierrez");
		if (db.containsRelationship("Jose Gutierrez", "Akemi Yamamoto")) {
			fail("removeRelationship() failed.");
		}
	}
	
	/**
	 * Test getNumberOfPersons() and getNumberOfRelationships() methods
	 */
	@Test
	void testNumberMethods() {
		if (db.getNumberOfPersons() != 95) {
			fail("getNumberOfPersons should have returned 95. It returned " + db.getNumberOfPersons());
		}
		if (db.getNumberOfRelationships() != 424) {
			fail("getNumberOfRelationships should have returned 424. It returned " + db.getNumberOfRelationships());
		}
	}
}
