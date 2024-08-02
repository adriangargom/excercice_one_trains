package org.example.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindShortestPathTest {

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
        int distance = graphA.findShortestPath("A", "C");
        assertEquals(
            "The minimum distance between A and C in graphA = 9",
            9,
            distance
        );
    }

    @Test
    public void testGraphATwo() {
        int distance = graphA.findShortestPath("B", "B");
        assertEquals(
            "The minimum distance between B and B in graphA = 9",
            9,
            distance
        );
    }

    // ----- Custom testcases

    @Test
    public void testGraphBOne() {
        int distance = graphB.findShortestPath("A", "F");
        assertEquals(
            "The minimum distance between A and F in graphB = 7",
            7,
            distance
        );
    }

    @Test
    public void testGraphBTwo() {
        int distance = graphB.findShortestPath("A", "C");
        assertEquals(
            "The minimum distance between A and C in graphB = 4",
            4,
            distance
        );
    }

}
