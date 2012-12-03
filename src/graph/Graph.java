package graph;

public class Graph {
	private final int V ;             ///< The number of vertices of the graph.
	private int E ;                   ///< The number of edges of the graph.
	private Bag< Integer >[] adj ;    ///< An array of "bags", which are containers whose removal of elements is forbidden.


	/**
	 * \fn Graph( int V )
	 *
	 * \brief Construtor.
	 *
	 * \param V The number of vertices of the graph.
	 */
	public Graph( int V )
	{
		/* Initialize the vertex and edge counters. */
		this.V = V ;
		this.E = 0 ;

		/* 
		 * Allocate memory for an array of adjacency lists.
		 */
		adj = ( Bag< Integer >[] ) new Bag[ V ] ;

		/*
		 * Create V adjacent lists.
		 */
		for ( int v = 0 ; v < V ; v++ )
			adj[ v ] = new Bag< Integer >() ;

		return ;
	}


	/**
	 * \fn int V() 
	 *
	 * \brief Returns the number of vertices of the graph.
	 *
	 * \return The number of vertices of the graph.
	 */
	public int V() 
	{ 
		return V ; 
	}


	/**
	 * \fn int E() 
	 *
	 * \brief Returns the number of edges of the graph.
	 *
	 * \return The number of edges of the graph.
	 */
	public int E() 
	{ 
		return E ; 
	}


	/**
	 * \fn void addEdge( int v , int w )
	 *
	 * \brief Add an edge to the graph.
	 *
	 * \param v A vertex incident to the edge.
	 * \param w Another vertex incident to the edge.
	 */
	public void addEdge( int v , int w )
	{
		adj[ v ].add( w ) ;
		adj[ w ].add( v ) ;
		E++ ;

		return ;
	}


	/**
	 * \fn Iterable< Integer > adj( int v )
	 *
	 * \brief  Returns an  iterator to  the adjacency  list associated
	 * with a vertex of the graph.
	 *
	 * \param v A vertex of the graph.
	 *
	 * \return  An iterator to  the adjacency  list associated  with a
	 * vertex of the graph.
	 */
	public Iterable< Integer > adj( int v ) {
		return adj[ v ] ;
	}


	/**
	 * \fn String toString()
	 *
	 * \brief Returns  a string describing the adjacency  lists of the
	 * graph.
	 *
	 * \return A string describing the adjacency lists of the graph.
	 */
	public String toString() {
		String s = V + " vertices, " + E + " edges\n" ;
		for ( int v = 0 ; v < V ; v++ ) {
			s += v + ": " ;
			for ( int w : this.adj( v ) ) 
				s += w + " " ;
			s += "\n" ;
		}

		return s ;
	}


	/**
	 * \fn void main( String[] args )
	 *
	 * \brief Tests the class Graph.
	 *
	 * \param args An array of strings with the command-line options.
	 */

	/*
	public static void main( String[] args ) {
		Graph G = new Graph(6);
		G.addEdge(0,1);
		G.addEdge(0,2);
		G.addEdge(1,2);
		G.addEdge(0,5);
		G.addEdge(3,5);
		G.addEdge(3,2);
		G.addEdge(3,4);
		G.addEdge(2,4);


		System.out.println( G.toString() ) ;
	}
	 */

}