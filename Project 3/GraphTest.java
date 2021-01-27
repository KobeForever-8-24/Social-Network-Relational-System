// --== CS400 File Header Information ==--
// Name: Weijie Zhou
// Email: wzhou226@wisc.edu
// Team: CG
// TA: Yeping Wang
// Lecturer: Gary Dahl
// Notes to Grader: None
/**
 * @program: Dijkstra's Shortest Path Algorithm
 * @description: Test for CS400Graph
 * @author: Weijie Zhou
 * @create: 2020-10-30 16:57
 **/
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<Integer> graph;
    @BeforeEach
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert verticies 0-9
        for(int i=0;i<10;i++)
            graph.insertVertex(i);
        // insert edges from Week 08. Dijkstra's Activity
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,7,2);
        graph.insertEdge(1,8,4);
        graph.insertEdge(2,4,4);
        graph.insertEdge(2,6,3);
        graph.insertEdge(3,1,6);
        graph.insertEdge(3,7,2);
        graph.insertEdge(4,5,4);
        graph.insertEdge(5,0,2);
        graph.insertEdge(5,1,4);
        graph.insertEdge(5,9,1);
        graph.insertEdge(6,3,1);
        graph.insertEdge(7,0,3);
        graph.insertEdge(7,6,1);
        graph.insertEdge(8,9,3);
        graph.insertEdge(9,4,5);
    }


    /**
     * Checks the distance/total weight cost from the vertex labelled 0 to 8
     * (should be 15), and from the vertex labelled 9 to 8 (should be 17).
     */
    @Test
    public void providedTestToCheckPathCosts() {
        assertTrue(graph.getPathCost(0,8) == 15);
        assertTrue(graph.getPathCost(9,8) == 17);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * labelled 0 to 8, and from the vertex labelled 9 to 8.
     */
    @Test
    public void providedTestToCheckPathContents() {
        assertTrue(graph.shortestPath(0, 8).toString().equals(
                "[0, 2, 6, 3, 1, 8]"
        ));
        assertTrue(graph.shortestPath(9, 8).toString().equals(
                "[9, 4, 5, 1, 8]"
        ));
    }


    /**
    * @Description: Checks if the path cost of last week acvitity correct
    * @Param: []
    * @return: void
    * @Author: Weijie Zhou
    * @Date: 2020/11/4
    */
    @Test
    public void testActivityPathCost(){
        assertTrue(graph.getPathCost(3, 5) == 14);
    }

    /**
    * @Description: Check if the sequence of value of last week activity correct
    * @Param: []
    * @return: void
    * @Author: Weijie Zhou
    * @Date: 2020/11/4
    */
    @Test
    public void testActivitySequenceOfValue(){
        assertTrue(graph.shortestPath(3, 5).toString().equals(
                "[3, 7, 0, 2, 4, 5]"
        ));
    }

    /**
    * @Description: Check if making the number 4 as the source node correct for both sequence and distance
    * @Param: []
    * @return: void
    * @Author: Weijie Zhou
    * @Date: 2020/11/4
    */
    @Test
    public void testActivityStartFromFour(){
        assertTrue(graph.shortestPath(4, 8).toString().equals(
                "[4, 5, 1, 8]"
        ));
        assertTrue(graph.getPathCost(4, 8) == 12);
    }

    /**
    * @Description: Check if making the number 5 as the source node correct for sequence
    * @Param:
    * @return:
    * @Author: Weijie Zhou
    * @Date: 2020/11/4
    */
    @Test
    public void testActivityStartFromFive(){
        assertTrue(graph.shortestPath(5, 8).toString().equals(
                "[5, 1, 8]"
        ));
    }

    /**
    * @Description: Check if making the number 1 as the source node correct for sequence
    * @Param:
    * @return:
    * @Author: Weijie Zhou
    * @Date: 2020/11/4
    */
    @Test
    public void testActivityStartFromOne(){
        assertTrue(graph.shortestPath(1, 5).toString().equals(
                "[1, 7, 0, 2, 4, 5]"
        ));
    }


}