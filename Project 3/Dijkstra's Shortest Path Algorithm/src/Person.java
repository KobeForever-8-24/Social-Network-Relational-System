// --== CS400 File Header Information ==--
// Name: Weijie Zhou
// Email: wzhou226@wisc.edu
// Team: CG
// TA: Yeping Wang
// Lecturer: Gary Dahl
// Notes to Grader: None
/**
 * @program: Dijkstra's Shortest Path Algorithm
 * @description: Person class
 * @author: Weijie Zhou
 **/
public class Person{
    private String name;
    private int age;
    private String profession;

    /**
     * default constructor
     */
    public Person(){}

    /**
     * constructor for employee
     *
     * @param name
     * @param age
     * @param
     */
    public Person(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    /**
     * get method for person name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set method for person name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get method for age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * set method for person age
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * get method for person profession
     *
     * @return profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * set method for person profession
     *
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

}
