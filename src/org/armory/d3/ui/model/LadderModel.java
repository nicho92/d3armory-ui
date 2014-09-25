package org.armory.d3.ui.model;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import org.armory.d3.services.BNetLadderRetriever;

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
		return 4;
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column)
		{
		case 0 : return "Rank";
		case 1 : return "Tag";
		case 2 : return "Level";
		case 4 : return "Time";
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
		case 2 : return bNetLadder.getLadders().get(row).getLevelRift();
		case 3 : return bNetLadder.getLadders().get(row).getTime();
		default : return "";
		
		}
	}
}
