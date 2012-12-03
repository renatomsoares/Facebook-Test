package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import graph.BreadthFirstPaths;
import graph.Graph;

import org.junit.Before;
import org.junit.Test;


public class TesteBFS {
	
	Graph G;
	BreadthFirstPaths bfs;
	int s;
	
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
		s = 0;
		bfs = new BreadthFirstPaths(G, s);
		
		System.out.println(G.toString());
		
	}
	
	@Test
	public void testPathStoV() {
		
		boolean[] expected = {true,true,true,true,true,true,false};
		boolean[] atual = bfs.getArrayMarked();
		
		for (int i=0; i<expected.length;i++) {
			assertEquals(expected[i],atual[i]);
		}
		
	}
	
	@Test
	public void testNumberOfEdge1() {
		int vertice = 3;
		int expected = 2;
		int atual = bfs.distTo(vertice);
		
		assertEquals(expected,atual);
	}
	
	@Test
	public void testNumberOfEdge2() {
		int vertice = 2;
		int expected = 1;
		int atual = bfs.distTo(vertice);
		
		assertEquals(expected,atual);
	}
	
	@Test
	public void testNumberOfEdge3() {
		int vertice = 4;
		int expected = 2;
		int atual = bfs.distTo(vertice);
		
		assertEquals(expected,atual);
	}
	
	@Test
	public void testHasPathToTrue() {
		int vertice = 3;
		boolean expected = true;
		boolean atual = bfs.hasPathTo(vertice);
		
		assertEquals(expected,atual);
	}
	
	@Test
	public void testHasPathToFalse(){
		int vertice = 6;
		boolean expected = false;
		boolean atual = bfs.hasPathTo(vertice);
	
		assertEquals(expected,atual);
	}
	
	@Test
	public void testPathTo1() {
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(0);
		expected.add(5);
		expected.add(3);
		int vertice = 3;
		List<Integer> atual = new ArrayList<Integer>();
		
		for (int v : bfs.pathTo(vertice)) {
			atual.add(v);
		}
		
		for (int i = 0; i < atual.size(); i++) {
			assertEquals(expected.get(i), atual.get(i));
		}
	}
	
	@Test
	public void testPathTo2() {
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(0);
		expected.add(2);
		expected.add(4);
		int vertice = 4;
		List<Integer> atual = new ArrayList<Integer>();
		
		for (int v : bfs.pathTo(vertice)) {
			atual.add(v);
		}
		
		for (int i = 0; i < atual.size(); i++) {
			assertEquals(expected.get(i), atual.get(i));
		}
	}

}
