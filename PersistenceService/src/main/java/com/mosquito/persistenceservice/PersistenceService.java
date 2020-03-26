package com.mosquito.persistenceservice;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmQuery;

public class PersistenceService implements IPersistenceService {

    private Realm realm;

    //
    // Init
    //

    public PersistenceService(Realm realm)
    {
        this.realm = realm;
    }

    //
    // Create
    //

    public <T extends RealmObject> T makeManagedItem(T item)
    {
        return realm.copyToRealm(item);
    }

    /**
     * Updates an existing RealmObject that is identified by the same {@link io.realm.annotations.PrimaryKey} or creates
     * a new copy if no existing object could be found. This is a deep copy or update i.e., all referenced objects will be
     * either copied or updated.
     * */
    public <T extends RealmObject> void saveItem(T item)
    {
        if (item == null) {
            return;
        }

        RealmObjectSchema schema = realm.getSchema().get(item.getClass().getSimpleName());

        if (schema != null && !schema.hasPrimaryKey()) {
            realm.beginTransaction();
            realm.copyToRealm(item);
            realm.commitTransaction();
        } else {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(item);
            realm.commitTransaction();
        }
    }

    /**
     * Updates an existing RealmObject that is identified by the same {@link io.realm.annotations.PrimaryKey} or creates
     * a new copy if no existing object could be found. This is a deep copy or update i.e., all referenced objects will be
     * either copied or updated.
     * */
    public <T extends RealmObject> void saveItemList(List<T> itemList)
    {
        if(itemList != null && itemList.size() > 0) {
            RealmObjectSchema schema = realm.getSchema().get(itemList.get(0).getClass().getSimpleName());
            realm.beginTransaction();

            if (schema != null && !schema.hasPrimaryKey()) {
                for (T item : itemList) {
                    realm.copyToRealmOrUpdate(item);
                }
            } else {
                for (T item : itemList) {
                    realm.copyToRealm(item);
                }
            }

            realm.commitTransaction();
        }
    }

    //
    // Read
    //

    public <T extends RealmObject> T getUnmanagedItem(Class<T> itemType)
    {
        RealmQuery<T> query = realm.where(itemType);

        if (query.findFirst() == null) {
            return null;
        } else {
            return realm.copyFromRealm(query.findFirst());
        }
    }

    public <T extends RealmObject> List<T> getUnmanagedItemList(Class<T> itemType)
    {
        RealmQuery<T> query = realm.where(itemType);

        if (query.findAll() == null) {
            return null;
        } else {
            return realm.copyFromRealm(query.findAll());
        }
    }

    public <T extends RealmObject> T getItem(Class<T> itemType)
    {
        RealmQuery<T> query = realm.where(itemType);

        return query.findFirst();
    }

    public <T extends RealmObject> T getItemForKeyValuePair(Class<T> itemType, String key, String value)
    {
        RealmQuery<T> query = realm.where(itemType).equalTo(key, value);

        return query.findFirst();
    }

    public <T extends RealmObject> List<T> getItemList(Class<T> itemType)
    {
        RealmQuery<T> query = realm.where(itemType);

        return query.findAll();
    }

    //
    // Update
    //

    public void updateItem(DatabaseUpdate databaseUpdate)
    {
        realm.beginTransaction();
        databaseUpdate.update();
        realm.commitTransaction();
    }

    public <T extends RealmObject> void insertOrUpdateItem(T item)
    {
        realm.beginTransaction();
        realm.insertOrUpdate(item);
        realm.commitTransaction();
    }

    /**
     * Inserts or updates a list of unmanaged RealmObjects
     */
    public <T extends RealmObject> void insertOrUpdateItemList(List<T> itemList)
    {
        realm.beginTransaction();
        realm.insertOrUpdate(itemList);
        realm.commitTransaction();
    }

    //
    // Delete
    //

    public <T extends RealmObject> void deleteItem(T item)
    {
        realm.beginTransaction();
        item.deleteFromRealm();
        realm.commitTransaction();
    }

    public <T extends RealmObject> void deleteAllByType(Class<T> itemType)
    {
        realm.beginTransaction();
        realm.delete(itemType);
        realm.commitTransaction();
    }

    public <T extends RealmObject> void deleteAll()
    {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
}
