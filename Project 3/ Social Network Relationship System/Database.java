// --== CS400 File Header Information ==--
// Name: Weijie Zhou
// Email: wzhou226@wisc.edu
// Team: CG
// TA: Yeping Wang
// Lecturer: Gary Dahl
// Notes to Grader: None
/**
 * Database class
 **/

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.NoSuchElementException;

/**
 * This is the class for database of the program.
 * The instance is a graph used to store all the persons and relationships.
 *
 * @author Weijie Zhou
 */
public class Database {
    private CS400Graph<String> graph;
    private Hashtable<String, Person> people;

    /**
     * Constructor for an instance of database.
     */
    public Database(){
        graph = new CS400Graph<>();
        people = new Hashtable<>();
        importPeople();
    }

    /**
    * @Description: Return all people in the graph
    * @Param: []
    * @return: java.util.List<Person>
    * @Author: Weijie Zhou
    */
    public List<Person> getAllPeople(){
        return new ArrayList<>(people.values());
    }

    /**
     * Returns the Person with the provided name.
     * <p>
     * @param name - the name of the person being searched for.
     * @return a reference to the person with the provided name.
     */
    public Person getPerson(String name){
        return people.getOrDefault(name, null);
    }

    /**
     * Determines whether a person with the provided name exists within the database.
     * <p>
     * @param personName - the name of the person whose existence within the database is under question.
     * @return true if the person exists within the database, false otherwise.
     */
    public boolean containsPerson(String personName){
        return graph.containsVertex(personName);
    }

    /**
     * Adds a person to the database.
     * <p>
     * @param person - a instance of Person class
     * @return true when the person is successfully added, false otherwise.
     */
    public boolean addPerson(Person person){
        if(people.containsKey(person.getName())){
            return false;
        }else{
            people.put(person.getName(), person);
            graph.insertVertex(person.getName());
            return true;
        }
    }

    /**
     * Updates the person with the provided name by replacing it with the new person.
     * <p>
     * @param person - the new person to replace the old person.
     * @return true if the person is successfully updated, false otherwise.
     */
    public boolean updatePerson(Person person){
        if(people.containsKey(person.getName())) {
            people.put(person.getName(), person);
            return true;
        }
        return false;
    }

    /**
     * delete the person by entered name
     * @param name
     * @return true if the deletion is successful, false if no match is found.
     */
    public boolean removePersonByName(String name){
        if(!people.containsKey(name)){
            return false;
        }else{
            people.remove(name);
            graph.removeVertex(name);
            return true;
        }
    }

    /**
     * Determines whether a relationship with two provided names exists within the database.
     * <p>
     * @param personName1 - the name of the first person
     * @param personName2 - the name of the second person
     * @return true if the relationship exists within the database, false otherwise.
     */
     public boolean containsRelationship(String personName1, String personName2){
        try{
            return graph.containsEdge(personName1, personName2);
        }catch(Exception e){
            return false;
        }
    }


    /**
     * Adds a relationship to the database.
     * <p>
     * @param namePerson1 - the name of the first person
     * @param namePerson2 - the name of the second person
     * @param weight - the weight of the relationship
     * @return true when the person is successfully added, false otherwise.
     */
    public boolean addRelationship (String namePerson1, String namePerson2, int weight){
        try {
            return graph.insertEdge(namePerson1, namePerson2, weight) && graph.insertEdge(namePerson2, namePerson1, weight);
        }catch (Exception e){
            return false;
        }

    }

    /**
     * delete the relationship between two person
     * @param namePerson1 - the name of the first person
     * @param namePerson2 - the name of the second person
     * @return true if the deletion is successful, false if no match is found.
     */
    public boolean removeRelationship (String namePerson1, String namePerson2){
        try{
            return graph.removeEdge(namePerson1, namePerson2) && graph.removeEdge(namePerson2, namePerson1);
        }catch (Exception e){
            return false;
        }
    }

