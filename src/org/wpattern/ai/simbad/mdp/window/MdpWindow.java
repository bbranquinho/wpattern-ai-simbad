package org.wpattern.ai.simbad.mdp.window;

import org.wpattern.ai.simbad.MazeBuilder;
import org.wpattern.ai.simbad.beans.MazeBean;
import org.wpattern.ai.simbad.loader.MazeLoader;
import org.wpattern.ai.simbad.loader.MdpLoader;
import org.wpattern.ai.simbad.mdp.beans.MdpBean;
import org.wpattern.ai.simbad.mdp.interfaces.IRobotMdp;

import simbad.gui.Simbad;

public class MdpWindow {

	public void start(String filename) {
		// request antialising
		System.setProperty("j3d.implicitAntialiasing", " true");

		// Maze
		MazeBean maze = (new MazeLoader()).load(filename);

		// MDP
		MdpBean mdp = (new MdpLoader()).load(maze, filename);

		// MDP Window
		ValuePolicyWindow policyWindow = new ValuePolicyWindow(maze, 10, 10, WindowType.POLICY);
		ValuePolicyWindow valueWindow = new ValuePolicyWindow(maze, 360, 10, WindowType.VALUE);

		// Simbad Maze
		MazeBuilder simbadMaze = new MazeBuilder(maze);

		if (maze.getRobot() instanceof IRobotMdp) {
			((IRobotMdp)maze.getRobot()).setMdp(mdp);
			((IRobotMdp)maze.getRobot()).registerListener(policyWindow);
			((IRobotMdp)maze.getRobot()).registerListener(valueWindow);
		}

		// Simbad
		Simbad simbad = new Simbad(simbadMaze, false);

		simbad.getDesktopPane().add(policyWindow);
		simbad.getDesktopPane().add(valueWindow);
	}

}
