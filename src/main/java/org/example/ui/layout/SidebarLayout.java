package org.example.ui.layout;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import org.example.ui.pages.BirimPages;
import org.example.ui.pages.IsPages;
import org.example.ui.pages.PersonelPages;

public class SidebarLayout extends VerticalLayout {

    private Button personelButton;
    private Button isButton;
    private Button birimButton;
    private ContentLayout contentLayout;
    private PersonelPages personelPages;
    private IsPages isPages;
    private BirimPages birimPages;

    public SidebarLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
        init();
        setWidth("100px");
        setHeight("45%");
        addListeners();
    }
    private void addListeners(){
        personelButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                contentLayout.personelPagesView();
            }
        });

        isButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                contentLayout.isPagesView();
            }
        });
        birimButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                contentLayout.birimPagesView();
            }
        });
    }

    private void init() {
        personelButton = new Button();
        personelButton.setCaption("Personel");
        addComponent(personelButton);

        isButton = new Button();
        isButton.setCaption("İş");
        addComponent(isButton);

        birimButton = new Button();
        birimButton.setCaption("Birim");
        addComponent(birimButton);

        setComponentAlignment(personelButton, Alignment.MIDDLE_LEFT);
        setComponentAlignment(isButton, Alignment.MIDDLE_LEFT);
        setComponentAlignment(birimButton, Alignment.MIDDLE_LEFT);
    }
//    public void personelPagesView() {
//        personelPages.setVisible(true);
//        isPages.setVisible(false);
//        birimPages.setVisible(false);
//    }
//
//    public void isPagesView() {
//        personelPages.setVisible(false);
//        isPages.setVisible(true);
//        birimPages.setVisible(false);
//    }
//
//    public void birimPagesView() {
//        personelPages.setVisible(false);
//        isPages.setVisible(false);
//        birimPages.setVisible(true);
//    }

}