    /**
    * @Description: update relationship. returning true if successfully updated, false otherwise.
    * @Param: [person1name, person2name, weight]
    * @return: boolean
    * @Author: Weijie Zhou
    */
    public boolean updateRelationship(String person1name, String person2name, int weight){
        try{
            removeRelationship(person1name, person2name);
            return addRelationship(person1name, person2name, weight);
        }catch (Exception e){
            return false;
        }
    }

    /**
    * @Description: Return the relation number between two people
    * @Param: [person1name, person2name]
    * @return: int
    * @Author: Weijie Zhou
    */
    public int getRelationshipWeight(String person1name, String person2name){
        try{
            return graph.getWeight(person1name, person2name);
        }catch (Exception e){
            return -1;
        }
    }

    /**
    * @Description: Get path(String) of relationships from a person.
    * @Param: [person1name, person2name]
    * @return: java.util.List<Person>
    * @Author: Weijie Zhou
    */
    public List<Person> getShortestPath(String person1name, String person2name){
        try{
            List<String> namePath = graph.shortestPath(person1name, person2name);
            List<Person> personPath = new ArrayList<>();
            for(String name : namePath){
                personPath.add(people.get(name));
            }
            return personPath;
        }catch (Exception e){
            return null;
        }
    }

    /**
    * @Description: Get the total degree of relationships between two people
    * @Param: [person1name, person2name]
    * @return: int
    * @Author: Weijie Zhou
    */
    public int getPathCost(String person1name, String person2name){
        try{
            return graph.getPathCost(person1name, person2name);
        }catch (Exception e){
            return -1;
        }
    }

    /**
    * @Description: Get the number of person in the network
    * @Param: []
    * @return: int
    * @Author: Weijie Zhou
    */
    public int getNumberPerson(){
        return graph.getVertexCount();
    }

    /**
    * @Description: Get the number of relationship in the network
    * @Param: []
    * @return: int
    * @Author: Weijie Zhou
    */
    public int getNumberRelationship(){
        return graph.getEdgeCount();
    }

    /**
     * Imports Persons and relationships from RelationshipInformation.txt into the relationship database.
     */

    private void importPeople(){
        // Get the current operating system
        String os = System.getProperty("os.name");
        // Create a file path
        String filePath = "network.txt";
        // Create a new file reader
        FileReader fileReader;

        // If the current operation system is Windows , change the file path for windows
        if (os.toLowerCase().contains("window")){
            filePath = "src\\network.txt";
        }else if(os.toLowerCase().contains("mac"))
            filePath = "src/network.txt";

        // Import the file and extract every pieces of data
        try{
            // Get ready for file reader and buffered reader
            fileReader = new FileReader(filePath);
            // Create a file reader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Scan file line by line
            String line = bufferedReader.readLine();
            while(line != null && !line.equals("")){
                // Split the line by comma and store them into a String array
                String[] info = line.split(",");

                String personOneName = info[0];

                int personOneAge = Integer.parseInt(info[1]);

                String personOneProfession = info[2];

                String personTwoName = info[3];

                int personTwoAge = Integer.parseInt(info[4]);

                String personTwoProfession = info[5];

                int relationNumber = Integer.parseInt(info[6]);


                // Insert two new person into graph with relation
                graph.insertVertex(personOneName);
                graph.insertVertex(personTwoName);
                graph.insertEdge(personOneName, personTwoName, relationNumber);
                graph.insertEdge(personTwoName, personOneName, relationNumber);

                // Add these two people into the people hashtable
                people.put(personOneName, new Person(personOneName, personOneAge, personOneProfession));
                people.put(personTwoName, new Person(personTwoName, personTwoAge, personTwoProfession));

                // Read the next line in the file
                line = bufferedReader.readLine();
            }
            // Close the Buffer
            bufferedReader.close();
        }catch (Exception e){
            // Catch any exceptions and stop importing. Ignore the exception
            e.printStackTrace();
        }
    }

    /**
     * Clears the database of all persons and relationships.
     */
    public void clearDatabase() {
        graph = new CS400Graph<>();
        people = new Hashtable<>();
    }
}
