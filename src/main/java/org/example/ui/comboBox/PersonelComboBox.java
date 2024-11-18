package org.example.ui.comboBox;

import com.vaadin.ui.ComboBox;
import org.example.core.entity.Personel;
import org.example.core.service.PersonelService;

import java.util.List;

public class PersonelComboBox extends ComboBox {

    PersonelService personelService = new PersonelService();

    public PersonelComboBox() {
        fillCombobox();
    }

    private void fillCombobox() {
        this.removeAllItems();
        for (Personel personel : personelService.findAll()) {
            this.addItem(personel);
//            setItemCaption(personel, personel.getName());
        }
    }

    @Override
    public Class<Personel> getType() {
        return Personel.class;
    }
}
