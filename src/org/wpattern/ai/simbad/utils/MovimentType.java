package org.wpattern.ai.simbad.utils;

import java.util.HashMap;
import java.util.Map;

public enum MovimentType {

	NORTH("N", "NORTH"),

	WEST("W", "WEST"),

	SOUTH("S", "SOUTH"),

	EAST("E", "EAST");

	private static final Map<String, MovimentType> map = new HashMap<String, MovimentType>();

	static {
		for (MovimentType value : values()) {
			map.put(value.symbol, value);
			map.put(value.fullname, value);
		}
	}

	private final String symbol;

	private final String fullname;

	private MovimentType(String symbol, String fullname) {
		this.symbol = symbol;
		this.fullname = fullname;
	}

	public static MovimentType parse(String moviment) {
		return map.get(moviment);
	}

}
