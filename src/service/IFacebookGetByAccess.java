package service;
import java.util.List;

import graph.Usuario;


public interface IFacebookGetByAccess {

	String getName();
	String getEmail();
	String getBirthday();
	String getLocale();
	void setAcesso(IFacebookAccess acesso);
	String getId();
	List<Usuario> getFriends();
}
