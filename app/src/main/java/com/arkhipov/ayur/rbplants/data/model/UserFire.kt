package com.arkhipov.ayur.rbplants.data.model

import com.google.firebase.auth.FirebaseUser

data class UserFire(
    var uId: String = "",
    var email: String = "",
    var fullName: String = "",
    var phoneNumber: String = "",
    var role: String = "",
    var score: Long = 0L, // Score count
    var groupId: String = ""
) {
    companion object {
        val TYPE_ADMIN = "role_admin"
        val TYPE_USER = "role_user"

        fun importDoc(document: MutableMap<String, Any>): UserFire {
            val user = UserFire()
            document.forEach { key, value ->
                when (key) {
                    "token" -> user.uId= value as String
                    "email" -> user.email = value as String
                    "fullName" -> user.fullName = value as String
                    "phoneNumber" -> user.phoneNumber = value as String
                    "role" -> user.role = value as String
                    "score" -> user.score = value as Long
                    "groupId" -> user.groupId = value as String
                }
            }
            return user
        }

        fun getCollection(): String = "users"
    }
}

data class UserFirePhotos(
    var datetime: String = "",
    var pathToPhoto: String = "",
    var status: String = ""
) {
    companion object {
        fun getCollection() = "photos"
    }
}