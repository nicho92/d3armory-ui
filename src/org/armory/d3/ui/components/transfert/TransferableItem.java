package org.armory.d3.ui.components.transfert;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import org.armory.d3.ui.components.ItemLabel;

import com.pihen.d3restapi.beans.Item;

public class TransferableItem implements Transferable {
	 
    protected static DataFlavor itemFlavor = new DataFlavor(Item.class, "A item");
    protected static DataFlavor itemLabelFlavor = new DataFlavor(ItemLabel.class, "Label item");
    
    
    protected static DataFlavor[] supportedFlavors = {
    	itemFlavor,
    	itemLabelFlavor,
        DataFlavor.stringFlavor,
    };

    ItemLabel itemLab;
    
    public TransferableItem(ItemLabel i) { this.itemLab = i; }

    public DataFlavor[] getTransferDataFlavors() { return supportedFlavors; }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
    if (flavor.equals(itemFlavor) || 
        flavor.equals(DataFlavor.stringFlavor)) return true;
    return false;
  }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
    {
      if (flavor.equals(itemFlavor))
          return itemLab.getItem();
      else if (flavor.equals(DataFlavor.stringFlavor)) 
          return itemLab.toString();
      else if(flavor.equals(itemLabelFlavor))
    	  return itemLab;
      else
          throw new UnsupportedFlavorException(flavor);
    }
 }