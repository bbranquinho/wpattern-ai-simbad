package org.wpattern.ai.simbad.beans;

import org.wpattern.ai.simbad.utils.ActionType;

public class MovimentBean {

	private final ActionType movimentType;

	private int startCounter;

	private int endCounter;

	public MovimentBean(ActionType movimentType) {
		super();
		this.movimentType = movimentType;
	}

	public MovimentBean(ActionType movimentType, int startCounter, int endCounter) {
		super();
		this.movimentType = movimentType;
		this.startCounter = startCounter;
		this.endCounter = endCounter;
	}

	public int getStartCounter() {
		return this.startCounter;
	}

	public void setStartCounter(int startCounter) {
		this.startCounter = startCounter;
	}

	public int getEndCounter() {
		return this.endCounter;
	}

	public void setEndCounter(int endCounter) {
		this.endCounter = endCounter;
	}

	public ActionType getMovimentType() {
		return this.movimentType;
	}

}
