package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.components.ListeTagTree;
import org.jdesktop.application.Application;


public class TagsManagerFrame extends javax.swing.JFrame {
	private JPanel panelContenu;
	private JButton btnCancel;
	private JComboBox cboListServer;
	private JLabel lblServeur;
	private JTextField txtTag;
	private JLabel lblbattleTag;
	private JButton btnSave;
	private JPanel panneauBas;
	private ListeTagTree tagsList;
	
	public TagsManagerFrame(ListeTagTree listeTagTree) {
		super();
		initGUI();
		tagsList = listeTagTree;
	}
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				panelContenu = new JPanel();
				GridLayout panelContenuLayout = new GridLayout(2, 2);
				panelContenuLayout.setVgap(5);
				panelContenuLayout.setColumns(2);
				panelContenuLayout.setRows(2);
				panelContenu.setLayout(panelContenuLayout);
				getContentPane().add(panelContenu, BorderLayout.CENTER);
				panelContenu.setPreferredSize(new java.awt.Dimension(448, 147));
				{
					lblbattleTag = new JLabel();
					panelContenu.add(lblbattleTag);
					lblbattleTag.setName("lblbattleTag");
				}
				{
					txtTag = new JTextField();
					panelContenu.add(txtTag);
					txtTag.setPreferredSize(new java.awt.Dimension(221, 48));
				}
				{
					lblServeur = new JLabel();
					panelContenu.add(lblServeur);
					lblServeur.setName("lblServeur");
				}
				{
					ComboBoxModel cboListServerModel = 
							new DefaultComboBoxModel(
									new String[] { "us", "eu","ch","kr","tw" });
					cboListServer = new JComboBox();
					panelContenu.add(cboListServer);
					cboListServer.setModel(cboListServerModel);
				}
			}
			{
				panneauBas = new JPanel();
				getContentPane().add(panneauBas, BorderLayout.SOUTH);
				{
					btnSave = new JButton();
					panneauBas.add(btnSave);
					btnSave.setName("btnSave");
					btnSave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnSaveActionPerformed(evt);
						}
					});
				}
				{
					btnCancel = new JButton();
					panneauBas.add(btnCancel);
					btnCancel.setName("btnCancel");
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnCancelActionPerformed(evt);
						}
					});
				}
			}
			setLocationRelativeTo(null);
			pack();
			this.setSize(354, 134);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnSaveActionPerformed(ActionEvent evt)
	{
		D3ArmoryControler.getInstance().addTags(txtTag.getText(), cboListServer.getSelectedItem().toString());
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)((DefaultTreeModel)tagsList.getModel()).getRoot();
		for(int i=0;i<root.getChildCount();i++)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)((DefaultTreeModel)tagsList.getModel()).getChild(root, i);
			if(node.toString().equalsIgnoreCase(cboListServer.getSelectedItem().toString()))
				node.add(new DefaultMutableTreeNode(txtTag.getText()+"#"+cboListServer.getSelectedItem().toString()));
		}
		((DefaultTreeModel)tagsList.getModel()).reload();
		this.dispose();
	}
	
	
	private void btnCancelActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
