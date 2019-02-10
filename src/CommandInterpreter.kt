import java.io.PrintStream
import java.net.Socket
import java.util.*

class CommandInterpreter(val s: Socket): Runnable, ChatObserver {
    val scanner = Scanner(s.getInputStream())
    val printer = PrintStream(s.getOutputStream(), true)

    override fun newMessage(message: ChatMessage) {


        printer.println(message)

    }

    override fun run() {

        ChatHistory.register(this)
        var name = ""
        var chatCount = 0

        while (true) {

            var input = scanner.nextLine()

            if (input!!.startsWith(":username")) { // If input starts with :username start loop.

                input = input.substring(10) // Take :username part out of input

                if (!Users.checkUser(input)) {  // If there is no same name start loop.

                    Users.removeUser(name) // Remove previous username
                    Users.addUser(input) // Add user to set in Users
                    printer.println("Name set")
                    name = input

                } else { // Run this if given username is already in use

                    printer.println("Username already in use, try something else.")
                    printer.println("Usernames already in use: ${Users.users}")

                }
            }else if(input.startsWith(":removeuser")){ // If input starts with :removeuser start loop.

                input = input.substring(12) // Take :removeuser part out of input

                if (!Users.checkUser(input)){ // If there is given username start loop

                    Users.removeUser(input) // Remove given username
                    printer.println("User $input removed")

                }else{

                    printer.println("User not found.")
                }

            }else if(input.startsWith(":history")){ // If input starts with :history start loop

                printer.println(ChatHistory.toString()) 

            }else if (input == ":quit") {
                break
            }else if(input == ":users"){ // If input starts with :users start loop
                printer.println(Users.toString())
            }else if ( Users.checkUser(name)){

                chatCount += 1
                TopChatter.insert(name, chatCount)
                ChatHistory.insert(ChatMessage(name ,input)) // Add user and message to ChatHistory
                TopChatter.message()


            }else{

                printer.println("Add user by typing :username 'username' ")

            }

            println()
        }
        scanner.close()
    }
}
