
public class AeronaveAterrissagem extends Aeronave {

	private Pista pista;

	public AeronaveAterrissagem(long id, Pista pistaColetiva) {
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
		if (this.pista.filaAerea.size() != 0) {
			aviaoAux = this.pista.filaAerea.getFirst();

		}

		return this.equals(aviaoAux);

	}
}