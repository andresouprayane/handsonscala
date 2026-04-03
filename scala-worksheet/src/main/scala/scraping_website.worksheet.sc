import $ivy.`org.jsoup:jsoup:1.13.1`, org.jsoup._

val doc = Jsoup.connect("http://en.wikipedia.org/").get()

val headlines = doc.select("#mp-itn b a")



