package org.armory.d3.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Item;

public class PaintedLabel extends JLabel {

	
    Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item i) {
		this.item = i;
	}

	
	public Icon getIcon() {
		setHorizontalAlignment(JLabel.CENTER);
		if(item != null)
		try {
			URL url = new URL("http://media.blizzard.com/d3/icons/items/large/"+item.getIcon()+".png");
			return new ImageIcon(url);
		} catch (Exception e1) {
			e1.printStackTrace();
			return new ImageIcon();
		}
		return super.getIcon();
	}

	public Border getBorder() {
		if(item != null)
		{
			switch (item.getDisplayColor()) {
			case "orange":return new LineBorder(Color.orange, 1, true);
			case "yellow":return new LineBorder(Color.yellow, 1, true);
			case "green":return new LineBorder(Color.green, 1, true);
			case "blue":return new LineBorder(new Color(30,144,255), 1, true);
			case "white":return new LineBorder(Color.white, 1, true);
			case "grey":return new LineBorder(Color.gray, 1, true);
			default:return new LineBorder(Color.white, 0, true);
			}
		}
		return super.getBorder();
	}
    
	 public void paint( Graphics g )
	  {
		 if(item!=null){
			    int width = getWidth();
			    int height = getHeight();
		
			    Color end=toColor(item.getDisplayColor()); 
			    Color start=Color.BLACK;
			    GradientPaint paint = new GradientPaint( 0, 0, start, width, height, end, true );
			    Graphics2D g2d = ( Graphics2D )g;
			    Paint oldPaint = g2d.getPaint();
			    	g2d.setPaint( paint );
			    	g2d.fillRect( 0, 0, width, height );
			    	g2d.setPaint( oldPaint );
	  	}
	    super.paint( g );
	  }
    
	  public Color toColor(String s)
	  {
			switch (s) {
			case "orange":return new Color(210,105,30);
			case "yellow":return new Color(255,215,0);
			case "green":return new Color(107,142,35);
			case "blue":return new Color(65,105,225);
			case "white":return Color.white;
			case "grey":return Color.gray;
			default:return Color.white;
	  }
	 	
}
	 	
}
