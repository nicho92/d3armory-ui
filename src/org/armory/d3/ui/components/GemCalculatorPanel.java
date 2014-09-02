package org.armory.d3.ui.components;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import com.pihen.d3restapi.beans.Gem;

import java.awt.Insets;

public class GemCalculatorPanel extends JPanel {

	
	public GemCalculatorPanel() {
		setBackground(Color.BLACK);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{191, 68, 0};
	gridBagLayout.rowHeights = new int[]{14, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	setLayout(gridBagLayout);
	JLabel lblGemeInitial = new JLabel("What to craft?");
	lblGemeInitial.setForeground(Color.WHITE);
	GridBagConstraints gbc_lblGemeInitial = new GridBagConstraints();
	gbc_lblGemeInitial.insets = new Insets(0, 0, 5, 5);
	gbc_lblGemeInitial.anchor = GridBagConstraints.NORTH;
	gbc_lblGemeInitial.gridx = 0;
	gbc_lblGemeInitial.gridy = 1;
	this.add(lblGemeInitial, gbc_lblGemeInitial);
	
	JComboBox cboFromGem = new JComboBox(Gem.QUALITIES());
	GridBagConstraints gbc_cboFromGem = new GridBagConstraints();
	gbc_cboFromGem.insets = new Insets(0, 0, 5, 0);
	gbc_cboFromGem.fill = GridBagConstraints.HORIZONTAL;
	gbc_cboFromGem.gridx = 1;
	gbc_cboFromGem.gridy = 1;
	add(cboFromGem, gbc_cboFromGem);
	
	JLabel lblNewLabel = new JLabel("Craft from :");
	lblNewLabel.setForeground(Color.WHITE);
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 2;
	add(lblNewLabel, gbc_lblNewLabel);
	
	JComboBox cboToGem = new JComboBox(Gem.QUALITIES());
	cboToGem.setSelectedIndex(Gem.QUALITIES().length-1);
	GridBagConstraints gbc_cboToGem = new GridBagConstraints();
	gbc_cboToGem.insets = new Insets(0, 0, 5, 0);
	gbc_cboToGem.fill = GridBagConstraints.HORIZONTAL;
	gbc_cboToGem.gridx = 1;
	gbc_cboToGem.gridy = 2;
	add(cboToGem, gbc_cboToGem);
	
	JLabel lblNewLabel_1 = new JLabel("Already Have :");
	lblNewLabel_1.setForeground(Color.WHITE);
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.anchor = GridBagConstraints.ABOVE_BASELINE;
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
	gbc_lblNewLabel_1.gridx = 0;
	gbc_lblNewLabel_1.gridy = 3;
	add(lblNewLabel_1, gbc_lblNewLabel_1);
	}
	
}
