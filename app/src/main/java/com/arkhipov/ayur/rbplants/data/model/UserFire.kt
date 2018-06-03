package com.arkhipov.ayur.rbplants.data.model

import com.google.firebase.auth.FirebaseUser

data class UserFire(
    //var userId: String = "",
    var email: String = "",
    var fullName: String = "",
    var token: String = "",
    var phoneNumber: String = "",
    var role: String = "",
    var score: Long = 0L, // Score count
    var groupId: String = ""
) : FirestorePojo {

    override fun getDocument(): String = "users"

    companion object {
        val TYPE_ADMIN = "role_admin"
        val TYPE_USER = "role_user"

        fun importDoc(document: MutableMap<String, Any>): UserFire {
            val user = UserFire()
            document.forEach { key, value ->
                when (key) {
                    "email" -> user.email = value as String
                    "fullName" -> user.fullName = value as String
                    "token" -> user.token = value as String
                    "phoneNumber" -> user.phoneNumber = value as String
                    "role" -> user.role = value as String
                    "score" -> user.score = value as Long
                    "groupId" -> user.groupId = value as String
                }
            }
            return user
        }
    }
}