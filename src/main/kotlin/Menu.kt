import java.util.Scanner

abstract class MenuScreen {
    private val scanner = Scanner(System.`in`)

    fun showMenu(title: String, options: List<Pair<String, () -> Unit>>) {
        while (true) {
            println("\n$title")
            options.forEachIndexed { index, option ->
                println("$index. ${option.first}")
            }
            println("${options.size}. Выход")

            print("Выберите опцию: ")
            val input = scanner.nextLine()

            try {
                val choice = input.toInt()

                if (choice in options.indices) {
                    options[choice].second.invoke()
                } else if (choice == options.size) {
                    println("Выход")
                    break
                } else {
                    println("Некорректный выбор. Попробуйте снова.")
                }
            } catch (e: NumberFormatException) {
                println("Введите число!")
            }
        }
    }

    protected fun readLine(prompt: String): String {
        print(prompt)
        return scanner.nextLine()
    }
}