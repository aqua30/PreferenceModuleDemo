package com.aqua30.local_preference

import kotlinx.coroutines.flow.Flow

/**
 * @author Saurabh Pant
 * @since 11/8/22
 * @summary Main contract for accessing or modifying the user related preference
 */
interface UserPreference {

    /**
     * returns user name flow
     * */
    fun userName(): Flow<String>

    /**
     * saves user name in data store
     * */
    suspend fun saveUserName(name: String)
}