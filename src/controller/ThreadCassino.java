package controller;

import java.util.concurrent.Semaphore;

public class ThreadCassino extends Thread {

	private int idPlayer;
	private int pontuacao = 0;
	private static int posicao;
	private Semaphore semaforo;

	public ThreadCassino(int idPlayer, Semaphore semaforo) {
		super();
		this.idPlayer = idPlayer;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		jogarDados();
	}

	private void jogarDados() {
		while (pontuacao != 5) {
			int dado1 = (int) (Math.random() * 6) + 1;
			int dado2 = (int) (Math.random() * 6) + 1;
			int somaDados = dado1 + dado2;
			if (somaDados == 7 || somaDados == 11) {
				pontuacao++;
				System.out.println("O jogador #" + idPlayer + " rolou os dados com " + dado1 + " e " + dado2
						+ ", somando " + somaDados + ", portanto conseguiu 1 ponto! Pontuaçao total: " + pontuacao);

			} else {
				System.out.println("O jogador #" + idPlayer + " rolou os dados com " + dado1 + " e " + dado2
						+ ", somando " + somaDados + ", portanto não conseguiu pontuar.");
			}
		}
		try {
			semaforo.acquire();
			if (posicao == 0) {
				System.out.println("O jogador #" + idPlayer + " fez 5 pontos e ficou em 1º! Ganhou 5000,00.");
				posicao++;
			} else {
				if (posicao == 1) {
					System.out.println("O jogador #" + idPlayer + " fez 5 pontos e ficou em 2º! Ganhou 4000,00.");
					posicao++;
				} else {
					if (posicao == 2) {
						System.out.println("O jogador #" + idPlayer + " fez 5 pontos e ficou em 3º! Ganhou 3000,00.");
						posicao++;
					} else {
						System.out.println("O jogador #" + idPlayer + " não ganhou nada.");
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}

}
