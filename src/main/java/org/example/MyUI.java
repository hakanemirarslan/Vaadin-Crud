package org.example;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.example.ui.layout.*;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@Theme("mytheme")
@Widgetset("org.example.MyAppWidgetset")
public class MyUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        HeaderLayout headerLayout = new HeaderLayout();
        mainLayout.addComponent(headerLayout);

        BodyLayout bodyLayout =new BodyLayout();
        mainLayout.addComponent(bodyLayout);

        FooterLayout footerLayout = new FooterLayout();
        mainLayout.addComponent(footerLayout);

        mainLayout.setExpandRatio(headerLayout, .1f);
        mainLayout.setExpandRatio(bodyLayout, .8f);
        mainLayout.setExpandRatio(footerLayout, .1f);

        setContent(mainLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
