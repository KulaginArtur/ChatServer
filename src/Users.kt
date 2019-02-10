object Users {
    val users : HashSet<String> = hashSetOf()

    fun addUser(user : String){
        users.add(user)
    }

    fun removeUser(user: String){
        users.remove(user)
    }

    fun checkUser(user: String): Boolean{
        return user in users
    }

    override fun toString(): String {
        var result = ""
        for (user in users) {
            result = "$result\n $user"
        }
        return result
    }
}