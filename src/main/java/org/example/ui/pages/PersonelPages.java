package org.example.ui.pages;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.Not;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import org.example.core.entity.Is;
import org.example.core.entity.Personel;
import org.example.core.entity.dto.PersonelDto;
import org.example.core.service.PersonelService;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class PersonelPages extends VerticalLayout {
    private TextField fieldIsim;
    private TextField fieldKullaniciAdi;
    private TextField fieldSifre;
    private TextField fieldId;
    private Button kaydetButton;
    private Button aramaButton;
    private Button silButton;
    private Button guncelleButton;
    private Table tableField;
    private IndexedContainer container;
    private Personel selectedPersonel;
    private PersonelService personelService = new PersonelService();

    public PersonelPages() {
        createContainer();
        init();
        setSizeFull();
        setSpacing(true);
        setMargin(true);
        addListeners();
        fillTable();
    }

    private IndexedContainer createContainer() {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("ID", Long.class, null);
        container.addContainerProperty("name", String.class, null);
        container.addContainerProperty("kullaniciAdi", String.class, null);
        return container;
    }

    private void addListeners() {
        kaydetButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String isim = fieldIsim.getValue();
                String kullaniciAdi = fieldKullaniciAdi.getValue();
                String sifre = fieldSifre.getValue();

                if (isim.isEmpty() || kullaniciAdi.isEmpty() || sifre.isEmpty()) {
                    Notification.show("Lütfen Tüm Alanları Doldurunuz", Notification.Type.WARNING_MESSAGE);
                } else {
                    Personel personel = new Personel();
                    personel.setName(isim);
                    personel.setKullaniciAdi(kullaniciAdi);
                    personel.setPassword(sifre);
                    personelService.save(personel);
                    tableField.addItem(new Object[]{personel.getId(), isim, kullaniciAdi, sifre}, personel.getId());
                    clearFields();
                    fillTable();
                    Notification.show("Kayıt Başarıyla Eklendi", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        });

        silButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (selectedPersonel != null) {
                    String deletedPersonel = personelService.deleteById(selectedPersonel.getId());
                    Notification.show(deletedPersonel, Notification.Type.TRAY_NOTIFICATION);
                    fillTable();
                    clearFields();
                } else {
                    Notification.show("Lütfen silinecek bir personeli seçin.", Notification.Type.WARNING_MESSAGE);
                }
            }
        });

        guncelleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (selectedPersonel != null){
                    String updatedIsim = fieldIsim.getValue();
                    String updatedKullaniciAdi = fieldKullaniciAdi.getValue();
                    String updatedSifre = fieldSifre.getValue();
                    if ( updatedIsim.isEmpty()||updatedSifre.isEmpty() || updatedKullaniciAdi.isEmpty()){
                        Notification.show("Tüm alanları doldurunuz.", Notification.Type.WARNING_MESSAGE);
                    }else {
                        selectedPersonel.setName(updatedIsim);
                        selectedPersonel.setKullaniciAdi(updatedKullaniciAdi);
                        selectedPersonel.setPassword(updatedSifre);
                        personelService.update(selectedPersonel);
                        fillTable();
                        clearFields();
                        Notification.show("Güncelleme yapıldı.", Notification.Type.TRAY_NOTIFICATION);
                    }
                }else {
                    Notification.show("Güncelleme yapılamadı", Notification.Type.WARNING_MESSAGE);
                }
            }
        });
        tableField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Personel selected = (Personel) event.getProperty().getValue();

                if (selected != null) {
                    selectedPersonel = personelService.findById(selected.getId());

                    if (selectedPersonel != null) {
                        fieldIsim.setValue(selectedPersonel.getName());
                        fieldKullaniciAdi.setValue(selectedPersonel.getKullaniciAdi());
                        fieldSifre.setValue(selectedPersonel.getPassword());
                        setSelectedPersonel(selected);
                    }
                }
            }
        });

        aramaButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                PersonelDto personelDto = new PersonelDto();
                personelDto.setName(fieldIsim.getValue());
                personelDto.setKullaniciAdi(fieldKullaniciAdi.getValue());

                try {
                    List<Personel> personelList = personelService.findAllByDto(personelDto);
                    container.removeAllItems();
                    if (!personelList.isEmpty()) {
                        for (Personel personel : personelList) {
                            Item item = container.addItem(personel.getId());
                            item.getItemProperty("ID").setValue(personel.getId());
                            item.getItemProperty("name").setValue(personel.getName());
                            item.getItemProperty("kullaniciAdi").setValue(personel.getKullaniciAdi());
                        }
                        Notification.show("Personel bulundu!", Notification.Type.TRAY_NOTIFICATION);
                    } else {
                        Notification.show("Personel bulunamadı!", Notification.Type.WARNING_MESSAGE);
                    }
                    fillTable();
                    clearFields();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        });

    }

    private void fillTable() {
        List<Personel> personelList = personelService.findAll();
        container.removeAllItems();

        for (Personel personel : personelList) {
            Item item = container.addItem(personel);
            item.getItemProperty("ID").setValue(personel.getId());
            item.getItemProperty("name").setValue(personel.getName());
            item.getItemProperty("kullaniciAdi").setValue(personel.getKullaniciAdi());

        }
    }


    private void init() {
        HorizontalLayout panelLayout = new HorizontalLayout();

        tableField = new Table("Personel Listesi");
        tableField.setSelectable(true);
        tableField.setHeight(150, Unit.PIXELS);
        tableField.setWidth(50, Unit.PERCENTAGE);

        container = createContainer();
        tableField.setColumnHeader("name", "Isim");
        tableField.setColumnHeader("kullaniciAdi", "Kullanıcı Adı");
        tableField.setContainerDataSource(container);

        panelLayout.setHeight(100, Unit.PIXELS);
        panelLayout.setWidth(100, Unit.PERCENTAGE);

        panelLayout.addComponent(tableField);
        addComponent(panelLayout);

        HorizontalLayout fieldsLayout = new HorizontalLayout();
        fieldsLayout.setSpacing(true);

        fieldId = new TextField("");
        fieldId.setVisible(false);
        fieldsLayout.addComponent(fieldId);

        fieldIsim = new TextField();
        fieldIsim.setCaption("Isim");
        fieldsLayout.addComponent(fieldIsim);

        fieldKullaniciAdi = new TextField();
        fieldKullaniciAdi.setCaption("Kullanıcı Adı");
        fieldsLayout.addComponent(fieldKullaniciAdi);

        fieldSifre = new TextField();
        fieldSifre.setCaption("Sifre");
        fieldsLayout.addComponent(fieldSifre);

        addComponent(fieldsLayout);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        fieldsLayout.setSpacing(true);

        kaydetButton = new Button();
        kaydetButton.setCaption("Kaydet");
        kaydetButton.setIcon(FontAwesome.SAVE);
        buttonLayout.addComponent(kaydetButton);

        aramaButton = new Button();
        aramaButton.setCaption("Ara");
        aramaButton.setIcon(FontAwesome.SEARCH);
        buttonLayout.addComponent(aramaButton);

        silButton = new Button();
        silButton.setCaption("Sil");
        silButton.setIcon(FontAwesome.TRASH);
        buttonLayout.addComponent(silButton);

        guncelleButton = new Button();
        guncelleButton.setCaption("Güncelle");
        guncelleButton.setIcon(FontAwesome.EDIT);
        buttonLayout.addComponent(guncelleButton);

        addComponent(buttonLayout);
    }

    private void setSelectedPersonel(Personel personel){
        this.selectedPersonel = personel;
    }

    private void clearFields(){
        fieldIsim.clear();
        fieldKullaniciAdi.clear();
        fieldSifre.clear();
        selectedPersonel = null;
    }
}