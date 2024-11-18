package org.example.ui.pages;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.example.core.entity.Birim;
import org.example.core.entity.Is;
import org.example.core.entity.EnumOnayDurumu;
import org.example.core.entity.Personel;
import org.example.core.entity.dto.IsDto;
import org.example.core.service.BirimService;
import org.example.core.service.IsService;
import org.example.core.service.PersonelService;
import org.example.ui.comboBox.BirimComboBox;
import org.example.ui.comboBox.PersonelComboBox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IsPages extends VerticalLayout {
    private TextField fieldId;
    private TextField fieldBaslik;
    private TextField fieldAciklama;
    private PersonelComboBox comboCozenKisi;
    private BirimComboBox birimComboBox;
    private DateField fieldTarih;
    private ComboBox comboOnayDurumu;
    private TextField fieldTamamlananSure;
    private Button kaydetButton;
    private Button aramaButton;
    private Button silButton;
    private Button guncelleButton;
    private Is selectedIs;
    private Table tableField;
    private IndexedContainer container;
    private BirimService birimService= new BirimService();
    private IsService isService = new IsService();
    private PersonelService personelService = new PersonelService();
    private PersonelComboBox personelComboBox = new PersonelComboBox();

    public IsPages() {
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
        container.addContainerProperty("aciklama", String.class, null);
        container.addContainerProperty("baslik", String.class, null);
        container.addContainerProperty("cozenKisi", Personel.class, null);
        container.addContainerProperty("birim", Birim.class,null);
        container.addContainerProperty("tarih", Date.class, null);
        container.addContainerProperty("onayDurumu", EnumOnayDurumu.class, null);
        container.addContainerProperty("tamamlananSure", String.class, null);
        return container;
    }

    private void addListeners() {
        kaydetButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String baslik = fieldBaslik.getValue();
                String aciklama = fieldAciklama.getValue();
                Date tarih = fieldTarih.getValue();
                EnumOnayDurumu onayDurumu = (EnumOnayDurumu) comboOnayDurumu.getValue();
                String tamamlananSure = fieldTamamlananSure.getValue();
                Personel cozenKisi = (Personel) comboCozenKisi.getValue();
                Birim birim = (Birim) birimComboBox.getValue();

                if (baslik.isEmpty() || aciklama.isEmpty() || tarih == null || onayDurumu == null || tamamlananSure.isEmpty()) {
                    Notification.show("Lütfen tüm alanları doldurun", Notification.Type.WARNING_MESSAGE);
                } else {
                    Is yeniIs = new Is();
                    yeniIs.setBaslik(baslik);
                    yeniIs.setAciklama(aciklama);
                    yeniIs.setCozenKisi(cozenKisi);
                    yeniIs.setBirim(birim);
                    yeniIs.setTarih(tarih);
                    yeniIs.setOnayDurumu(onayDurumu);
                    yeniIs.setTamamlananSure(tamamlananSure);
                    isService.save(yeniIs);
                    tableField.addItem(new Object[]{yeniIs.getId(),aciklama, baslik,cozenKisi,birim, tarih, onayDurumu, tamamlananSure}, yeniIs.getId());
                    clearFields();
                    fillTable();
                    Notification.show("Kayıt başarıyla eklendi", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        });

        silButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (selectedIs != null) {
                    String deletedIs = isService.deleteById(selectedIs.getId());
                    Notification.show(deletedIs, Notification.Type.TRAY_NOTIFICATION);
                    clearFields();
                    fillTable();
                } else {
                    Notification.show("Lütfen silinecek işi seçiniz!");
                }
            }
        });

        guncelleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (selectedIs != null) {
                    String updatedAciklama = fieldAciklama.getValue();
                    String updatedBaslik = fieldBaslik.getValue();
                    Personel updatedCozenKisi = (Personel) comboCozenKisi.getValue();
                    Birim updatedBirim = (Birim) birimComboBox.getValue();
                    Date updatedTarih = fieldTarih.getValue();
                    EnumOnayDurumu updatedOnay = (EnumOnayDurumu) comboOnayDurumu.getValue();
                    String updatedTamamlananSure = fieldTamamlananSure.getValue();
                    if (updatedAciklama.isEmpty() || updatedBaslik.isEmpty() || updatedOnay == null || updatedTamamlananSure.isEmpty()) {
                        Notification.show("Alanları doldurunuz.", Notification.Type.WARNING_MESSAGE);
                    } else {
                        selectedIs.setAciklama(updatedAciklama);
                        selectedIs.setBaslik(updatedBaslik);
                        selectedIs.setCozenKisi(updatedCozenKisi);
                        selectedIs.setBirim(updatedBirim);
                        selectedIs.setTarih(updatedTarih);
                        selectedIs.setOnayDurumu(updatedOnay);
                        selectedIs.setTamamlananSure(updatedTamamlananSure);
                        isService.update(selectedIs);
                        fillTable();
                        clearFields();
                        Notification.show("Güncelleme yapıldı.", Notification.Type.TRAY_NOTIFICATION);
                    }
                } else {
                    Notification.show("Güncelleme yapılamadı.", Notification.Type.WARNING_MESSAGE);
                }
            }
        });
        tableField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Is selected = (Is) event.getProperty().getValue();

                if (selected != null) {
                    selectedIs = isService.findById(selected.getId());

                    if (selectedIs != null) {
                        fieldAciklama.setValue(selectedIs.getAciklama());
                        fieldBaslik.setValue(selectedIs.getBaslik());
                        Personel personelById = personelService.findPersonelById(selectedIs.getCozenKisi().getId());
                        comboCozenKisi.setValue(personelById);
                        Birim birim = birimService.findByBirimId(selectedIs.getBirim().getId());
                        birimComboBox.setValue(birim);
                        fieldTarih.setValue(selectedIs.getTarih());
                        comboOnayDurumu.setValue(selectedIs.getOnayDurumu());
                        fieldTamamlananSure.setValue(selectedIs.getTamamlananSure());
                        setSelectedIs(selected);
                    }
                }
            }
        });

        aramaButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                IsDto isDto = new IsDto();
                isDto.setBaslik(fieldBaslik.getValue());
                isDto.setAciklama(fieldAciklama.getValue());
                isDto.setTarih(fieldTarih.getValue());
                isDto.setTamamlananSure(fieldTamamlananSure.getValue());
                isDto.setOnayDurumu((EnumOnayDurumu) comboOnayDurumu.getValue());
                try {
                    List<Is> isList = isService.findAllByDto(isDto);
                    container.removeAllItems();
                    if (!isList.isEmpty()) {
                        for (Is is : isList) {
                            Item item = container.addItem(is);
                            item.getItemProperty("ID").setValue(is.getId());
                            item.getItemProperty("aciklama").setValue(is.getAciklama());
                            item.getItemProperty("baslik").setValue(is.getBaslik());
                            item.getItemProperty("cozenKisi").setValue(is.getCozenKisi());
                            item.getItemProperty("birim").setValue(is.getBirim());
                            item.getItemProperty("tarih").setValue(is.getTarih());
                            item.getItemProperty("onayDurumu").setValue(is.getOnayDurumu());
                            item.getItemProperty("tamamlananSure").setValue(is.getTamamlananSure());
                        }
                        Notification.show("Is bulundu!", Notification.Type.TRAY_NOTIFICATION);
                    } else {
                        Notification.show("Kayıt bulunamadı!", Notification.Type.WARNING_MESSAGE);
                    }
                    clearFields();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void fillTable() {
        List<Is> isList = isService.findAll();
        container.removeAllItems();
        for (Is is : isList) {
            Item item = container.addItem(is);
            item.getItemProperty("ID").setValue(is.getId());
            item.getItemProperty("aciklama").setValue(is.getAciklama());
            item.getItemProperty("baslik").setValue(is.getBaslik());
            item.getItemProperty("cozenKisi").setValue(is.getCozenKisi());
            item.getItemProperty("tarih").setValue(is.getTarih());
            item.getItemProperty("onayDurumu").setValue(is.getOnayDurumu());
            item.getItemProperty("tamamlananSure").setValue(is.getTamamlananSure());
            item.getItemProperty("birim").setValue(is.getBirim());
        }
    }


    private void init() {
        HorizontalLayout panelLayout = new HorizontalLayout();

        tableField = new Table("Iş Listesi");
        tableField.setSelectable(true);
        tableField.setHeight(150, Unit.PIXELS);
        tableField.setWidth(50, Unit.PERCENTAGE);

        container = createContainer();
        tableField.setColumnHeader("baslik", "Baslik");
        tableField.setColumnHeader("aciklama", "Açıklama");
        tableField.setColumnHeader("cozen_kisi_id", "Cozen Kisi");
        tableField.setColumnHeader("tarih", "Tarih");
        tableField.setColumnHeader("onayDurumu", "Onay Durumu");
        tableField.setColumnHeader("tamamlananSure", "Tamamlanan Sure");
        tableField.setColumnHeader("birim_adi","Birim Adı");
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

        fieldAciklama = new TextField();
        fieldAciklama.setCaption("Açıklama");
        fieldsLayout.addComponent(fieldAciklama);

        fieldBaslik = new TextField();
        fieldBaslik.setCaption("Başlık");
        fieldsLayout.addComponent(fieldBaslik);

        comboCozenKisi = new PersonelComboBox();
        comboCozenKisi.setCaption("Çözen Kişi");
        fieldsLayout.addComponent(comboCozenKisi);

        birimComboBox = new BirimComboBox();
        birimComboBox.fillComboBox();
        birimComboBox.setCaption("Birim Adı");
        fieldsLayout.addComponent(birimComboBox);

        fieldTarih = new DateField("Tarih");
        fieldTarih.setDateFormat("dd-MM-yyyy");
        fieldsLayout.addComponent(fieldTarih);

        comboOnayDurumu = new ComboBox();
        comboOnayDurumu.setCaption("Onay Durumu");
        comboOnayDurumu.addItem(EnumOnayDurumu.ONAYLANDI);
        comboOnayDurumu.addItem(EnumOnayDurumu.REDDEDILDI);
        comboOnayDurumu.addItem(EnumOnayDurumu.BEKLEMEDE);
        fieldsLayout.addComponent(comboOnayDurumu);

        fieldTamamlananSure = new TextField();
        fieldTamamlananSure.setCaption("Tamamlanan Süre");
        fieldsLayout.addComponent(fieldTamamlananSure);

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

    private void setSelectedIs(Is is) {
        this.selectedIs = is;
    }

    private void clearFields() {
        fieldAciklama.clear();
        fieldBaslik.clear();
        comboCozenKisi.clear();
        fieldTarih.clear();
        fieldTamamlananSure.clear();
        comboOnayDurumu.clear();
        selectedIs = null;
    }
}
