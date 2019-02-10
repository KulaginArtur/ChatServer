object TopChatter: ChatObserver {

    private val allChatters : MutableMap<String, Int> = mutableMapOf()

    fun insert(name : String ,chatCount : Int ){
        allChatters[name] = chatCount
    }
    override fun newMessage(message: ChatMessage) {
        // Change list of topChatters when new message comes

    }
    fun message() {
        var topChatters = allChatters
        println(topChatters)
    }
}