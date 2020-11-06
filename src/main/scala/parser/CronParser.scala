package parser

class CronParser {
  private def parse(string: String, startValue: Int, upperLimit: Int): Seq[Int] = {
    def parse(string: String): Seq[Int] = string match {
      case "*" => startValue until upperLimit

      case string if string.contains("/") =>
        val startAndStep = string.split("/")

        val (start, step) = (startAndStep.head, startAndStep.last.toInt)

        if (start == "*") {
          startValue until upperLimit by step
        } else {
          start.toInt until upperLimit by step
        }

      case string if string.contains("-") =>
        val range = string.split("-")

        range.head.toInt to range.last.toInt

      case string => Seq(string.toInt)
    }

    string.split(",").flatMap(parse).distinct.sorted
  }

  def parse(string: String): String = {
    val parts = string.split(" ")

    if (parts.size != 6) {
      throw new IllegalArgumentException(s"$string is not valid")
    } else {
      s"""minute        ${parse(parts(0), 0, 60).mkString(" ")}
         |hour          ${parse(parts(1), 0, 24).mkString(" ")}
         |day of month  ${parse(parts(2), 1, 32).mkString(" ")}
         |month         ${parse(parts(3), 1, 13).mkString(" ")}
         |day of week   ${parse(parts(4), 0, 7).mkString(" ")}
         |command       ${parts(5)}""".stripMargin
    }
  }
}

object CronParserMain {
  def main(args: Array[String]): Unit = {
    val parser = new CronParser()

    println(parser.parse(args(0)))
  }
}
