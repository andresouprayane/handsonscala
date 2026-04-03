class Msg (id: Int, parentId : Option[Int], message : String) {
    val msg = message
    val parId = parentId    
}


val messages = Array(new Msg(1,None,"H"),
    new Msg(2,Some(1),"e"),
    new Msg(3,None,"l"),
    new Msg(4,Some(3),"l"),
    new Msg(5,Some(4),"o"))


def printMsg(messages : Array[Msg]) = {
    var indent = ""
    for(currentMsg <- messages) {
        if(!currentMsg.parId.isEmpty)
            {
            indent = indent + "   "
            }
        else if(indent == "")
            {
            }
        else
            indent = indent.replace("   ","")
        println(indent + currentMsg.msg)           
    }
}

printMsg(messages)


def printMsg2(messages : Array[Msg]) : Unit = {

    def printFrag(messages : Array[Msg], indent : String) : Unit =
        {
        val currentMsg = messages(0)
        var indentCurrent = indent

        //var indent = ""    
        if(!currentMsg.parId.isEmpty)
            {
            indentCurrent = indentCurrent + "   "
            }
        else if(indentCurrent == "")
            {
            }
        else
            indentCurrent = indentCurrent.replace("   ","")

        println(indentCurrent + currentMsg.msg)

        if (messages.length > 0)
            {
            printFrag(messages.drop(1),indentCurrent)
            }
            
        }
    printFrag(messages,"")
}

//printMsg2(messages)


class Msg2(val id: Int, val parent: Option[Int], val txt: String)

val messages2 = Array(new Msg2(1,None,"H"),
    new Msg2(2,Some(1),"e"),
    new Msg2(3,None,"l"),
    new Msg2(4,Some(3),"l"),
    new Msg2(5,Some(4),"o"))


def printMessages(messages: Array[Msg2]): Unit = {
  def printFrag(parent: Option[Int], indent: String): Unit = {
    for (msg <- messages if msg.parent == parent) {
      println(s"$indent#${msg.id} ${msg.txt}")
      printFrag(Some(msg.id), indent + "    ")
    }
  }
  printFrag(None, "")
}

printMessages(messages2)


