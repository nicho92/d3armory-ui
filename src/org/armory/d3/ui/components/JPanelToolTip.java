package org.armory.d3.ui.components;

/*
 * JScrollableToolTip.java
 */
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JToolTip;
import javax.swing.ToolTipManager;

import org.armory.d3.ui.components.panels.RunePanel;
import org.armory.d3.ui.components.panels.SkillPanel;

 
public class JPanelToolTip extends JToolTip {
 
    private SkillPanel tipArea;
    private SkillLabel comp;
 
    public JPanelToolTip(final int width, final int height, final SkillLabel comp) {
        this.comp = comp;
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        tipArea = new SkillPanel(comp.getSkill());
        tipArea.setBackground(Color.BLACK);
        add(tipArea,BorderLayout.CENTER);
        ToolTipManager.sharedInstance().setDismissDelay(600000);
        if(comp.getSkill().getRune()!=null)
        	add(new RunePanel(comp.getSkill()),BorderLayout.SOUTH);


    }
 
  
    public void setTipText(final String tipText) {
//        String oldValue = this.tipArea.getText();
//        tipArea.setText(tipText);
//        tipArea.setCaretPosition(0);
//        firePropertyChange("tiptext", oldValue, tipText);
    }
 
    @Override
    public String getTipText() {
        return tipArea == null ? "" : tipArea.toString();
    }
 
   
   
}