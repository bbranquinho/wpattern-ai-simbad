package org.wpattern.ai.simbad.mdp.interfaces;

import org.wpattern.ai.simbad.mdp.beans.MdpBean;

public interface IRobotMdp {

	public void setMdp(MdpBean mdp);

	public void registerListener(IMdpListener listener);

}
