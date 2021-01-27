// --== CS400 File Header Information ==--
// Name: Dawson Bauer
// Email: djbauer2@wisc.edu
// Team: CG
// Role: Backend Developer
// TA: Yeping Wang
// Lecturer: Florian
// Notes to Grader: None
import java.util.List;

public class RelationshipDatabase implements RelationshipDatabaseInterface{

    private Database database;

    public RelationshipDatabase(){
        database = new Database();
    }

    @Override
    public List<Person> getAllPeople() {
        return database.getAllPeople();
    }

    @Override
    public Person getPerson(String name) {
        return database.getPerson(name);
    }

    @Override
    public boolean hasPerson(String name) {
        return database.containsPerson(name);
    }

    @Override
    public boolean addPerson(String name, int age, String profession) {
        return database.addPerson(new Person(name, age, profession));
    }

    @Override
    public boolean updateAge(String name, int newAge) {
        if(hasPerson(name)) {
            Person person = getPerson(name);
            person.setAge(newAge);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateProfession(String name, String newProfession) {
        if(hasPerson(name)) {
            Person person = getPerson(name);
            person.setProfession(newProfession);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean removePerson(String name) {
        return database.removePersonByName(name);
    }

    @Override
    public int getRelationshipWeight(String name1, String name2) {
        return database.getRelationshipWeight(name1, name2);
    }

    @Override
    public boolean containsRelationship(String namePerson1, String namePerson2) {
        return database.containsRelationship(namePerson1, namePerson2);
    }

    @Override
    public boolean removeRelationship(String namePerson1, String namePerson2) {
        return database.removeRelationship(namePerson1, namePerson2);
    }

    @Override
    public boolean addsRelationship(String namePerson1, String namePerson2, int weight) {
        return database.addRelationship(namePerson1, namePerson2, weight);
    }

    @Override
    public boolean updateRelationship(String name1, String name2, int newWeight) {
        if(database.containsRelationship(name1, name2) && database.containsRelationship(name2, name1)) {
            database.removeRelationship(name1, name2);
            database.removeRelationship(name2, name1);
            database.addRelationship(name1, name2, newWeight);
            database.addRelationship(name2, name1, newWeight);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Person> getRelationshipPath(String name1, String name2) {
        return database.getShortestPath(name1, name2);
    }

    @Override
    public int getTotalRelationshipDegree(String name1, String name2) {
        return database.getPathCost(name1, name2);
    }

    @Override
    public int getNumberOfPersons() {
        return database.getNumberPerson();
    }

    @Override
    public int getNumberOfRelationships() {
        return database.getNumberRelationship();
    }
}
