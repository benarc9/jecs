package jecs.events;

import jecs.Entity;

public class EntityAddedEvent {
    public Entity entity;

    public EntityAddedEvent(Entity added)
    {
        this.entity = added;
    }
}
