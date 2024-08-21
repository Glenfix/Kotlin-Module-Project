class NoteViewScreen(private val note: Note) : MenuScreen() {
    fun show() {
        println("\nЗаметка: ${note.title}")
        println("Содержание:\n${note.content}")
        readLine("Нажмите Enter для возврата.")
    }
}