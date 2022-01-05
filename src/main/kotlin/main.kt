import UI.*
import androidx.compose.ui.window.*
import model.storage.*
import model.storage.mongodb.*

fun main(){
    val dbConnection = getDBConnectionInfo()

    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()

    driver.use{
        val mongoRepository = MongoDbChess(driver.getDatabase(dbConnection.dbName))
        application {
            MainWindow(/* mongoRepository */ ::exitApplication)
        }
    }
}