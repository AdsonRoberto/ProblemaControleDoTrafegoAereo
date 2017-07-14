import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;


public class Aeroporto {

	public static void main(String[] args) {

		long tempoInicio = System.currentTimeMillis();
		Pista pistaColetiva = new Pista();
		int totalAeronaves = 20;
		Random aleatorio = new Random();
		int countAterrissagem = 0;
		int countDecolagem = 0;
		LinkedList<Aeronave> listaDeAeronaves = new LinkedList<>();

		for (int i = 1; countDecolagem < 10 || countAterrissagem < 10; i++) {

			Aeronave aeronave = null;
			boolean vaiDecolar = aleatorio.nextBoolean();
			if (vaiDecolar) {
				if (countDecolagem < 10) {
					aeronave = new AeronaveDecolagem(i, pistaColetiva);
					long nascimentoAeronave = System.currentTimeMillis();
					aeronave.setTempoNascimentoAux(nascimentoAeronave);
					System.out.println("Aeronane de decolagem criada em  " + pegarHorario() + "s");
					countDecolagem++;
				} else {
					aeronave = new AeronaveAterrissagem(i, pistaColetiva);
					long nascimentoAeronave = System.currentTimeMillis();
					aeronave.setTempoNascimentoAux(nascimentoAeronave);
					System.out.println("Aeronane de aterrissagem criada em  " + pegarHorario() + "s");
					countAterrissagem++;
				}

			} else {

				if (countAterrissagem < 10) {
					aeronave = new AeronaveAterrissagem(i, pistaColetiva);
					long nascimentoAeronave = System.currentTimeMillis();
					aeronave.setTempoNascimentoAux(nascimentoAeronave);
					System.out.println("Aeronane de aterrissagem criada em  " + pegarHorario() + "s");
					countAterrissagem++;
				} else {

					aeronave = new AeronaveDecolagem(i, pistaColetiva);
					long nascimentoAeronave = System.currentTimeMillis();
					aeronave.setTempoNascimentoAux(nascimentoAeronave);
					System.out.println("Aeronane de decolagem criada em  " + pegarHorario() + "s");
					countDecolagem++;

				}
			}

			if (aeronave instanceof AeronaveAterrissagem) {
				inserirNoAr(aeronave, pistaColetiva);
				aeronave.start();
				pistaColetiva.imprimeFilaAerea();
				pistaColetiva.imprimeFilaPista();
			} else {
				boolean inseriuNaTerra = inserirNaTerra(aeronave, pistaColetiva);
				if (inseriuNaTerra) {
					aeronave.start();
					pistaColetiva.imprimeFilaAerea();
					pistaColetiva.imprimeFilaPista();

				} else
					System.out.println("Aeronave n." + aeronave.getId() + " Colidiu!");

			}

			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void inserirNoAr(Aeronave aeronave, Pista pistaColetiva) {
		pistaColetiva.filaAerea.add(aeronave);

	}

	public static boolean inserirNaTerra(Aeronave aeronave, Pista pistaColetiva) {
		if (pistaColetiva.filaPista.size() < 3) {
			pistaColetiva.filaPista.add(aeronave);
			return true;
		} else {
			System.out.println("Fila de espera está cheia.");
			return false;
		}
	}

	

	public static String pegarHorario() {
		DateFormat formatacao = new SimpleDateFormat(" HH:mm:ss");
		Date data = new Date();
		return formatacao.format(data);
	}

}