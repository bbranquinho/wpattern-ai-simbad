package org.wpattern.ai.simbad.robots;

import javax.vecmath.Vector3d;

import org.wpattern.ai.simbad.RobotMotion;
import org.wpattern.ai.simbad.beans.MazeBean;

public class Robot extends RobotMotion {

	public Robot(MazeBean maze, Vector3d startPosition, String name) {
		super(maze, startPosition, name);
	}

	@Override
	public void init() {
	}

	@Override
	public void run(int line, int column) {
	}

}
