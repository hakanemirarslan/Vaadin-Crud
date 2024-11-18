package org.example.ui.comboBox;

import com.vaadin.ui.ComboBox;
import org.example.core.entity.Birim;
import org.example.core.service.BirimService;

public class BirimComboBox extends ComboBox {
    BirimService birimService =new BirimService();

    public void fillComboBox(){
        this.removeAllItems();
        for (Birim birim: birimService.findAll() ) {
            this.addItem(birim);
            setItemCaption(birim, birim.getName());
        }
    }
}
