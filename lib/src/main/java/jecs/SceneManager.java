package jecs;

import java.lang.reflect.Constructor;

public class SceneManager {
    private static SceneManager instance = null;

    private Class<Scene>[] scenes;
    private Scene currentScene = null;
    private int sceneIndex = 0;

    private SceneManager() {}

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }

        return instance;
    }

    public void loadScene(int sceneIndex) {
        try {
            Constructor<?> constructor = scenes[sceneIndex].getConstructor(Const.EMPTY_CLASS_ARRAY);
            ECS.getInstance().dumpAssets();
            currentScene = (Scene) constructor.newInstance();
            this.sceneIndex = sceneIndex;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextScene() {
        if (this.sceneIndex + 1 <= this.scenes.length)
        {
            this.sceneIndex++;
            try {
                Constructor<?> constructor = scenes[this.sceneIndex].getConstructor(Const.EMPTY_CLASS_ARRAY);
                ECS.getInstance().dumpAssets();
                currentScene = (Scene) constructor.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
