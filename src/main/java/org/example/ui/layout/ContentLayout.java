package org.example.ui.layout;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.example.ui.pages.BirimPages;
import org.example.ui.pages.IsPages;
import org.example.ui.pages.PersonelPages;

public class ContentLayout extends VerticalLayout {
    private Label bodyLabel;
    private PersonelPages personelPages;
    private IsPages isPages;
    private BirimPages birimPages;

    public ContentLayout() {
        init();
        setSizeFull();
    }

    private void init() {

        bodyLabel = new Label();
        addComponent(bodyLabel);
        setComponentAlignment(bodyLabel, Alignment.TOP_CENTER);

        personelPages = new PersonelPages();
        isPages = new IsPages();
        birimPages = new BirimPages();

        addComponent(personelPages);
        addComponent(isPages);
        addComponent(birimPages);

        allViewVisibleFalse();

    }

    public void personelPagesView() {
        personelPages.setVisible(true);
        isPages.setVisible(false);
        birimPages.setVisible(false);
        bodyLabel.setVisible(false);
    }

    public void isPagesView() {
        personelPages.setVisible(false);
        isPages.setVisible(true);
        birimPages.setVisible(false);
        bodyLabel.setVisible(false);
    }

    public void birimPagesView() {
        personelPages.setVisible(false);
        isPages.setVisible(false);
        birimPages.setVisible(true);
        bodyLabel.setVisible(false);
    }

    public void allViewVisibleFalse() {
        personelPages.setVisible(false);
        isPages.setVisible(false);
        birimPages.setVisible(false);
    }


}


