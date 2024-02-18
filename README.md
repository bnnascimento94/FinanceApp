Finance App
This is the application to you track your finances and see how much you spend your money in each category and account.

📚 Android tech stack
This Application was developed using the latest technologies in android native development market.

🧑🏻‍💻 Android development: 
- Kotlin
- Jetpack Compose
- Coroutines
- Hilt
- Room Database
- Firebase
- Modularization

🏛 Architecture:

The Archtecture used is based on MVVM (recommended by Google) and Clean Archtecture. For better comparison the app has two branches, one in a single module and the second one in a multimodular archtecture. 
The chosen approach for the multimodular archtecture was modularization by layer.

Let's take a look in each major module of the application:

app - The Application module. It contains all the initialization logic for the Android environment.
common - Where is the common resources used by other modules.
data - The module containing the repository logic and access to datasource
domain - The modules containing the most important part of the application: the business logic. This module depends only on itself and all interaction it does is via dependency inversion.
ui - Contains all the screens of application
room - Module that contains local database
sharedpreferences - Module which contains sharedpreferences file.

