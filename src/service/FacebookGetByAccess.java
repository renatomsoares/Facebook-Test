package service;

import graph.Usuario;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.types.User;

public class FacebookGetByAccess implements IFacebookGetByAccess {

	private static FacebookGetByAccess instance = null;
	
	private IFacebookAccess acesso;
	
	private FacebookGetByAccess(IFacebookAccess acesso) {
		this.acesso = acesso;
	}
	
	public static FacebookGetByAccess getInstance(IFacebookAccess acesso) {
		if (instance == null) {
			instance = new FacebookGetByAccess(acesso);
		}
		return instance;
	}
	
	@Override
	public String getName() {
		return acesso.getUser().getName();
	}
	

	@Override
	public String getEmail() {
		return acesso.getUser().getEmail();
	}

	@Override
	public String getBirthday() {
		return acesso.getUser().getBirthday();
	}

	@Override
	public String getLocale() {
		return acesso.getUser().getLocale();
	}
	
	@Override
	public String getId() {
		return acesso.getUser().getId();
	}
		
	@Override
	public List<Usuario> getFriends() {
		List<Usuario> listaAmigos = new ArrayList<Usuario>();
		List<Usuario> amigos = acesso.getFriendsQuery();
		
		for (int i = 0; i < amigos.size();i++) {
			listaAmigos.add(new Usuario(i,getName(), getId(), getLocale(), getBirthday()));
		}
		return listaAmigos;
	}
	
	@Override
	public void setAcesso(IFacebookAccess acesso) {
		this.acesso = acesso;
	}
}
