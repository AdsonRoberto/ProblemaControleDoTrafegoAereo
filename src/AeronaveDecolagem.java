
public class AeronaveDecolagem extends Aeronave {

	private Pista pista;

	public AeronaveDecolagem(long id, Pista pistaColetiva) {
		super(id);
		this.pista = pistaColetiva;
		//System.out.println("Aeronave n. " + this.getId() + " criada!");
	}

	@Override
	public void run() {
		super.run();
		pista.liberarPista(this);

	}

	@Override
	public boolean podeUsarPista() {
		Aeronave aviaoAux = null;
		if (this.pista.filaPista.size() > 0) {
			aviaoAux = this.pista.filaPista.getFirst();

		}

		return this.equals(aviaoAux);

	}
}
