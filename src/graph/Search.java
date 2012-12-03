package graph;

public interface Search {

	/**
	 * \fn boolean connected( int v , int w )
	 * 
	 * \brief Determines whether there is a path in the graph from a given
	 * vertex to another, i.e., determines whether the two vertices are
	 * connected in the graph.
	 * 
	 * \param v A given vertex of the graph. \param w Another vertex of the
	 * graph.
	 * 
	 * \return The logic value true if the given vertices are connected, and the
	 * logic value false otherwise.
	 */
	public boolean connected(int v, int w);

	/**
	 * \fn Iterable< Integer > path( int v , int w )
	 * 
	 * \brief Returns a collection with the vertices on a path from a given
	 * vertex to another given vertex. If the two given vertices are not
	 * connected, the method returns an empty collection of vertices.
	 * 
	 * \param v A vertex of the graph. \param w Another vertex of the graph
	 * 
	 * \return A collection with the vertices on a path from a given vertex to
	 * another given vertex, if the two given vertices are not connected.
	 * Otherwise, returns an empty collection of vertices.
	 */
	public Iterable<Integer> path(int v, int w);

	/**
	 * \fn int numberOfCC()
	 * 
	 * \brief Returns the number of connected components of the graph.
	 * 
	 * \return The number of connected components of the graph.
	 */
	public int numberOfCC();

}
