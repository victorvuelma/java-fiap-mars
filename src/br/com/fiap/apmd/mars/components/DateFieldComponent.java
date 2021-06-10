package br.com.fiap.apmd.mars.components;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class DateFieldComponent extends JFormattedTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754393724343342766L;
	
	public DateFieldComponent() {
		super(createFormatter("##'/##'/####"));
	}
	
	private static MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}

}
