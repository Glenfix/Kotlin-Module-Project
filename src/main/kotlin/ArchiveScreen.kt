class ArchiveScreen : MenuScreen() {
    private val archives = mutableListOf<Archive>()

    fun show() {
        showMenu("Список архивов", buildMenuOptions())
    }

    private fun buildMenuOptions(): List<Pair<String, () -> Unit>> {
        val options = mutableListOf<Pair<String, () -> Unit>>()

        options.add("Создать архив" to ::createArchive)
        archives.forEach { archive ->
            options.add(archive.name to { ArchiveNoteScreen(archive).show() })
        }

        return options
    }

    private fun createArchive() {
        val name = readlnOrNull()
        if (name != null) {
            if (name.isNotBlank()) {
                archives.add(Archive(name))
                println("Архив '$name' создан.")
            } else {
                println("Название архива не может быть пустым.")
            }
        }
    }
}