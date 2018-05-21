package com.arkhipov.ayur.rbplants

import io.realm.annotations.RealmModule

@RealmModule(
        classes = [(User::class)]
)
class AppRealmModule