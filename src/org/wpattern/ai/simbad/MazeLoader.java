package org.wpattern.ai.simbad;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.wpattern.ai.simbad.beans.MazeBean;
import org.wpattern.ai.simbad.beans.StateBean;
import org.wpattern.ai.simbad.utils.MovimentType;

public class MazeLoader {

	private static final Charset ENCODING = StandardCharsets.UTF_8;

	private static final String GOAL_FIELD = "GOAL";

	private static final String HEIGHT_FIELD = "HEIGHT";

	private static final String ROBOT_HEIGHT = "ROBOT_HEIGHT";

	private static final String SCALE_FACTOR_FIELD = "SCALE_FACTOR";

	private static final String START_FIELD = "START";

	private static final String WALL_HEIGHT_FIELD = "WALL_HEIGHT";

	private static final String WIDTH_FIELD = "WIDTH";

	private static final String STATE_FIELD = "S";

	public MazeLoader() {
	}

	public MazeBean load(String filename) {
		System.out.println(String.format("Loading map [%s].", filename));

		MazeBean maze = new MazeBean();

		try (Scanner scanner =  new Scanner(Paths.get(filename), ENCODING.name())){
			String line;

			while (scanner.hasNextLine()){
				line = scanner.nextLine().trim();

				if (line.length() > 0) {
					this.processLine(maze, line);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return maze;
	}

	private void processLine(MazeBean maze, String line) {
		System.out.println(String.format("Maze processing line [%s].", line));

		String[] fields = this.removeSpaces(line.split("="));

		switch (fields[0]) {
		case GOAL_FIELD:
			maze.setGoal(this.buildState(fields[1]));
			break;

		case HEIGHT_FIELD:
			maze.setMazeHeight(Integer.parseInt(fields[1]));
			break;

		case ROBOT_HEIGHT:
			maze.setRobotHeight(Double.parseDouble(fields[1]));
			break;

		case SCALE_FACTOR_FIELD:
			maze.setScaleFactor(Double.parseDouble(fields[1]));
			break;

		case START_FIELD:
			maze.setStart(this.buildState(fields[1]));
			break;

		case WALL_HEIGHT_FIELD:
			maze.setWallHeight(Double.parseDouble(fields[1]));
			break;

		case WIDTH_FIELD:
			maze.setMazeWidth(Integer.parseInt(fields[1]));
			break;

		case "": break;

		default:
			if (fields[0].toUpperCase().substring(0, 1).equals(STATE_FIELD)) {
				this.processState(maze, fields[0], fields[1]);
			}
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void processState(MazeBean maze, String state, String value) {
		if (maze.getMap() == null) {
			maze.setMap(new ArrayList[maze.getMazeHeight()][maze.getMazeWidth()]);
		}

		StringTokenizer stringTokenizer = new StringTokenizer(state, "(),");

		stringTokenizer.nextToken();

		int line = Integer.parseInt(stringTokenizer.nextToken().trim());
		int column = Integer.parseInt(stringTokenizer.nextToken().trim());

		this.addMoviments(maze, line, column, this.removeSpaces(value.split(",")));
	}

	private void addMoviments(MazeBean maze, int lineIndex, int columnIndex, String[] values) {
		int nearLineIndex, nearColumnIndex;

		if ((lineIndex >= maze.getMazeHeight()) || (columnIndex >= maze.getMazeWidth())) {
			System.out.println("WARNING: Line or/and column is/are outside the maze.");
			return;
		}

		if (maze.getMap()[lineIndex][columnIndex] == null) {
			maze.getMap()[lineIndex][columnIndex] = new ArrayList<MovimentType>();
		}

		MovimentType moviment = null, nearMoviment = null;

		for (int i = 0; i < values.length; i++) {
			moviment = MovimentType.parse(values[i]);

			if (this.ignoreMoviment(maze, lineIndex, columnIndex, moviment)) {
				System.out.println(String.format("WARNING: Ignoring moviment [%s] of state [%s, %s].", moviment, lineIndex, columnIndex));
				continue;
			}

			nearLineIndex = lineIndex;
			nearColumnIndex = columnIndex;

			switch (moviment) {
			case NORTH:
				nearLineIndex--;
				nearMoviment = MovimentType.SOUTH;
				break;

			case WEST:
				nearColumnIndex--;
				nearMoviment = MovimentType.EAST;
				break;

			case SOUTH:
				nearLineIndex++;
				nearMoviment = MovimentType.NORTH;
				break;

			case EAST:
				nearColumnIndex++;
				nearMoviment = MovimentType.WEST;
				break;
			}

			maze.getMap()[lineIndex][columnIndex].add(moviment);

			if (maze.getMap()[nearLineIndex][nearColumnIndex] == null) {
				maze.getMap()[nearLineIndex][nearColumnIndex] = new ArrayList<MovimentType>();
			}

			maze.getMap()[nearLineIndex][nearColumnIndex].add(nearMoviment);
		}
	}

	private boolean ignoreMoviment(MazeBean maze, int lineIndex, int columnIndex, MovimentType moviment) {
		if ((moviment == MovimentType.NORTH) && (lineIndex == 0)) {
			return true;
		}

		if ((moviment == MovimentType.WEST) && (columnIndex == 0)) {
			return true;
		}

		if ((moviment == MovimentType.SOUTH) && (lineIndex == maze.getMazeHeight() - 1)) {
			return true;
		}

		if ((moviment == MovimentType.EAST) && (lineIndex == maze.getMazeWidth() - 1)) {
			return true;
		}

		return false;
	}

	private String[] removeSpaces(String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			fields[i] = fields[i].trim().toUpperCase();
		}

		return fields;
	}

	private StateBean buildState(String value) {
		String[] indexState = this.removeSpaces(value.split(","));

		return new StateBean(Integer.parseInt(indexState[0]), Integer.parseInt(indexState[1]));
	}

}
