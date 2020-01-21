# MDPersistence


## Installation

```ruby
    implementation 'mosquito.digital.template.Persistence:MDPersistence:0.2.0'
```
```ruby
 allprojects {
    repositories {
        jcenter()
    }
 }
```
## About

This provides an easy to use wrapper around realm to help avoid having realm dependancy leaking in to lots of areas of your App. Making replacing the database implmentsion easier if the need arises and giving convient helper methods.

## Author

Benjamin Pollard, for Mosquito Digital

## License

MDPersistence is available under the MIT license. See the LICENSE file for more info.


## Publishing Updates
To publish updates follow these steps.
#Step One
Make what ever code changes are needed

#Step Two
Jump the version numbers 
```ruby
  versionName "0.2.0"
```
```ruby
  def publishVersionID = '0.2.0'
```

#Step Three
Run this on the commnad line (MAC, drop w from gradle for windows)
```ruby
./gradlew clean build bintrayUpload -PbintrayUser=mosquito-digital -PbintrayKey=8ac5e9504ca4ab4a5bd56a057dbb20321fbf0d6c -PdryRun=false
```

This will push the project to the https://bintray.com/ Account for publising to jCenter(Our Libaray Hosting)

#Step Four
Login too https://bintray.com/ with account details username: Mosquito-Digital and password: 6WFT4t@whP8xb4D
and inside your project there is a 'Send to jCenter' Button , press that in the next 2 hours you will be able to use the new code in your project


