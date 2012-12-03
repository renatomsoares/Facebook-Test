package graph;

import com.restfb.Facebook;

public class Usuario {

	private int chave;
	
	@Facebook
	 String name;
	
	@Facebook
	String uid;
	
	@Facebook
	String locale;
	
	@Facebook
	String birthday;
	
	@Facebook
	String relationship_status;
	
	@Facebook
	String pic;
	
	@Facebook
	String profile_url;
	
	@Facebook
	String sex;
	
	public String getRelationship_status() {
		return relationship_status;
	}

	public void setRelationship_status(String relationship_status) {
		this.relationship_status = relationship_status;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	private String statusVisita;

	public Usuario() {
		
	}
	
	public Usuario(int chave, String nome, String id, String locale, String birthday) {
		this.chave = chave;
		this.name = nome;
		this.uid = id;
		this.locale = locale;
		this.birthday = birthday;
	}	

	public String getStatusVisita() {
		return this.statusVisita;
	}

	public void setStatusVisita(String status) {
		this.statusVisita = status;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getId() {
		return uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getLink() {
		return profile_url;
	}

	public void setLink(String link) {
		this.profile_url = link;
	}

	public String getGender() {
		return sex;
	}

	public void setGender(String gender) {
		this.sex = gender;
	}
	
	
	
}