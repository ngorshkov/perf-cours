package perf

import io.gatling.core.Predef._

object Feeders {

  val users = csv("users.csv").circular
  val departureCity = csv("departureCity.csv").random
  val arrivvalCity = csv("arrivialCity.csv").random
}