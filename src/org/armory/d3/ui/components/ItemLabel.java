package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Item;
import org.armory.d3.services.D3ArmoryControler;

public class ItemLabel extends JLabel implements MouseListener {
	
    Item item;
    ItemPanelDetails details;
	private boolean disabled;
    
    
    public ItemLabel(ItemPanelDetails pan)
    {
    	this.details=pan;
    	addMouseListener(this);
    }
    
 	public Item getItem() {
		return item;
	}

	public void setItem(Item i) {
		this.item = i;
	}

	
	public Image getImage(){
		if(item != null)
			try {
				URL url = new URL("http://media.blizzard.com/d3/icons/items/large/"+item.getIcon()+".png");
				return new ImageIcon(url).getImage();
			} catch (Exception e1) {
				e1.printStackTrace();
				return new ImageIcon().getImage();
			}
			return null;
	}
	public Icon getIcon(boolean off) {
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);

		if(item != null)
		try {
			URL url = new URL("http://media.blizzard.com/d3/icons/items/large/"+item.getIcon()+".png");
			ImageIcon i = new ImageIcon(url);
			if(off==false)
				return i;
			else
				return new ImageIcon(GrayFilter.createDisabledImage((i).getImage()));
			
			
		} catch (Exception e1) {
			return new ImageIcon();
		}
		return super.getIcon();
	}
	
	
	public Icon getIcon() {
		return getIcon(disabled);
	}
	
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Border getBorder() {
		
		try{
			if(item != null)
			{
				switch (item.getDisplayColor()) {
					case "orange":return new LineBorder(new Color(223,116,1), 1, true);
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
		catch(Exception e)
		{
			return new LineBorder(Color.white,0,true);
		}
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
    
	  public static Color toColor(String s)
	  {
		  if(s==null)
			  return Color.white;
		  
		  
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

	@Override
	public void mouseClicked(MouseEvent e) {
		details.showItem(item);
		}

	@Override
	public void mouseEntered(MouseEvent e) {
		details.showItem(item);
		details.getLblIcon().setIcon(this.getIcon());
		details.repaint();
		
		if(item!=null)
			D3ArmoryControler.getInstance().setSelectedItem(item);
		 
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//details.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e))
		{
			List<String> listeTag= D3ArmoryControler.getInstance().getListTags();
			JPopupMenu popupMenu = new JPopupMenu();
				popupMenu.add(new JMenuItem("COMPARE"));
				popupMenu.add(new JSeparator());
			JMenuItem itNewItem = new JMenuItem("New Item");
			  popupMenu.add(itNewItem);
			  popupMenu.add(createMenu(listeTag));
			  popupMenu.show(e.getComponent(),e.getX(), e.getY());
		}
		
	}
	public JMenu createMenu(List<String> listeTag){
		  JMenu m = new JMenu("Profile");
		  for(String tag:listeTag)
		  	  m.add(new JMenuItem(tag));
		  
		  return m;
		  }

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	 	
}
