package org.insa.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * Class representing a path between nodes in a graph.
 * </p>
 * 
 * <p>
 * A path is represented as a list of {@link Arc} with an origin and not a list
 * of {@link Node} due to the multi-graph nature (multiple arcs between two
 * nodes) of the considered graphs.
 * </p>
 *
 */
public class Path {

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the fastest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     */
	
    public static Path createFastestPathFromNodes(Graph graph, List<Node> nodes)
    	throws IllegalArgumentException {
    	
    	// Liste des arcs à suivre pour creer le graphe
    	List<Arc> arcs = new ArrayList<Arc>();
        
        // Detection de la taille de la liste 'nodes' //
    	
    	Iterator<Node> itNode = nodes.iterator();
        
        // S'il n'y a aucun noeud dans la liste, on crée un Path vide 
        if (nodes.size() == 0) {
        	return new Path(graph);
        }
        // S'il n'y a qu'un noeud, on crée un Path ne contenant qu'un noeud
        else if (nodes.size() == 1) {
        	return new Path(graph, nodes.get(0));
        }
        // S'il y a plusieurs noeud :
        else {
        
        	Node current = itNode.next();
        	Node next = null;
        	
        	List<Arc> successors = new ArrayList<Arc>();
        	
        	
        	Arc nextArc = null;
        	Arc arcInsert = null;
        	boolean arcInsertIsNull = true;
        	
        	// On parcourt la liste de noeuds
        	while(itNode.hasNext()) {
        		next = itNode.next();
        		successors = current.getSuccessors();
        		Iterator<Arc> itArc = successors.iterator();
        		
        		// On parcourt la liste d'arcs dont le noeud current est à l'origine
        		while(itArc.hasNext()) {
        			nextArc = itArc.next();
        			
        			// Si l'arc courant a pour destination le suivant noeud dans la liste
        			if(nextArc.getDestination().equals(next)) {
        				
        				// Si c'est le premier arc valable que l'on trouve, on le garde 
        				if (arcInsertIsNull) {
        					arcInsert = nextArc;
        					arcInsertIsNull = false;
        				}
        				// Sinon, on regarde si on prend moins de temps pour parcourrir l'arc que pour celui que l'on a gardé
        				// Si c'est le cas, on le garde
        				else if (arcInsert.getMinimumTravelTime() > nextArc.getMinimumTravelTime()) {
        					arcInsert = nextArc;
        				}
        			}
        		}
        		
        		// Si nous n'avons pas trouvé d'arc unissant les deux noeuds en question, cela veut dire que la 
        		// liste de noeuds donnée n'est pas valable
        		if (arcInsert == null) {
        			throw new IllegalArgumentException();
        		}
        		// Sinon, on met l'arc trouvé dans la liste permettant de créer notre chemin 
        		else {
        			arcs.add(arcInsert);
        			current = next;
        			arcInsertIsNull = true;
        		}
        	}
        	return new Path(graph, arcs);
        }
    }

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the shortest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     */
    public static Path createShortestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
    	
    	// Liste des arcs à suivre pour creer le graphe
    	List<Arc> arcs = new ArrayList<Arc>();
        
        Iterator<Node> itNode = nodes.iterator();
        
