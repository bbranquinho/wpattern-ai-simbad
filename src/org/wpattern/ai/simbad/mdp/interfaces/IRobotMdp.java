package org.wpattern.ai.simbad.mdp.interfaces;

import org.wpattern.ai.simbad.beans.MazeBean;
import org.wpattern.ai.simbad.mdp.beans.MdpBean;

public interface IRobotMdp {

	public void setMdp(MdpBean mdp);

	public void registerListener(IMdpListener listener);

	public void setMaze(MazeBean maze);

}
