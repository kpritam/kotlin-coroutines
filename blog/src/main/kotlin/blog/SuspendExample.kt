package blog

data class User(val name: String, val id: Long, val address: String)
suspend fun updateUserInfo(name: String, id: Long): User {
    TODO()
}