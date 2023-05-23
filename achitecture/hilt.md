# Hilt
https://developer.android.com/training/dependency-injection/hilt-android

`@Inject` annotated constructors for classes

`@Module` Let Hilt know how to provide some instance you need. In a module there would be many `@Provides` or `@Binds` 

`@Provides` the code from 3rd party or somewhere you don't own

`@Binds` Bind an Interface to an implementation

`@Qualifier` If you have 2 implementation of ... httpService, and you need to inject them both, use `@Qualifier` to identify.
