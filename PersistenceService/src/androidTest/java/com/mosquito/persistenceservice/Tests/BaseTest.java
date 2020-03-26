package com.mosquito.persistenceservice.Tests;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.mosquito.persistenceservice.PersistenceService;

import org.junit.After;

import io.realm.Realm;

public class BaseTest {

    public PersistenceService getDatabase() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Realm.init(appContext);

        return new PersistenceService(Realm.getDefaultInstance());
    }

    @After
    public void CleanUp()
    {
        new PersistenceService(Realm.getDefaultInstance()).deleteAll();
    }

}
