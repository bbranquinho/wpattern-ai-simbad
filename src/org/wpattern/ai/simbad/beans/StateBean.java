package org.wpattern.ai.simbad.beans;

public class StateBean {

	private int line;

	private int column;

	public StateBean() {
	}

	public StateBean(int line, int column) {
		super();
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return this.line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
