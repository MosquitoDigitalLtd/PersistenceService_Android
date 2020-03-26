package com.mosquito.persistenceservice.Tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mosquito.persistenceservice.Models.NoPrimaryKeyModel;
import com.mosquito.persistenceservice.Models.PrimaryKeyModel;
import com.mosquito.persistenceservice.PersistenceService;

import org.junit.Test;
import org.junit.runner.RunWith;

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

        persistenceService.saveItem(new PrimaryKeyModel());

        persistenceService.saveItem(model);

        assertEquals(text1,  persistenceService.getItemForKeyValuePair(PrimaryKeyModel.class,"id", id).text);
    }

    @Test
    public void WithoutPrimaryKey() {
        final String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        final NoPrimaryKeyModel model = new NoPrimaryKeyModel();
        model.text = text1;

        persistenceService.saveItem( new NoPrimaryKeyModel());

        persistenceService.saveItem(model);

        assertEquals(text1, persistenceService.getItemForKeyValuePair(NoPrimaryKeyModel.class,"text", text1).text);

    }
}
