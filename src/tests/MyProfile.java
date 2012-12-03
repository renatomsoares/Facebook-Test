package tests;

import java.util.ArrayList;

import com.restfb.*;
import com.restfb.types.User;

public class MyProfile {
	
	//INSIRA A ACCESS TOKEN NO PARAMETRO
	FacebookClient facebookClient = new DefaultFacebookClient("AAACEdEose0cBADaAdwKzVTC2uoZCb7I6CYR49O6mBaPDDIGXZAElzbiCkzBJ9JUGnDtxnSdQS7mQ3CYV0QUSKX5bx8nTZAEo1teO6PqJQZDZD");
	
	private Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
	
	private User user = facebookClient.fetchObject("me", User.class);
	
	public ArrayList<String> myFriendsIDs;
	
	public User getUser(){
		return user;
	}
	
	public Connection<User> getFriends(){
		return myFriends;
	}
	
	
	//retorna Ids dos amigos
	public ArrayList<String> getFriendsIds(){
		for (int i = 0 ; i < getFriends().getData().size() ; i++) {		
			myFriendsIDs.add(i, getFriends().getData().get(i).getId());
		}
		return myFriendsIDs;
	}
	

	

}
