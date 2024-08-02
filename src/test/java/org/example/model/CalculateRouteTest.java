package org.example.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculateRouteTest {

    private Graph graphA;
    private Graph graphB;

    @Before
    public void setUp() {
        graphA = new Graph("./src/main/resources/input.txt");
        graphB = new Graph("./src/main/resources/my_input.txt");
    }

    // ----- Exercise testcases

    @Test
    public void testGraphAOne() {
        String[] path = new String[] {"A", "B", "C"};
        String distance = graphA.calculateRouteDistance(path);
        assertEquals(
            "Path [A, B, C] of graphA measures = 9",
            "9",
            distance
        );
    }

    @Test
    public void testGraphATwo() {
        String[] path = new String[] {"A", "D"};
        String distance = graphA.calculateRouteDistance(path);
        assertEquals(
            "Path [A, D] of graphA measures = 5",
            "5",
            distance
        );
    }

    @Test
    public void testGraphAThree() {
        String[] path = new String[] {"A", "D", "C"};
        String distance = graphA.calculateRouteDistance(path);
        assertEquals(
            "Path [A, D, C] of graphA measures = 13",
            "13",
            distance
        );
    }

    @Test
    public void testGraphAFour() {
        String[] path = new String[] {"A", "E", "B", "C", "D"};
        String distance = graphA.calculateRouteDistance(path);
        assertEquals(
            "Path [A, E, B, C, D] of graphA measures = 22",
            "22",
            distance
        );
    }

    @Test
    public void testGraphAFive() {
        String[] path = new String[] {"A", "E", "D"};
        String distance = graphA.calculateRouteDistance(path);
        assertEquals(
            "Path [A, E, D] of graphA measures = -1",
            "NO SUCH ROUTE",
            distance
        );
    }

    // ----- Custom testcases

    @Test
    public void testGraphBOne() {
        String[] path = new String[] {"F", "C",};
        String distance = graphB.calculateRouteDistance(path);
        assertEquals(
            "Path [F, C] of graphB measures = -1",
            "NO SUCH ROUTE",
            distance
        );
    }

    @Test
    public void testGraphBTwo() {
        String[] path = new String[] {"A", "B", "C"};
        String distance = graphB.calculateRouteDistance(path);
        assertEquals(
            "Path [A, B, C] of graphB measures = 5",
            "5",
            distance
        );
    }

    @Test
    public void testGraphBThree() {
        String[] path = new String[] {"A", "F"};
        String distance = graphB.calculateRouteDistance(path);
        assertEquals(
            "Path [A, F] of graphB measures = 10",
            "10",
            distance
        );
    }

    @Test
    public void testGraphBFour() {
        String[] path = new String[] {"A", "B", "E", "D", "C"};
        String distance = graphB.calculateRouteDistance(path);
        assertEquals(
            "Path [A, B, E, D, C] of graphB measures = 11",
            "11",
            distance
        );
    }

}
