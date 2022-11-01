# Albums
# Leboncoin interview
An application to consume a web service in order to list the elements returned in a list.
This project is developed by Clean Architecture (MVVM+LiveData+Navigation) and it's composed of 3 modules.

## Modules

 - [app]
   Contains the UI (Activities and fragments) and the view model objects
     - [di] We define the different dependency injection modules. We use #DaggerHilt for this part
     - [view] Activities and fragments
     - [vieModel] The controller for each fragment. We use #LiveData + #Coroutine to retrieve and observe the needed data

 - [data]
   In this module we have implemented the service layer to retrieve remote data (by calling the API) and the management of the local database which is used as a cache system
     - [dataSource]
        - [local:] Data base Layer: We used #Room library to create the local data base
        - [remote] Api layer: We use #Retrofit2 to call our Api
        - [dto] When we can find the data transfer objects
        - [mapping] objects used to mapping the dto objects to model objects or the opposite
        - [repositoriesImp] whe we found the implementation of the different repositories interface prensented in the domain module

 - [domain]
In this module, we can find all application models and logic,
    - [dataResult] The object form that will be consumed on screen controller, it contain the data state after each call.
    - [entities]
    - [repositories] The different abstract interfaces that contains the differents method to invoke api or local dataBase
    - [usecase] It's the logic for each part that it use the repositories to communicate with api layer or local data base layer