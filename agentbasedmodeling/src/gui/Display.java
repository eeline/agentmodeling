package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import threadmanager.MainHandler;


public class Display extends JPanel {
	/**
	 *  Crummy display to add an exit dialog and let the user know just what, exactly, happened. 
	 */
	private static final long serialVersionUID = -8073029977468444353L;
	private static final String DIALOG_TEXT = "Run Agent Model Sample";
	private static final String CLOSE_TEXT = "Output logged";

	
	private Display(){

	}
	
	public static void main(String[] args){
		if (JOptionPane.showConfirmDialog(null, DIALOG_TEXT, null, JOptionPane.OK_CANCEL_OPTION) ==0){
				MainHandler.start();
				
				if(JOptionPane.showConfirmDialog(null, CLOSE_TEXT, null, JOptionPane.DEFAULT_OPTION)==0)
					MainHandler.die();
		}
		
		else MainHandler.die();
	}
}
