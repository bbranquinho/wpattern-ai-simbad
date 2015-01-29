package org.wpattern.ai.simbad.mdp.interfaces;

import org.wpattern.ai.simbad.utils.ActionType;

public interface IMdpListener {

	public void onPolicyChange(int line, int column, ActionType newAction);

	public void onValueChange(int line, int column, double newValue);

}
