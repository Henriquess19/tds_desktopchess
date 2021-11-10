fun main(){
    /*
    val driver =
        if (checkEnvironment() == DbMode.REMOTE)
            createMongoClient(System.getenv(ENV_DB_CONNECTION))
        else createMongoClient()

    val board: Board = MongoDbChess(driver.getDatabase(System.getenv(ENV_DB_NAME)))
    */

}

/*
private const val ENV_DB_NAME = "MONGO_DB_NAME"
private const val ENV_DB_CONNECTION = "MONGO_DB_CONNECTION"

private enum class DbMode { LOCAL, REMOTE }

private fun checkEnvironment(): DbMode {
    requireNotNull(System.getenv(ENV_DB_NAME)) {
        "You must specify the environment variable $ENV_DB_NAME"
    }

    return if (System.getenv(ENV_DB_CONNECTION) != null) DbMode.REMOTE
    else DbMode.LOCAL
}
*/