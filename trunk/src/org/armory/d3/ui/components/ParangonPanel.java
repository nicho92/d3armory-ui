package org.armory.d3.ui.components;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.service.remote.exception.D3ServerCommunicationException;
import com.pihen.d3restapi.service.util.StuffCalculator;
import com.pihen.d3restapi.service.util.StuffCalculator.KEY;

public class ParangonPanel extends JPanel {
	
	
	
	public void updateParametre(String att,double val,JLabel label){
		
			StuffCalculator calc = new StuffCalculator(D3ArmoryControler.getInstance().getCalculator().getStuffs(),D3ArmoryControler.getInstance().getSelectedHero(false));
							calc.addBonus(att, new MinMaxBonus(val));
		double val2=calc.calculate().get(KEY.DPS);
		double val1=D3ArmoryControler.getInstance().getCalculator().calculate().get(KEY.DPS);
		double diff = val2-val1;
		label.setText("+"+StuffCalculator.format(diff)+ " (+"+ StuffCalculator.format(((val2 - val1) / val1 ) * 100)+"%)");
		
		
	}
	
	
	
	
	public ParangonPanel() {
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{130, 269, 122, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblDamage = new JLabel("DAMAGE");
		lblDamage.setOpaque(true);
		lblDamage.setBackground(new Color(128, 0, 0));
		lblDamage.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDamage = new GridBagConstraints();
		gbc_lblDamage.fill = GridBagConstraints.BOTH;
		gbc_lblDamage.gridwidth = 3;
		gbc_lblDamage.insets = new Insets(0, 0, 5, 5);
		gbc_lblDamage.gridx = 0;
		gbc_lblDamage.gridy = 1;
		add(lblDamage, gbc_lblDamage);
		
		JLabel lblPrimaryBaseStat = new JLabel("Primary Base Stat");
		lblPrimaryBaseStat.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPrimaryBaseStat = new GridBagConstraints();
		gbc_lblPrimaryBaseStat.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrimaryBaseStat.gridx = 0;
		gbc_lblPrimaryBaseStat.gridy = 2;
		add(lblPrimaryBaseStat, gbc_lblPrimaryBaseStat);
		
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 2;
		add(spinner, gbc_spinner);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblCriticalChance = new JLabel("Critical Chance");
		lblCriticalChance.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCriticalChance = new GridBagConstraints();
		gbc_lblCriticalChance.insets = new Insets(0, 0, 5, 5);
		gbc_lblCriticalChance.gridx = 0;
		gbc_lblCriticalChance.gridy = 3;
		add(lblCriticalChance, gbc_lblCriticalChance);
		
		final JSpinner spinner_1 = new JSpinner();
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 3;
		add(spinner_1, gbc_spinner_1);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblCriticalDamage = new JLabel("Critical Damage");
		lblCriticalDamage.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCriticalDamage = new GridBagConstraints();
		gbc_lblCriticalDamage.insets = new Insets(0, 0, 5, 5);
		gbc_lblCriticalDamage.gridx = 0;
		gbc_lblCriticalDamage.gridy = 4;
		add(lblCriticalDamage, gbc_lblCriticalDamage);
		
		final JSpinner spinner_2 = new JSpinner();
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 4;
		add(spinner_2, gbc_spinner_2);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 4;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		
		
		
		JLabel label = new JLabel("Attack Speed");
		label.setForeground(Color.WHITE);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 5;
		add(label, gbc_label);
		
		final JSpinner spinner_3 = new JSpinner();
		GridBagConstraints gbc_spinner_3 = new GridBagConstraints();
		gbc_spinner_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_3.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_3.gridx = 1;
		gbc_spinner_3.gridy = 5;
		add(spinner_3, gbc_spinner_3);
		
		final JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 5;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		spinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner.getValue().toString());
				String att = D3ArmoryControler.getInstance().getSelectedHero(false).getPrimaryStat()+"_PARANGON";
				updateParametre(att, val,lblNewLabel);
			}
		});
		
		spinner_1.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_1.getValue().toString())/100;
				String att = "Crit_Percent_Bonus_Capped_PARANGON";
				updateParametre(att, val,lblNewLabel_1);
			}
		});
		
			
		spinner_2.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_2.getValue().toString())/100;
				String att = "Crit_Damage_Percent_PARANGON";
				updateParametre(att, val,lblNewLabel_2);
			}
		});
		
		
		spinner_3.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_3.getValue().toString())/100;
				String att = "Attacks_Per_Second_Item_Percent_PARANGON";
				updateParametre(att, val,lblNewLabel_3);
			}
		});
		
	}

}
