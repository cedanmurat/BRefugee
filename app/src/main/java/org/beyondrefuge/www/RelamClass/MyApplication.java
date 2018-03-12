package org.beyondrefuge.www.RelamClass;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by dell on 3/5/2018.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // The default Realm file is "default.realm" in Context.getFilesDir();
        // we'll change it to "myrealm.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("RefugeOrnek1.realm").build();
        Realm.setDefaultConfiguration(config);
    }

}
