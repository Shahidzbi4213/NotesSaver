package com.example.noterssaver.domain.usecase.notes

import com.example.noterssaver.data.model.Note
import com.example.noterssaver.domain.repository.NotesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GetNotesUseCase(private val notesRepo: NotesRepo) {

    operator fun invoke(
        orderType: OrderType = OrderType.DESCENDING,
        searchFlow: MutableStateFlow<String>
    ): Flow<List<Note>> {
        return notesRepo.getNotes().combine(searchFlow) { notes, search ->
            if (search.isBlank()) notes
            else notes.filter { it.title.contains(search, true) }
        }.map {
            when (orderType) {
                OrderType.ASCENDING -> it
                OrderType.DESCENDING -> it.asReversed()
            }
        }
    }
}

enum class OrderType {
    ASCENDING, DESCENDING
}