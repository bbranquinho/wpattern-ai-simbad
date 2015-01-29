package org.wpattern.ai.simbad.mdp.window;

import javax.swing.AbstractListModel;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import org.wpattern.ai.simbad.beans.MazeBean;
import org.wpattern.ai.simbad.mdp.interfaces.IMdpListener;
import org.wpattern.ai.simbad.utils.ActionType;

public final class ValuePolicyWindow extends JInternalFrame implements IMdpListener {

	private static final long serialVersionUID = 201412080750L;

	private DefaultTableModel tableModel;

	private JTable table;

	private MazeBean maze;

	private WindowType windowType;

	public ValuePolicyWindow() {
		this.initialize();
		this.initializeWindow();
	}

	private void initializeWindow() {
		this.show();
	}

	public ValuePolicyWindow(MazeBean maze, int xLocation, int yLocation, WindowType windowType) {
		this.maze = maze;
		this.windowType = windowType;

		this.setLocation(xLocation, yLocation);
		this.initialize();

		this.initializeWindow();
	}

	@Override
	public void onPolicyChange(int line, int column, ActionType newAction) {
		if (this.windowType == WindowType.POLICY) {
			this.tableModel.setValueAt(newAction, line, column);
		}
	}

	@Override
	public void onValueChange(int line, int column, double newValue) {
		if (this.windowType == WindowType.VALUE) {
			this.tableModel.setValueAt(newValue, line, column);
		}
	}

	private void initialize() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow,fill]", "[211.00,grow]"));
		panel.setBounds(100, 100, 250, 200);

		this.setResizable(true);
		this.setTitle("Markov Decision Processes (MDP) - " + this.windowType);
		this.setContentPane(panel);

		// Header model
		ListModel<String> rowHeaderModel = new AbstractListModel<String>() {
			private static final long serialVersionUID = 201412081328L;

			@Override
			public int getSize() {
				return ValuePolicyWindow.this.maze.getMazeHeight();
			}

			@Override
			public String getElementAt(int index) {
				return "" + index;
			}
		};

		String[] columnNames = this.buildHeaderName(this.maze);

		// Table model
		this.tableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 201412091611L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Table
		this.table = new JTable(this.tableModel);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setRowSelectionAllowed(false);
		panel.add(this.table);

		// Header
		JList<String> header = new JList<String>(rowHeaderModel);
		header.setFixedCellWidth(20);
		header.setFixedCellHeight(this.table.getRowHeight());
		header.setCellRenderer(new RowHeaderRenderer(this.table));

		// Scroll
		JScrollPane scroll = new JScrollPane(this.table);
		panel.add(scroll);
		scroll.setRowHeaderView(header);

		// Setup
		this.setupRows();

		this.pack();
	}

	private String[] buildHeaderName(MazeBean maze) {
		if (maze == null) {
			return new String[0];
		}

		String[] headerName = new String[maze.getMazeWidth()];

		for (int i = 0; i < maze.getMazeWidth(); i++) {
			headerName[i] = i + "";
		}

		return headerName;
	}

	private void setupRows() {
		if (this.maze == null) {
			return;
		}

		for (int i = 0; i < this.maze.getMazeHeight(); i++) {
			String[] item = new String[this.maze.getMazeWidth()];

			for (int j = 0; j < this.maze.getMazeWidth(); j++) {
				item[j] = "";
			}

			this.tableModel.addRow(item);
		}
	}

}
