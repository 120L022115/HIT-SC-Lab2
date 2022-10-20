/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = a graph.
    //   vertices[i]={a--0->b} -> a to b as a 0 weight edge.
    //
    // Representation invariant:
    //   no two Vertices has a same name.
    //
    // Safety from rep exposure:
    //   all fields are private.
    //   all returns are immutable.
    //   except the Set but it has deal with Defensive Coping.
    
    // constructor
    // nothing to do
    
    // checkRep
    private boolean checkRep() {
    	Set<L> s = new HashSet<L>();
    	for(Vertex<L> v:vertices) {
    		if(s.contains(v.name())) {
    			assert false:"two vertices has a same name.";
    			return false;
    		}
    		else s.add(v.name());
    	}
    	return true;
    }
    // methods
    @Override public boolean add(L vertex) {
    	for(Vertex<L> r : vertices) {
        	if(r.name().equals(vertex)) {
        		return false;
        	}
        }
    	checkRep();
    	return vertices.add(new Vertex<L>(vertex));
    	
    }
    
    @Override public int set(L source, L target, int weight) {
    	
    	this.add(source);
    	this.add(target);
    	for(Vertex<L> v : vertices) {
        	if(v.name().equals(source)) {
        		if(weight==0) v.removeEdgeTarget(target);
        		for(Vertex<L> r : vertices) {
                	if(r.name().equals(target)) {
                		if(weight==0) {
                			return r.removeEdgeSource(source);
                		}
                		v.addOut(r.name(), weight);
                		return r.addIn(v.name(), weight);
                	}
                }
        	}
        }
    	checkRep();
    	return 0;
    }
    
    @Override public boolean remove(L vertex) {
    	
    	for(Vertex<L> v : vertices) {
        	if(v.name().equals(vertex)) {
        		vertices.remove(v);
        		var s = v.delVertex();
        		for(L sp : s) {
        			for(Vertex<L> p : vertices) {
        				if(p.name().equals(sp)) {
                    		p.synDelVertex(vertex);
                    	}
        			}
                }
        		return true;
        	}
        }
    	checkRep();
    	return false;
    }
    
    @Override public Set<L> vertices() {
        var s = new HashSet<L>();
        for(Vertex<L> v : vertices) {
        	s.add(v.name());
        }
    	return Set.copyOf(s);
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	for(Vertex<L> v : vertices) {
        	if(v.name().equals(target)) {
        		return v.in();
        	}
        }
    	return null;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	for(Vertex<L> v : vertices) {
        	if(v.name().equals(source)) {
        		return v.out();
        	}
        }
    	return null;
    }
    
    // toString()
    /**
     * convert the graph to string.
     * as every edge in a line, formatted with a--w->b.
     * @return string
     */
    @Override public String toString() {
    	StringBuilder s = new StringBuilder("{");
    	for(Vertex<L> v : vertices) {
    		String tmp = v.toString();
    		if(tmp.equals("")) continue;
    		if(tmp.equals(" ")) continue;
    		if(tmp.equals("\n")) continue;
    		//System.out.println("11"+tmp+"11");
    		s.append("\n\t");
        	s.append(tmp);
        	
        }
    	s.append("\n");
    	s.append("}");
        return s.toString();
    }
}

