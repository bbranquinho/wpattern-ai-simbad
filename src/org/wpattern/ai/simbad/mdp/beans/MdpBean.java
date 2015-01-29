package org.wpattern.ai.simbad.mdp.beans;

import java.util.List;

import org.wpattern.ai.simbad.beans.StateBean;
import org.wpattern.ai.simbad.utils.ActionType;

public class MdpBean {

	private double gamma;

	private ActionType[] actions;

	private ActionType[][] policy;

	private double[][] reward;

	private double[] cost;

	private double[][] transition;

	private double value[][];

	private List<StateBean> terminationStates;

	public MdpBean() {
	}

	public double getGamma() {
		return this.gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public ActionType[][] getPolicy() {
		return this.policy;
	}

	public void setPolicy(ActionType[][] policy) {
		this.policy = policy;
	}

	public double[][] getReward() {
		return this.reward;
	}

	public void setReward(double[][] reward) {
		this.reward = reward;
	}

	public double[] getCost() {
		return this.cost;
	}

	public void setCost(double[] cost) {
		this.cost = cost;
	}

	public double[][] getTransition() {
		return this.transition;
	}

	public void setTransition(double[][] transition) {
		this.transition = transition;
	}

	public double[][] getValue() {
		return this.value;
	}

	public void setValue(double[][] value) {
		this.value = value;
	}

	public ActionType[] getActions() {
		return this.actions;
	}

	public void setActions(ActionType[] actions) {
		this.actions = actions;
	}

	public List<StateBean> getTerminationStates() {
		return this.terminationStates;
	}

	public void setTerminationStates(List<StateBean> terminationStates) {
		this.terminationStates = terminationStates;
	}

}
