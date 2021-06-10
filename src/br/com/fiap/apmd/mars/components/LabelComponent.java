package br.com.fiap.apmd.mars.components;

import javax.swing.JLabel;

import br.com.fiap.apmd.mars.factory.ColorFactory;
import br.com.fiap.apmd.mars.factory.FontFactory;

public class LabelComponent extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1727142728617170300L;

	public LabelComponent(String text) {
		super(text);
		
		init();
	}
	
	private void init() {
		this.setFont(FontFactory.getBold(12));
		this.setForeground(ColorFactory.getTextColor());
	}
	
}
