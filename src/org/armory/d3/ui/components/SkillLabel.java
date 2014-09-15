package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.SwingMainFrame;
import org.armory.d3.ui.model.CalculatorModel;
import org.armory.d3.ui.model.EHPCalculatorModel;

import com.pihen.d3restapi.beans.SkillRune;
import com.pihen.d3restapi.service.util.BuffCalculator;
import com.pihen.d3restapi.service.util.SkillsFactory;

public class SkillLabel extends JLabel implements MouseListener {

	private SkillRune skill;
	private boolean enabled=true;
	private String size;
	private JPopupMenu popupMenu;
	public static final String SMALL = "21";
	public static final String LARGE = "64";
	
	public SkillRune getSkill() {
		return skill;
	}

	public SkillLabel(boolean enabled)
	{
		super();
		size="64";
		this.enabled=enabled;
		addMouseListener(this);
	}
	
	public SkillLabel(SkillRune s)
	{
		this.skill=s;
		size=LARGE;
		addMouseListener(this);
	}
	
	public Border getBorder() {
		if(skill==null)
			return super.getBorder();
			
		if(skill.getSkill()==null)
			return super.getBorder();
		
		if(enabled)
			return new LineBorder(Color.red);
		else
			return super.getBorder(); 
	}

	public Icon getIcon(){
		if(skill != null)
			try {
				URL url = new URL("http://media.blizzard.com/d3/icons/skills/"+size+"/"+skill.getSkill().getIcon()+".png");
				return new ImageIcon(url);
			} catch (Exception e1) {
				//e1.printStackTrace();
				return new ImageIcon();
			}
			return super.getIcon();
	}


	public void setSkillRune(SkillRune skillRune) {
		this.skill=skillRune;
		if(skill.getSkill()!=null)
		{
			this.setToolTipText(" ");
		}
	}

    public JToolTip createToolTip() {
		
    	int height=200;
    	int width=350;
    	
    	if(skill.getRune()!=null)
    	{
    		height=300;
    	}
    	
    	
    	JPanelToolTip t = new JPanelToolTip(width,height,this);
        			t.setComponent(this);
        return t;
    }


	public void initRightClick(final int position) {
		JMenu mnu = new JMenu("Change skill with");
		popupMenu = new JPopupMenu();
		popupMenu.add(mnu);
		
		List<SkillRune> list = SkillsFactory.getSkillsFor(D3ArmoryControler.getInstance().getSelectedHero(false).getClazz());
		
		for(final SkillRune r : list)
		{
			
			JMenuItem jmi = new JMenuItem(r.getSkill().getName());
			SkillLabel l = new SkillLabel(r);
			l.setSize(SkillLabel.SMALL);
			jmi.setIcon(l.getIcon());
			jmi.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					D3ArmoryControler.getInstance().getCalculator().removeBonus(BuffCalculator.getBuff(skill, D3ArmoryControler.getInstance().getCalculator()).keySet());
					setSkillRune(r);
					D3ArmoryControler.getInstance().getSelectedHero(false).getSkills().getPassive().set(position, r);
					
					repaint();
					D3ArmoryControler.getInstance().getCalculator().addBonus(BuffCalculator.getBuff(skill, D3ArmoryControler.getInstance().getCalculator()));
					D3ArmoryControler.getInstance().getCalculator().calculate();
					((SwingMainFrame)getTopLevelAncestor()).getTableauDetailsModel().fireTableDataChanged();
					((SwingMainFrame)getTopLevelAncestor()).getMnuSaveBuild().setEnabled(true);
					((CalculatorModel)((SwingMainFrame)getTopLevelAncestor()).getTableauDetailsCalc().getModel()).fireTableDataChanged();
					((EHPCalculatorModel)((SwingMainFrame)getTopLevelAncestor()).getPanneauEHP().getTable().getModel()).fireTableDataChanged();
				}
			});
			
			 mnu.add(jmi);
		}
		
	}

	public void mouseClicked(MouseEvent me) {
		
		
		if(SwingUtilities.isRightMouseButton(me))
		{
			popupMenu.show(me.getComponent(),me.getX(), me.getY());
			return;
		}
		//else left click button

		if(enabled)
			enabled=false;
		else
			enabled=true;
		
		if(enabled)
			D3ArmoryControler.getInstance().getCalculator().addBonus(BuffCalculator.getBuff(skill, D3ArmoryControler.getInstance().getCalculator()));
		else
			D3ArmoryControler.getInstance().getCalculator().removeBonus(BuffCalculator.getBuff(skill, D3ArmoryControler.getInstance().getCalculator()).keySet());
		
		D3ArmoryControler.getInstance().getCalculator().calculate();
		((SwingMainFrame)this.getTopLevelAncestor()).getTableauDetailsModel().fireTableDataChanged();
		((EHPCalculatorModel)((SwingMainFrame)this.getTopLevelAncestor()).getPanneauEHP().getTable().getModel()).fireTableDataChanged();
		((CalculatorModel)(((SwingMainFrame)this.getTopLevelAncestor()).getTableauDetailsCalc().getModel())).fireTableDataChanged();
		
		repaint();
	}



	public void mouseEntered(MouseEvent arg0) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}



	public void mouseExited(MouseEvent arg0) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
	}



	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSize(String size) {
		this.size = size;
	}

	
	
	
}
