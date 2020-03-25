package com.mosquito.persistenceservice.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PrimaryKeyModel extends RealmObject {
    public String text;
    @PrimaryKey
    public String id;
}
