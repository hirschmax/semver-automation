package com.github.hirschm.permissions.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

public class Permission extends PanacheEntityBase {
    private final String uniqueName;
    private final String caption;

    public Permission(String uniqueName, String caption) {
        this.uniqueName = uniqueName;
        this.caption = caption;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getCaption() {
        return caption;
    }
}
