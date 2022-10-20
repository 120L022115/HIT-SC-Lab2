/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // test other vertex label types in Problem 3.2
    @Test
    public void testGraph1() {
    	Graph<Integer> g = Graph.empty();
    	g.add(1);
    	g.add(2);
    	g.set(1, 2, 1);
    	assertEquals("{\n\t1--1->2\n}",g.toString());
    	g.add(3);
    	g.add(4);
    	g.set(3, 4, 1);
    	assertEquals("{\n\t1--1->2\n\t3--1->4\n}",g.toString());
    	Graph<Integer> g1 = Graph.empty();
    	g1.add(1);
    	g1.add(2);
    	g1.set(1, 2, 1);
    	g1.add(3);
    	g1.add(4);
    	g1.set(3, 4, 1);
    	g1.add(5);
    	g1.set(3, 5, 1);
    	g1.set(5, 4, 1);
    	g1.remove(5);
    	assertEquals(g.toString(),g1.toString());
    }
    @Test public void testGraph2() {
    	Graph<String> g = Graph.empty();
    	g.add("a");
    	g.add("b");
    	g.set("a", "b", 1);
    	assertEquals("{\n\ta--1->b\n}",g.toString());
    	g.add("c");
    	g.add("d");
    	g.set("c", "d", 1);
    	assertEquals("{\n\ta--1->b\n\tc--1->d\n}",g.toString());
    	Graph<String> g1 = Graph.empty();
    	g1.add("a");
    	g1.add("b");
    	g1.set("a", "b", 1);
    	g1.add("c");
    	g1.add("d");
    	g1.set("c", "d", 1);
    	g1.add("e");
    	assertEquals(0,g1.set("c", "e", 1));
    	assertEquals(0,g1.set("e", "d", 1));
    	g1.remove("e");
    	assertEquals(g.toString(),g1.toString());
    }
}
