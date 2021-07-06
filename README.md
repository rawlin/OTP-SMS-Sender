# OTP-SMS-Sender
This Android application sends sms's using the Twilio Messaging API and is intended to be used as stencil to create other apps in the MVVM architecture along with other best practices such as Depencecy Injection, Test cases and much more. Just an app to show best practices at the moment in the Android world.

## Things required to get the project started
- To get started and contributing you need to add SID and Auth Token from your Twilio account to the `local.properties` file as shown below
```groovy
SID="YOUR_SID"
AUTH_TOKEN="YOUR_AUTH_TOKEN"
```

## Stuff left to do
- [ ] Fix the Repository Pattern
- [ ] Use dependency injection via Hilt-Dagger
- [ ] Write test cases
- [ ] Switch from GSON to Moshi
- [ ] Move to navigation components

## Contributing
Pull requests are welcome. Please do try to fix the stuff in the "Stuff Left to do" section first. For major changes, please open an issue first to discuss what you would like to change.
