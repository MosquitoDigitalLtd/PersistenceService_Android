package mosquito.digital.template.mdpersistence;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import mosquito.digital.template.mdpersistence.Models.NoPrimaryKeyModel;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Realm.init(appContext);
        PersistenceService persistenceService = new PersistenceService();
        persistenceService.SaveItem(new NoPrimaryKeyModel());
        assertEquals("mosquito.digital.template.mdpersistence.test", appContext.getPackageName());

    }
}
