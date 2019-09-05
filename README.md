# Mindownloader
Downloading and Caching library for Android

# Intro
A library meant to cache different types of data, already supports (Image, JSON) can support different types as long as you implement the raw functionality of inferring the bytes into the type supported, you have not to worry about any caching, as this utility will save you the efforts.

# Usage
1- To start using the library, all what you have to do is to include `Mindownloader` module into your desired module `build.gradle` as follows:
```
implementation project(':mindownloader')
```

2- The whole functionality starts at the main component called `DownloadManager` is all where the magic happens, do not worry about switching threads for this one, as it is handled using RxJava, all you have to do is to initialize an instance.

3- You can request a resource using the following function:
```kotlin
fun loadResource(RESOURCE_URL, BASE_RESOURCE_INSTACE, SUCCESS_CALLBACK(BaseResource), FAILURE_CALLBACK(NetworkError))
```

4- `BaseResource<T>` is the main interface that wraps other extended resources through type parameters, i.e, ImageResource which implements the `BaseResource<Bitmap>`, upon class implementation, implement the method `convert()` from byte stream to whatever type you specified in the type parameters.

5- `NetworkError` is a general class error model that just contains a string, open for later expansion as well.

# Stack used
1- Kotlin</br>
2- RxJava</br>
3- Okhttp/Retrofit/Gson</br>
4- AndroidX Support Libraries</br>
5- Dagger Android</br>
6- Lifecycle Components</br>
7- MVVM + LiveData </br>
