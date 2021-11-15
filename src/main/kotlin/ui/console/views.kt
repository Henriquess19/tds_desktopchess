package ui.console

typealias View = (input: Any?) -> Unit

fun getViews(): Map<String,View>{
   return mapOf(
      "OPEN" to ::openView,
      "JOIN" to ::joinView,
      "PLAY" to ::playView,
      "REFRESH" to ::refreshView,
      "MOVES" to ::movesView,
      "EXIT" to ::exitView,
   )
}

fun openView(input: Any?) {
   TODO()
}

fun joinView(input: Any?) {
   TODO()
}
fun playView(input: Any?) {
   TODO()
}
fun refreshView(input: Any?) {
   TODO()
}
fun movesView(input: Any?) {
   TODO()
}
fun exitView(input: Any?) {
   TODO()
}