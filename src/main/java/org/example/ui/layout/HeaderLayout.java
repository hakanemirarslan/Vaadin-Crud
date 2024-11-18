package org.example.ui.layout;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;

public class HeaderLayout extends HorizontalLayout {

    private Label headerLabel;
    private Image logo;

    private Button girisButton;

    public HeaderLayout() {
        setWidth(100, Unit.PERCENTAGE);
        setHeight(15, Unit.PERCENTAGE);
        init();
    }

    private void init() {
        logo = new Image(null, new ExternalResource("https://www.uni-yaz.com/wp-content/uploads/2019/02/Universal__Yaz%C4%B1l%C4%B1m-A.%C5%9E._Logo-Dikey-Kullan%C4%B1m.png"));
        logo.setHeight("60px");
        logo.setWidth("100px");
        addComponent(logo);
        setComponentAlignment(logo, Alignment.TOP_LEFT);

        headerLabel = new Label();
        headerLabel.setValue("UNIVERSAL YAZILIM");
        headerLabel.setSizeUndefined();
        addComponent(headerLabel);
        setComponentAlignment(headerLabel, Alignment.TOP_CENTER);

        girisButton = new Button();
        girisButton.setCaption("Giriş Yap");
        addComponent(girisButton);
        setComponentAlignment(girisButton,Alignment.TOP_RIGHT);

        setExpandRatio(logo, 1f);
        setExpandRatio(headerLabel, 8f);
    }
}
