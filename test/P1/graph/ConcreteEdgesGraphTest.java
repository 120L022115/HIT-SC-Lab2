/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    // build a graph, to string, then evaluate equals.
    
    // tests for ConcreteEdgesGraph.toString()
    @Test public void testGraphToString() {
    	Graph<String> g = emptyInstance();
    	g.add("a");
    	g.add("b");
    	g.set("a", "b", 1);
    	assertEquals("{\n\ta--1->b\n}",g.toString());
    	g.add("c");
    	g.add("d");
    	g.set("c", "d", 1);
    	assertEquals("{\n\ta--1->b\n\tc--1->d\n}",g.toString());
    	Graph<String> g1 = emptyInstance();
    	g1.add("a");
    	g1.add("b");
    	g1.set("a", "b", 1);
    	g1.add("c");
    	g1.add("d");
    	g1.set("c", "d", 1);
    	g1.add("e");
    	g1.set("c", "e", 1);
    	g1.set("e", "d", 1);
    	g1.remove("e");
    	assertEquals(g.toString(),g1.toString());
    	g1.set("c", "d", 0);
    	g1.remove("000");
    	g1.set("c", "d", 1);
    	g1.set("d", "c", 1);
    	g1.set("f", "g", 1);
    	g1.set("g", "f", 0);
    	g1.targets("c");
    	g1.sources("c");
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // toString()
    //     no inputs, only output is String
    //     evaluate with equals()
    // equals()
    //     input another Edge, output is if or not equal.
    
    // tests for operations of Edge
    @Test public void testEdgeToString() {
    	Edge<String> e = new Edge<String>("a","b",0);
    	assertEquals("a--0->b",e.toString());
    }
    @Test public void testEdgeEquals() {
    	Edge<String> e0 = new Edge<String>("a","b",0);
    	Edge<String> e1 = new Edge<String>("a","b",1);
    	Edge<String> e2 = new Edge<String>("a","b",0);
    	assertEquals(true,e0.equals(e2));
    	assertEquals(false,e0.equals(e1));
    	assertEquals(false,e1.equals(e2));
    }
    
    @Test public void testEdgeEquals2() {
    	Edge<String> e0 = new Edge<String>("1","2",0);
    	Edge<String> e1 = new Edge<String>("1","2",1);
    	Edge<String> e2 = new Edge<String>("1","2",0);
    	Edge<String> e3 = new Edge<String>("1","2",0);
    	Edge<String> e4 = new Edge<String>("1","2",1);
    	//Edge<String> e5 = new Edge<String>("1","2",0);
    	assertEquals(true,e3.equals(e2));
    	assertEquals(false,e0.equals(e4));
    	assertEquals(false,e1.equals(e2));
    }
}
