
package edu.njit.cs114;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Author: Kevin Aguilar
 * Date created: 12/4/2022
 */
public class GraphSearch {
    public static final int VISITED = 1;
    public static final int UNVISITED = 0;
    public static void preVisit(Graph g, int v) {
        System.out.print(v+"(" + g.getMark(v)+ "),");
    }
    public static void postVisit(Graph g, int v) {}
    public static void graphTraverseBFS(Graph g) {
        System.out.println("breadth-first search of graph..");
        for (int v = 0; v < g.numVertices(); v++) {
            g.setMark(v, UNVISITED);
        }
        for (int v = 0; v < g.numVertices(); v++) {
            if (g.getMark(v) == UNVISITED) {
                System.out.println("Start vertex : "+v);
                bfs(g, v);
                System.out.println("");
            }
        }
        System.out.println("");
    }
    public static void graphTraverseDFS(Graph g) {
        System.out.println("Depth-first search of graph..");
        for (int v = 0; v < g.numVertices(); v++) {
            g.setMark(v, UNVISITED);
        }
        for (int v = 0; v < g.numVertices(); v++) {
            if (g.getMark(v) == UNVISITED) {
                System.out.println("Start vertex : "+v);
                dfs(g, v);
                System.out.println("");
            }
        }
        System.out.println("");
    }
    public static void dfs(Graph g, int v) {
        preVisit(g, v);
        g.setMark(v,VISITED);
        Iterator<Graph.Edge> outEdgeIter = g.getOutgoingEdges(v);
        while (outEdgeIter.hasNext()) {
            Graph.Edge edge = outEdgeIter.next();
            int w = edge.to;
            if (g.getMark(w) == UNVISITED) {
                dfs(g, w);
            }
        }
        postVisit(g, v);
    }
    public static void bfs(Graph g, int start) {
        Queue<Integer> vertexQueue = new LinkedList<Integer>();
        vertexQueue.add(start);
        g.setMark(start,1);
        while (!vertexQueue.isEmpty()) {
        	int v = vertexQueue.remove();
        	Iterator<Graph.Edge> edgeIter = g.getOutgoingEdges(v);
        	preVisit(g,v);
        	while(edgeIter.hasNext()) {
                Graph.Edge edge = edgeIter.next();
                if (g.getMark(edge.to) == 0)
                {
                    g.setMark(edge.to, g.getMark(v)+1);
                    vertexQueue.add(edge.to);
                }
            }
            postVisit(g, v);
        	}        	        	
            // complete the code for lab assignment
            // v = remove vertex from queue
            //check if it visited , if not, mark it 1+v and add to queue        	
        }
    /**
     * (complete it for homework assignment)
     * Returns true if a cycle exists in undirected graph
     * @param g undirected graph
     * @return
     */
    public static boolean cycleExists(Graph g) {
    	int v;
        for (v = 0; v < g.numVertices(); v++) {
            g.setMark(v, UNVISITED);
        }
        for (v = 0; v < g.numVertices(); v++) {
            if (g.getMark(v) == UNVISITED) {
                System.out.println("Start vertex : "+v);
                break;
            }
        }
        return cycleExists(g,v);
    }
    
    public static boolean cycleExists(Graph g, int start) {
    	if(g.getMark(start)== VISITED) {
    		return true; 
    	}
    	Queue<Integer> vertexQueue = new LinkedList<Integer>();
    	vertexQueue.add(start);
    	g.setMark(start, VISITED);
    	while (!vertexQueue.isEmpty()) {
    		int v = vertexQueue.remove();
        	Iterator<Graph.Edge> edgeIter = g.getOutgoingEdges(v);
    		
    	}
    	
        return false;
    }
    /**
     * (complete it for homework assignment)
     * Returns true if a simple cycle with odd number of edges exists in undirected graph
     * @param g undirected graph
     * @return
     */
    public static boolean oddCycleExists(Graph g) {
        return false;
    }
    /**
     * Does the directed graph have a cycle of directed edges ? (Extra credit)
     * @param g
     * @return
     */
    public static boolean cycleExistsDirect(Graph g) {
        return false;
    }
    public static void main(String [] args) throws Exception {
        Graph g = new AdjListGraph(8, true);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(2,0);
        g.addEdge(3,2);
        g.addEdge(4,3);
        g.addEdge(4,6);
        g.addEdge(5,7);
        g.addEdge(6,5);
        g.addEdge(7,5);
        g.addEdge(7,6);
        System.out.println(g);
        graphTraverseBFS(g);
        graphTraverseDFS(g);
        //System.out.println("Directed cycle exists in g ? " + cycleExistsDirect(g));
        g = new AdjListGraph(8, false);
        g.addEdge(0,1);
        g.addEdge(1,3);
        g.addEdge(3,2);
        g.addEdge(3,4);
        g.addEdge(4,5);
        g.addEdge(5,7);
        g.addEdge(4,6);
        System.out.println(g);
        graphTraverseBFS(g);
        graphTraverseDFS(g);
//        System.out.println("Cycle exists in g ? " + cycleExists(g));
        g = new AdjListGraph(8, false);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(3,2);
        g.addEdge(3,4);
        g.addEdge(4,5);
        g.addEdge(6,5);
        g.addEdge(5,7);
        g.addEdge(7,6);
        System.out.println(g);
        graphTraverseBFS(g);
        graphTraverseDFS(g);
//        System.out.println("Cycle exists in g ? " + cycleExists(g));
//        System.out.println("Odd cycle exists in g ? " + oddCycleExists(g));
//        g = new AdjListGraph(7, false);
//        g.addEdge(0,1);
//        g.addEdge(0,2);
//        g.addEdge(0,3);
//        g.addEdge(2,4);
//        g.addEdge(2,5);
//        g.addEdge(3,6);
//        System.out.println(g);
//        System.out.println("Cycle exists in g ? " + cycleExists(g));
//        g = new AdjListGraph(7, false);
//        g.addEdge(0,1);
//        g.addEdge(1,2);
//        g.addEdge(2,3);
//        g.addEdge(3,0);
//        g.addEdge(3,4);
//        g.addEdge(4,5);
//        g.addEdge(5,6);
//        g.addEdge(6,3);
//        System.out.println(g);
//        System.out.println("Cycle exists in g ? " + cycleExists(g));
//        System.out.println("Odd cycle exists in g ? " + oddCycleExists(g));
    }
}
