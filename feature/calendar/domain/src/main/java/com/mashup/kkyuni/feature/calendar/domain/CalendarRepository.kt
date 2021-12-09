package com.mashup.kkyuni.feature.calendar.domain

import com.mashup.kkyuni.feature.calendar.domain.model.DiaryEntity

interface CalendarRepository {
    suspend fun getDiary(date: String): DiaryEntity
    fun setPreview(boolean: Boolean)
    fun getPreview(): Boolean
    fun getAccessToken(): String?
}