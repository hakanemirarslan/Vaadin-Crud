    package org.example.ui.pages;

    import com.vaadin.data.Item;
    import com.vaadin.data.Property;
    import com.vaadin.data.util.IndexedContainer;
    import com.vaadin.server.FontAwesome;
    import com.vaadin.ui.*;
    import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
    import org.example.core.entity.Birim;
    import org.example.core.entity.dto.BirimDto;
    import org.example.core.service.BirimService;

    import java.util.List;

    public class BirimPages extends VerticalLayout {
        private TextField fieldAdi;
        private TextField fieldId;
        private Button kaydetButton;
        private Button aramaButton;
        private Button silButton;
        private Button guncelleButton;
        private Table tableField;
        private IndexedContainer container;
        private Birim selectedBirim;
        private BirimService birimService = new BirimService();

        public BirimPages() {
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
            return container;
        }

        private void addListeners() {
            kaydetButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    String birimAdi = fieldAdi.getValue();

                    if (birimAdi.isEmpty()) {
                        Notification.show("Lütfen Tüm Alanları Doldurunuz.", Notification.Type.WARNING_MESSAGE);
                    } else {
                        Birim birim = new Birim();
                        birim.setName(birimAdi);
                        birimService.save(birim);
                        tableField.addItem(new Object[]{birim.getId(), birimAdi}, birim.getId());
                        clearFields();
                        fillTable();
                        Notification.show("Kayıt Başarıyla Eklendi", Notification.Type.TRAY_NOTIFICATION);
                    }
                }
            });
            silButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (selectedBirim != null) {
                        String deletedBirim = birimService.deleteById(selectedBirim.getId());
                        Notification.show(deletedBirim, Notification.Type.TRAY_NOTIFICATION);
                        fillTable();
                        clearFields();
//                        selectedBirim = null;
                    } else {
                        Notification.show("Lütfen silinecek bir birim seçin.", Notification.Type.WARNING_MESSAGE);
                    }
                }
            });

            guncelleButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (selectedBirim != null){
                        String updatedBirim = fieldAdi.getValue();
                        if (updatedBirim != null){
                            selectedBirim.setName(updatedBirim);
                            birimService.update(selectedBirim);
                            fillTable();
                            clearFields();
                            Notification.show("Güncelleme yapıldı.", Notification.Type.TRAY_NOTIFICATION);
                        }else {
                            Notification.show("Güncellenicek birimi seçiniz.", Notification.Type.WARNING_MESSAGE);
                        }
                }

                }
            });

            tableField.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    Birim selected = (Birim) event.getProperty().getValue();
                    if (selected != null) {
                        selectedBirim = birimService.findById(selected.getId());
                        if (selectedBirim != null) {
                            fieldAdi.setValue(selectedBirim.getName());
                            setSelectedBirim(selected);
                        }
                    }
                }
            });

            aramaButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    BirimDto birimDto = new BirimDto();
                    birimDto.setName(fieldAdi.getValue());
                    try {
                        List<Birim> birimList = birimService.findAllByDto(birimDto);
                        container.removeAllItems();
                        if (!birimList.isEmpty()) {
                            for (Birim birim : birimList) {
                                Item item = container.addItem(birim.getId());
                                item.getItemProperty("ID").setValue(birim.getId());
                                item.getItemProperty("name").setValue(birim.getName());
                            }
                            Notification.show("Birim bulundu!", Notification.Type.TRAY_NOTIFICATION);
                        } else {
                            Notification.show("Birim bulunamadı!", Notification.Type.WARNING_MESSAGE);
                        }
                        clearFields();
                    } catch (Exception e) {
                        Notification.show("Arama sırasında bir hata oluştu!", Notification.Type.ERROR_MESSAGE);
                        throw new RuntimeException(e);
                    }
                }

            });

        }

        private void fillTable() {
            List<Birim> birimList = birimService.findAll();
            container.removeAllItems();

            for (Birim birim : birimList) {
                Item item = container.addItem(birim);
                item.getItemProperty("ID").setValue(birim.getId());
                item.getItemProperty("name").setValue(birim.getName());

            }
        }

        private void init() {
            HorizontalLayout panelLayout = new HorizontalLayout();
            tableField = new Table("Birim Listesi");
            tableField.setSelectable(true);
            tableField.setHeight(150, Unit.PIXELS);
            tableField.setWidth(50, Unit.PERCENTAGE);

            container = createContainer();
            tableField.setColumnHeader("name", "Birim Adı");
            tableField.setContainerDataSource(container);

            panelLayout.addComponent(tableField);
            panelLayout.setHeight(100, Unit.PIXELS);
            panelLayout.setWidth(100, Unit.PERCENTAGE);
            addComponent(panelLayout);

            HorizontalLayout fieldsLayout = new HorizontalLayout();
            fieldsLayout.setSpacing(true);

            fieldId = new TextField("");
            fieldId.setVisible(false);
            fieldsLayout.addComponent(fieldId);

            fieldAdi = new TextField();
            fieldAdi.setCaption("Birim Adı");
            fieldsLayout.addComponent(fieldAdi);

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

        private void setSelectedBirim(Birim birim) {
            this.selectedBirim = birim;
        }
        private void clearFields() {
            fieldId.clear();
            fieldAdi.clear();
            selectedBirim = null;
        }
    }