        // S'il n'y a aucun noeud dans la liste, on crée un Path vide 
        if (nodes.size() == 0) {
        	return new Path(graph);
        }
        // S'il n'y a qu'un noeud, on crée un Path ne contenant qu'un noeud
        else if (nodes.size() == 1) {
        	return new Path(graph, nodes.get(0));
        }
        // S'il y a plusieurs noeud :
        else {
        
        	Node current = itNode.next();
        	Node next = null;
        	
        	List<Arc> successors = new ArrayList<Arc>();
        	
        	
        	Arc nextArc = null;
        	Arc arcInsert = null;
        	boolean arcInsertIsNull = true;
        	
        	// On parcourt la liste de noeuds
        	while(itNode.hasNext()) {
        		next = itNode.next();
        		successors = current.getSuccessors();
        		Iterator<Arc> itArc = successors.iterator();
        		
        		// On parcourt la liste d'arcs dont le noeud current est à l'origine
        		while(itArc.hasNext()) {
        			nextArc = itArc.next();
        			
        			// Si l'arc courant a pour destination le suivant noeud dans la liste
        			if(nextArc.getDestination().equals(next)) {
        				
        				// Si c'est le premier arc valable que l'on trouve, on le garde 
        				if (arcInsertIsNull) {
        					arcInsert = nextArc;
        					arcInsertIsNull = false;
        				}
        				// Sinon, on regarde s'il est plus court que celui que l'on a gardé
        				// Si c'est le cas, on le garde
        				else if (arcInsert.getLength() > nextArc.getLength()) {
        					arcInsert = nextArc;
        				}
        			}
        		}
        		
        		// Si nous n'avons pas trouvé d'arc unissant les deux noeuds en question, cela veut dire que la 
        		// liste de noeuds donnée n'est pas valable
        		if (arcInsert == null) {
        			throw new IllegalArgumentException();
        		}
        		// Sinon, on met l'arc trouvé dans la liste permettant de créer notre chemin 
        		else {
        			arcs.add(arcInsert);
        			current = next;
        			arcInsertIsNull = true;
        		}
        	}
        	return new Path(graph, arcs);
        }
    }

    /**
     * Concatenate the given paths.
     * 
     * @param paths Array of paths to concatenate.
     * 
     * @return Concatenated path.
     * 
     * @throws IllegalArgumentException if the paths cannot be concatenated (IDs of
     *         map do not match, or the end of a path is not the beginning of the
     *         next).
     */
    public static Path concatenate(Path... paths) throws IllegalArgumentException {
        if (paths.length == 0) {
            throw new IllegalArgumentException("Cannot concatenate an empty list of paths.");
        }
        final String mapId = paths[0].getGraph().getMapId();
        for (int i = 1; i < paths.length; ++i) {
            if (!paths[i].getGraph().getMapId().equals(mapId)) {
                throw new IllegalArgumentException(
                        "Cannot concatenate paths from different graphs.");
            }
        }
        ArrayList<Arc> arcs = new ArrayList<>();
        for (Path path: paths) {
            arcs.addAll(path.getArcs());
        }
        Path path = new Path(paths[0].getGraph(), arcs);
        if (!path.isValid()) {
            throw new IllegalArgumentException(
                    "Cannot concatenate paths that do not form a single path.");
        }
        return path;
    }

    // Graph containing this path.
    private final Graph graph;

    // Origin of the path
    private final Node origin;

    // List of arcs in this path.
    private final List<Arc> arcs;

    /**
     * Create an empty path corresponding to the given graph.
     * 
     * @param graph Graph containing the path.
     */
    public Path(Graph graph) {
        this.graph = graph;
        this.origin = null;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path containing a single node.
     * 
     * @param graph Graph containing the path.
     * @param node Single node of the path.
     */
    public Path(Graph graph, Node node) {
        this.graph = graph;
        this.origin = node;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path with the given list of arcs.
     * 
     * @param graph Graph containing the path.
     * @param arcs Arcs to construct the path.
     */
    public Path(Graph graph, List<Arc> arcs) {
        this.graph = graph;
        this.arcs = arcs;
        this.origin = arcs.size() > 0 ? arcs.get(0).getOrigin() : null;
    }

    /**
     * @return Graph containing the path.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @return First node of the path.
     */
    public Node getOrigin() {
        return origin;
    }

    /**
     * @return Last node of the path.
     */
    public Node getDestination() {
        return arcs.get(arcs.size() - 1).getDestination();
    }

    /**
     * @return List of arcs in the path.
     */
    public List<Arc> getArcs() {
        return Collections.unmodifiableList(arcs);
    }

    /**
     * Check if this path is empty (it does not contain any node).
     * 
     * @return true if this path is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.origin == null;
    }

    /**
     * Get the number of <b>nodes</b> in this path.
     * 
     * @return Number of nodes in this path.
     */
    public int size() {
        return isEmpty() ? 0 : 1 + this.arcs.size();
    }

    /**
     * Check if this path is valid.
     * 
     * A path is valid if any of the following is true:
     * <ul>
     * <li>it is empty;</li>
     * <li>it contains a single node (without arcs);</li>
     * <li>the first arc has for origin the origin of the path and, for two
     * consecutive arcs, the destination of the first one is the origin of the
     * second one.</li>
     * </ul>
     * 
     * @return true if the path is valid, false otherwise.
     */
    public boolean isValid() {

    	boolean Valid = false;
    	if (this.isEmpty()) {
    		Valid = true;
    	}
    	else if ((this.origin != null) && (this.arcs == null)) {
    		Valid = true;
    	}
    	else {
    		Valid = true;
    		Iterator<Arc> it = arcs.iterator();
    		Node prec = this.origin;
    		Arc current = null;
    		
    		while (Valid && (it.hasNext())) {
    			current = it.next();
    			if (current.getOrigin() == prec) {
    				prec = current.getDestination();
    			}
    			else {
    				Valid = false;
    			}
    		}
    	}
    	
        return Valid;
    }

    /**
     * Compute the length of this path (in meters).
     * 
     * @return Total length of the path (in meters).
     */
    public float getLength() {  	
    	float totalLength = 0;
    	for (Arc arc : this.arcs) {
    		totalLength += arc.getLength();
    	}       
        return totalLength;
    }

    /**
     * Compute the time required to travel this path if moving at the given speed.
     * 
     * @param speed Speed to compute the travel time.
     * 
     * @return Time (in seconds) required to travel this path at the given speed (in
     *         kilometers-per-hour).
     */
    public double getTravelTime(double speed) {
        
    	float totalLength = this.getLength();
    	double speedmpersec = speed / 3.6;
    	double travelTime = (double)totalLength/speedmpersec;
    	
        return travelTime;
    }

    /**
     * Compute the time to travel this path if moving at the maximum allowed speed
     * on every arc.
     * 
     * @return Minimum travel time to travel this path (in seconds).
     * 
     */
    public double getMinimumTravelTime() {
    	float minimumPath = 0;
    	for(Arc arc : this.arcs){
    		minimumPath += arc.getMinimumTravelTime();
    	}
        return minimumPath;
    }

}
