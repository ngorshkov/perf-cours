package perf

import io.gatling.http.Predef._
import io.gatling.core.Predef._


class TestSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://webtours.load-test.ru:1090")
    .acceptHeader("text/html,application/xhtml+xml,appplication/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .disableFollowRedirect

  setUp(
    CommonScenario().inject(rampUsersPerSec(1) to 10 during (1200))
  ).protocols(httpProtocol)
}
