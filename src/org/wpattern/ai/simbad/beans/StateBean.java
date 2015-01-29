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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + this.column;
		result = prime * result + this.line;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		StateBean other = (StateBean) obj;

		if (this.column != other.column)
			return false;

		if (this.line != other.line)
			return false;
		return true;
	}

}
