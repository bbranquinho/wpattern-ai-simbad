package org.wpattern.ai.simbad.beans;

import java.util.List;

import org.wpattern.ai.simbad.utils.MovimentType;

public class MazeBean {

	private List<MovimentType>[][] map;

	private double scaleFactor;

	private double wallHeight;

	private double robotHeight;

	private int mazeHeight;

	private int mazeWidth;

	private StateBean start;

	private StateBean goal;

	public MazeBean() {
	}

	public double getScaleFactor() {
		return this.scaleFactor;
	}

	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	public double getWallHeight() {
		return this.wallHeight;
	}

	public void setWallHeight(double wallHeight) {
		this.wallHeight = wallHeight;
	}

	public double getRobotHeight() {
		return this.robotHeight;
	}

	public void setRobotHeight(double robotHeight) {
		this.robotHeight = robotHeight;
	}

	public List<MovimentType>[][] getMap() {
		return this.map;
	}

	public void setMap(List<MovimentType>[][] map) {
		this.map = map;
	}

	public int getMazeHeight() {
		return this.mazeHeight;
	}

	public void setMazeHeight(int mazeHeight) {
		this.mazeHeight = mazeHeight;
	}

	public int getMazeWidth() {
		return this.mazeWidth;
	}

	public void setMazeWidth(int mazeWidth) {
		this.mazeWidth = mazeWidth;
	}

	public StateBean getStart() {
		return this.start;
	}

	public void setStart(StateBean start) {
		this.start = start;
	}

	public StateBean getGoal() {
		return this.goal;
	}

	public void setGoal(StateBean goal) {
		this.goal = goal;
	}

}
