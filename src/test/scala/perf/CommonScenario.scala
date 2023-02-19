package perf

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.Actions._

object CommonScenario {
  def apply(): ScenarioBuilder = new CommonScenario().mainScenario
}

class CommonScenario {

  //open web page
  val open = group("open") {
    exec(webtours)
      .exec(welcomePl)
      .exec(navPl)
  }

  //login
  val login = group("login") {
    exec(loginPL)
      .exec(navPlHome)
      .exec(loginPlIntro)
  }

  //open page for buy ticket
  val flights = group("flights") {
    exec(welcomePlSearch)
      .exec(navPlFlights)
      .exec(reservationsPl)
  }

  //buy the ticket
  val buyTicket = group("buyTicket") {
    exec(reservationsPlFlightInformation)
      .exec(reservationsPlChooseTicket)
      .exec(reservationsPlBuyTicket)
  }

  //viewing tickets
  val ticketsPage = group("ticketsPage") {
    exec(welcomePlItinerary)
      .exec(navPlItinerary)
      .exec(itineraryPl)
  }

  //logout
  val signOff = group("signOff") {
    exec(welcomePlSignOff)
  }

  //request the main scenarion perfomance
  val mainScenario = scenario("mainScenario")
    .feed(Feeders.users)
    .feed(Feeders.arrivvalCity)
    .feed(Feeders.departureCity)
    .exec(open)
    .exec(login)
    .exec(flights)
    .exec(buyTicket)
    .exec(ticketsPage)
    .exec(signOff)
}
