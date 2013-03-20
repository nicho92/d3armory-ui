package org.armory.d3.ui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

public class TableListHero extends JTable {

	
	public Component getTableCellRendererComponent(JTable jtable, Object value,boolean bln, boolean bln1, int i, int i1) {
        JLabel lbl = new JLabel();
        lbl.setIcon((ImageIcon) value);
        return lbl;
    }
}
