/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    // build a graph, to string, then evaluate equals.
    
    // tests for ConcreteVerticesGraph.toString()
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
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // toString()
    //     no inputs, only output is String
    //     evaluate with equals()
    // equals()\addIn()\addOut()\addInOut()
    //     input Vertices and edges, output is if or not equal.
    
    //  tests for operations of Edge
    @Test public void testVertexToString() {
    	Vertex<String> e0 = new Vertex<>("a");
    	//Vertex e1 = new Vertex("b");
    	e0.addOut("b", 0);
    	assertEquals("a--0->b",e0.toString());
    }
    @Test public void testVertexEquals() {
    	Vertex<String> e0 = new Vertex<>("a");
    	Vertex<String> e1 = new Vertex<>("b");
    	e0.addOut("b", 0);
    	Vertex<String> e2 = new Vertex<>("a");
    	e2.addOut("b", 0);
    	assertEquals(true,e0.equals(e2));
    	assertEquals(false,e0.equals(e1));
    	assertEquals(false,e1.equals(e2));
    	e2.addOut("b", 1);
    	e2.addOut("b",20);
    	e2.addInOut("b", 0);
    	e2.removeEdgeSource("b");
    	e2.removeEdgeTarget("b");
    }
}
