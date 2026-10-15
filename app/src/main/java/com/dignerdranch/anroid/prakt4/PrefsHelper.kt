package com.dignerdranch.anroid.prakt4

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefsHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("ToDoPrefs", Context.MODE_PRIVATE)

    private val gson = Gson()

    companion object {
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_TASKS = "tasks_json"
    }

    fun saveUserName(name: String) {
        prefs.edit().putString(KEY_USER_NAME, name).apply()
    }

    fun getUserName(): String {
        return prefs.getString(KEY_USER_NAME, "") ?: ""
    }

    fun isAuthorized(): Boolean = getUserName().isNotEmpty()

    fun saveTasks(tasks: List<Task>) {
        val json = gson.toJson(tasks)
        prefs.edit().putString(KEY_TASKS, json).apply()
    }

    fun getTasks(): MutableList<Task> {
        val json = prefs.getString(KEY_TASKS, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Task>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }

    fun addTask(task: Task) {
        val tasks = getTasks()
        tasks.add(task)
        saveTasks(tasks)
    }

    fun clearAll() {
        prefs.edit().clear().apply()
    }
}