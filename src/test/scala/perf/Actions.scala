package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Actions {


  //------------------open---------------------
  val webtours = http("/webtours/")
    .get("/webtours/")
    .check(status is 200)

  val welcomePl = http("/cgi-bin/welcome.pl")
    .get("/cgi-bin/welcome.pl")
    .queryParam("singOff", "true")
    .check(status is 200)

  val navPl = http("/cgi-bin/nav.pl")
    .get("/cgi-bin/nav.pl")
    .queryParam("in", "home")
    .check(status is 200)
    .check(regex("""name="userSession" value="(.+)"""").saveAs("userSession"))

  //------------------login---------------------
  val loginPL = http("/cgi-bin/login.pl")
    .post("/cgi-bin/login.pl")
    .formParam("userSession", "${userSession}")
    .formParam("username", "${login}")
    .formParam("password", "${password}")
    .formParam("login.x", "77")
    .formParam("login.y", "14")
    .formParam("JSFromSubmit", "off")
    .check(status is 200)

  //------------------open home page---------------------
  val navPlHome = http("/cgi-bin/nav.pl?page=menu&in=home")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "home")
    .check(status is 200)

  val loginPlIntro = http("/cgi-bin/login.pl?intro=true")
    .get("/cgi-bin/login.pl")
    .queryParam("intro", "true")
    .check(status is 200)

  //------------------buy ticket---------------------
  val welcomePlSearch = http("/cgi-bin/welcome.pl?page=search")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page", "search")
    .check(status is 200)

  val navPlFlights = http("/cgi-bin/nav.pl?page=menu&in=flights")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "flights")
    .check(status is 200)

  val reservationsPl = http("/cgi-bin/reservations.pl?page=welcome")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page", "welcome")
    .check(status is 200)

  //data for ticket
  val reservationsPlFlightInformation = http("/cgi-bin/reservations.pl (заполнение информации о покупке билета)")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", "0")
    .formParam("depart", "${departureCity}")
    .formParam("departDate", "02/05/2023")
    .formParam("arrive", "${arrivialCity}")
    .formParam("returnDate", "02/15/2023")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "First")
    .formParam("findFlights.x", "37")
    .formParam("findFlights.y", "10")
    .check(status is 200)
    .check(
      regex("""name="outboundFlight" value="(.+)" checked="checked"""").saveAs("outboundFlight")
    )

  //choose time for flight
  val reservationsPlChooseTicket = http("/cgi-bin/reservations.pl (выбор билета из списка)")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "${outboundFlight}")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    .formParam("seatType", "Business")
    .formParam("seatPref", "Window")
    .formParam("findFlights.x", "54")
    .formParam("findFlights.y", "5")
    .check(status is 200)

  //buy ticket
  val reservationsPlBuyTicket = http("/cgi-bin/reservations.pl (покупка билета)")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName", "nik")
    .formParam("lastName", "nik")
    .formParam("address1", "nik")
    .formParam("address1", "nik")
    .formParam("pass1", "nik nik")
    .formParam("creditCard", "")
    .formParam("expDate", "")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("outboundFlight", "${outboundFlight}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "44")
    .formParam("buyFlights.y", "2")

  //------------------viewing ticket---------------------
  val welcomePlItinerary = http("/cgi-bin/welcome.pl?page=itinerary")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page", "itinerary")
    .check(status is 200)

  val navPlItinerary = http("/cgi-bin/nav.pl/page=menu&in=itinerary")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "itinerary")
    .check(status is 200)

  val itineraryPl = http("/cgi-bin/itinerary.pl")
    .get("/cgi-bin/itinerary.pl")
    .check(status is 200)

  //------------------log out---------------------
  val welcomePlSignOff = http("/cgi-bin/welcome.pl?signOff=1")
    .get("/cgi-bin/welcome.pl")
    .queryParam("signOff", "1")
    .check(status is 200)
}
