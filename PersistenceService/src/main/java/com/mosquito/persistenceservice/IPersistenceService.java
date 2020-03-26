package com.mosquito.persistenceservice;

import java.util.List;

import io.realm.RealmObject;

public interface IPersistenceService {

    // Create

    <T extends RealmObject> T makeManagedItem(T item);

    <T extends RealmObject> void saveItem(T item);
    <T extends RealmObject> void saveItemList(List<T> itemList);

    // Read

    <T extends RealmObject> T getUnmanagedItem(Class<T> itemType);
    <T extends RealmObject> List<T> getUnmanagedItemList(Class<T> itemType);

    <T extends RealmObject> T getItem(Class<T> itemType);
    <T extends RealmObject> T getItemForKeyValuePair(Class<T> itemType, String key, String value);
    <T extends RealmObject> List<T> getItemList(Class<T> itemType);

    // Update

    <T extends RealmObject> void updateItem(DatabaseUpdate databaseUpdate);

    <T extends RealmObject> void insertOrUpdateItem(T item);
    <T extends RealmObject> void insertOrUpdateItemList(List<T> itemList);

    // Delete

    <T extends RealmObject> void deleteItem(T item);
    <T extends RealmObject> void deleteAllByType(Class<T> itemType);
    <T extends RealmObject> void deleteAll();
}

