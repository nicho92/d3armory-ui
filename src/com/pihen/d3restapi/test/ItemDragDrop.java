package com.pihen.d3restapi.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.components.ItemLabel;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.service.remote.exception.D3ServerCommunicationException;


public class ItemDragDrop extends JFrame implements DragGestureListener {

	
	 public static void main(String[] args) throws D3ServerCommunicationException {
	        new ItemDragDrop();
	    }
	 
	 
    JPanel panel;
    
    public ItemDragDrop() throws D3ServerCommunicationException {
        setLayout(new BorderLayout());
        panel  = new JPanel(new GridLayout(5,5));
        
        for(Item i : D3ArmoryControler.getInstance().getRecorder().listSavedItems())
        {
        	ItemLabel left = new ItemLabel();
            left.setItem(i);
            
            left.setOverDetailed(false);
            left.setPreferredSize(new Dimension(100, 100));
            
            panel.add(left);
            
            DragSource ds = new DragSource();
            		   ds.createDefaultDragGestureRecognizer(left,DnDConstants.ACTION_COPY, this);
        }
        
        
        ItemLabel right = new ItemLabel();
    			  right.setOverDetailed(false);
    			  right.setPreferredSize(new Dimension(100, 100));
    			  right.setBorder(new LineBorder(Color.black));
    			  new MyDropTargetListener(right);

   

        add(right,BorderLayout.EAST);
        add(panel,BorderLayout.CENTER);

        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void dragGestureRecognized(DragGestureEvent event) 
    {
        Cursor cursor = null;
        ItemLabel itLab = (ItemLabel) event.getComponent();

        Item i = itLab.getItem();

        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }

        event.startDrag(cursor, new TransferableItem(i));
    }

    
    
    class MyDropTargetListener extends DropTargetAdapter {

        private DropTarget dropTarget;
        private ItemLabel itlab;

     public MyDropTargetListener(ItemLabel il) {
        this.itlab = il;
        dropTarget = new DropTarget(il, DnDConstants.ACTION_COPY,this, true, null);
      }


      public void drop(DropTargetDropEvent event) {
    	  
        try {

          Transferable tr = event.getTransferable();
          Item i = (Item) tr.getTransferData(TransferableItem.itemFlavor);
          
          
          
          
          
          if (event.isDataFlavorSupported(TransferableItem.itemFlavor)) {

              event.acceptDrop(DnDConstants.ACTION_COPY);
              itlab.setItem(i);
              itlab.repaint();
              event.dropComplete(true);
              return;
            }
          event.rejectDrop();
        } catch (Exception e) {
          e.printStackTrace();
          event.rejectDrop();
        }
      }
    }

   
}


class TransferableItem implements Transferable {
 
    protected static DataFlavor itemFlavor =
        new DataFlavor(Item.class, "A item Object");

    protected static DataFlavor[] supportedFlavors = {
    	itemFlavor,
        DataFlavor.stringFlavor,
    };

    Item item;

    public TransferableItem(Item i) { this.item = i; }

    public DataFlavor[] getTransferDataFlavors() { return supportedFlavors; }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
    if (flavor.equals(itemFlavor) || 
        flavor.equals(DataFlavor.stringFlavor)) return true;
    return false;
  }


   public Object getTransferData(DataFlavor flavor) 
        throws UnsupportedFlavorException
   {
     if (flavor.equals(itemFlavor))
         return item;
     else if (flavor.equals(DataFlavor.stringFlavor)) 
         return item.toString();
     else 
         throw new UnsupportedFlavorException(flavor);
   }
}