package jecs.events;

import jecs.Entity;

public class EntityRemovedEvent {
    public Entity entity;

    public EntityRemovedEvent(Entity entity) {
        this.entity = entity;
    }
}
