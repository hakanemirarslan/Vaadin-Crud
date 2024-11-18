package org.example.ui.layout;

import com.vaadin.ui.HorizontalLayout;

public class BodyLayout extends HorizontalLayout {
    public BodyLayout(){
        init();
        setSizeFull();
        setSpacing(true);
        setMargin(true);
    }
    private void init(){
        ContentLayout contentLayout = new ContentLayout();
        SidebarLayout sidebarLayout = new SidebarLayout(contentLayout);

        addComponent(sidebarLayout);
        addComponent(contentLayout);
        setExpandRatio(sidebarLayout,.2f);
        setExpandRatio(contentLayout, .8f);

    }
}
