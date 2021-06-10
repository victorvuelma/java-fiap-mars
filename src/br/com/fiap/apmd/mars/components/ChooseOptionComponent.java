package br.com.fiap.apmd.mars.components;

import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.fiap.apmd.mars.factory.ColorFactory;
import br.com.fiap.apmd.mars.factory.FontFactory;

public class ChooseOptionComponent extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6796708280537340342L;
	
	private String[] options;
	
	private ButtonGroup buttonGroup = new ButtonGroup();

	public ChooseOptionComponent(String[] options) {
		super();
		
		this.options = options;
		
		init();
	}
	
	public String getValue() {
		for (Enumeration<AbstractButton> buttons = this.buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
		
		return null;
	}
	
	public void setValue(String value) {
		for (Enumeration<AbstractButton> buttons = this.buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.getText().equals(value)) {
                button.setSelected(true);
            } else {
            	button.setSelected(false);
            }
        }
	}
	
	public void clearSelection() {
		this.buttonGroup.clearSelection();
	}
	
	private void init() {
		this.setFont(FontFactory.getBold(12));
		this.setForeground(ColorFactory.getTextColor());
		this.setLayout(new GridLayout(this.options.length, 1));
		
		for (String option : this.options) {
			JRadioButton optionRadio = new JRadioButton(option);
			optionRadio.setFont(FontFactory.getSize(12));
			optionRadio.setForeground(ColorFactory.getTextColor());
			
			this.buttonGroup.add(optionRadio);
			
			this.add(optionRadio);
		}
	}
	
}
