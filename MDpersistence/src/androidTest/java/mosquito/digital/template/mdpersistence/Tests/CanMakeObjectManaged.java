package mosquito.digital.template.mdpersistence.Tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import mosquito.digital.template.mdpersistence.DatabaseUpdate;
import mosquito.digital.template.mdpersistence.Models.NoPrimaryKeyModel;
import mosquito.digital.template.mdpersistence.Models.PrimaryKeyModel;
import mosquito.digital.template.mdpersistence.PersistenceService;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CanMakeObjectManaged extends BaseTest {
    @Test
    public void WithModelThatHasPrimaryKey() {
        final String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        final NoPrimaryKeyModel model = new NoPrimaryKeyModel();

        persistenceService.MakeObjectManaged(model);
        persistenceService.Update(new DatabaseUpdate() {
            @Override
            public void Update() {
                model.text = text1;
            }
        });
        assertEquals(text1,  persistenceService.GetItem(NoPrimaryKeyModel.class).text);

    }

    @Test
    public void WithoutPrimaryKey() {
        final String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        final PrimaryKeyModel model = new PrimaryKeyModel();

        persistenceService.MakeObjectManaged(model);
        persistenceService.Update(new DatabaseUpdate() {
            @Override
            public void Update() {
                model.text = text1;
            }
        });
        assertEquals(text1,  persistenceService.GetItem(PrimaryKeyModel.class).text);

    }
}
