package com.mosquito.persistenceservice.Tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mosquito.persistenceservice.Models.NoPrimaryKeyModel;
import com.mosquito.persistenceservice.Models.PrimaryKeyModel;
import com.mosquito.persistenceservice.PersistenceService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class CanUpdateOrInsert extends BaseTest {
    @Test
    public void WithModelThatHasPrimaryKey() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        NoPrimaryKeyModel model = new NoPrimaryKeyModel();

        model.text = text1;
        persistenceService.insertOrUpdateItem(model);

        assertEquals(text1, persistenceService.getItem(NoPrimaryKeyModel.class).text);
    }

    @Test
    public void WithoutModelThatHasPrimaryKey() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        PrimaryKeyModel model = new PrimaryKeyModel();

        model.text = text1;
        model.id = "sd";

        persistenceService.insertOrUpdateItem(model);

        assertEquals(text1, persistenceService.getItem(PrimaryKeyModel.class).text);
    }

    @Test
    public void WithoutModelThatHasPrimaryKeyAndUpdate() {
        String text1 = "asd";
        String text2 = "asasdad";

        final PersistenceService persistenceService = getDatabase();
        PrimaryKeyModel model = new PrimaryKeyModel();

        model.text = text1;
        model.id = "sd";
        persistenceService.insertOrUpdateItem(model);

        assertEquals(text1,  persistenceService.getItem(PrimaryKeyModel.class).text);

        PrimaryKeyModel modelunmanaged = persistenceService.getUnmanagedItem(PrimaryKeyModel.class);
        modelunmanaged.text = text2;
        persistenceService.insertOrUpdateItem(modelunmanaged);

        assertEquals(text2,  persistenceService.getItem(PrimaryKeyModel.class).text);
    }
}
