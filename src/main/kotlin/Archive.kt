class Archive(val name: String) {
    val notes = mutableListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }
}

class Note(
    val title: String,
    val content: String
)