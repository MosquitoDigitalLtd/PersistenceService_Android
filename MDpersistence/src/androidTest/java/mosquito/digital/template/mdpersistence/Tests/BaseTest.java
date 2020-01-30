package mosquito.digital.template.mdpersistence.Tests;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Test;

import io.realm.Realm;
import mosquito.digital.template.mdpersistence.Models.NoPrimaryKeyModel;
import mosquito.digital.template.mdpersistence.Models.PrimaryKeyModel;
import mosquito.digital.template.mdpersistence.PersistenceService;


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
        new PersistenceService(Realm.getDefaultInstance()).DropDatabase();
    }

}
