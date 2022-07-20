package com.raywenderlich.android.jetnotes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.raywenderlich.android.jetnotes.domain.model.NoteModel
import com.raywenderlich.android.jetnotes.ui.components.Note
import com.raywenderlich.android.jetnotes.ui.components.TopAppBar
import com.raywenderlich.android.jetnotes.viewmodel.MainViewModel

@Composable
fun NoteScreen(viewModel: MainViewModel) {
    val notes: List<NoteModel> by viewModel
        .notesNotInTrash
        .observeAsState(listOf())
    Column {
        TopAppBar(title = "JetNotes", icon = Icons.Filled.List, onIconClick = {})
        NoteList(
            notes = notes,
            onNoteClick = { viewModel.onNoteClick(it) },
            onNoteCheckedChange = { viewModel.onNoteCheckedChange(it) }
        )
    }
}

@Composable
fun NoteList(
    notes: List<NoteModel>,
    onNoteClick: (NoteModel) -> Unit,
    onNoteCheckedChange: (NoteModel) -> Unit
) {
    LazyColumn {
        items(notes.size) { noteIndex ->
            val note = notes[noteIndex]
            Note(
                note = note,
                onNoteClick = onNoteClick,
                onNoteCheckedChange = onNoteCheckedChange
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NoteListPreview() {
    NoteList(
        notes = listOf(
            NoteModel(1, "Note 1", "Content 1", null),
            NoteModel(1, "Note 1", "Content 1", false),
            NoteModel(1, "Note 1", "Content 1", true)
        ),
        onNoteClick = {},
        onNoteCheckedChange = {}
    )
}