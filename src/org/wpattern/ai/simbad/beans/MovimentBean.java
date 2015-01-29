package org.wpattern.ai.simbad.beans;

import org.wpattern.ai.simbad.utils.ActionType;

public class MovimentBean {

	private final ActionType movimentType;

	private final int startCounter;

	private final int endCounter;

	public MovimentBean(ActionType movimentType, int startCounter, int endCounter) {
		super();
		this.movimentType = movimentType;
		this.startCounter = startCounter;
		this.endCounter = endCounter;
	}

	public int getEndCounter() {
		return this.endCounter;
	}

	public int getStartCounter() {
		return this.startCounter;
	}

	public ActionType getMovimentType() {
		return movimentType;
	}

}
