package graph;

public class DepthFirstSearch implements Search 
{
	private int[] pred ;              ///< an array for storing the predecessors of the vertices.
	private int[] dtime ;             ///< an array for storing the discovery time of the vertices.
	private int[] ftime ;             ///< an array for storing the finishing time of the vertices.
	private int[] color ;             ///< an array for storing the color of the vertices.
	private int count ;               ///< a counter for the number of connected componets.
	private int times ;               ///< a time stamp for the vertices.

	private final int WHITE = 0 ;     ///< a color for undiscovery vertices.
	private final int GRAY = 1 ;      ///< a color for discovered (but not finished) vertices.
	private final int BLACK = 2 ;     ///< a color for finished vertices.

	/**
	 * \fn DepthFirstSearch( Graph G )
	 *
	 * \brief Constructor.
	 *
	 * \param G a graph.
	 */
	public DepthFirstSearch( Graph G )
	{
		/* Initialize the counter of connected components. */
		count = 0 ;

		/* Initialize the time stamp. */
		times = 0 ;

		/* Allocate memory for the array pred. */
		pred = new int[ G.V() ] ;

		/* Allocate memory for the arrays dtime and ftime. */
		dtime = new int[ G.V() ] ;
		ftime = new int[ G.V() ] ;

		/* Allocate memory for the array color. */
		color = new int[ G.V() ] ;

		for ( int v = 0 ; v < G.V() ; v++ ) {
			color[ v ] = WHITE ;
		}

		/* Start exploring the graph. */
		for ( int v = 0 ; v < G.V() ; v++ ) {
			if ( color[ v ] == WHITE ) {		
				/* Increment the connected component counter. */
				++count ;

				/* Vertex "v" has no predecessor. */
				pred[ v ] = -1 ;

				/* Visit vertex v. */
				dfs( G , v ) ;
			}
		}

		return ;
	}

	public int[] getPred() {return this.pred;}
	public int[] getColor() {return this.color;}



	/**
	 * \fn void dfs( Graph G , int u )
	 *
	 * \brief Performs  a depth-first search  on a given graph  from a
	 * given vertex of the graph, which is known as the source vertex.
	 *
	 * \param G a graph.
	 * \param u a vertex of the graph.
	 */
	public void dfs( Graph G , int u )
	{
		/* Increment the time stamp. */
		++times ;

		/* Set the discovery time of vertex "u". */
		dtime[ u ] = times ;

		/* Mark vertex "s" as "reached". */
		color[ u ] = GRAY ;

		/* Visit all WHITE vertices that are adjacent to "s". */
		for ( int w : G.adj( u ) ) {
			if ( color[ w ] == WHITE ) {
				/* The predecessor of Vertex "w" is vertex "v". */
				pred[ w ] = u ;

				/* Visit vertex "w". */
				dfs( G , w ) ;
			}
		}

		/* Mark vertex "u" as "visited". */
		color[ u ] = BLACK ;

		/* Increment the time stamp. */
		++times ;

		/* Set the finish time of vertex "u". */
		ftime[ u ] = times ;

		return ;
	}


	/**
	 * \fn boolean connected( int v , int w )
	 *
	 * \brief Determines whether  there is a path in  the graph from a
	 * given  vertex  to another,  i.e.,  determines  whether the  two
	 * vertices are connected in the graph.
	 *
	 * \param v A given vertex of the graph.
	 * \param w Another vertex of the graph.
	 *
	 * \return  The  logic  value  true  if  the  given  vertices  are
	 * connected, and the logic value false otherwise.
	 */
	public boolean connected( int v , int w )
	{
		return findLeastCommonAncestor( v , w ) != -1 ;
	}


