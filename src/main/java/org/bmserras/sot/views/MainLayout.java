package org.bmserras.sot.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.bmserras.sot.components.appnav.AppNav;
import org.bmserras.sot.components.appnav.AppNavItem;
import org.bmserras.sot.views.cabin.CabinView;
import org.bmserras.sot.views.helloworld.HelloWorldView;
import org.bmserras.sot.views.map.MapView;
import org.bmserras.sot.views.widgets.GaugeView;
import org.bmserras.sot.views.widgets.SolidGaugeView;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("sot");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("General View", HelloWorldView.class,
                LineAwesomeIcon.GLOBE_SOLID.create()));
        nav.addItem(new AppNavItem("Map", MapView.class, LineAwesomeIcon.MAP.create()));
        nav.addItem(new AppNavItem("Gauge", GaugeView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new AppNavItem("Solid Gauge", SolidGaugeView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new AppNavItem("Cabin", CabinView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new AppNavItem("List", ListView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new AppNavItem("Form", FormView.class, LineAwesomeIcon.FILE.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
