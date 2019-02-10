import java.net.ServerSocket

class ChatServer {
    fun serve(){
        val ss = ServerSocket(0)
        println("We have port " + ss.localPort)

        while (true){
            val s = ss.accept()

            val ci = CommandInterpreter(s)
            val t = Thread(ci)
            t.start()

        }
    }
}