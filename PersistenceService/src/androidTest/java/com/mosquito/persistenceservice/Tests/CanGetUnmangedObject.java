package com.mosquito.persistenceservice.Tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mosquito.persistenceservice.Models.NoPrimaryKeyModel;
import com.mosquito.persistenceservice.Models.PrimaryKeyModel;
import com.mosquito.persistenceservice.PersistenceService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CanGetUnmangedObject extends BaseTest {
    @Test
    public void WithModelThatHasPrimaryKey() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        NoPrimaryKeyModel model = new NoPrimaryKeyModel();
        model.text = text1;
        persistenceService.SaveItem(model);
        assertEquals(text1,  persistenceService.GetItem(NoPrimaryKeyModel.class).text);

    }

    @Test
    public void WithoutModelThatHasPrimaryKey() {
        String text1 = "asd";

        final PersistenceService persistenceService = getDatabase();
        PrimaryKeyModel model = new PrimaryKeyModel();
        model.text = text1;
        persistenceService.SaveItem(model);
        assertEquals(text1,  persistenceService.GetItem(PrimaryKeyModel.class).text);

    }
}
