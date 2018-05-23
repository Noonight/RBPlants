package com.arkhipov.ayur.rbplants.fragmentnavigations;

import com.arkhipov.ayur.rbplants.fragmentnavigations.NavigationDefaults.NavigationDefaultsHolder;
import com.arkhipov.ayur.rbplants.fragmentnavigations.layoutfactory.LayoutFactory;

public final class CustomLayoutNavigationBuilder extends NavigationBuilder<CustomLayoutNavigationBuilder> {

    public CustomLayoutNavigationBuilder(LayoutFactory layoutFactory) {
        super(layoutFactory, NavigationDefaultsHolder.navigationDefaults());
    }

    @Override
    protected CustomLayoutNavigationBuilder getThis() {
        return this;
    }

    public CustomLayoutNavigationBuilder toolbarId(int toolbarId) {
        this.toolbarId = toolbarId;
        return this;
    }

    public CustomLayoutNavigationBuilder bottomBarId(int bottomBarId) {
        this.bottomBarId = bottomBarId;
        return this;
    }

    public AutoLayoutNavigationBuilder auto() {
        return new AutoLayoutNavigationBuilder(layoutFactory());
    }
}
