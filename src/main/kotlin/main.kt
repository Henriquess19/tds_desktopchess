// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import UI.*
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.FabPosition.Companion.Center
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import model.domain.*
import model.storage.*
import model.storage.mongodb.*
import org.intellij.lang.annotations.JdkConstants
import org.jetbrains.skija.paragraph.Alignment
import org.litote.kmongo.MongoOperator


/*
fun main(){
   val dbConnection = getDBConnectionInfo()
   val driver =
      if (dbConnection.mode == DbMode.REMOTE)
         createMongoClient(dbConnection.connectionString)
      else createMongoClient()
   try {
      var localBoard = BoardState(openBoard = false)
      val mongoDB = MongoDbChess(driver.getDatabase(dbConnection.dbName))
      while (true){
         val dispatcher = buildCommandsHandler(localBoard = localBoard, dbBoard = mongoDB)
         val(command,parameter) = readChessCommand(localBoardState = localBoard)
         val handler = dispatcher[command.uppercase()]
         if (handler == null) println("Invalid Command")
         else{
            when (val result = handler.action(parameter)){
               is ExitResult -> break
               is ValueResult<*>->{
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
*/



@Composable
fun openGameView(mongoDB: MongoDbChess, ids:String,onSelected:(MovesList) -> Unit){
    Column {
        val id = remember{ mutableStateOf("-1")}
        val result = remember{ mutableStateOf(MovesList(_id = id.value))}
        Text(text = "Open a Game ", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, modifier= Modifier.background(color = Color.Gray) )
        Button(onClick = {
            id.value = ids

        }){
            if(id.value.toInt() > 0) {
                if (mongoDB.gamesIDList().contains(element = id.value)) {
                    val movesList = mongoDB.findgamebyId(id = id.value)
                    //val playsMade = movesList.content
                    //val team = if (playsMade.isEmpty()) Team.WHITE else playsMade.last().team.other
                    result.value = movesList

                } else {
                    mongoDB.createID(id = id.value)
                    mongoDB.createGame(MovesList(_id = id.value))
                    result.value = MovesList(_id = id.value)
                }
            }
            onSelected(result.value)
        }
    }
}



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