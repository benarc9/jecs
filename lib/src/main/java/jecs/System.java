package jecs;

import java.util.ArrayList;

import com.google.common.eventbus.Subscribe;

import jecs.events.EntityAddedEvent;
import jecs.events.EntityRemovedEvent;

public abstract class System {
    private Key key;
    private ArrayList<Entity> entities = new ArrayList<>();

    public System(Class<Component>...components) {
        this.key = new Key(components);
        ECS.getInstance().register(this);
    }

    @Subscribe
    public void OnEntityAdded(EntityAddedEvent event) {
        if (event.entity.key.compare(key)) {
            this.entities.add(event.entity);
        }
    }

    @Subscribe
    public void OnEntityRemoved(EntityRemovedEvent event) {
        this.entities.remove(event.entity);
    }
}
