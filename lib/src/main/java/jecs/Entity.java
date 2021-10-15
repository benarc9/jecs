package jecs;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public abstract class Entity {
    private HashMap<Class<Component>, Component> components = new HashMap<>();
    public Key key;

    public Entity(Class<Component>...components) {
        for(Class<Component> compType : components){
            try {
                Constructor<? extends Component> comp = compType.getConstructor(Const.EMPTY_CLASS_ARRAY);
                Component newComp = comp.newInstance();
                this.components.put(compType, newComp);
            } catch(Exception e) {

            }
        }
        updateKey();
    }

    public <T extends Component> T getComponent(Class<T> type) {
        return (T) components.get(type);
    }

    public void addComponent(Class<Component> type) {
        boolean doUpdate = !this.components.containsKey(type);

        try {
            Constructor<? extends Component> constructor = type.getConstructor(Const.EMPTY_CLASS_ARRAY);
            Component newComp = constructor.newInstance();
            this.components.put(type, newComp);
            if (doUpdate) {
                updateKey();
            }
        } catch (Exception e){

        }
    }

    public void removeComponent(Class<Component> type) {
        if (this.components.containsKey(type)) {
            this.components.remove(type);
            updateKey();
        }
        else {

        }
    }

    private void updateKey() {
        this.key = new Key(this.components.keySet());
    }
}
