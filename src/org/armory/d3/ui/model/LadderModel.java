package org.armory.d3.ui.model;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import org.armory.d3.services.impl.BNetLadderRetriever;

import com.pihen.d3restapi.beans.Ladder;

public class LadderModel extends DefaultTableModel
{
	BNetLadderRetriever bNetLadder;
	
	
	public Ladder getLadderAt(int i)
	{
		return bNetLadder.getLadders().get(i);
	}
	
	
	public LadderModel(BNetLadderRetriever bnetLadder ) throws IOException {
		this.bNetLadder = bnetLadder;
		
	}
	
	@Override
	public int getColumnCount() {
		return 6;
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column)
		{
		case 0 : return "Rank";
		case 1 : return "Tag";
		case 2 : return "Level";
		case 3 : return "Time";
		case 4 : return "Date";
		case 5 : return "class";
		default : return "";
		}
	}
	
	public int getRowCount() {
		if(bNetLadder==null)
			return 0;
		return bNetLadder.getLadders().size();
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		switch (column)
		{
		case 0 : return bNetLadder.getLadders().get(row).getRank();
		case 1 : return bNetLadder.getLadders().get(row).getProfile();
		case 2 : return bNetLadder.getLadders().get(row).getLevelrift();
		case 3 : return bNetLadder.getLadders().get(row).getTime();
		case 4 : return bNetLadder.getLadders().get(row).getDate();
		case 5 : return bNetLadder.getLadders().get(row).getIcon();
		default : return "";
		
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
	@Override
	public Class<?> getColumnClass(int c) {
		if(c==5)
			return ImageIcon.class;
		if(c==0 || c==2)
			return Integer.class;
		else
			return String.class;
	}
	
}
