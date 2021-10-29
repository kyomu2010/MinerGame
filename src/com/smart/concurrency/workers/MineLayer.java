package com.smart.concurrency.workers;

import java.util.Random;

import com.smart.concurrency.constants.Constants;
import com.smart.concurrency.view.Board;

public class MineLayer implements Runnable {

	private int id;
	private Board board;
	private volatile boolean layerRunning;

	public MineLayer(int id, Board board) {
		this.id = id;
		this.board = board;
		this.layerRunning = true;
	}

	@Override
	public void run() {

		Random random = new Random();

		while (layerRunning) {
			
			// check if thread has been interrupted
			if (Thread.currentThread().isInterrupted()) {
				return;
			}

			int index = random.nextInt(Constants.BOARD_ROWS * Constants.BOARD_COLUMNS);
			board.setMine(index);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isLayerRunning() {
		return layerRunning;
	}

	public void setLayerRunning(boolean layerRunning) {
		this.layerRunning = layerRunning;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
