package org.wpattern.ai.simbad;

import java.util.LinkedList;

import javax.vecmath.Vector3d;

import org.wpattern.ai.simbad.beans.MovimentBean;

import simbad.sim.Agent;
import simbad.sim.RobotFactory;

public abstract class RobotMotion extends Agent {

	private LinkedList<MovimentBean> plan;

	private MovimentBean currentMoviment;

	private double movimentRatio = 1.0;

	public RobotMotion(Vector3d startPosition, String name) {
		super(startPosition, name);

		// Add camera
		RobotFactory.addCameraSensor(this);

		// Add sonars
		RobotFactory.addSonarBeltSensor(this);

		this.plan = new LinkedList<MovimentBean>();
	}

	@Override
	protected final void initBehavior() {
		this.init();
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	// Call cyclically (20 times per second) by the simulator engine.
	@Override
	protected final void performBehavior() {
		if ((this.currentMoviment == null) && (this.plan.size() <= 0)) {
			this.run();
			return;
		}

		if (this.currentMoviment == null) {
			this.currentMoviment = this.plan.removeFirst();
			this.executeMoviment(this.currentMoviment);
		}

		if (this.getCounter() > this.currentMoviment.getEndCounter()) {
			this.stopMoviment(this.currentMoviment);
			this.currentMoviment = null;
		}
	}

	private void executeMoviment(MovimentBean moviment) {
		System.out.println(String.format("Start moviment [%s].", moviment.getMovimentType()));
	}

	private void stopMoviment(MovimentBean moviment) {
		System.out.println(String.format("Stop moviment [%s].", moviment.getMovimentType()));
		this.setTranslationalVelocity(0.0f);
		this.setTranslationalVelocity(0.0f);
	}

	public abstract void init();

	public abstract void run();

	public void moveNorth() {}

	public void moveWest() {}

	public void moveSouth() {}

	public void moveEast() {}

	public double getMovimentRatio() {
		return this.movimentRatio;
	}

	public void setMovimentRatio(double movimentRatio) {
		this.movimentRatio = movimentRatio;
	}

}
