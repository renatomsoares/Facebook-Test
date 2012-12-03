package graph;

public class Amizade {

	private Usuario origem;
	private Usuario destino;
	private String info;

	public Amizade(Usuario origem, Usuario destino, String info) {
		this.origem = origem;
		this.destino = destino;
		this.info = "";
	}

	public Usuario getOrigem() {
		return origem;
	}
	
	public void setOrigem(Usuario origem) {
		this.origem = origem;
	}
	
	public Usuario getDestino() {
		return destino;
	}
	
	public void setDestino(Usuario destino) {
		this.destino = destino;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
}