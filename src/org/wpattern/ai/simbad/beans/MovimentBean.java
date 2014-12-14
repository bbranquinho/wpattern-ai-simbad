package org.wpattern.ai.simbad.beans;

import org.wpattern.ai.simbad.utils.MovimentType;

public class MovimentBean {

	private final MovimentType movimentType;

	private final int startCounter;

	private final int endCounter;

	public MovimentBean(MovimentType movimentType, int startCounter, int endCounter) {
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

	public MovimentType getMovimentType() {
		return movimentType;
	}

}
