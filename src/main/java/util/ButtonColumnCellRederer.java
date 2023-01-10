/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;

/**
 *
 * @author HP
 */
public class ButtonColumnCellRederer extends DefaultTableCellRenderer {
    
    private String buttonType;

    public ButtonColumnCellRederer(String buttonType) {
        this.buttonType = buttonType;
    }
    
    
    
    

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    
    
    
      @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int col){
    
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            
            label.setHorizontalAlignment(JLabel.CENTER); 
            
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" +buttonType+ ".png")));
            
    return label;
    
}
}