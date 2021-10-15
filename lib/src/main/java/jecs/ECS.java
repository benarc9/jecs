package jecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.greenrobot.eventbus.*;

import jecs.events.*;

public class ECS {
    private static ECS instance;
    private EventBus eventBus;

    private ArrayList<Entity> entities = new ArrayList<>();
    private HashMap<Class<? extends System>, System> systemMap = new HashMap<>();

    private ECS() {
        eventBus = new EventBus();
    }

    private ECS(Class<Scene>...scenes) {
        eventBus = new EventBus();
    }

    public static ECS getInstance(Class<Scene>...scenes) {
        if (instance == null) {
            instance = new ECS(scenes);
            SceneManager.getInstance().loadScene(0);
        }

        return instance;
    }

    public static ECS getInstance() {
        if (instance == null) {
            instance = new ECS();
        }

        return instance;
    }

    public <T extends System> T startSystem(Class<T> sysType) {
        try {
            Constructor<?> constructor = sysType.getConstructor(Const.EMPTY_CLASS_ARRAY);
            T system;
            try {
                system = (T) constructor.newInstance();
                this.systemMap.put(sysType, system);
                return system;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends System> T getSystem(Class<System> sysType) {
        return (T) this.systemMap.get(sysType);
    }
    
    public <T extends Entity> T instantiate(Class<T> entType) {
        Constructor<?> constructor;
        try {
            constructor = entType.getConstructor(Const.EMPTY_CLASS_ARRAY);
            T newEnt;
            try {
                newEnt = (T) constructor.newInstance();
                this.entities.add(newEnt);
                eventBus.post(new EntityAddedEvent(newEnt));
                return newEnt;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void destroy(Entity entity) {
        this.entities.remove(entity);
        this.eventBus.post(new EntityRemovedEvent(entity));
    }

    public void dumpAssets() {
        this.entities = new ArrayList<>();
        this.systemMap = new HashMap<>();
    }

    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }
}
