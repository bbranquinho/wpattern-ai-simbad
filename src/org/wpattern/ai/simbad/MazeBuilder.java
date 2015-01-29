package org.wpattern.ai.simbad;

import java.lang.reflect.Constructor;

import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import org.wpattern.ai.simbad.beans.MazeBean;
import org.wpattern.ai.simbad.utils.ActionType;

import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;

public class MazeBuilder extends EnvironmentDescription {
	private double scaleFactor;

	@SuppressWarnings("unchecked")
	public MazeBuilder(MazeBean maze) {
		this.scaleFactor = maze.getScaleFactor();

		Vector3f vertical = new Vector3f((float) (0.2f * this.scaleFactor), (float)maze.getWallHeight(), (float) (this.scaleFactor * 1.0f));
		Vector3f horizontal = new Vector3f((float) (this.scaleFactor * 1.0f), (float)maze.getWallHeight(), (float) (0.2f * this.scaleFactor));
		boolean rows[][] = this.buildWalls(maze);
		double trans = this.scaleFactor * (-rows[0].length / 2.0);
		double xTrans = trans;
		double zTrans = trans;

		for (int i = 0; i < rows.length; i++) {
			int row = i / 2;

			if (i % 2 == 0) {
				for (int j = 0; j < rows[i].length; j++) {
					if (rows[i][rows[i].length - j - 1]) {
						Vector3d wallLoc = new Vector3d(this.scaleFactor * row + xTrans, 0.0, -this.scaleFactor * (-(0.5 + j)) + zTrans);
						this.add(new Box(wallLoc, vertical, this));
					}
				}
			} else {
				for (int j = 0; j < rows[i].length; j++) {
					if (rows[i][rows[i].length - j - 1]) {
						Vector3d wallLoc = new Vector3d(this.scaleFactor * (0.5 + row) + xTrans, 0.0, -this.scaleFactor * (-j) + zTrans);
						this.add(new Box(wallLoc, horizontal, this));
					}
				}
			}
		}

		Vector3d start = new Vector3d(maze.getStart().getLine() * this.scaleFactor - 1.5d * this.scaleFactor, 0,
				1.5d * this.scaleFactor - maze.getStart().getColumn() * this.scaleFactor);

		try {
			Class<RobotMotion> classs = (Class<RobotMotion>) Class.forName(maze.getRobotClassname());
			Constructor<RobotMotion> constructor = classs.getConstructor(Vector3d.class, String.class);
			RobotMotion robot = constructor.newInstance(start, "robot");

			robot.setRadius((float) (this.scaleFactor / 4.0f));
			robot.setHeight((float) maze.getRobotHeight());

			this.add(robot);

			maze.setRobot(robot);
		} catch (Throwable e) {
			System.err.println(String.format("Problem to instantiate the robot [%s].", e.getMessage()));
			System.exit(-1);
		}
	}

	@Override
	public String toString() {
		return "[MazeEnvironmentDescription: " + super.toString() + "]";
	}

	private boolean[][] buildWalls(MazeBean maze) {
		boolean[][] walls = new boolean[2 * maze.getMazeHeight() + 1][];

		for (int i = 0; i < walls.length; i++) {
			walls[i] = new boolean[maze.getMazeWidth() + i % 2];

			for (int j = 0; j < walls[i].length; j++) {
				walls[i][j] = true;
			}
		}

		for (int i = 0; i < maze.getMap().length; i++) {
			for (int j = 0; j < maze.getMap()[i].length; j++) {
				if ((maze.getMap()[i][j] != null) && maze.getMap()[i][j].contains(ActionType.NORTH)) {
					walls[2 * i][j] = false;
				}

				if ((maze.getMap()[i][j] != null) && maze.getMap()[i][j].contains(ActionType.WEST)) {
					walls[2 * i + 1][j] = false;
				}

				if ((maze.getMap()[i][j] != null) && maze.getMap()[i][j].contains(ActionType.SOUTH)) {
					walls[2 * (i + 1)][j] = false;
				}

				if ((maze.getMap()[i][j] != null) && maze.getMap()[i][j].contains(ActionType.EAST)) {
					walls[2 * i + 1][j + 1] = false;
				}
			}
		}

		return walls;
	}

}
