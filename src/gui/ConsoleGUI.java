package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.types.User;

import service.FacebookAccess;
import service.FacebookGetByAccess;
import service.IFacebookAccess;
import service.IFacebookGetByAccess;

import graph.BreadthFirstPaths;
import graph.DepthFirstSearch;
import graph.Graph;
import graph.Search;
import graph.Usuario;

public class ConsoleGUI implements IGUI {

	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static Graph grafo;
	private static String token;
	private static IFacebookAccess acesso;
	private static IFacebookGetByAccess meuAcesso;
	private static List<Usuario> amigos;
	private static Usuario eu;
	private static List<Usuario> listaAmigos;

	public ConsoleGUI() throws IOException{

		insereToken();

		acesso = FacebookAccess.getInstance(token);
		meuAcesso = FacebookGetByAccess.getInstance(acesso);

		amigos = acesso.getFriendsQuery();
		
		grafo = new Graph(amigos.size());

		eu = new Usuario(0, meuAcesso.getName(), meuAcesso.getId(), meuAcesso.getLocale(), meuAcesso.getBirthday());

		listaAmigos = new ArrayList<Usuario>();

		populaGrafo (amigos, listaAmigos);
		adicionaEdges(eu, listaAmigos, grafo);	
	}

	private static void adicionaEdges(Usuario eu, List<Usuario> listaAmigos,
			Graph G) {
		for (int i = 0; i < listaAmigos.size();i++) {
			G.addEdge(eu.getChave(), listaAmigos.get(i).getChave());
		}
	}

	public void buscaEmLargura(Usuario eu, Graph G) {
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

	public void buscaEmProfundidade(Graph G) {

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

	public void showMenu() {


		System.out.println("Escolha uma das opções abaixo: ");
		System.out.println("está funcionando");
		System.out.println("1 - Imprime amigos");
		System.out.println("2 - Imprimir informações do grafo");
		System.out.println("3 - Solteiros");
		System.out.println("4 - Casados");

		System.out.println("5 - Sair \n");
	}

	public void populaGrafo(List<Usuario> amigos2,
			List<Usuario> listaAmigos) {
		for (int i=1;i<=amigos.size() -1;i++) { // substituir depois 9 por amigos.getData().size()
			Usuario user = new Usuario(i, amigos2.get(i).getName(), amigos2.get(i).getId(), amigos2.get(i).getLocale(), amigos2.get(i).getBirthday());
			listaAmigos.add(user);
		}
	}

	public void imprimeVerticesDoGrafo(Usuario eu, List<Usuario> listaAmigos) {
		System.out.println("Nome: " + eu.getName() + " Chave: " + eu.getChave());
		for (int i=0; i < listaAmigos.size();i++) {
			System.out.println("Nome: " + listaAmigos.get(i).getName() + " Chave: " +listaAmigos.get(i).getChave());
		}
	}

	public void imprimirGrafo() {
		System.out.println(grafo.toString());
	}

	private static void insereToken() throws IOException {
		System.out.println("Insira a token de acesso: ");
		token = in.readLine();
	}


	public void imprimirAmigos() {
		for (int i = 0; i < amigos.size(); i++) {
			System.out.println(
					"Nome: " + amigos.get(i).getName()+
					" Relacionamento: " + amigos.get(i).getRelationship_status()
			);
		}
	}

	@Override
	public void consultaSolteirosSemSerNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imprimirCasados() {
		amigos = acesso.getQueryResult("relationship_status");
		for (int i = 0; i < amigos.size(); i++) {
			System.out.println(
					amigos.get(i).getName() + " is " + amigos.get(i).getRelationship_status()
			);
		}
		
	}

/*
	public void consultaSolteirosSemSerNull() {
		for (int i = 0 ; i < listaAmigos.size() ; i++) {

			if ((acesso.getInfoById(listaAmigos.get(i).getId()) != null) && ((acesso.getInfoById(listaAmigos.get(i).getId()) == "Single"))) {
				
					System.out.println(acesso.getInfoById(listaAmigos.get(i).getId()));
			}

		}
	}
*/

	//	public void consultaPorIdade() throws IOException{
	//
	//		String ini;
	//		String fin;
	//
	//		System.out.println("Idade inicial: ");
	//		ini = in.readLine();
	//
	//		System.out.println("Idade final: ");
	//		fin = in.readLine();
	//
	//		for (int i = 0 ; i < amigos.getData().size() ; i++) {
	//			... //sem acesso a algumas informações / rever acesso
	//		}

}