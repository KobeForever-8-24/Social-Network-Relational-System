// --== CS400 File Header Information ==--
// Name: Deming Xu
// Email: dxu227@wisc.edu
// Team: CG
// Role: Front End Developer 1
// TA: Yeping Wang
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.List;
import java.util.Scanner;

/**
 * Front End User Interface class
 *
 * @author Deming Xu
 */
public class Main {
    // private instance of relationship database
    private static RelationshipDatabase relationshipDatabase = new RelationshipDatabase();

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // scanner object
        while (true) {
            homePage(); // output homepage

            // Get the user input for option (1-13)
            String option = "";
            option = scanner.nextLine();
            // If the user input is not within the range, ask them to input again
            while (!option.equals("1")
                    && !option.equals("2")
                    && !option.equals("3")
                    && !option.equals("4")
                    && !option.equals("5")
                    && !option.equals("6")
                    && !option.equals("7")
                    && !option.equals("8")
                    && !option.equals("9")
                    && !option.equals("10")
                    && !option.equals("11")
                    && !option.equals("12")
                    && !option.equals("13")) {
                System.out.print("Invalid Option! Please try again: ");
                option = scanner.nextLine();
            }

            if (option.equals("1")) {
                // Get the top 10 people's names in the database
                get10People();
            } else if (option.equals("2")) {
                // Ge the person's information
                String name = "";
                System.out.print("Enter Name to search: ");
                // Ask user to input the name
                name = nameInput();
                if (containPerson(name)) {
                    // If the name is in the database, print the person's information
                    Person person = getPerson(name);
                    if (person != null) {
                        System.out.println("Profile of " + name + ": ");
                        System.out.println("Name: " + person.getName());
                        System.out.println("Age: " + person.getAge());
                        System.out.println("Profession: " + person.getProfession());
                    } else {
                        // If cannot find the person, print the error message
                        System.out.println("Cannot find the person!");
                    }
                } else {
                    // If cannot find the person, print the error message
                    System.out.println("Cannot find the person!");
                }
            } else if (option.equals("3")) {
                // Add the person to the database
                System.out.println("Enter the following info to add the person: ");
                String name = "";
                String age = "";
                String profession = "";
                // Ask user to input the person name
                System.out.print("Enter Person Name: ");
                name = nameInput();
                // Check whether the name is in the database
                if (!containPerson(name)) {
                    // Ask user to input the person age
                    System.out.print("Enter Person Age: ");
                    age = scanner.nextLine();
                    int a;
                    // If user input an invalid age, ask them to input again
                    while (true) {
                        try {
                            a = Integer.parseInt(age);  // Convert the user input (String) to integer
                            if (a <= 0) {
                                // If the input integer is negative, print error message and ask user to input again
                                System.out.println("Invalid Age!");
                                System.out.print("Enter Person Age: ");
                                age = scanner.nextLine();
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            // If user input a non-integer input, print error message and ask user to input again
                            System.out.println("Invalid Age!");
                            System.out.print("Enter Person Age: ");
                            age = scanner.nextLine();
                        }
                    }
                    // Ask user to input the person profession
                    System.out.print("Enter Person Profession: ");
                    profession = scanner.nextLine();
                    while (profession.equals("")) {
                        // If user input none, print error message and ask user to input again
                        System.out.println("Invalid Input!");
                        System.out.print("Enter Person profession: ");
                        profession = scanner.nextLine();
                    }
                    // Add the person to database, if success, print the success message, otherwise, print error message
                    if (addPerson(name, a, profession)) {
                        System.out.println("Person addition success");
                    } else {
                        System.out.println("Person addition fail");
                    }
                } else {
                    // if the name is contained in the database, print error message
                    System.out.println("No duplicate person allow!");
                }
            } else if (option.equals("4")) {
                // Update a person's information
                // Ask user to input a name of the update person
                System.out.print("Enter Person Name to Update: ");
                String name = nameInput();
                Person person = getPerson(name);    // Get the person object from database
                if (person != null) {
                    // If can find the person, output the person information and ask user which info needs to update
                    System.out.println("Here is the info of " + name + ":");
                    System.out.println(person.getName());
                    System.out.println("1. age: " + person.getAge());
                    System.out.println("2. profession: " + person.getProfession());
                    System.out.print("Select which info to update (1-2): ");
                    // Ask user to select which info needs to update
                    String opt = "";
                    opt = scanner.nextLine();
                    // If user enter invalid value, print error message and ask user to input again
                    while (!opt.equals("1")
                            && !opt.equals("2")) {
                        System.out.print("Invalid Option! Please try again: ");
                        opt = scanner.nextLine();
                    }
                    if (opt.equals("1")) {
                        // If user select 1, update age
                        System.out.println("Enter Age: ");
                        // Ask user to enter the new age
                        String age = scanner.nextLine();
                        int a;
                        // If user input an invalid age, ask them to input again
                        while (true) {
                            try {
                                a = Integer.parseInt(age);
                                if (a <= 0) {
                                    // If the input integer is negative, print error message and ask user to input again
                                    System.out.println("Invalid Age!");
                                    System.out.print("Enter Person Age: ");
                                    age = scanner.nextLine();
                                    continue;
                                }
                                break;
                            } catch (Exception e) {
                                // If user input a non-integer input, print error message and ask user to input again
                                System.out.println("Invalid Age!");
                                System.out.print("Enter Person Age: ");
                                age = scanner.nextLine();
                            }
                        }
                        // update the user age, if success, print the success message, otherwise, print error message
                        if (updateAge(name, a)) {
                            System.out.println("Age update success");
                        } else {
                            System.out.println("Age update fail");
                        }
                    } else {
                        // If user select 2, update profession
                        System.out.print("Enter Person Profession: ");
                        // Ask user to select profession
                        String profession = scanner.nextLine();
                        // If user input none, print the error message and ask user to input again
                        while (profession.equals("")) {
                            System.out.println("Invalid Input!");
                            System.out.print("Enter Person Profession: ");
                            profession = scanner.nextLine();
                        }
                        // update the person profession, if success, print the success message, otherwise, print error message
                        if (updateProfession(name, profession)) {
                            System.out.println("Profession update success");
                        } else {
                            System.out.println("Profession update fail");
                        }
                    }
                } else {
                    // If name is not in the database, print the error message
                    System.out.println("Cannot find the person in database.");
                }
            } else if (option.equals("5")) {
                // Delete a person from the database
                System.out.print("Enter Person Name to Delete: ");
                // Ask user to input the name
                String name = nameInput();
                if (containPerson(name)) {
                    // If person is in the database, delete it
                    // Delete the person, if success, print the success message, otherwise, print error message
                    if (deletePerson(name)) {
                        System.out.println("Person delete success");
                    } else {
                        System.out.println("Person delete fail");
                    }
                } else {
                    // If person is not in the database, print the error message
                    System.out.println("Cannot find the person in database.");
                }
            } else if (option.equals("6")) {
                // Check if the database contain the person
                System.out.print("Enter Person Name: ");
                // Ask user to input the person name
                String name = nameInput();
                // Check the person profession and print the result
                if (containPerson(name)) {
                    System.out.println(name + " is in the database.");
                } else {
                    System.out.println(name + " is NOT in the database.");
                }
            } else if (option.equals("7")) {
                // Add the relationship between two people
                System.out.print("Enter Person 1 Name: ");
                String name1 = nameInput(); // Input person 1 name
                System.out.print("Enter Person 2 Name: ");
                String name2 = nameInput(); // input person 2 name
                // Check whether names are equal
                if (!name1.equals(name2)) {
                    // Check whether those two people are in the database
                    if (containPerson(name1) && containPerson(name2)) {
                        // Check whether the relationship between those two people exists in the database
                        if (!containRelationship(name1, name2)) {
                            // If those two people has no relationship, ask user to enter the relationship degree
                            System.out.println("Enter the relationships between them -->");
                            System.out.println("1. Brothers/Sisters");
                            System.out.println("2. Cousins");
                            System.out.println("3. BestFriends");
                            System.out.println("4. NormalFriends");
                            System.out.println("5. Classmates");
                            System.out.println("6. Colleagues");
                            System.out.println("7. Acquaintances");
                            System.out.println("8. Others");
                            System.out.println("Enter relationship: ");
                            String relationship = "";
                            int weight;
                            relationship = scanner.nextLine();
                            while (true) {
                                // If user enter out-of-range input, print the error message and ask them to input again
                                try {
                                    weight = Integer.parseInt(relationship);
                                    if (weight < 1 || weight > 8) {
                                        System.out.println("Invalid Relationship!");
                                        System.out.print("Enter relationship: ");
                                        relationship = scanner.nextLine();
                                        continue;
                                    }
                                    break;
                                } catch (Exception e) {
                                    // If user enter non-integer value, print the error message and ask them to input again
                                    System.out.println("Invalid Relationship!");
                                    System.out.print("Enter Relationship: ");
                                    relationship = scanner.nextLine();
                                }
                            }
                            // Add the relationship, if success, print the success message, otherwise, print error message
                            if (addRelationship(name1, name2, weight)) {
                                System.out.println("Relationship between " + name1 + " and " + name2 + " is successfully added");
                            } else {
                                System.out.println("Cannot add Relationship between " + name1 + " and " + name2);
                            }
                        } else {
                            // if those two people has relationship in the database, print the error message
                            System.out.println("Relationship between " + name1 + " and " + name2 + " exists in the database!");
                        }
                    } else {
                        // If people are not in the database, print the error message
                        System.out.println("Name(s) not exist in the database!");
                    }
                } else {
                    // If user input two same names, print error message
                    System.out.println("Names cannot identical");
                }
            } else if (option.equals("8")) {
                // Get the relationship between two people
                System.out.print("Enter Person 1 Name: ");
                String name1 = nameInput(); // Input person 1 name
                System.out.print("Enter Person 2 Name: ");
                String name2 = nameInput(); // Input person 2 name
                // Check whether two names are identical
                if (!name1.equals(name2)) {
                    // Check whether those two people are in the database
                    if (containPerson(name1) && containPerson(name2)) {
                        // Get the weight of the relationship between people
                        int weight = getRelationshipWeight(name1, name2);
                        // Print the relationship based on the weight of the relationship
                        if (weight == 1) {
                            // Relationship degree 1
                            System.out.println(name1 + " and " + name2 + " are Brothers/Sisters (1)");
                        } else if (weight == 2) {
                            // Relationship degree 2
                            System.out.println(name1 + " and " + name2 + " are Cousins (2)");
                        } else if (weight == 3) {
                            // Relationship degree 3
                            System.out.println(name1 + " and " + name2 + " are BestFriends (3)");
                        } else if (weight == 4) {
                            // Relationship degree 4
                            System.out.println(name1 + " and " + name2 + " are NormalFriends (4)");
                        } else if (weight == 5) {
                            // Relationship degree 5
                            System.out.println(name1 + " and " + name2 + " are Classmates (5)");
                        } else if (weight == 6) {
                            // Relationship degree 6
                            System.out.println(name1 + " and " + name2 + " are Colleagues (6)");
                        } else if (weight == 7) {
                            // Relationship degree 7
                            System.out.println(name1 + " and " + name2 + " are Acquaintances (7)");
                        } else if (weight == 8) {
                            // Relationship degree 8
                            System.out.println(name1 + " and " + name2 + " are Other relationship (8)");
                        } else {
                            // No relationship
                            System.out.println(name1 + " and " + name2 + " have no relationship");
                        }
                    } else {
                        // If people are not in the database, print the error message
                        System.out.println("Name(s) not exist in the database!");
                    }
                } else {
                    // If user input two same names, print error message
                    System.out.println("Names cannot identical");
                }
            } else if (option.equals("9")) {
                // Update the relationship
                System.out.print("Enter Person 1 Name: ");
                String name1 = nameInput(); // Input person 1 name
                System.out.print("Enter Person 2 Name: ");
                String name2 = nameInput(); // Input person 2 name
                // Check whether database has two names
                if (!name1.equals(name2)) {
                    if (containPerson(name1) && containPerson(name2)) {
                        // Check whether those two people has two relationship
                        if (containRelationship(name1, name2)) {
                            // Ask user to select new relationship degree
                            System.out.println("Update the relationships between them -->");
                            System.out.println("1. Brothers/Sisters");
                            System.out.println("2. Cousins");
                            System.out.println("3. BestFriends");
                            System.out.println("4. NormalFriends");
                            System.out.println("5. Classmates");
                            System.out.println("6. Colleagues");
                            System.out.println("7. Acquaintances");
                            System.out.println("8. Others");
                            System.out.println("Enter relationship: ");
                            String relationship = "";
                            int weight;
                            relationship = scanner.nextLine();
                            while (true) {
                                try {
                                    weight = Integer.parseInt(relationship);
                                    // If user enter out-of-range input, print the error message and ask them to input again
                                    if (weight < 1 || weight > 8) {
                                        System.out.println("Invalid Relationship!");
                                        System.out.print("Enter relationship: ");
                                        relationship = scanner.nextLine();
                                        continue;
                                    }
                                    break;
                                } catch (Exception e) {
                                    // If user enter non-integer value, print the error message and ask them to input again
                                    System.out.println("Invalid Relationship!");
                                    System.out.print("Enter Relationship: ");
                                    relationship = scanner.nextLine();
                                }
                            }
                            // Update the relationship, if success, print the success message, otherwise, print error message
                            if (updateRelationship(name1, name2, weight)) {
                                System.out.println("Relationship between " + name1 + " and " + name2 + " is successfully updated");
                            } else {
                                System.out.println("Cannot update Relationship between " + name1 + " and " + name2);
                            }
                        } else {
                            // If the relationship is not existed, print the error message
                            System.out.println("Cannot update non-exist relationship!");
                        }
                    } else {
                        // if name is not in the database, print the error message
                        System.out.println("Name(s) not exist in the database!");
                    }
                } else {
                    // If user input two same names, print error message
                    System.out.println("Names cannot identical");
                }
            } else if (option.equals("10")) {
                // Delete the relationship
                System.out.print("Enter Person 1 Name: ");
                String name1 = nameInput(); // Input person 1 name
                System.out.print("Enter Person 2 Name: ");
                String name2 = nameInput(); // Input person 2 name
                // Check whether two names are identical
                if (!name1.equals(name2)) {
                    // Check whether database has two names
                    if (containPerson(name1) && containPerson(name2)) {
                        // Check whether those two people has two relationship
                        if (containRelationship(name1, name2)) {
                            // Delete the relationship, if success, print the success message, otherwise, print error message
                            if (deleteRelationship(name1, name2)) {
                                System.out.println("Relationship between " + name1 + " and " + name2 + " is successfully deleted");
                            } else {
                                System.out.println("Cannot delete Relationship between " + name1 + " and " + name2);
                            }
                        } else {
                            // If the relationship is not existed, print the error message
                            System.out.println("Cannot delete non-exist relationship!");
                        }
                    } else {
                        // if name is not in the database, print the error message
                        System.out.println("Name(s) not exist in the database!");
                    }
                } else {
                    // If user input two same names, print error message
                    System.out.println("Names cannot identical");
                }
            } else if (option.equals("11")) {
                // Check whether the relationship exists in the database
                System.out.print("Enter Person 1 Name: ");
                String name1 = nameInput(); // Input person 1 name
                System.out.print("Enter Person 2 Name: ");
                String name2 = nameInput(); // Input person 2 name
                // Check whether two names are identical
                if (!name1.equals(name2)) {
                    // Check whether people are in the database
                    if (containPerson(name1) && containPerson(name2)) {
                        // Check whether the relationship exists, and print the result
                        if (containRelationship(name1, name2)) {
                            System.out.println("Relationship between " + name1 + " and " + name2 + " exists in the database");
                        } else {
                            System.out.println("Relationship between " + name1 + " and " + name2 + " NOT exists in the database");
                        }
                    } else {
                        // If name is not in the database, print the error message
                        System.out.println("Name(s) not exist in the database!");
                    }
                } else {
                    // If user input two same names, print error message
                    System.out.println("Names cannot identical");
                }
            } else if (option.equals("12")) {
                // Get the shortest path and the total degree between two people
                System.out.print("Enter Person 1 Name: ");
                String name1 = nameInput(); // Input person 1 name
                System.out.print("Enter Person 2 Name: ");
                String name2 = nameInput(); // Input person 2 name
                // Check whether two names are identical
                if (!name1.equals(name2)) {
                    // Check whether those two people are in the database
                    if (containPerson(name1) && containPerson(name2)) {
                        List<Person> path = getPathOfRelationship(name1, name2);    // Get the path of relationship
                        // Check whether the path exists in the database
                        if (path != null) {
                            // Print the path with person name and age: Name(Age) -> Name(Age) ...
                            for (int i = 0; i < path.size(); i++) {
                                System.out.print(path.get(i).getName() + "(" + path.get(i).getAge() + ")");
                                if (i != path.size() - 1) {
                                    System.out.print(" -> ");
                                } else {
                                    System.out.println();
                                }
                            }
                            // Print the total degree of the relationship
                            System.out.println("Total Degree of Relationship between " + name1 + " and " + name2 + " is " + getTotalDegreeOfRelationship(name1, name2));
                        } else {
                            // If relationship is not in the database, print the error message
                            System.out.println("Cannot find path of Relationship between " + name1 + " and " + name2);
                        }
                    } else {
                        // If name is not in the database, print the error message
                        System.out.println("Name(s) not exist in the database!");
                    }
                } else {
                    // If user input two same names, print error message
                    System.out.println("Names cannot identical");
                }
            } else {
                // If user input 13, exit the program
                break;
            }
            // Output an empty line
            System.out.println();
        }
    }

