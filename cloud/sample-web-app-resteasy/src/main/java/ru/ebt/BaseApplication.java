package ru.ebt;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class BaseApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> objects = new HashSet<>();
        objects.add(new LightAppController());
        objects.add(new UiApplicationController());
        return objects;
    }
}