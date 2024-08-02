package org.example.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountTripsWithTotalStopsTest {

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
        int distance = graphA.countTripsWithExactStops("A", "C", 4);
        assertEquals(
            "The total number of trips between A and C in graphA with a total of 4 stops = 3",
            3,
            distance
        );
    }

    // ----- Custom testcases

    @Test
    public void testGraphATwo() {
        int distance = graphA.countTripsWithExactStops("A", "E", 3);
        assertEquals(
            "The total number of trips between A and E in graphA  with a total of 3 stops = 2",
            2,
            distance
        );
    }

    @Test
    public void testGraphBOne() {
        int distance = graphB.countTripsWithExactStops("A", "F", 1);
        assertEquals(
            "The total number of trips between A and F in graphB with a total of 1 stops = 1",
            1,
            distance
        );
    }

}
