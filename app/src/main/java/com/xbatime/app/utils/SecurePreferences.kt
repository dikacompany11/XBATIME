package com.xbatime.app.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import timber.log.Timber

/**
 * Secure SharedPreferences untuk menyimpan token dan data sensitif
 */
class SecurePreferences(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "xbatime_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val gson = Gson()

    /**
     * Save token
     */
    fun saveToken(token: String) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, token).apply()
        Timber.d("Token saved")
    }

    /**
     * Get token
     */
    fun getToken(): String? {
        return prefs.getString(KEY_ACCESS_TOKEN, null)
    }

    /**
     * Save refresh token
     */
    fun saveRefreshToken(token: String) {
        prefs.edit().putString(KEY_REFRESH_TOKEN, token).apply()
    }

    /**
     * Get refresh token
     */
    fun getRefreshToken(): String? {
        return prefs.getString(KEY_REFRESH_TOKEN, null)
    }

    /**
     * Save user ID
     */
    fun saveUserId(userId: String) {
        prefs.edit().putString(KEY_USER_ID, userId).apply()
    }

    /**
     * Get user ID
     */
    fun getUserId(): String? {
        return prefs.getString(KEY_USER_ID, null)
    }

    /**
     * Save user role
     */
    fun saveUserRole(role: String) {
        prefs.edit().putString(KEY_USER_ROLE, role).apply()
    }

    /**
     * Get user role
     */
    fun getUserRole(): String? {
        return prefs.getString(KEY_USER_ROLE, null)
    }

    /**
     * Save generic JSON data
     */
    fun <T> saveData(key: String, data: T) {
        val json = gson.toJson(data)
        prefs.edit().putString(key, json).apply()
    }

    /**
     * Get generic JSON data
     */
    inline fun <reified T> getData(key: String, defaultValue: T? = null): T? {
        return try {
            val json = prefs.getString(key, null)
            if (json != null) {
                gson.fromJson(json, T::class.java)
            } else {
                defaultValue
            }
        } catch (e: Exception) {
            Timber.e(e, "Error deserializing data for key: $key")
            defaultValue
        }
    }

    /**
     * Check if logged in
     */
    fun isLoggedIn(): Boolean {
        return !getToken().isNullOrEmpty()
    }

    /**
     * Clear all (logout)
     */
    fun clear() {
        prefs.edit().clear().apply()
        Timber.d("SharedPreferences cleared")
    }

    /**
     * Remove specific key
     */
    fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_ROLE = "user_role"

        @Volatile
        private var INSTANCE: SecurePreferences? = null

        fun getInstance(context: Context): SecurePreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SecurePreferences(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
