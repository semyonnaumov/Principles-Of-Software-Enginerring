package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        runEventsCycle(smartHome);
    }

    private static void runEventsCycle(SmartHome smartHome) {
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);

            smartHome.processIncomingEvent(event, null);

            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }

}