/**
 * Vertex and its linked Vertex.
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // fields
	private L name;	
	private Map<L,Integer> in = new HashMap<L,Integer>();
	private Map<L,Integer> out = new HashMap<L,Integer>();
	
    // Abstraction function:
    //   in<OtherVertex,0> -> a name Vertex to OtherVertex has a 0 weight edge.
	//   out<OtherVertex,0> -> a OtherVertex to name Vertex has a 0 weight edge.
	//
    // Representation invariant:
    //   no two same vertex in the in edge or the out edge.
	//
    // Safety from rep exposure:
    //   all fields are private.
    //   all returns are immutable, besides the L due to the user using.
	/**
     * get the name of this Vertex.
     * @return the name of this vertex
     */
	public L name() {
		return this.name;
	}
	/**
     * get the in edge to this Vertex.
     * @return in edges of this vertex
     */
	public Map<L,Integer> in() {
		return Map.copyOf(in);
	}
	/**
     * get the out edge to this Vertex.
     * @return out edges of this vertex
     */
	public Map<L,Integer> out() {
		return Map.copyOf(out);
	}
    
	
	// constructor
    
	/**
     * Create a Vertex.
     * @param name of the vertex
     */
	public Vertex(L name){
		this.name = name;
	}
	// checkRep
	private boolean checkRep() {
		Set<L> s1 = new HashSet<L>();
		for(L l : in.keySet()) {
			if(s1.contains(l)) {
				assert false:"two equal name of vertex in a edge.";
				return false;
			}
			s1.add(l);
		}
		return true;
    }
    // methods
	/**
	 * delete a vertex and its relative edges
	 * @param p the deleted vertex
	 */
	public void synDelVertex(L v) {
		Set<L> keys = in.keySet();
		Iterator<L> iterator = keys.iterator();
	    while (iterator.hasNext()){
	    	L p = iterator.next();        	
        	if(v.equals(p)) {
        		in.remove(p);
        	}
        }
	    keys = out.keySet();
		iterator = keys.iterator();
	    while (iterator.hasNext()){
	    	L p = iterator.next();        	
        	if(v.equals(p)) {
        		out.remove(p);
        	}
        }
	}
	/**
	 * delete this Vertex
	 * @return the set of relative Vertices
	 */
	public Set<L> delVertex() {
		Set<L> re = new HashSet<L>();
		Set<L> keys = in.keySet();
		Iterator<L> iterator = keys.iterator();
	    while (iterator.hasNext()){
	    	L p = iterator.next();        	
        	re.add(p);
        }
	    keys = out.keySet();
		iterator = keys.iterator();
	    while (iterator.hasNext()){
	    	L p = iterator.next();        	
	    	re.add(p);
        }
	    return re;
	}
	/**
	 * add or update in edges
	 * @param p the parent vertex
	 * @param w weight
	 * @return previous weight or 0 if not exists before add.
	 */
	public int addIn(L p,int w) {
		if(this.in.containsKey(p)) {
			return this.in.put(p, w);
		}
		this.in.put(p, w);
		return 0;
	}
	/**
	 * add or update out edges
	 * @param p the child vertex
	 * @param w weight
	 * @return previous weight or 0 if not exists before add.
	 */
	public int addOut(L p,int w) {
		if(this.out.containsKey(p)) {
			return this.out.put(p, w);
		}
		this.out.put(p, w);
		return 0;
	}
	/**
	 * add both out and in edge
	 * @param p vertex
	 * @param w weight
	 */
	public void addInOut(L p,int w) {
		this.in.put(p, w);
		this.out.put(p, w);
		checkRep();
	}
	/**
	 * remove out edge
	 * @param t the target vertex
	 * @return the weight if exists
	 */
	public int removeEdgeTarget(L t) {
		return this.out.remove(t);
	}
	/**
	 * remove in edge
	 * @param s the source vertex
	 * @return the weight if exists
	 */
	public int removeEdgeSource(L s) {
		return this.in.remove(s);
	}
	
	/**
	 * evaluate equals
	 * @param p the parent vertex
	 * @return equals
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
        Vertex<L> v = null;
        try {
        	v = (Vertex<L>) o;
        }catch(ClassCastException u) {
        	return false;
        }
		return name.equals(v.name()) && in.equals(v.in()) && out.equals(v.out());
	}
	@Override
	public int hashCode() {
		return name.hashCode()+in.hashCode()+out.hashCode();
	}
	/**
	 * to String
	 * formatted with name1--weight->name2
	 * @return string
	 */
    // toString()
	@Override public String toString() {
		Set<L> keys = out.keySet();
	    Iterator<L> iterator= keys.iterator();
	    StringBuilder childs = new StringBuilder("");
        boolean fi = true;
	    while (iterator.hasNext()){
	    	L p = iterator.next();        	
        	if(!fi) childs.append("\n\t");
        	fi = false;
        	childs.append(name+"--"+out.get(p).toString()+"->"+p.toString());
        }
		return childs.toString();
	}
}
