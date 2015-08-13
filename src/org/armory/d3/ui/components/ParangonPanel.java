package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.MinMaxBonus;
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
	
	public void updateEHP(String att,double val,JLabel label){
		
		StuffCalculator calc = new StuffCalculator(D3ArmoryControler.getInstance().getCalculator().getStuffs(),D3ArmoryControler.getInstance().getSelectedHero(false));
						calc.addBonus(att, new MinMaxBonus(val));
						
		double val2=calc.calculate().get(KEY.TOUGHNESS);
		double val1=D3ArmoryControler.getInstance().getCalculator().calculate().get(KEY.TOUGHNESS);
		double diff = val2-val1;
		label.setText("+"+StuffCalculator.format(diff)+ " (+"+ StuffCalculator.format(((val2 - val1) / val1 ) * 100)+"%)");
	
	
}
	
	
	public ParangonPanel() {
		setBackground(Color.BLACK);
		setMinimumSize(new Dimension(0,0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{130, 269, 122, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblDamage = new JLabel("DAMAGE");
		lblDamage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDamage.setOpaque(true);
		lblDamage.setBackground(new Color(128, 0, 0));
		lblDamage.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDamage = new GridBagConstraints();
		gbc_lblDamage.fill = GridBagConstraints.BOTH;
		gbc_lblDamage.gridwidth = 3;
		gbc_lblDamage.insets = new Insets(0, 0, 5, 0);
		gbc_lblDamage.gridx = 0;
		gbc_lblDamage.gridy = 1;
		add(lblDamage, gbc_lblDamage);
		
		JLabel lblPrimaryBaseStat = new JLabel("Primary Stat");
		lblPrimaryBaseStat.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPrimaryBaseStat = new GridBagConstraints();
		gbc_lblPrimaryBaseStat.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrimaryBaseStat.gridx = 0;
		gbc_lblPrimaryBaseStat.gridy = 2;
		add(lblPrimaryBaseStat, gbc_lblPrimaryBaseStat);
		
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 2;
		add(spinner, gbc_spinner);
		
		final JLabel lblPrimaryStatValue = new JLabel("");
		lblPrimaryStatValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPrimaryStatValue = new GridBagConstraints();
		gbc_lblPrimaryStatValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrimaryStatValue.gridx = 2;
		gbc_lblPrimaryStatValue.gridy = 2;
		add(lblPrimaryStatValue, gbc_lblPrimaryStatValue);
		
		JLabel lblCriticalChance = new JLabel("Critical Chance");
		lblCriticalChance.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCriticalChance = new GridBagConstraints();
		gbc_lblCriticalChance.insets = new Insets(0, 0, 5, 5);
		gbc_lblCriticalChance.gridx = 0;
		gbc_lblCriticalChance.gridy = 3;
		add(lblCriticalChance, gbc_lblCriticalChance);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 3;
		add(spinner_1, gbc_spinner_1);
		
		final JLabel lblCCCValue = new JLabel("");
		lblCCCValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCCCValue = new GridBagConstraints();
		gbc_lblCCCValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblCCCValue.gridx = 2;
		gbc_lblCCCValue.gridy = 3;
		add(lblCCCValue, gbc_lblCCCValue);
		
		JLabel lblCriticalDamage = new JLabel("Critical Damage");
		lblCriticalDamage.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCriticalDamage = new GridBagConstraints();
		gbc_lblCriticalDamage.insets = new Insets(0, 0, 5, 5);
		gbc_lblCriticalDamage.gridx = 0;
		gbc_lblCriticalDamage.gridy = 4;
		add(lblCriticalDamage, gbc_lblCriticalDamage);
		
		final JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 4;
		add(spinner_2, gbc_spinner_2);
		
		final JLabel lblCCDValue = new JLabel("");
		lblCCDValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCCDValue = new GridBagConstraints();
		gbc_lblCCDValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblCCDValue.gridx = 2;
		gbc_lblCCDValue.gridy = 4;
		add(lblCCDValue, gbc_lblCCDValue);

		
		
		
		JLabel label = new JLabel("Attack Speed");
		label.setForeground(Color.WHITE);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 5;
		add(label, gbc_label);
		
		final JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_3 = new GridBagConstraints();
		gbc_spinner_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_3.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_3.gridx = 1;
		gbc_spinner_3.gridy = 5;
		add(spinner_3, gbc_spinner_3);
		
		final JLabel lblASValue = new JLabel("");
		lblASValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblASValue = new GridBagConstraints();
		gbc_lblASValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblASValue.gridx = 2;
		gbc_lblASValue.gridy = 5;
		add(lblASValue, gbc_lblASValue);
		
		JLabel lblEhp = new JLabel("TOUGHNESS");
		lblEhp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEhp.setOpaque(true);
		lblEhp.setForeground(Color.WHITE);
		lblEhp.setBackground(new Color(60, 179, 113));
		GridBagConstraints gbc_lblEhp = new GridBagConstraints();
		gbc_lblEhp.insets = new Insets(0, 0, 5, 0);
		gbc_lblEhp.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEhp.gridwidth = 3;
		gbc_lblEhp.gridx = 0;
		gbc_lblEhp.gridy = 7;
		add(lblEhp, gbc_lblEhp);
		
		JLabel lblVitality = new JLabel("Vitality");
		lblVitality.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblVitality = new GridBagConstraints();
		gbc_lblVitality.insets = new Insets(0, 0, 5, 5);
		gbc_lblVitality.gridx = 0;
		gbc_lblVitality.gridy = 8;
		add(lblVitality, gbc_lblVitality);
		
		final JSpinner spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_4 = new GridBagConstraints();
		gbc_spinner_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_4.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_4.gridx = 1;
		gbc_spinner_4.gridy = 8;
		add(spinner_4, gbc_spinner_4);
		
		final JLabel lblVitalityValue = new JLabel("");
		lblVitalityValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblVitalityValue = new GridBagConstraints();
		gbc_lblVitalityValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblVitalityValue.gridx = 2;
		gbc_lblVitalityValue.gridy = 8;
		add(lblVitalityValue, gbc_lblVitalityValue);
		
		JLabel lblArmor = new JLabel("Armor");
		lblArmor.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblArmor = new GridBagConstraints();
		gbc_lblArmor.insets = new Insets(0, 0, 5, 5);
		gbc_lblArmor.gridx = 0;
		gbc_lblArmor.gridy = 9;
		add(lblArmor, gbc_lblArmor);
		
		final JSpinner spinner_5 = new JSpinner();
		spinner_5.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_5 = new GridBagConstraints();
		gbc_spinner_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_5.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_5.gridx = 1;
		gbc_spinner_5.gridy = 9;
		add(spinner_5, gbc_spinner_5);
		
		final JLabel lblArmorValue = new JLabel("");
		lblArmorValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblArmorValue = new GridBagConstraints();
		gbc_lblArmorValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblArmorValue.gridx = 2;
		gbc_lblArmorValue.gridy = 9;
		add(lblArmorValue, gbc_lblArmorValue);
		
		JLabel lblLife = new JLabel("% life");
		lblLife.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLife = new GridBagConstraints();
		gbc_lblLife.insets = new Insets(0, 0, 5, 5);
		gbc_lblLife.gridx = 0;
		gbc_lblLife.gridy = 10;
		add(lblLife, gbc_lblLife);
		
		final JSpinner spinner_6 = new JSpinner();
		spinner_6.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_6 = new GridBagConstraints();
		gbc_spinner_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_6.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_6.gridx = 1;
		gbc_spinner_6.gridy = 10;
		add(spinner_6, gbc_spinner_6);
		
		final JLabel lblLifeValue = new JLabel("");
		lblLifeValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLifeValue = new GridBagConstraints();
		gbc_lblLifeValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblLifeValue.gridx = 2;
		gbc_lblLifeValue.gridy = 10;
		add(lblLifeValue, gbc_lblLifeValue);
		
		JLabel lblResistance = new JLabel("Resistance");
		lblResistance.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblResistance = new GridBagConstraints();
		gbc_lblResistance.insets = new Insets(0, 0, 5, 5);
		gbc_lblResistance.gridx = 0;
		gbc_lblResistance.gridy = 11;
		add(lblResistance, gbc_lblResistance);
		
		final JSpinner spinner_7 = new JSpinner();
		spinner_7.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_7 = new GridBagConstraints();
		gbc_spinner_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_7.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_7.gridx = 1;
		gbc_spinner_7.gridy = 11;
		add(spinner_7, gbc_spinner_7);
		
		final JLabel lblResistValue = new JLabel("");
		lblResistValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblResistValue = new GridBagConstraints();
		gbc_lblResistValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblResistValue.gridx = 2;
		gbc_lblResistValue.gridy = 11;
		add(lblResistValue, gbc_lblResistValue);
		
		JButton btnAddTo = new JButton("Add 10 to all");
		GridBagConstraints gbc_btnAddTo = new GridBagConstraints();
		gbc_btnAddTo.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddTo.gridx = 1;
		gbc_btnAddTo.gridy = 12;
		add(btnAddTo, gbc_btnAddTo);
	
		
		
		btnAddTo.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                spinner.setValue(((Integer)spinner.getValue())+10);
                spinner_1.setValue(((Integer)spinner_1.getValue())+10);
                spinner_2.setValue(((Integer)spinner_2.getValue())+10);
                spinner_3.setValue(((Integer)spinner_3.getValue())+10);
                spinner_4.setValue(((Integer)spinner_4.getValue())+10);
                spinner_5.setValue(((Integer)spinner_5.getValue())+10);
                spinner_6.setValue(((Integer)spinner_6.getValue())+10);
                spinner_7.setValue(((Integer)spinner_7.getValue())+10);
            }
        });      
		
		
		
		
		
		
		spinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner.getValue().toString());
				String att = D3ArmoryControler.getInstance().getSelectedHero(false).getPrimaryStat()+"_PARANGON";
				updateParametre(att, val,lblPrimaryStatValue);
			}
		});
		
		spinner_1.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_1.getValue().toString())/100;
				String att = "Crit_Percent_Bonus_Capped_PARANGON";
				updateParametre(att, val,lblCCCValue);
			}
		});
		
			
		spinner_2.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_2.getValue().toString())/100;
				String att = "Crit_Damage_Percent_PARANGON";
				updateParametre(att, val,lblCCDValue);
			}
		});
		
		
		spinner_3.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_3.getValue().toString())/100;
				String att = "Attacks_Per_Second_Item_Percent_PARANGON";
				updateParametre(att, val,lblASValue);
			}
		});
		
		spinner_4.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_4.getValue().toString());
				String att = "Vitality_PARANGON";
				updateEHP(att, val,lblVitalityValue);
			}
		});
		
		spinner_5.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_5.getValue().toString());
				String att = "Armor_PARANGON";
				updateEHP(att, val,lblArmorValue);
			}
		});
		
		spinner_6.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_6.getValue().toString());
				String att = "Hitpoints_Max_Percent_Bonus_Item_PARANGON";
				updateEHP(att, val/100,lblLifeValue);
			}
		});
		
		spinner_7.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				double val = Double.parseDouble(spinner_7.getValue().toString());
				String att = "Resistance_All_PARANGON";
				updateEHP(att, val,lblResistValue);
			}
		});
		
	}

}
