package com.example


val listOfGym = listOf(
    GymModel(1,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(2,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(3,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(4,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(5,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(6,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(7,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(8,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(9,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(10,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(11,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(12,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(13,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(14,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(15,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(16,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(17,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(18,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt"),
    GymModel(19,"UpTown Gym", "20 El-Gihad Mit Akaba, Agouza,Giza Governorate 3754234,Egypt")
)

data class GymModel(val id : Int , val gymName: String, val gymDescription: String,var isFavorite : Boolean = false)