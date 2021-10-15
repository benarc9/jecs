package jecs;

public abstract class Scene {

    public Scene(Class<System>[] systems, Class<Entity>...entities) {
        for (Class<System> sysType : systems) {
            ECS.getInstance().startSystem(sysType);
        }

        for (Class<Entity> entType : entities) {
            ECS.getInstance().instantiate(entType);
        }
    }
}
