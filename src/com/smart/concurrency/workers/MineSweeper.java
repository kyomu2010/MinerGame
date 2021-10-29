package com.smart.concurrency.workers;

import java.util.Random;

import com.smart.concurrency.constants.Constants;
import com.smart.concurrency.view.Board;

public class MineSweeper implements Runnable {

	private int id;
	private Board board;
	private volatile boolean sweeperRunning;

	public MineSweeper(int id, Board board) {
		this.id = id;
		this.board = board;
		this.sweeperRunning = true;
	}

	@Override
	public void run() {

		Random random = new Random();

		while (sweeperRunning) {
			
			// check if thread has been interrupted
			if (Thread.currentThread().isInterrupted()) {
				return;
			}

			int index = random.nextInt(Constants.BOARD_ROWS * Constants.BOARD_COLUMNS);
			board.sweepMine(index);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public boolean isSweeperRunning() {
		return sweeperRunning;
	}

	public void setSweeperRunning(boolean sweeperRunning) {
		this.sweeperRunning = sweeperRunning;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
