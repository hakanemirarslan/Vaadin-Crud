package org.example.ui.layout;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class FooterLayout extends HorizontalLayout {

    private Label footerLabel;

    public FooterLayout(){
        init();
        setWidth(100, Unit.PERCENTAGE);
        setHeight(15, Unit.PERCENTAGE);
        setSizeFull();
    }

    private void init(){
        footerLabel = new Label();
        footerLabel.setValue("@Universal Yazılım");
        footerLabel.setSizeUndefined();
        addComponent(footerLabel);
        setComponentAlignment(footerLabel, Alignment.BOTTOM_CENTER);
    }
}
