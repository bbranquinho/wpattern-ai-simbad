package org.wpattern.ai.simbad;

import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Vector3d;

import org.wpattern.ai.simbad.beans.MazeBean;
import org.wpattern.ai.simbad.beans.MovimentBean;
import org.wpattern.ai.simbad.utils.ActionType;

import simbad.sim.Agent;
import simbad.sim.RobotFactory;

public abstract class RobotMotion extends Agent {

	private LinkedList<MovimentBean> plan;

	private MovimentBean currentMoviment;

	private double movimentRatio;

	private boolean initiated;

	private int currentLine, currentColumn;

	private MazeBean maze;

	public RobotMotion(MazeBean maze, Vector3d startPosition, String name) {
		super(startPosition, name);

		// Add camera
		RobotFactory.addCameraSensor(this);

		// Add sonars
		RobotFactory.addSonarBeltSensor(this);

		this.initiated = false;
		this.movimentRatio = 1.0;
		this.plan = new LinkedList<MovimentBean>();
		this.currentLine = maze.getStart().getLine();
		this.currentColumn = maze.getStart().getColumn();
		this.maze = maze;
	}

	@Override
	protected final void initBehavior() {
		this.currentMoviment = null;
		this.init();
		this.initiated = true;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	@Override
	protected void reset() {
		super.reset();
		this.initiated = false;
		this.currentMoviment = null;
	}

	// Call cyclically (20 times per second) by the simulator engine.
	@Override
	protected final void performBehavior() {
		if (!this.initiated) {
			this.initBehavior();
		}

		if ((this.currentMoviment == null) && (this.plan.size() <= 0)) {
			this.run(this.currentLine, this.currentColumn);
			return;
		}

		if (this.currentMoviment == null) {
			this.currentMoviment = this.plan.removeFirst();
			this.currentMoviment.setStartCounter(this.getCounter());
			this.currentMoviment.setEndCounter(this.getCounter() + 20);
		}

		if (this.getCounter() <= this.currentMoviment.getEndCounter()) {
			this.executeMoviment(this.currentMoviment);
		} else {
			this.stopMoviment(this.currentMoviment);
			this.currentMoviment = null;
		}
	}

	private void executeMoviment(MovimentBean moviment) {
		System.out.println(String.format("Start moviment [%s].", moviment.getMovimentType()));

		System.out.println(this.getCounter());

		//this.setTranslationalVelocity(0.1f);
		this.setRotationalVelocity(1.0f);
	}

	private void stopMoviment(MovimentBean moviment) {
		System.out.println(String.format("Stop moviment [%s].", moviment.getMovimentType()));
		this.setTranslationalVelocity(0.0f);
		this.setRotationalVelocity(0.0f);
	}

	public abstract void init();

	public abstract void run(int line, int column);

	static boolean oi = false;

	public void move(ActionType action) {
		if (!oi) {
			oi = true;
			this.plan.add(new MovimentBean(ActionType.NORTH));
			return;
		}

		//		switch (action) {
		//		case NORTH:
		//			this.moveNorth();
		//			break;
		//
		//		case WEST:
		//			this.moveWest();
		//			break;
		//
		//		case SOUTH:
		//			this.moveSouth();
		//			break;
		//
		//		case EAST:
		//			this.moveEast();
		//			break;
		//
		//		case STAY:
		//			// Do nothing.
		//			break;
		//		}
	}

	public void addPlan(List<ActionType> plan) {
		for (ActionType action : plan) {
			this.plan.add(new MovimentBean(action));
		}
	}

	public void cleaPlan() {
		this.plan.clear();
	}

	public void moveNorth() {
		this.plan.add(new MovimentBean(ActionType.NORTH));
	}

	public void moveWest() {
		this.plan.add(new MovimentBean(ActionType.WEST));
	}

	public void moveSouth() {
		this.plan.add(new MovimentBean(ActionType.SOUTH));
	}

	public void moveEast() {
		this.plan.add(new MovimentBean(ActionType.EAST));
	}

	public double getMovimentRatio() {
		return this.movimentRatio;
	}

	public void setMovimentRatio(double movimentRatio) {
		this.movimentRatio = movimentRatio;
	}

}
