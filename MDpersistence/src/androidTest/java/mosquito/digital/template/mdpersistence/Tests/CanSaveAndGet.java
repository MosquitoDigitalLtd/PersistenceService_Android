package mosquito.digital.template.mdpersistence.Tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import mosquito.digital.template.mdpersistence.DatabaseUpdate;
import mosquito.digital.template.mdpersistence.Models.NoPrimaryKeyModel;
import mosquito.digital.template.mdpersistence.Models.PrimaryKeyModel;
import mosquito.digital.template.mdpersistence.PersistenceService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class CanSaveAndGet extends BaseTest {
    @Test
    public void WithModelThatHasPrimaryKey() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        NoPrimaryKeyModel model = new NoPrimaryKeyModel();

        model.text = text1;
        persistenceService.SaveItem(model);

        assertEquals(text1,  persistenceService.GetItem(NoPrimaryKeyModel.class).text);

        NoPrimaryKeyModel unmanagedModel = persistenceService.GetUnmanagedObject(NoPrimaryKeyModel.class);
        unmanagedModel.text = "sdf";

        assertNotEquals(unmanagedModel.text,  persistenceService.GetItem(NoPrimaryKeyModel.class).text);

    }

    @Test
    public void WithoutModelThatHasPrimaryKey() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        PrimaryKeyModel model = new PrimaryKeyModel();

        model.text = text1;
        persistenceService.SaveItem(model);

        assertEquals(text1,  persistenceService.GetItem(PrimaryKeyModel.class).text);

        PrimaryKeyModel unmanagedModel = persistenceService.GetUnmanagedObject(PrimaryKeyModel.class);
        unmanagedModel.text = "sdf";

        assertNotEquals(unmanagedModel.text,  persistenceService.GetItem(PrimaryKeyModel.class).text);

    }
}
