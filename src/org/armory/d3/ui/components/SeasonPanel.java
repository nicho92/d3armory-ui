package org.armory.d3.ui.components;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.armory.d3.services.D3ArmoryControler;

import com.pihen.d3restapi.beans.Season;
import com.pihen.d3restapi.beans.SeasonalProfile;

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
		
		DecimalFormat df2 = new DecimalFormat( "#,###,###,##0" );
		SeasonalProfile sp = (SeasonalProfile)((JList)evt.getSource()).getSelectedValue();
		editorPane.setText("Parangon Level : " + sp.getParagonLevel()+ "\n" + 
						   "HC Parangon : " + sp.getParagonLevelHardcore() +"\n" + 
						   "Elites Kills : " + df2.format(sp.getKills().getElites()) +"\n"+
						   "Monsters Kills : " + df2.format(sp.getKills().getMonsters())+"\n"+
						   "HC Monsters Kills : " + df2.format(sp.getKills().getHardcoreMonsters())+"\n"
				);
	}
	
	
	
}
