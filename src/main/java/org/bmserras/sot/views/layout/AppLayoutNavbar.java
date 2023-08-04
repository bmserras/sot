package org.bmserras.sot.views.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import org.bmserras.sot.views.project.ProjectsView;
import org.bmserras.sot.views.WidgetsView;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class AppLayoutNavbar extends AppLayout {

    private Image logo;
    private Tabs menu;
    private MenuBar avatar;

    public AppLayoutNavbar() {

        logo = getLogo();
        menu = getMenu();
        avatar = getAvatar();

        addToNavbar(logo, menu, avatar);
    }

    private Image getLogo() {

        Image logo = new Image("icons/logo-variation-sot.svg", "Synoptics of Things Logo");
        logo.getStyle().set("width", "150px")
                .set("margin-left", "20px")
                .set("position", "absolute");

        return logo;
    }

    private Tabs getMenu() {

        RouterLink projectsLink = new RouterLink("", ProjectsView.class);
        projectsLink.add(LineAwesomeIcon.FOLDER.create(), new Span("Projects"));
        Tab projectsTab = new Tab(projectsLink);
        projectsTab.setTooltipText("Create and view projects").withPosition(Tooltip.TooltipPosition.BOTTOM);

        RouterLink widgetsLink = new RouterLink("", WidgetsView.class);
        widgetsLink.add(LineAwesomeIcon.LIGHTBULB.create(), new Span("Widgets"));
        Tab widgetsTab = new Tab(widgetsLink);
        widgetsTab.setTooltipText("Create and view common widgets").withPosition(Tooltip.TooltipPosition.BOTTOM);

        // Set the icon on top
        for (Tab tab : new Tab[] { projectsTab, widgetsTab }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }

        Tabs tabs = new Tabs(projectsTab, widgetsTab);
        tabs.getStyle().set("margin", "auto");

        return tabs;
    }

    private MenuBar getAvatar() {

        Avatar avatar = new Avatar();

        avatar.getStyle().set("margin-right", "20px")
                .set("position", "end");

        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);

        MenuItem menuItem = menuBar.addItem(avatar);
        SubMenu subMenu = menuItem.getSubMenu();
        subMenu.addItem("Profile");
        subMenu.addItem("Settings");
        subMenu.addItem("Help");
        subMenu.addItem("Sign out");

        return menuBar;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        RouteConfiguration configuration = RouteConfiguration.forSessionScope();
        if (configuration.isRouteRegistered(this.getContent().getClass())) {
            Optional<String> urlBase = configuration.getUrlBase(this.getContent().getClass());
            urlBase.ifPresent(target -> {
                Optional<Component> tabToSelect = menu.getChildren().filter(tab -> {
                    Component child = tab.getChildren().findFirst().get();
                    return child instanceof RouterLink && ((RouterLink) child).getHref().equals(target);
                }).findFirst();
                tabToSelect.ifPresentOrElse(tab -> menu.setSelectedTab((Tab) tab), () -> menu.setSelectedTab(null));
            });
        } else {
            menu.setSelectedTab(null);
        }
    }
}
