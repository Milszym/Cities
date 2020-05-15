## Netguru Cities

##### Google Maps API Key

In order to configure google maps api key, please build the app with environmental variable named:
`GOOGLE_API_KEY` or edit `BuildVariables.kt:googleApiKey` fallback.

Please keep in mind that the app will crash without providing this key with an appropriate error. 
It's a desired behaviour.

##### Persistence

App is using Room for persisting emitted city-color items.
Emitted values are saved on the device. Even after app restart all already emitted values will
be displayed on the master list.

##### One Java Class

One class written in Java is: `CityColorProducer.java`.

##### Tests&Lint

Please run following command to run all tests and check link:
```shell script
./gradlew lint testDebugUnitTest
```

##### Additional info

Please note that the project is only configured for debug mode.

According to toolbar, I was not sure how you wan't to have it in the app. If all over the app, like
an action bar or like a view inside of particular screen. I decided to put it as a view in a particular
screen. Splash activity looks better without it in my opinion.
Let me know if you wanted to have it any other way. I can adjust it to your requirements.

Well, thanks for the task, it was a nice challenge. I know there is a thousands way to provide
master details flow, I hope you'd like main.
Hope to hear from you soon!
Szymon :)