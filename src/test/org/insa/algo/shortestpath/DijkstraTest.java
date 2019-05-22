package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Point;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest{

	// Small graph use for tests
    private static Graph graph;
    
    // List of nodes
    private static Node[] nodes;
    
    // List of arcs in the graph, a2b is the arc from node A (0) to B (1).
    @SuppressWarnings("unused")
    private static Arc a2b, a2e, a2i, b2a, b2c, c2d, c2e, d2c, e2d, e2i, f2h, g2f, h2g, i2d, i2e;
    
    @BeforeClass
    public static void initAll() throws IOException{
    	
    	// 2 types of road : 100km/h and 50km/h
    	RoadInformation speed100 = new RoadInformation(RoadType.MOTORWAY, null, true, 100, "");
    	RoadInformation speed50  = new RoadInformation(RoadType.MOTORWAY, null, true,  50, "");
    	
    	// Create all the nodes
    	nodes = new Node[9];
    	nodes[0] = new Node(0, new Point(5,6));
    	nodes[1] = new Node(1, new Point(8,4));
    	nodes[2] = new Node(2, new Point(8,2));
    	nodes[3] = new Node(3, new Point(5,-2));
    	nodes[4] = new Node(4, new Point(5,3));
    	nodes[5] = new Node(5, new Point(-1,5));
    	nodes[6] = new Node(6, new Point(-3,2));
    	nodes[7] = new Node(7, new Point(-1,0));
    	nodes[8] = new Node(8, new Point(2,3));
    	
    	// Create all the arcs
    	a2b = Node.linkNodes(nodes[0], nodes[1], 3, speed100, null);
    	a2e = Node.linkNodes(nodes[0], nodes[5], 7, speed100, null);
    	a2i = Node.linkNodes(nodes[0], nodes[8], 5, speed50 , null);
    	b2a = Node.linkNodes(nodes[1], nodes[0], 3, speed100, null);
    	b2c = Node.linkNodes(nodes[1], nodes[2], 2, speed100, null);
    	c2d = Node.linkNodes(nodes[2], nodes[3], 7, speed50 , null);
    	c2e = Node.linkNodes(nodes[2], nodes[4], 1, speed50 , null);
    	d2c = Node.linkNodes(nodes[3], nodes[2], 3, speed100, null);
    	e2d = Node.linkNodes(nodes[4], nodes[3], 4, speed50 , null);
    	e2i = Node.linkNodes(nodes[4], nodes[8], 3, speed50 , null);
    	f2h = Node.linkNodes(nodes[5], nodes[7], 3, speed50 , null);
    	g2f = Node.linkNodes(nodes[6], nodes[5], 1, speed100, null);
    	h2g = Node.linkNodes(nodes[7], nodes[6], 2, speed50 , null);
    	i2d = Node.linkNodes(nodes[8], nodes[3], 6, speed50 , null);
    	i2e = Node.linkNodes(nodes[8], nodes[4], 4, speed100, null);
    	
    	graph = new Graph("MyID", "MyMAP", Arrays.asList(nodes), null);
    }
    
    public void testShortestPathA2D() {
    	
    }
	
	
}