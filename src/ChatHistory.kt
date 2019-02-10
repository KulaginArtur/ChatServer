object ChatHistory : ChatObservable{
    private val allChat : MutableList<ChatMessage> = mutableListOf()
    private val chatObservers : MutableSet<ChatObserver> = mutableSetOf()

    fun insert(message: ChatMessage){
        allChat.add(message)
        notify(message)
    }
    override fun toString(): String{
        var result = ""
        for (chat in allChat){
            chat.toString()
            result = "$result\n $chat"
        }
        return result
    }

    override fun register(who: ChatObserver) {
        chatObservers.add(who)
    }

    override fun notify(message: ChatMessage) {
        for (c in chatObservers){
            c.newMessage(message)
        }
    }

    override fun deregister(who: ChatObserver) {
        chatObservers.remove(who)
    }

}

interface ChatObserver{
    fun newMessage (message: ChatMessage)
}


interface ChatObservable {
    fun register(who: ChatObserver)
    fun deregister(who: ChatObserver)
    fun notify(message: ChatMessage)
}
