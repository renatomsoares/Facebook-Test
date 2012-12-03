package service;



import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import graph.Usuario;

public class FacebookAccess implements IFacebookAccess {

	private static FacebookAccess instance = null;
	private FacebookClient facebookClient;
	private List<Usuario> friendsBase;			// Lista de usuários amigos da token
	private List<Usuario> friendsQuery;			// Lista de usuários resultante de uma consulta
	private String[] friendsIds; 
	private User user;

	
	private FacebookAccess() {}
	
	private FacebookAccess(String accessToken) {
		this.facebookClient = new DefaultFacebookClient(accessToken);
		// Lista contendo informações básicas dos meus amigos
		this.friendsBase = facebookClient.executeQuery("SELECT uid, name, locale FROM user WHERE uid = me() or uid in (SELECT uid2 FROM friend WHERE uid1 = me())", Usuario.class);
		this.user = facebookClient.fetchObject("me", User.class);
		
		// Transferindo todos os ids dos amigos do usuário para um array.
		this.friendsIds = new String[friendsBase.size()];
		for (int i = 0; i < friendsBase.size(); i++) {
			friendsIds[i] = friendsBase.get(i).getId();
		}
		
		// A lista de consulta padrão vai ser uma lista de usuários solteiros.
		this.friendsQuery = friendsBase;
	}
	
	// Método que executa uma query com parametros. Retorna uma lista de usuarios.
	public List<Usuario> getQueryResult(String field, String param) {
		String ids = implodeArray(friendsIds, ",");
		String query = "SELECT name, relationship_status, pic, sex, profile_url FROM user WHERE uid IN (" + ids + ") and " + field + " = '" + param + "'";
		return facebookClient.executeQuery(query, Usuario.class);
	}
	
	public static FacebookAccess getInstance() {
		if (instance == null) {
			instance = new FacebookAccess();
		}
		return instance;
	}
	
	public static FacebookAccess getInstance(String accessToken) {
		if (instance == null) {
			instance = new FacebookAccess(accessToken);
		}
		return instance;
	}
	
	@Override
	public User getUser() {
		return user;
	}
	
 
	public List<Usuario> getMyFriends(){
		return friendsBase;
	}
	
	public List<Usuario> getFriendsQuery() {
		return friendsQuery;
	}

	@Override
	public void init(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> getFriendsIds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static String implodeArray(String[] inputArray, String glueString) {

	/** Output variable */
	String output = "";

	if (inputArray.length > 0) {
		StringBuilder sb = new StringBuilder();
		sb.append(inputArray[0]);

		for (int i=1; i<inputArray.length; i++) {
			sb.append(glueString);
			sb.append(inputArray[i]);
		}

		output = sb.toString();
	}

	return output;
	}

}