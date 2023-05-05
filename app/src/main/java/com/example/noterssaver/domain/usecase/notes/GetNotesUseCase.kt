package com.example.noterssaver.domain.usecase.notes

import com.example.noterssaver.data.model.Note
import com.example.noterssaver.domain.repository.NotesRepo
import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.presentation.setting.util.OrderType
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class GetNotesUseCase(private val notesRepo: NotesRepo, private val settingRepo: SettingRepo) {

    operator fun invoke(
        searchFlow: MutableStateFlow<String>
    ): Flow<List<Note>> {
        return notesRepo.getNotes().combine(searchFlow) { notes, search ->
            if (search.isBlank()) notes
            else notes.filter {
                it.title.contains(search, true) || it.content.contains(search, true)
            }
        }.combine(settingRepo.getCurrentSortingOrder()) { notes, order ->
            when (order) {
                OrderType.ASCENDING -> notes.sortedBy { note -> note.title }
                OrderType.DESCENDING -> notes.sortedByDescending { note -> note.title }
                OrderType.TIME -> notes.sortedByDescending { note -> note.timestamp }
            }
        }
    }
}

