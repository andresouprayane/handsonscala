class ImmutableTrie(val inputs: Seq[String]) {
  // class Node(var hasValue: Boolean,
  //            val children: collection.mutable.Map[Char, Node] = collection.mutable.Map())
  
  // val root = new Node(false)

  // for(word <- words) add(word)

  class Node(index: Int, inputs: Seq[String]) {
    val hasValue = inputs.exists(_.length == index)
    val children = {
      val filteredInputs = inputs.filter(_.length > index)
      for((childChar, childInputs) <- filteredInputs.groupBy(_.charAt(index)))
      yield (childChar, new Node(index + 1, childInputs))
    }
  }

  val root = new Node(0, inputs)

  // Define an ImmutableTrie class that has the same methods as the Trie class
  // but instead of a def add method it should take a sequence of strings during construction
  // and construct the data structure without any use of vars or mutable collections.


  def contains(s: String): Boolean = {
    var current = Option(root)
    for (c <- s if current.nonEmpty) current = current.get.children.get(c)
    current.exists(_.hasValue)
  }

  def prefixesMatchingString0(s: String): Set[Int] = {
    var current = Option(root)
    val output = Set.newBuilder[Int]
    for ((c, i) <- s.zipWithIndex if current.nonEmpty) {
      if (current.get.hasValue) output += i
      current = current.get.children.get(c)
    }
    if (current.exists(_.hasValue)) output += s.length
    output.result()
  }

  def prefixesMatchingString(s: String): Set[String] = {
    prefixesMatchingString0(s).map(s.substring(0, _))
  }

  def stringsMatchingPrefix(s: String): Set[String] = {
    var current = Option(root)
    for (c <- s if current.nonEmpty) current = current.get.children.get(c) // initial walk
    if (current.isEmpty) Set()
    else {
      val output = Set.newBuilder[String]
      def recurse(current: Node, path: List[Char]): Unit = {
        if (current.hasValue) output += (s + path.reverse.mkString)
        for ((c, n) <- current.children) recurse(n, c :: path)
      }
      recurse(current.get, Nil) // recursive walk
      output.result()
    }
  }
}

val t = new ImmutableTrie(Seq("mango", "mandarin", "map", "man"))







// val t = new Trie()

// t.add("mango")
// t.add("mandarin")
// t.add("map")
// t.add("man")
// t.add("pan")



t.contains("mango")
// t.contains("mandarine")
// t.contains("pan")


