package jecs;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Key {
    private Class<Component>[] key;

    public Key(Class<Component>...components) {
        this.key = components;
    }

    public Key(Set<Class<Component>> components) {
        this.key = (Class<Component>[])components.toArray();
    }

    public boolean compare(Key other) {
        // Check if this key fits in other key
        List<Class<Component>> otherKey = Arrays.asList(other.key);
        for (Class<Component> comp : this.key) {
            if (!otherKey.contains(comp))
            {
                return false;
            }
        }

        return true;
    }
}
