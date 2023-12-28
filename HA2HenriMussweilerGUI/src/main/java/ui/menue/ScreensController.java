package ui.menue;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import ui.menue.ControlledScreen;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreensController extends StackPane {

        private HashMap<String, Node> screens = new HashMap<>();
        private HashMap<String, ControlledScreen> controllers = new HashMap<>();
        public static String sourcePath = "";

        public ScreensController(){
            super();
        }


        public void addScreen(String name, Node screen) {
            screens.put(name, screen);
        }

    public boolean loadScreen(String name, String resource) {
        System.out.println("ID: "+name);
        System.out.println("Resource: "+resource);

        String file = System.getProperty("user.dir")+"/bin/"+resource;
//		System.out.println(file);

        try {
            FXMLLoader myLoader = new FXMLLoader();
            File f = new File(file);
            /* for Reading from File-System use absolute Path*/
//		       URL url = f.toURI().toURL();

            /* for Reading from jar-File user relative Path */
            URL url = getClass().getClassLoader().getResource(resource).toURI().toURL();

            myLoader.setLocation(url);

            /* Scene laden */
            Parent loadScreen = (Parent) myLoader.load();

            /* Controller-Klasse zur Scene laden und in HashMap ablegen */
            ControlledScreen myScreenControler =
                    ((ControlledScreen) myLoader.getController());
            this.controllers.put(name, myScreenControler);

            myScreenControler.setScreenParent(this);

            /* Scene in HashMap ablegen */
            addScreen(name, loadScreen);

            /* Konsolen-Ausgabe zur Kontrolle bei Programmstartn */
            System.out.println("Anzahl Screens: "+screens.size());
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean setScreen(final String name) {

        Node screenToRemove;
        if(screens.get(name) != null){   //screen loaded
            if(!getChildren().isEmpty()){    //if there is more than one screen
                getChildren().add(0, screens.get(name));     //add the screen
                screenToRemove = getChildren().get(1);
                getChildren().remove(1);                    //remove the displayed screen
            }else{
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
            }
            this.controllers.get(name).initData();
            return true;
        }else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }
    }

    public boolean unloadScreen(String name) {
        if(screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

    public void print() {
        Set<String> keys = screens.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            System.out.println("key: "+it.next());
        }

    }
}
