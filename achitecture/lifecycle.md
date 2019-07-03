# Android-Lifecycle

Fragments, Activities are already implemented LifecycleOwner. So we can call `ViewModelProviders.of(this)...`

## Get ViewModel in LifeCycleOwner (Fragments, Activities)

```java
    mSeekBarViewModel = ViewModelProviders.of(this).get(SeekBarViewModel.class);
    mSeekBarViewModel = ViewModelProviders.of(getActivity()).get(SeekBarViewModel.class);
```

> the `owner` in `of(this)` of this snippet should be the real LifecycleOwner. Depends on situation.


## `MutableLiveData` supports Observer mode

A basic ViewModel might contains many data like this:

```java
public class MyViewModel extends ViewModel {
    public MutableLiveData<Integer> value = new MutableLiveData<>();
}
```

In this case, this `ViewModel` supports `Observable` mode

### Observable

```java
    myViewModel.value.postValue(0611);
```

### Observer

```java
myViewModel.value.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                // ...
            }
        });
```

## Called on desinated stage

The below will be called when onResume() of it's lifecyele owner

```java
static class BoundLocationListener implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void addLocationListener() {...}
}
```

## SavedStateVMFactory

In owner:

```java
    mSavedStateViewModel = ViewModelProviders.of(this, new SavedStateVMFactory(this))
            .get(SavedStateViewModel.class);
```

In ViewModel, provide another constructor takes `SavedStateHandle`

```java
public class SavedStateViewModel extends ViewModel {
    private SavedStateHandle mState;
    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        mState = savedStateHandle;
    }
    //...
}
```

And now we can use get/set method of handle to access the immutable data.

```java
private static final String NAME_KEY = "name";

// Expose an immutable LiveData
LiveData<String> getName() {
    return mState.getLiveData(NAME_KEY);
}

void saveNewName(String newName) {
    mState.set(NAME_KEY, newName);
}
```
