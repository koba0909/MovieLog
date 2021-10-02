package com.mashup.kkyuni.playlist.domain.model

sealed class PlayListState {
    object Progress: PlayListState()

    data class Success(
        val list: List<MusicModel>
    ): PlayListState()

    data class Fail(
        val message: String
    ): PlayListState()
}