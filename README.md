# PersistenceService

This library is created to help with performing any transactions in [Realm](https://realm.io/docs/java/latest/) database. It includes wrappers for basic CRUD operations and some helper functions. For full list of available functions see `IPersistenceService`.

## Usage

Add Realm plugin and this library to `build.gradle` (app module):

```
apply plugin: 'realm-android'

...

dependencies {
	...
	implementation 'com.mosquito.persistenceservice:persistence-service:1.0.6'
}
```

Add Realm to `build.gradle` (root):

```
dependencies {
	classpath "io.realm:realm-gradle-plugin:4.0.0"
}
```

Initialize Realm in your Application class:

```
Realm.init(this);

RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(1).build();

Realm.setDefaultConfiguration(config);
```

And finally initialize PersistenceService class:

```
PersistenceService persistenceService = new PersistenceService(Realm.getDefaultInstance());
```

To make basic operations make a call like this:

```
persistenceService.saveItem(new YourModel());
```

And for update:

```
persistenceService.updateItem(new DatabaseUpdate() {
	@Override
	public void update() {
		// Update your model here. 
		// This will open write transaction in database and close it after applying your changes
	}
});
```

## How library had been created

To create library like this you can use [How to publis and distribute your Android library tutorial](https://medium.com/swlh/how-to-publish-and-distribute-your-android-library-ce845c68c7f7) or [How to publish Android library on Bitray tutorial](http://blogs.quovantis.com/how-to-publish-android-library-on-bintrayjcenter/).

There is also [bintray-release](https://github.com/novoda/bintray-release) library which can be used to make releases a bit easier.

## Publishing updates

To publish updates follow these steps.

### Step one

Bump the version numbers in `build.gradle` (`PersistenceService` module):

```
defaultConfig {
	...
	versionCode 106
	versionName "1.0.6"
}
```

```
publish {
	...
	publishVersion = '1.0.6'
}

```

### Step two

In terminal, navigate to library root directory and run:

```
./gradlew clean build bintrayUpload -PbintrayUser=mosquito-digital -PbintrayKey=8ac5e9504ca4ab4a5bd56a057dbb20321fbf0d6c -PdryRun=false
```

This will push the project to the [Bintray](https://bintray.com/).

### Step three

Verify if the new release is listed on Bintray dashboard.

Use below credentials to access dashboard:

```
username: Mosquito-Digital
password: 6WFT4t@whP8xb4D
```

**Note:**
If you are using this Readme to create new library, keep in mind on the first release you will also need to press `Send to jCenter` button which (as of March 2020) is only visible on the 'Old view' so you'll need to press `Go to Old Look` button on the top of the dashboard first. After that it may take up to 2 hours for the library to be available for including to your project.

## License

PersistenceService is available under the MIT license. See the LICENSE file for more info.