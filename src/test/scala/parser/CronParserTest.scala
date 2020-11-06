package parser

import org.scalatest.{FunSuite, Matchers}

class CronParserTest extends FunSuite with Matchers {
  val parser = new CronParser()

  test("parse cron strings") {
    parser.parse("0 0 12 * * /usr/bin/script.sh") shouldEqual
      """minute        0
        |hour          0
        |day of month  12
        |month         1 2 3 4 5 6 7 8 9 10 11 12
        |day of week   0 1 2 3 4 5 6
        |command       /usr/bin/script.sh""".stripMargin

    parser.parse("1-30,*/15,55 0 1,15 * 1-5 /usr/bin/find") shouldEqual
      """minute        0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 45 55
        |hour          0
        |day of month  1 15
        |month         1 2 3 4 5 6 7 8 9 10 11 12
        |day of week   1 2 3 4 5
        |command       /usr/bin/find""".stripMargin

    parser.parse("*/15 0 1,15 * 1-5 /usr/bin/find") shouldEqual
      """minute        0 15 30 45
        |hour          0
        |day of month  1 15
        |month         1 2 3 4 5 6 7 8 9 10 11 12
        |day of week   1 2 3 4 5
        |command       /usr/bin/find""".stripMargin

    parser.parse("*/13 0,4/17 1,3-7,21/10 1-3,10-11 0,2/3 /usr/bin/find") shouldEqual
      """minute        0 13 26 39 52
        |hour          0 4 21
        |day of month  1 3 4 5 6 7 21 31
        |month         1 2 3 10 11
        |day of week   0 2 5
        |command       /usr/bin/find""".stripMargin
  }
}
