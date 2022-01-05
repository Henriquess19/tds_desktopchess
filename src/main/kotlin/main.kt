
/*
fun main(){
    val dbConnection = getDBConnectionInfo()

    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()

    driver.use{
        val mongoRepository = MongoDbChess(driver.getDatabase(dbConnection.dbName))
        application {
            MainWindow(mongoRepository,::exitApplication)
        }
    }
}*/
import UI.*
import androidx.compose.ui.window.*
import isel.leic.tds.tictactoe.storage.mongodb.createMongoClient
import model.domain.*
import model.storage.*
import model.ui.console.readChessCommand
import org.litote.kmongo.service.MongoClientProvider.createMongoClient

/*
fun main(){
    val dbConnection = getDBConnectionInfo()

    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()

    driver.use{
        val mongoRepository = MongoDbChess(driver.getDatabase(dbConnection.dbName))
        application {
            MainWindow(mongoRepository,::exitApplication)
        }
    }
}*/



fun main(){
    val dbConnection = getDBConnectionInfo()
    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()
    try {
        var localBoard = BoardState()
        val mongoDB = MongoDbChess(driver.getDatabase(dbConnection.dbName))
        while (true){
            val dispatcher = buildCommandsHandler(localBoard = localBoard, dbBoard = mongoDB)
            val(command,parameter) = readChessCommand(localBoardState = localBoard)
            val handler = dispatcher[command.uppercase()]
            if (handler == null) println("Invalid Command")
            else{
                when (val result = handler.action(parameter)){
                    is ExitResult -> break
                    is ValueResult<*> ->{
                        val resulted = result.data as toReturn
                        localBoard = resulted.boardState
                        handler.display(result.data)
                    }
                }
            }
        }
    }
    catch (e: ChessDBAccessException) {
        println(
            "An unknown error occurred while trying to reach the database. " +
                    if (dbConnection.mode == DbMode.REMOTE) "Check your network connection."
                    else "Is your local database started?"
        )
    }
    finally {
        println("Closing driver ...")
        driver.close()
    }
}
