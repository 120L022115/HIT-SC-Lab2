/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

import P1.graph.Graph;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   inputs string, output poem.
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // tests
    @Test public void testAnalyze() {
    	Graph<String> g = Graph.empty();
    	g.add("a");
    	g.add("b");
    	g.set("a", "b", 1);
    	g.add("c");
    	g.add("d");
    	g.set("c", "d", 1);
    	g.add("e");
    	g.set("b", "c", 1);
    	g.set("a", "e", 1);
    	g.set("d", "e", 1);
    	g.set("c", "e", 3);
    	var l = Graph.GraphMaxWeight(g,"a","a");
    	assertEquals(null,l);
    	l = Graph.GraphMaxWeight(g,"a","b");
    	assertEquals(null,l);
    	l = Graph.GraphMaxWeight(g,"b","e");
    	assertEquals("c",l);
    }
    
    @Test public void testPoem() throws IOException {
    	final GraphPoet nimoy = new GraphPoet(new File("src/P1/poet/mugar-omni-theater.txt"));
        final String input = "Test the system.";
        assertEquals("Test of the system.",nimoy.poem(input));
    }
    
    @Test public void testToString() throws IOException {
    	final GraphPoet nimoy = new GraphPoet(new File("src/P1/poet/mugar-omni-theater.txt"));
        assertEquals("{\n"
        		+ "	This--1->is\n"
        		+ "	is--1->a\n"
        		+ "	a--2->test\n"
        		+ "	test--2->of\n"
        		+ "	of--2->the\n"
        		+ "	the--1->Mugar\n"
        		+ "	Mugar--1->Omni\n"
        		+ "	Omni--1->Theater\n"
        		+ "	Theater--1->sound\n"
        		+ "	sound--1->system.\n"
        		+ "	system.--1->is\n"
        		+ "	is--1->data\n"
        		+ "	data--1->a\n"
        		+ "}",nimoy.toString());
    }
    
}
