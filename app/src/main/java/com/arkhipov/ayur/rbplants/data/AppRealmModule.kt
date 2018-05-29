package com.arkhipov.ayur.rbplants.data

import io.realm.annotations.RealmModule

@RealmModule(
    classes = [(User::class)]
)
class AppRealmModule