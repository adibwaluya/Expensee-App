package de.htwberlin.expensee.data.repository

import com.google.firebase.firestore.Exclude
import java.io.Serializable

class UserObject : Serializable {

    var userName: String? = null
    var userEmail: String? = null
    var userPassword: String? = null

    @Exclude
    var isAuthenticated = false
    @Exclude
    var isNew = false
    @Exclude
    var isCreated = false
}