package com.arkhipov.ayur.rbplants.data.model

import com.google.firebase.auth.FirebaseUser

data class UserFire(
    var userId: String = "",
    var token: String = "",
    var login: String = "",
    var email: String = "",
    var fullName: String = "",
    var phoneNumber: String = "",
    var isEmailVerified: Boolean = false,
    var role: String = "",
    var score: Long = 0L, // Score count
    var groupId: String
)