# Stocks App

# Architecture used - MVVM
This application makes use of the MVVM architecture for development purpose.
    Reasons for using MVVM architecture
    
        1. Enhances separation of concerns
        2. Easier testing and modularity
     
Dagger is used for dependency injection. 
    Reasons for using Dagger
    
        1. Tons of documentations, tutorial and supports available on this library.
        2. Dagger 2 is an improved version from Dagger 1. It is no longer using Reflection, which makes its runtime much efficient than its previous generation.

Rx Java along with Retrofit is used for making the API calls/Networking layer.
    Reasons for using RxJava
    
        1. RxJava is already quite well established.
        2. Implementation of real time features in future. 

Live data with View Model is used for following an observable pattern to notify the UI of any changes.
    Reasons for using Live Data
    
        1. Ensures that UI matches the data state.
        2. Avoids memory leaks as observers are lifecycle aware.
        3. Handles configuration changes
Use of Data binding for the UI.
    Reasons for using data binding
        
        1. To allow the view and model to communicate directly with each other
App follows a single Activity architecture.
