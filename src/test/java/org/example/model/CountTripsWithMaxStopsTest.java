package org.example.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountTripsWithMaxStopsTest {

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
        int distance = graphA.countTripsWithMaxStops("C", "C", 3);
        assertEquals(
            "The total number of trips between C and C in graphA with a max of 3 stops = 2",
            2,
            distance
        );
    }

    // ----- Custom testcases

    @Test
    public void testGraphATwo() {
        int distance = graphA.countTripsWithMaxStops("A", "E", 3);
        assertEquals(
            "The total number of trips between A and E in graphA  with a max of 3 stops = 3",
            4,
            distance
        );
    }

    @Test
    public void testGraphBOne() {
        int distance = graphB.countTripsWithMaxStops("A", "F", 3);
        assertEquals(
                "The total number of trips between A and F in graphB with a max of 3 stops = 3",
                3,
                distance
        );
    }

}
