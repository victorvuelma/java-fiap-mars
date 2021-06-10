package br.com.fiap.apmd.mars.components;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class GridPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1972528069021760820L;

	public GridPanel(int rows, int cols) {
		super(new GridLayout(rows, cols));
	}
	
	public GridPanel(int rows, int cols, int hgap, int vgap) {
		super(new GridLayout(rows, cols, hgap, vgap));
	}
	
}