    /**
     * Home page view
     */
    public static void homePage() {
        // Print the total number of people and relationships in the databases, as well as operation options
        System.out.println("--- Welcome to the network relationship system ---");
        System.out.println("Currently, there are " + totalNumOfPeople() + " People, and " + totalNumOfRelationships() + " relationships.");
        System.out.println("    1. Get first 10 people in the database");
        System.out.println("-> Person Operations: ");
        System.out.println("    2. Get a Person info");
        System.out.println("    3. Add a Person");
        System.out.println("    4. Update a Person");
        System.out.println("    5. Delete a Person");
        System.out.println("    6. is Contain a Person");
        System.out.println("-> Relationship Operations: ");
        System.out.println("    7. Add a Relationship");
        System.out.println("    8. get a Relationship Degree");
        System.out.println("    9. Update a Relationship Weight");
        System.out.println("    10. Delete a Relationship");
        System.out.println("    11. is Contain a Relationship");
        System.out.println("-> Network Operations: ");
        System.out.println("    12. Get path and the total degree of relationship");
        System.out.println("-> Application Operation: ");
        System.out.println("    13. Exit");
        System.out.println();
        System.out.print("Please select an option (1-13): ");
    }

    /**
     * Ask user to input the name
     *
     * @return name
     */
    public static String nameInput() {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        name = scanner.nextLine();
        // If user input none, print the error message and ask user to input again
        while (name.equals("")) {
            System.out.println("Invalid Name!");
            System.out.print("Enter Person Name: ");
            name = scanner.nextLine();
        }
        return name;
    }

