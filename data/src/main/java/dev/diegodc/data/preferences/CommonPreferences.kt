package dev.diegodc.data.preferences

import dev.diegodc.data.preferences.model.Filters

interface CommonPreferences {
    fun saveFilters(filters: Filters)
    fun getFilters() : Filters
    fun clearFilters()
}