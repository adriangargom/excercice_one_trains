package org.example.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindNumberOfDifferentRoutesTest {

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
        int distance = graphA.countRoutesWithMaxDistance("C", "C", 30);
        assertEquals(
            "The number of routes from C to C in graphA with a total distance of less than 30 = 7",
            7,
            distance
        );
    }

    // ----- Custom testcases

    @Test
    public void testGraphBOne() {
        int distance = graphB.countRoutesWithMaxDistance("A", "E", 10);
        assertEquals(
            "The number of routes from A to E in graphB with a total distance of less than 10 = 2",
            2,
            distance
        );
    }

    @Test
    public void testGraphBTwo() {
        int distance = graphB.countRoutesWithMaxDistance("A", "F", 8);
        assertEquals(
            "The number of routes from A to F in graphB with a total distance of less than 8 = 1",
            1,
            distance
        );
    }

}
