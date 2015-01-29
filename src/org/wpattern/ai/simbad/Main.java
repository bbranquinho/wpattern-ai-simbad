package org.wpattern.ai.simbad;

import org.wpattern.ai.simbad.beans.MazeBean;
import org.wpattern.ai.simbad.loader.MazeLoader;

import simbad.gui.Simbad;

public class Main {

	public static void main(String[] args) {
		// request antialising
		System.setProperty("j3d.implicitAntialiasing", " true");

		MazeLoader mazeLoader = new MazeLoader();

		MazeBean maze = mazeLoader.load(System.getProperty("user.dir") + "/resources/mazes/maze_01.sbd");

		new Simbad(new MazeBuilder(maze), false);
	}

}