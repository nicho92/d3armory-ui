package org.armory.d3.ui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.armory.d3.services.D3ArmoryControler;
import org.jdesktop.application.Application;


public class LocaleManagerFrame extends javax.swing.JFrame {
	private JLabel lblLibelle;
	private JLabel lblMessage;
	private JButton btnValider;
	private JComboBox cboListeLocal;

	/**
	* Auto-generated main method to display this JFrame
	*/
		
	public LocaleManagerFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			FlowLayout thisLayout = new FlowLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				lblLibelle = new JLabel();
				getContentPane().add(lblLibelle);
				lblLibelle.setName("lblLibelle");
			}
			{
				//TODO code pour la gestion locale

				String[] locales = {"EN_us","FR_fr","ES_es","DE_de","IT_it"};
				//ComboBoxModel cboListeLocalModel = new DefaultComboBoxModel(SimpleDateFormat.getAvailableLocales());
				ComboBoxModel cboListeLocalModel = new DefaultComboBoxModel(locales);
								
				cboListeLocal = new JComboBox();
				getContentPane().add(cboListeLocal);
				cboListeLocal.setModel(cboListeLocalModel);
				cboListeLocal.setPreferredSize(new java.awt.Dimension(269, 23));
			}
			{
				btnValider = new JButton();
				getContentPane().add(btnValider);
				btnValider.setName("btnValider");
				btnValider.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnValiderActionPerformed(evt);
					}
				});
			}
			{
				lblMessage = new JLabel();
				getContentPane().add(lblMessage);
				lblMessage.setName("lblMessage");
			}
			setLocationRelativeTo(null);
			pack();
			this.setSize(432, 112);
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	private void btnValiderActionPerformed(ActionEvent evt) {
		D3ArmoryControler.getInstance().setLocal(cboListeLocal.getSelectedItem().toString());
		this.dispose();
	}

}
