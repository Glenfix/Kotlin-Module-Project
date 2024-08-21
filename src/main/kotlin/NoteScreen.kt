class ArchiveNoteScreen(private val archive: Archive) : MenuScreen() {
    fun show() {
        showMenu("Архив: ${archive.name}", buildMenuOptions())
    }

    private fun buildMenuOptions(): List<Pair<String, () -> Unit>> {
        val options = mutableListOf<Pair<String, () -> Unit>>()

        options.add("Создать заметку" to ::createNote)
        archive.notes.forEach { note ->
            options.add(note.title to { NoteViewScreen(note).show() })
        }

        return options
    }

    private fun createNote() {
        val title = readLine("Введите заголовок заметки: ")
        val content = readLine("Введите текст заметки: ")

        if (title.isNotBlank() && content.isNotBlank()) {
            archive.addNote(Note(title, content))
            println("Заметка '$title' создана.")
        } else {
            println("Заметка не может быть без заголовка или текста.")
        }
    }
}