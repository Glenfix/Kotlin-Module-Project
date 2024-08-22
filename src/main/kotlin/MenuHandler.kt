import java.util.Scanner

class MenuHandler {
    private val scanner = Scanner(System.`in`)
    private val archives = mutableListOf<Archive>()

    fun showMainMenu() {
        val options = mutableListOf(
            MenuOption("Создать архив", ::createArchive),
            *archives.mapIndexed { index, archive -> MenuOption(archive.name) { openArchive(archive) } }.toTypedArray(),
            MenuOption("Выход", ::exitProgram)
        )
        showMenu("Список архивов", options)
    }

    private fun showMenu(title: String, options: List<MenuOption>) {
        while (true) {
            println("$title:")
            options.forEachIndexed { index, option -> println("${index + 1}. ${option.name}") }

            val input = readInput(options.size)
            options[input - 1].action()
        }
    }

    private fun createArchive() {
        println("Введите название архива:")
        val name = scanner.nextLine()
        if (name.isBlank()) {
            println("Название архива не может быть пустым!")
            return
        }
        archives.add(Archive(name))
        println("Архив '$name' создан.")
        showMainMenu()
    }

    private fun openArchive(archive: Archive) {
        val options = mutableListOf(
            MenuOption("Создать заметку") { createNoteInArchive(archive) },
            *archive.notes.mapIndexed { index, note -> MenuOption(note.title) { viewNoteInArchive(note) } }.toTypedArray(),
            MenuOption("Назад", ::showMainMenu)
        )
        showMenu("Архив '${archive.name}'", options)
    }

    private fun createNoteInArchive(archive: Archive) {
        println("Введите название заметки:")
        val title = scanner.nextLine()
        if (title.isBlank()) {
            println("Название заметки не может быть пустым!")
            return
        }

        println("Введите текст заметки:")
        val content = scanner.nextLine()
        if (content.isBlank()) {
            println("Текст заметки не может быть пустым!")
            return
        }

        archive.notes.add(Note(title, content))
        println("Заметка '$title' создана.")
        openArchive(archive)
    }

    private fun viewNoteInArchive(note: Note) {
        println("Заметка '${note.title}':")
        println(note.content)
        println("Нажмите Enter для возврата.")
        scanner.nextLine()
    }

    private fun exitProgram() {
        println("Выход из программы...")
        System.exit(0)
    }

    private fun readInput(maxOption: Int): Int {
        while (true) {
            try {
                val input = scanner.nextLine().toInt()
                if (input in 1..maxOption) {
                    return input
                }
                println("Некорректный выбор. Попробуйте снова.")
            } catch (e: NumberFormatException) {
                println("Пожалуйста, введите цифру.")
            }
        }
    }
}
