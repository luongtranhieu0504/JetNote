@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetnote.screen


import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import com.example.jetnote.util.formatDate
import java.time.format.DateTimeFormatter


@SuppressLint("UnrememberedMutableState")
@Composable
fun NoteScreen(
        notes: List<Note>,
        onAddNote: (Note) -> Unit,
        onRemoveNote: (Note) -> Unit,
        onUpdateNote: (Note) -> Unit
            ) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var selectedNoteIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name))},
            actions = {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Icon",
                    )
            },
            backgroundColor = Color(0xFFDADFE3)
        )

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            NoteInputText(modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        })title = it
                })

            NoteInputText(modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp),
                text = description,
                label = "Add to note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        })description = it
                })

            NoteButton(text = "Save",
                onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()){
                    onAddNote(Note(tile = title, description = description))
                    title = ""
                    description = ""
                    Toast.makeText(context,"Add Note",Toast.LENGTH_SHORT).show()
                }
            })
        }
        Divider(modifier = Modifier.padding(6.dp))
        LazyColumn{
            items(notes){note ->
                NoteRow(note = note,
                    onNoteClicked = {onRemoveNote(note)},
                    onNotePress = {onUpdateNote(note)
                        var index = notes.indexOfFirst { it.id == note.id }
                        title = note.tile
                        description = note.description
                        selectedNoteIndex = index
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit,
    onNotePress: (Note) -> Unit
            ){
    Surface(modifier = Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xFFDFE6EB)) {
        Column(
            modifier
                .combinedClickable(
                    onClick = { onNoteClicked(note) },
                    onLongClick = { onNotePress(note) }
                )
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
                horizontalAlignment = Alignment.Start) {

            Text(text = note.tile,
                style = MaterialTheme.typography.subtitle2)

            Text(text = note.description,
                style = MaterialTheme.typography.subtitle2)

            Text(text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.caption)
        }
    }
}





@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {}, onUpdateNote = {})
}