	/**
	 * \fn Iterable< Integer > path( int v , int w )
	 *
	 * \brief Returns a collection with  the vertices on a path from a
	 * given vertex to another given vertex. If the two given vertices
	 * are not  connected, the method  returns an empty  collection of
	 * vertices.
	 *
	 * \param v A vertex of the graph.
	 * \param w Another vertex of the graph
	 *
	 * \return A collection  with the vertices on a  path from a given
	 * vertex to another  given vertex, if the two  given vertices are
	 * not  connected.  Otherwise,  returns  an  empty  collection  of
	 * vertices.
	 */
	public Iterable< Integer > path( int v , int w ) 
	{
		/*
		 * Find the  first vertex, say  u, on the  path from w  to the
		 * source vertex  of the  DFS, which is  also a vertex  of the
		 * path from v  to the source vertex of the  DFS.  Note that u
		 * can be equal to v or w.   This is the case if, and only if,
		 * v is  a descendent of w or  vise-versa. If v and  w are not
		 * connected to  each other, then there is  no common ancestor
		 * for them.
		 */
		int u = findLeastCommonAncestor( v , w ) ;

		if ( u == -1 ) {
			return null ;
		}
		else {
			/*
			 * If u is equal to w  then we just follow the path from v
			 * to w.
			 */
			if ( u == w ) {
				int s = v ;
				int f = w ;
				Queue< Integer > path = new Queue< Integer >() ;
				while ( s != f ) {
					path.enqueue( s ) ;
					s = pred[ s ] ;
				}
				path.enqueue( f ) ;

				return path ;
			}
			else if ( u == v ) {
				/*
				 * If u  is equal  to v then  we just follow  the path
				 * from w to v.
				 */
				int s = w ;
				int f = v ;
				Stack< Integer > path = new Stack< Integer >() ;
				while ( s != f ) {
					path.push( s ) ;
					s = pred[ s ] ;
				}
				path.push( f ) ;

				return path ;
			}
			else {
				/*
				 * If u is not equal to  v nor equal to w then we just
				 * follow the paths  from v to u and from  w to u, and
				 * then conactenate the first  with the reverse of the
				 * second.
				 */
				int s = v ;
				int f = u ;
				Queue< Integer > path = new Queue< Integer >() ;
				while ( s != f ) {
					path.enqueue( s ) ;
					s = pred[ s ] ;
				}
				path.enqueue( f ) ;

				s = w ;
				f = u ;
				Stack< Integer > p1 = new Stack< Integer >() ;
				while ( s != f ) {
					p1.push( s ) ;
					s = pred[ s ] ;
				}

				while ( !p1.isEmpty() ) {
					path.enqueue( p1.pop() ) ;
				}

				return path ;
			}
		}
	}


	/**
	 * \fn int numberOfCC()
	 *
	 * \brief Returns the number of connected components of the graph.
	 *
	 * \return The number of connected components of the graph.
	 */
	public int numberOfCC() 
	{
		return count ;
	}


	/**
	 * \fn int findLeastCommonAncestor( int v , int w )
	 *
	 * \brief  Returns the  closest  ancestor  of v  that  is also  an
	 * ancestor of w in the predecessor  graph, if any. If there is no
	 * such a vertex  in the predecessor graph, then  return the value
	 * -1.
	 *
	 * \param v A given vertex of the graph.
	 * \param w Another vertex of the graph.
	 *
	 * \return The closest ancestor of v that is also an ancestor of u
	 * in the predecessor graph, or  -1 if there is no common ancestor
	 * for these two vertices in the predecessor graph.
	 */
	private int findLeastCommonAncestor( int v , int w )
	{
		if ( v == w ) {
			/*
			 * v and w are the same vertex.
			 */
			return v ;
		}
		else if ( 
				( dtime[ v ] < dtime[ w ] ) &&
				( ftime[ v ] > ftime[ w ] ) 
				) 
		{
			/*
			 * v is a proper ancestor of w.
			 */
			return v ;

		}
		else if ( 
				( dtime[ w ] < dtime[ v ] ) && 
				( ftime[ w ] > ftime[ v ] ) 
				) 
		{
			/*
			 * w is a proper ancestor of v.
			 */
			return w ;
		}

		/*
		 * Neither v nor w is an ancestor of each other. Let us verify
		 * if they have  a common ancestor. If so,  we find and return
		 * it.
		 */
		int s = pred[ v ] ;
		while ( 
				( s != -1 ) && 
				( 
						( dtime[ s ] > dtime[ w ] ) || 
						( ftime[ s ] < ftime[ w ] ) 
						) 
				) 
		{
			s = pred[ s ] ;
		}

		return s ;
	}


	/**
	 * \fn void main( String[] args )
	 *
	 * \brief Tests the class DepthFirstSearch.
	 *
	 * \param args An array of strings with the command-line options.
	 */
	public static void main( String[] args ) {
		/*
		 * Read in a graph from a file.
		 */
		Graph G = new Graph(6);
		G.addEdge(0,1);
		G.addEdge(0,2);
		G.addEdge(1,2);
		G.addEdge(0,5);
		G.addEdge(3,5);
		G.addEdge(3,2);
		G.addEdge(3,4);
		G.addEdge(2,4);

		/*
		 * Create an object to search on the graph.
		 */
		Search search = new DepthFirstSearch( G ) ;

		/*
		 * Print out each pair of reachable vertices.
		 */
		for ( int u = 0 ; u < G.V() ; u++ ) {
			for ( int w = 0 ; w < G.V() ; w++ ) {
				/* 
				 * Are "u"  and "w" connected?   If so, print  out the
				 * vertices of  a DFS  path from "u"  to "w",  in this
				 * order.
				 */
				System.out.print( u + " to " + w + ": " ) ;
				if ( search.connected( u , w ) ) {
					for ( int v : search.path( u , w ) ) {
						if ( v == u ) {
							System.out.print( v ) ;
						}
						else {
							System.out.print( "-" + v ) ;
						}
					}
				}
				System.out.println() ;
			}
		}

		/*
		 * Write out the number of connected components of the graph.
		 */
		int ncc = search.numberOfCC() ;
		if ( ncc > 1 ) { 
			System.out.println( "The graph has " + ncc + " connected components." ) ;
		}
		else {
			System.out.println( "The graph has one connected component." ) ;
		}

		return ;
	}

}