    /**
     * Get top 10 people in the database
     */
    public static void get10People() {
        List<Person> people = relationshipDatabase.getAllPeople();  // Get all the people from the database
        try {
            // Output the top 10 people names
            for (int i = 0; i < 10; i++) {
                System.out.println(people.get(i).getName());
            }
        } catch (Exception e) {
            // If an error occur during the retrieving, print error message
            System.out.println("Cannot get people information!");
        }
    }

    /**
     * Get the person information
     *
     * @param name
     * @return Person object, or null if not found
     */
    public static Person getPerson(String name) {
        return relationshipDatabase.getPerson(name);
    }

    /**
     * Get the relationship weight between two person
     *
     * @param name1
     * @param name2
     * @return relationship weight, -1 if no relationship exists
     */
    public static int getRelationshipWeight(String name1, String name2) {
        return relationshipDatabase.getRelationshipWeight(name1, name2);
    }

    /**
     * Add the person to the database
     *
     * @param name
     * @param age
     * @param profession
     * @return true if add successfully, otherwise return false
     */
    public static boolean addPerson(String name, int age, String profession) {
        return relationshipDatabase.addPerson(name, age, profession);
    }

    /**
     * Add the relationship between two people
     *
     * @param person1
     * @param person2
     * @param weight
     * @return true if add successfully, otherwise return false
     */
    public static boolean addRelationship(String person1, String person2, int weight) {
        return relationshipDatabase.addsRelationship(person1, person2, weight);
    }

