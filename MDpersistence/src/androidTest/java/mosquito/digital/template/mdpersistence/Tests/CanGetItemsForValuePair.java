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
public class CanGetItemsForValuePair extends BaseTest {
    @Test
    public void WithModelThatHasPrimaryKey() {
        final String text1 = "asd";
        String id = "dfsfff";

        final PersistenceService persistenceService = getDatabase();
        final PrimaryKeyModel model = new PrimaryKeyModel();
        model.text = text1;
        model.id = id;
        persistenceService.SaveItem( new PrimaryKeyModel());

        persistenceService.SaveItem(model);

        assertEquals(text1,  persistenceService.GetItemForKeyValuePair(PrimaryKeyModel.class,"id",id).text);

    }

    @Test
    public void WithoutPrimaryKey() {
        final String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        final NoPrimaryKeyModel model = new NoPrimaryKeyModel();
        model.text = text1;
        persistenceService.SaveItem( new NoPrimaryKeyModel());

        persistenceService.SaveItem(model);

        assertEquals(text1,  persistenceService.GetItemForKeyValuePair(NoPrimaryKeyModel.class,"text",text1).text);

    }
}
