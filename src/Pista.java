
import java.util.LinkedList;

public class Pista {

	LinkedList<Aeronave> filaPista;
	LinkedList<Aeronave> filaAerea;

	public Pista() {
		filaPista = new LinkedList<>();
		filaAerea = new LinkedList<>();

	}

	public synchronized void liberarPista(Aeronave aeronave) {

		if (aeronave instanceof AeronaveAterrissagem) {

			while (prioridadeProximoAviaoDecolar(aeronave)) {

				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Aeronave n." + aeronave.getId() + " aterrissará [" + Aeroporto.pegarHorario() + "]");
			filaAerea.remove(aeronave);
			System.out.println("Aeronave n." + aeronave.getId() + " aterrissou" );
			//System.out.println("Aeronave n. " + aeronave.getId() + " aterrissou");
			notifyAll();

		} else {

			while (prioridadeProximoAviaoAterrissar(aeronave)) {

				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Aeronave n." + aeronave.getId() + " decolará [" + Aeroporto.pegarHorario() + "]");
			filaPista.remove(aeronave);
			System.out.println("Aeronave n." + aeronave.getId() + " decolou");
			notifyAll();

		}

	}

	public void imprimeFilaPista() {
		String saida = "filaPista = { ";

		for (Aeronave aeronave : filaPista) {

			saida += " " + aeronave.getId();
		}

		saida += " }";
		System.out.println(saida);
	}

	public void imprimeFilaAerea() {
		String saida = "filaAerea = { ";

		for (Aeronave aeronave : filaAerea) {

			saida += " " + aeronave.getId();
		}
		saida += " }";
		System.out.println(saida);
	}

	public boolean prioridadeProximoAviaoDecolar(Aeronave aeronave) {

		return !prioridadeAviaoAr(aeronave) || !aeronave.podeUsarPista();

	}

	public boolean prioridadeAviaoAr(Aeronave aeronave) {
		if (filaPista.size() < 3) {
			if (filaAerea.size() >= filaPista.size()) {
				return true;

			} else {
				if (filaAerea.size() != 0) {

					if (this.pegarProximaAeronaneDeAterrissagem().poucoCombustivel()) {
						return true;
					} else

						return false;
				}else{
					return false;
				}
			}

		} else {
			if (filaAerea.isEmpty()) {
				return false;
			} else {
				if (filaAerea.size() != 0) {

					if (this.pegarProximaAeronaneDeAterrissagem().poucoCombustivel()) {
						return true;
					} else

						return false;
				}else{
					return false;
				}
			}
		}
	}

	public boolean prioridadeProximoAviaoAterrissar(Aeronave aeronave) {

		return prioridadeAviaoAr(aeronave) || !aeronave.podeUsarPista();

	}

	public Aeronave pegarProximaAeronaneDeAterrissagem() {
		Aeronave aviaoAux = null;
		if (this.filaAerea.size() != 0) {
			aviaoAux = this.filaAerea.getFirst();
		}
		return aviaoAux;
	}
}