    /**
     * Delete the person from the database
     *
     * @param name
     * @return true if delete successfully, otherwise return false
     */
    public static boolean deletePerson(String name) {
        return relationshipDatabase.removePerson(name);
    }

    /**
     * Update person age
     *
     * @param name
     * @param newAge
     * @return true if update successfully, otherwise return false
     */
    public static boolean updateAge(String name, int newAge) {
        return relationshipDatabase.updateAge(name, newAge);
    }

    /**
     * Update person profession
     *
     * @param name
     * @param newProfession
     * @return true if update successfully, otherwise return false
     */
    public static boolean updateProfession(String name, String newProfession) {
        return relationshipDatabase.updateProfession(name, newProfession);
    }

    /**
     * Check whether the person contained in the database
     *
     * @param name
     * @return true if contains, otherwise return false
     */
    public static boolean containPerson(String name) {
        return relationshipDatabase.hasPerson(name);
    }

    /**
     * Check whether the relationship contained in the database
     *
     * @param name1
     * @param name2
     * @return true if contains, otherwise return false
     */
    public static boolean containRelationship(String name1, String name2) {
        return relationshipDatabase.containsRelationship(name1, name2);
    }

    /**
     * Delete relationship between two people
     *
     * @param person1
     * @param person2
     * @return true if delete successfully, otherwise return false
     */
    public static boolean deleteRelationship(String person1, String person2) {
        return relationshipDatabase.removeRelationship(person1, person2);
    }

    /**
     * Update the relationship weight
     *
     * @param person1
     * @param person2
     * @param weight
     * @return true if update successfully, otherwise return false
     */
    public static boolean updateRelationship(String person1, String person2, int weight) {
        return relationshipDatabase.updateRelationship(person1, person2, weight);
    }

    /**
     * Get the shortest path of the relationship
     *
     * @param name1
     * @param name2
     * @return List object that contains the path, null if relationship not found
     */
    public static List<Person> getPathOfRelationship(String name1, String name2) {
        return relationshipDatabase.getRelationshipPath(name1, name2);
    }

    /**
     * Get the total degree of relationship
     *
     * @param name1
     * @param name2
     * @return the total degree of relationship, return -1 if no relationship found
     */
    public static int getTotalDegreeOfRelationship(String name1, String name2) {
        return relationshipDatabase.getTotalRelationshipDegree(name1, name2);
    }

    /**
     * Total number of people in the database
     *
     * @return total number of people
     */
    public static int totalNumOfPeople() {
        return relationshipDatabase.getNumberOfPersons();
    }

    /**
     * Total number of relationships in the database
     *
     * @return total number of relationships
     */
    public static int totalNumOfRelationships() {
        return relationshipDatabase.getNumberOfRelationships();
    }
}

