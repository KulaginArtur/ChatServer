import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

open class ChatMessage(private val user: String, private val message: String) {
    override fun toString(): String {

        val current = LocalDateTime.now() // Adding time to message
        val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        val formatted = current.format(formatter)

        return "$user : $message\n$formatted"
    }
}