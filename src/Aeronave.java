
public abstract class Aeronave extends Thread {

	private long Id;
	private double tempoCombustivel;
	boolean proximoUsarPista = false; //flag
	private long tempoNascimentoAux;
	

	public Aeronave(long id) {
		setId(id);

	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public double getTempoCombustivel() {
		return tempoCombustivel;
	}

	public void setTempoCombustivel(long tempoCombustivel) {
		this.tempoCombustivel = tempoCombustivel;
	}
	
	public long getTempoNascimentoAux() {
		return tempoNascimentoAux;
	}

	public void setTempoNascimentoAux(long tempoNascimentoAux) {
		this.tempoNascimentoAux = tempoNascimentoAux;
	}
	
	public abstract boolean podeUsarPista();
	
	public boolean poucoCombustivel(){
		double tempoCombustivel = (System.currentTimeMillis() - tempoNascimentoAux)/1000;
		if(tempoCombustivel >= 22){
			return true;
		}
		
		return false;
	}


		
	
	}
