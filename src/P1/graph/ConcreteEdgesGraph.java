/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   Vertex in Vertices, edge in edges,
    //   to describe a graph.
    //   AF(vertices,edges) = a graph which has vertices and edges
    //   the vertices are a set of vertices.
    //   the edges are a list of edges.
    
    // Representation invariant:
    //   no Vertex of edges is not in vertices.
    //   no two Vertices has the same name.
    
    // Safety from rep exposure:
    //   all fields are private.
    //   all returns are immutable.
    //	except the Map but it has deal with Defensive Coping.
    
    // constructor
    public ConcreteEdgesGraph() {
    	
    }
    // checkRep
    private boolean checkRep() {
    	for(int i=0;i<edges.size();) {
    		Edge<L> e = edges.get(i);
    		if(!vertices.contains(e.end())||!vertices.contains(e.start())) {
    			assert false:"Vertex of edges is not in vertices.";
    			return false;
    		}else i++;
    	}
    	return true;
    }
    @Override public boolean add(L vertex) {
    	if(vertices.contains(vertex)) return false;
    	vertices.add(vertex);
    	checkRep();
    	return true;
    }
    
    @Override public int set(L source, L target, int weight) {
    	if(!vertices.contains(source)) {
    		vertices.add(source);
    	}
    	if(!vertices.contains(target)) {
    		vertices.add(target);
    	}
    	for(int i=0;i<edges.size();i++) {
			Edge<L> e = edges.get(i);
			if(e.start().equals(source)&&e.end().equals(target)) {
				if(weight==0) {
					edges.remove(i);
					return e.weight();
				}
				edges.set(i, new Edge<L>(source,target,weight));
				return e.weight();
			}
		}
		edges.add(new Edge<L>(source,target,weight));
		checkRep();
        return 0;
    }
    
    @Override public boolean remove(L vertex) {
        if(!vertices.contains(vertex)) return false;
    	vertices.remove(vertex);
    	for(int i=0;i<edges.size();) {
    		Edge<L> e = edges.get(i);
    		if(e.start().equals(vertex)||e.end().equals(vertex)) {
    			edges.remove(i);
    		}else i++;
    	}
    	checkRep();
    	return true;
    }
    
    @Override public Set<L> vertices() {
    	return vertices;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> m = new HashMap<>();
        for(int i=0;i<edges.size();i++) {
        	Edge<L> e = edges.get(i);
        	if(e.end().equals(target)) {
        		 m.put(e.start(), e.weight());
        	}
        }
        return m;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L,Integer> m = new HashMap<>();
        for(int i=0;i<edges.size();i++) {
        	Edge<L> e = edges.get(i);
        	if(e.start().equals(source)) {
        		 m.put(e.end(), e.weight());
        	}
        }
        return m;
    }
    
    // toString()
    /**
     * convert the graph to string.
     * as every edge in a line, formatted with a--w->b.
     * @return string
     */
    @Override public String toString() {
    	StringBuilder s = new StringBuilder("{\n");
    	for(int i=0;i<edges.size();i++) {
    		s.append("\t");
    		s.append(edges.get(i).toString());
    		s.append("\n");
    	}
    	s.append("}");
        return s.toString();
    }
}

/**
 * Describe a connection between two vertex, and is has direction.
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 */
class Edge<L> {
    
    // fields
	private L start,end;
	private int weight;
	
	// Abstraction function:
    //   AF({start,end,weight}) = a edge in graph
	//   	start: the start vertex of the edge
	//	 	end: the end vertex of the edge
	//		weight: the weight of the edge
	
    // Representation invariant:
    //   the weight is a positive integer
    
    // Safety from rep exposure:
	//   all fields are private.
    //   all returns are immutable.
    /**
     * get the start Vertex of this edge.
     * @return the start Vertex
     */
	public L start() {
		return start;
	}
	/**
     * get the end Vertex of this edge.
     * @return the end Vertex
     */
	public L end() {
		return end;
	}
	/**
     * get the weight of this edge.
     * @return the weight
     */
	public int weight() {
		return weight;
	}
    
    // constructor
    /**
	 * Create a Edge between two Vertex.
	 * @param start
	 * @param end
	 */
    public Edge(L start,L end,int weight) {
    	this.start = start;
    	this.end = end;
    	this.weight = weight;
    	checkRep();
    }
    
    // checkRep
	private boolean checkRep() {
    	if(weight<0) {
    		assert false:"weight negative";
    		return false;
    	}
		return true;
    }
    // methods
    /**
	 * evaluate equals
	 * @param p the vertex to compare
	 * @return equals or not
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		//判断内存地址
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if(!(this.getClass().isInstance(o))) return false;
        Edge<L> e = null;
        try {
        	e = (Edge<L>) o;
        }catch(ClassCastException u) {
        	return false;
        }
        return this.start.equals(e.start()) && this.end.equals(e.end()) && this.weight==e.weight();
    }
	@Override
	public int hashCode() {
		return this.start.hashCode()+this.end.hashCode()+this.weight;
	}
    
    // toString()
    /**
	 * to String
	 * convert the edge to string.
     * formatted with a--w->b.
	 * @return string
	 */
    @Override public String toString() {
    	return start.toString()+"--"+Integer.toString(weight)+"->"+end.toString();
    }
}
