package com.arkhipov.ayur.rbplants.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
        @PrimaryKey var userID: String = "def123",
        var token: String = "def321",
        var login: String = "def123",
        var name: String = "def123",
        var date: Long = 0L
) : RealmObject()