// --== CS400 File Header Information ==--
// Name: Dawson Bauer
// Email: djbauer2@wisc.edu
// Team: CG
// Role: Backend Developer
// TA: Yeping Wang
// Lecturer: Florian
// Notes to Grader: None
import java.util.List;

public interface RelationshipDatabaseInterface {
    public List<Person> getAllPeople();
    public Person getPerson(String name);
    public boolean hasPerson(String name);
    public boolean addPerson(String name, int age, String profession);
    public boolean updateAge(String name, int newAge);
    public boolean updateProfession (String name, String newProfession);
    public boolean removePerson(String name);
    public int getRelationshipWeight(String name1, String name2);
    public boolean containsRelationship (String namePerson1, String namePerson2);
    public boolean removeRelationship (String namePerson1, String namePerson2);
    public boolean addsRelationship (String namePerson1, String namePerson2, int weight);
    public boolean updateRelationship (String namePerson1, String namePerson2, int weight);
    public List<Person> getRelationshipPath(String name1, String name2);
    public int getTotalRelationshipDegree(String name1, String name2);
    public int getNumberOfPersons();
    public int getNumberOfRelationships();
}
