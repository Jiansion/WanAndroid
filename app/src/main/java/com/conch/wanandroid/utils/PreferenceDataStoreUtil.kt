package com.conch.wanandroid.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "wanandroid")

/**
 * @author YeJain
 * @date 2022/8/8 12:11
 * 数据持久化操作工具
 */
class PreferenceDataStoreUtil(private val context: Context) {

    fun getString(key: String, defaultValue: String? = null): Flow<String?> {
        return context.dataStore.data.map {
            it[stringPreferencesKey(key)] ?: defaultValue
        }
    }


    fun getInt(key: String, defaultValue: Int = 0): Flow<Int> {
        return context.dataStore.data.map {
            it[intPreferencesKey(key)] ?: defaultValue
        }
    }

    fun getDouble(key: String, defaultValue: Double = 0.0): Flow<Double> {
        return context.dataStore.data.map {
            it[doublePreferencesKey(key)] ?: defaultValue
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Flow<Boolean> {
        return context.dataStore.data.map {
            it[booleanPreferencesKey(key)] ?: defaultValue
        }
    }

    fun getFloat(key: String, defaultValue: Float = 0f): Flow<Float> {
        return context.dataStore.data.map {
            it[floatPreferencesKey(key)] ?: defaultValue
        }
    }


    fun getLong(key: String, defaultValue: Long = 0L): Flow<Long> {
        return context.dataStore.data.map {
            it[longPreferencesKey(key)] ?: defaultValue
        }
    }

    fun getStringSet(key: String): Flow<Set<String>?> {
        return context.dataStore.data.map {
            it[stringSetPreferencesKey(key)]
        }
    }

    /**
     * 保存数据
     * @param key 保存的 key
     * @param data 需要缓存的数据数据类型为 [Int] [Double] [String] [Boolean] [Float] [Long]
     */
    suspend fun saveData(key: String, data: Any) {
        context.dataStore.edit {
            when (data) {
                is Int -> it[intPreferencesKey(key)] = data
                is Double -> it[doublePreferencesKey(key)] = data
                is String -> it[stringPreferencesKey(key)] = data
                is Boolean -> it[booleanPreferencesKey(key)] = data
                is Float -> it[floatPreferencesKey(key)] = data
                is Long -> it[longPreferencesKey(key)] = data
                else -> throw ClassCastException("保存的数据类型错误")
            }
        }
    }

    /**
     * 保存  Set<String> 类型数据
     */
    suspend fun saveStringSet(key: String, data: Set<String>) {
        context.dataStore.edit {
            it[stringSetPreferencesKey(key)] = data
        }
    }

}