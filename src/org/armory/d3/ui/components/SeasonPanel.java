package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Season;
import com.pihen.d3restapi.beans.SeasonalProfile;
import javax.swing.JEditorPane;
import java.awt.Color;

public class SeasonPanel extends JPanel {
	private JList<SeasonalProfile> list;
	DefaultListModel<SeasonalProfile> model;
	private JEditorPane editorPane;
	
	
	public void init(Season season) {
		
		model= new DefaultListModel<SeasonalProfile>();
		for(int i=0;i<=D3ArmoryControler.getInstance().getSeason();i++)
		{
			try {
				SeasonalProfile sp = season.getSeason(i);
				if(sp!=null)
				{
					model.addElement(sp);
				}
				
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		list.setModel(model);
		
	}

	public SeasonPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.WEST);
		list = new JList();
		scrollPane.setViewportView(list);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		
		add(editorPane, BorderLayout.CENTER);
		
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				showInfo(evt);
			}
		});
	
	}

	protected void showInfo(MouseEvent evt) {
		SeasonalProfile sp = (SeasonalProfile)((JList)evt.getSource()).getSelectedValue();
		editorPane.setText("Parangon Level : " + sp.getParagonLevel()+ "\n" + 
						   "HC Parangon : " + sp.getParagonLevelHardcore() +"\n" + 
						   "Elites Kills : " + sp.getKills().getElites() +"\n"+
						   "Monsters Kills : " + sp.getKills().getMonsters()+"\n"+
						   "HC Monsters Kills : " + sp.getKills().getHardcoreMonsters()+"\n"
				);
	}
	
	
	
}
