package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import graph.DepthFirstSearch;
import graph.Graph;

import org.junit.Before;
import org.junit.Test;

public class TesteDFS {

	Graph G;
	DepthFirstSearch dfs;

	@Before
	public void setUp() {
		G = new Graph(7);
		G.addEdge(0, 1);
		G.addEdge(0, 2);
		G.addEdge(1, 2);
		G.addEdge(0, 5);
		G.addEdge(3, 5);
		G.addEdge(3, 2);
		G.addEdge(3, 4);
		G.addEdge(2, 4);
		dfs = new DepthFirstSearch(G);

		System.out.println(G.toString());
	}

	@Test
	public void testTamanhoColor() {
		int[] color = new int[7];
		assertEquals(color.length, dfs.getColor().length);
	}

	@Test
	public void testElemColor() {

		int[] color = { 2, 2, 2, 2, 2, 2,2};
		for (int i = 0; i < color.length; i++) {
			assertEquals(color[i], dfs.getColor()[i]);
		}
	}

	@Test
	public void testVerticeAtingivelTrue1() {

		boolean expected = true;
		boolean atual = false;

		if (dfs.connected(0, 1)) {
			atual = true;
		}

		assertEquals(expected, atual);

	}

	@Test
	public void testVerticeAtingivelTrue2() {

		boolean expected = true;
		boolean atual = false;

		if (dfs.connected(1, 2)) {
			atual = true;
		}

		assertEquals(expected, atual);

	}

	@Test
	public void testVerticeAtingivelTrue3() {

		boolean expected = true;
		boolean atual = false;

		if (dfs.connected(2, 3)) {
			atual = true;
		}

		assertEquals(expected, atual);

	}

	@Test
	public void testVerticeAtingivelTrue4() {

		boolean expected = true;
		boolean atual = false;

		if (dfs.connected(3, 4)) {
			atual = true;
		}

		assertEquals(expected, atual);

	}

	@Test
	public void testVerticeAtingivelTrue5() {

		boolean expected = true;
		boolean atual = false;

		if (dfs.connected(4, 5)) {
			atual = true;
		}

		assertEquals(expected, atual);

	}

	@Test
	public void testVerticeAtingivelTrue6() {

		boolean expected = true;
		boolean atual = false;

		for ( int u = 0 ; u < G.V()-1 ; u++ ) {
		    for ( int w = 0 ; w < G.V()-1 ; w++ ) {
		   
		    	if (dfs.connected(u, w)) {
		    		atual = true;
		    	}
		    	
		    }
		}
		assertEquals(expected, atual);

	}
	
	@Test
	public void testVerticeAtingivelFalse1() {

		boolean expected = false;
		boolean atual = false;

		for (int u = 0; u < G.V()-1; u++) {
			if (dfs.connected(u, 6)) {
				atual = true;
			}
		}

		assertEquals(expected, atual);

	}

	@Test
	public void testPath1() {

		List<Integer> expected = new ArrayList<Integer>();
		expected.add(0);
		expected.add(5);
		expected.add(3);
		expected.add(4);
		List<Integer> atual = new ArrayList<Integer>();

		for (int v : dfs.path(0, 4)) {
			atual.add(v);
		}

		for (int i = 0; i < atual.size(); i++) {
			assertEquals(expected.get(i), atual.get(i));
		}
	}
	
	@Test
	public void testPath2() {

		List<Integer> expected = new ArrayList<Integer>();
		expected.add(5);
		expected.add(3);
		expected.add(4);
		expected.add(2);
		List<Integer> atual = new ArrayList<Integer>();

		for (int v : dfs.path(5, 2)) {
			atual.add(v);
		}

		for (int i = 0; i < atual.size(); i++) {
			assertEquals(expected.get(i), atual.get(i));
		}
	}

}
