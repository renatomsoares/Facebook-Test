package service;

import graph.Usuario;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.types.User;

public interface IFacebookAccess {

	public User getUser();
	public List<Usuario> getMyFriends();
	public List<Usuario> getFriendsQuery();
	public void init(String token);
	public ArrayList<String> getFriendsIds();
	public List<Usuario> getQueryResult(String field, String value);
}
