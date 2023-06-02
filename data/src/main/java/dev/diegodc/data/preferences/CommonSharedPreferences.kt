package dev.diegodc.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dev.diegodc.data.preferences.model.Filters
import kotlinx.serialization.json.Json

class CommonSharedPreferences(
    context: Context
) : CommonPreferences {

    companion object {
        private const val NAME = "common"
        private const val FILTERS = "filters"
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    private val json by lazy {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    override fun saveFilters(filters: Filters) {
        prefs.edit {
            putString(FILTERS, json.encodeToString(Filters.serializer(), filters))
        }
    }

    override fun getFilters(): Filters {
        return prefs.getString(FILTERS, null).let {
            if (it == null)
                Filters()
            else
                json.decodeFromString(Filters.serializer(), it)
        }
    }

    override fun clearFilters() {
        prefs.edit{
            remove(FILTERS)
        }
    }
}