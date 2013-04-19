package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
import org.armory.d3.ui.ItemCreatorFrame;

import com.sdfteam.d3armory.service.util.EnumerationStuff;

public class ItemLabel extends JLabel implements MouseListener {
	
    Item item;
    ItemPanelDetails details;
	private boolean disabled;
    private EnumerationStuff gear;
    
    public ItemLabel(ItemPanelDetails pan)
    {
    	this.details=pan;
    	addMouseListener(this);
    }
    
 	public Item getItem() {
		return item;
	}

	public void setItem(Item i,EnumerationStuff e) {
		this.item = i;
		this.gear = e;
	}

	
	public EnumerationStuff getGear() {
		return gear;
	}

	public void setGear(EnumerationStuff gear) {
		this.gear = gear;
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
					if("orange".equals(item.getDisplayColor()))
							return new LineBorder(new Color(223,116,1), 1, true);
					if("yellow".equals(item.getDisplayColor()))
							return new LineBorder(Color.yellow, 1, true);
					if("green".equals(item.getDisplayColor()))
							return new LineBorder(Color.green, 1, true);
					if("blue".equals(item.getDisplayColor()))
							return new LineBorder(new Color(30,144,255), 1, true);
					if("white".equals(item.getDisplayColor()))
						return new LineBorder(Color.white, 1, true);
					if("grey".equals(item.getDisplayColor()))
						return new LineBorder(Color.gray, 1, true);
					
					return new LineBorder(Color.white, 0, true);
				
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
		  
		  
				if (s.equals("orange"))
					return new Color(210,105,30);
				if (s.equals("yellow"))
					return new Color(255,215,0);
				if (s.equals("green"))
					return new Color(107,142,35);
				if (s.equals("blue"))
					return new Color(65,105,225);
				if (s.equals("white"))
					return Color.white;
				if (s.equals("grey"))
					return Color.gray;
			
				return Color.white;
	  
	 	
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
				popupMenu.add(new JMenuItem("Compare " + item.getType().getId() + " with"));
				popupMenu.add(new JSeparator());
			JMenuItem itNewItem = new JMenuItem("New Item");
				itNewItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ItemCreatorFrame f = new ItemCreatorFrame(item.clone(),gear);
						f.getItemPanelDetails().getLblIcon().setIcon(getIcon());
					}
				}
				);
			  popupMenu.add(itNewItem);
			  popupMenu.add(createMenu(listeTag));
			  popupMenu.add(createMenu(D3ArmoryControler.getInstance().getListeFileItem()));
			  popupMenu.show(e.getComponent(),e.getX(), e.getY());
		}
		
	}
	private JMenu createMenu(File[] listeFileItem) {
		 JMenu m = new JMenu("Saved Item");
		  for(final File f:listeFileItem)
		  {	 
			  JMenuItem a = new JMenuItem(f.getName().substring(0, f.getName().lastIndexOf(".")));
			    m.add(a);
		  		a.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Item i = D3ArmoryControler.getInstance().loadItem(f);
					System.out.println(f);
					ItemCreatorFrame f = new ItemCreatorFrame(i,gear);
							
				}
			});
		  }
		  return m;
	}

	public JMenu createMenu(List<String> listeTag){
		  JMenu m = new JMenu("Profile");
		  for(String tag:listeTag)
		  {
			  m.add(new JMenuItem(tag));
			 
		  }
		  
		  return m;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	 	
}
