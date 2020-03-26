package com.mosquito.persistenceservice.Tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mosquito.persistenceservice.Models.NoPrimaryKeyModel;
import com.mosquito.persistenceservice.Models.PrimaryKeyModel;
import com.mosquito.persistenceservice.PersistenceService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CanRemoveObjects extends BaseTest {

    @Test
    public void WithModelThatHasPrimaryKey() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        NoPrimaryKeyModel model = new NoPrimaryKeyModel();

        model.text = text1;

        persistenceService.saveItem(model);

        assertEquals(text1, persistenceService.getItem(NoPrimaryKeyModel.class).text);

        persistenceService.deleteItem(persistenceService.getItem(NoPrimaryKeyModel.class));

        assertTrue(persistenceService.getItemList(NoPrimaryKeyModel.class).isEmpty());
    }

    @Test
    public void WithoutModelThatHasPrimaryKey() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        PrimaryKeyModel model = new PrimaryKeyModel();

        model.text = text1;

        persistenceService.saveItem(model);

        assertEquals(text1, persistenceService.getItem(PrimaryKeyModel.class).text);

        persistenceService.deleteItem(persistenceService.getItem(PrimaryKeyModel.class));

        assertTrue(persistenceService.getItemList(PrimaryKeyModel.class).isEmpty());

    }


    @Test
    public void AllObjectsOfGivenType() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        NoPrimaryKeyModel model = new NoPrimaryKeyModel();

        model.text = text1;
        persistenceService.saveItem(model);
        persistenceService.saveItem(new NoPrimaryKeyModel());
        persistenceService.saveItem(new NoPrimaryKeyModel());

        persistenceService.deleteAllByType(NoPrimaryKeyModel.class);

        assertTrue(persistenceService.getItemList(NoPrimaryKeyModel.class).isEmpty());

    }
}