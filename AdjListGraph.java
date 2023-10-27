
package edu.njit.cs114;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * Author: Kevin Aguilar
 * Date created: 12/4/2022
 */
public class AdjListGraph extends AbstractGraph {
    private List<Edge> [] adjLists;
    public AdjListGraph(int n, boolean directed) {
        super(n, directed);
        init(n);
    }
    @Override
    public void init(int n) {
        adjLists = (List<Edge> []) Array.newInstance(List.class,n);
        for (int i=0; i < n; i++) {
            adjLists[i] = new LinkedList<>();
        }
    }
    @Override
    public void addGraphEdge(int u, int v, int weight) throws GraphException {
        if (v < 0 || v >= adjLists.length) {
            throw new GraphException("Invalid vertex "+v);
        }
        // throw GraphException if the edge already exists
        Iterator<Edge> edgeIter = getOutgoingEdges(u);
        while(edgeIter.hasNext()) {
        	Edge nextEdge = edgeIter.next();
        	if(nextEdge.to == v)
        		throw new GraphException("Edge already exists");
        }
        adjLists[u].add(new Edge(u,v,weight));

        /*
         * Complete code here
         * Use iterator
         */
    }
    @Override
    public Iterator<Edge> getOutgoingEdges(int v) {
        if (v < 0 || v >= adjLists.length) {
            throw new IllegalArgumentException("Invalid vertex "+v);
        }
        return adjLists[v].iterator();
    }
    @Override
    public Edge delGraphEdge(int u, int v) {
    	Iterator<Edge> edgeIter = getOutgoingEdges(u);
    	while(edgeIter.hasNext()) {
    		Edge nextEdge = edgeIter.next();
    		if(nextEdge.to == v) {
    			edgeIter.remove();
    			return nextEdge;  		
    		}
    	}   	
        /*
         * Complete code here
         * Use iterator
         */
        return null;
    }
    /**
     * Get edge from u to v if it exists else null
     * @param u
     * @param v
     * @return
     */
    public Edge getEdge(int u, int v) {
    	Iterator<Edge> edgeIter = getOutgoingEdges(u);
    	while(edgeIter.hasNext()) {
    		Edge nextEdge = edgeIter.next();
    		if(nextEdge.to == v)
    			return nextEdge;
    	}
    	
        /*
         * Complete code here
         * Use iterator
         */
        return null;
    }
    @Override
    public boolean isEdge(int u, int v) {
        return (getEdge(u, v) != null);
    }
    @Override
    public int weight(int u, int v) throws GraphException {
        Edge edge = getEdge(u,v);
        if (edge == null) {
            throw new GraphException("No edge from " +u+ " to "+v + " exists");
        }
        return edge.weight;
    }
}
