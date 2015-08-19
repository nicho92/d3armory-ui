package org.armory.d3.ui.components.transfert;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import com.pihen.d3restapi.beans.Item;

public class TransferableItem implements Transferable {
	 
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

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
    {
      if (flavor.equals(itemFlavor))
          return item;
      else if (flavor.equals(DataFlavor.stringFlavor)) 
          return item.toString();
      else 
          throw new UnsupportedFlavorException(flavor);
    }
 }