package com.example.noterssaver.domain.usecases

import com.example.noterssaver.domain.utils.OrderType
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.domain.repository.NotesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// Created by Shahid Iqbal on 3/13/2023.

class GetNotes(private val notesRepo: NotesRepo) {


    operator fun invoke(orderType: OrderType = OrderType.DESCENDING): Flow<List<Note>> {

        return notesRepo.getNotes().map {
            when (orderType) {
                OrderType.ASCENDING -> it
                OrderType.DESCENDING -> it.asReversed()
            }
        }
    }
}