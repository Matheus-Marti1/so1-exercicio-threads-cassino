package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCassino;

public class Cassino {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for (int id = 1; id <= 10; id++) {
			ThreadCassino tCassino = new ThreadCassino(id, semaforo);
			tCassino.start();
		}

	}

}
