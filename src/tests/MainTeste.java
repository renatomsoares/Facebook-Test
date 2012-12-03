package tests;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import service.FacebookAccess;
import service.FacebookGetByAccess;
import service.IFacebookAccess;
import service.IFacebookGetByAccess;


import com.restfb.Connection;
import com.restfb.types.User;

import graph.BreadthFirstPaths;
import graph.DepthFirstSearch;
import graph.Graph;
import graph.Search;
import graph.Usuario;

public class MainTeste {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static String token;
	public static void main(String[] args) throws IOException {

		insereToken();//ok

		IFacebookAccess acesso = FacebookAccess.getInstance(token);
		acesso.init(token);
		
		IFacebookGetByAccess gets = FacebookGetByAccess.getInstance(acesso);
		gets.setAcesso(acesso);

		Connection<User> amigos = acesso.getFriendsQuery(null, null);
		Usuario eu = new Usuario(0, gets.getName(), gets.getLocale(), gets.getBirthday(), gets.getId());

		List<Usuario> listaAmigos = new ArrayList<Usuario>();

		populaGrafo(amigos, listaAmigos);
		imprimeVerticesDoGrafo(eu, listaAmigos);

		Graph G = new Graph(10);

		adicionaEdges(eu, listaAmigos, G);

		System.out.println(G.toString());

		buscaEmProfundidade(G);
		buscaEmLargura(eu, G);
		System.out.println(eu.getLocalidade());
	}
		

	private static void buscaEmLargura(Usuario eu, Graph G) {
		System.out.println("Busca de Largura - Caminhos mais curto do vertice raiz ao x \n");
		int s = eu.getChave();
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

		for (int v = 0; v < G.V(); v++) {
			if (bfs.hasPathTo(v)) {
				System.out.println("Do vertice " + s + " ate " + v + " (Quantidade de Aresta: " + bfs.distTo(v) +"): ");
				for (int x : bfs.pathTo(v)) {
					if (x == s) System.out.print(x);
					else        System.out.print("-" + x);
				}
				System.out.println();
			}

			else {
				System.out.println(s + " to " + v + "(-): not connected \n" );
			}
		}
	}

	private static void buscaEmProfundidade(Graph G) {

		Search search = new DepthFirstSearch(G);		
		System.out.println("Busca de profundidade - Caminho do vertice x ao y \n");

		for ( int u = 0 ; u < G.V() ; u++ ) {
			for ( int w = 0 ; w < G.V() ; w++ ) {

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
	}

	private static void insereToken() throws IOException {
		System.out.println("Insira a token de acesso: ");
		token = in.readLine();
	}

	private static void adicionaEdges(Usuario eu, List<Usuario> listaAmigos,
			Graph G) {
		for (int i = 0; i < listaAmigos.size();i++) {
			G.addEdge(eu.getChave(), listaAmigos.get(i).getChave());
		}
	}

	private static void imprimeVerticesDoGrafo(Usuario eu,
			List<Usuario> listaAmigos) {
		System.out.println("Nome: " + eu.getNome() + " Chave: " + eu.getChave());
		for (int i=0; i < listaAmigos.size();i++) {
			System.out.println("Nome: " + listaAmigos.get(i).getNome() + " Chave: " +listaAmigos.get(i).getChave());
		}
	}

	private static void populaGrafo(Connection<User> amigos,
			List<Usuario> listaAmigos) {
		for (int i=1;i<=9;i++) { // substituir depois 9 por amigos.getData().size()
			Usuario user = new Usuario(i, amigos.getData().get(i).getName(),
					amigos.getData().get(i).getHometownName(),
					amigos.getData().get(i).getBirthday(),
					amigos.getData().get(i).getId());
			listaAmigos.add(user);
		}
	}
}
