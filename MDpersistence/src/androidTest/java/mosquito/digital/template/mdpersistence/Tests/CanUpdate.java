package mosquito.digital.template.mdpersistence.Tests;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import mosquito.digital.template.mdpersistence.DatabaseUpdate;
import mosquito.digital.template.mdpersistence.Models.NoPrimaryKeyModel;
import mosquito.digital.template.mdpersistence.Models.PrimaryKeyModel;
import mosquito.digital.template.mdpersistence.PersistenceService;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CanUpdate extends BaseTest {
    @Test
    public void WithModelThatHasPrimaryKey() {
        String text1 = "asd";
        final String text2 = "dsxxc";

        final PersistenceService persistenceService = getDatabase();
        NoPrimaryKeyModel model = new NoPrimaryKeyModel();
        model.text = text1;
        persistenceService.SaveItem(model);
        persistenceService.Update(new DatabaseUpdate() {
            @Override
            public void Update() {
                NoPrimaryKeyModel model2 = persistenceService.GetItem(NoPrimaryKeyModel.class);
                model2.text = text2;
            }
        });

        assertEquals(text2,  persistenceService.GetItem(NoPrimaryKeyModel.class).text);

    }

    @Test
    public void WithoutModelThatHasPrimaryKey() {

        String text1 = "asd";
        final String text2 = "dsxxc";

        final PersistenceService persistenceService = getDatabase();
        PrimaryKeyModel model = new PrimaryKeyModel();
        model.text = text1;
        persistenceService.SaveItem(model);
        persistenceService.Update(new DatabaseUpdate() {
            @Override
            public void Update() {
                PrimaryKeyModel model2 = persistenceService.GetItem(PrimaryKeyModel.class);
                model2.text = text2;
            }
        });

        assertEquals(text2,  persistenceService.GetItem(PrimaryKeyModel.class).text);

    }
}
