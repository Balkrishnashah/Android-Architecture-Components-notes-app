# Android Architecture Components Example
-------------------------------------------------

This sample showcases the following Architecture Components:

* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [ViewModels](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)
* [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html)

<img src="https://developer.android.com/codelabs/android-room-with-a-view-kotlin/img/8e4b761713e3a76b.png" width="50%">


The View and ViewModel communicate  using LiveData and the following design principles:
* ViewModel objects expose data using `LiveData` objects. `LiveData` allows you to observe changes to data across multiple components of your app without creating explicit and rigid dependency paths between them.

* Views, including the recyclerView  used in this sample, subscribe to corresponding `LiveData` objects. Because `LiveData` is lifecycle-aware, it doesn’t push changes to the underlying data if the observer is not in an active state, and this helps to avoid many common bugs.


The database is created using Room and it has One entities: a `Note Entity` that generate corresponding SQLite tables at runtime.

Room populates the database asynchronously when it's created, via the `RoomDatabase#Callback`. To simulate low-performance, an artificial delay is added. To let 
 other components know when the data has finished populating, the `AppDatabase` exposes a 
 `LiveData` object.

To access the data and execute queries, you use a [Data Access Object](https://developer.android.com/topic/libraries/architecture/room.html#daos) (DAO). For example, a product is loaded with the following query:

```kotlin
    @Query("SELECT * FROM note_table WHERE id IN (:noteIds)")
    fun loadAllByIds(noteIds: IntArray): LiveData<List<Note>>
```


Queries that return a `LiveData` object can be observed, so when  a change in one of the affected record is detected, `LiveData` delivers a notification of that change to the registered observers.

The `DataRepository` exposes the data to the UI layer. To ensure that the UI uses the list of notes only after the database has been pre-populated.

Here is the sample video of the developed application :



