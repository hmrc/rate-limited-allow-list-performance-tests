/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.example

import io.gatling.core.Predef._
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.example.CheckRequests._

import java.util.UUID

class RateLimitedAllowListSimulation extends PerformanceTestRunner {

  val userIds: Iterator[String] =
    Iterator
      .continually(
        (11 to 20).map(i => s"00000000-0000-0000-0000-0000000000$i")
      )
      .flatten
      .iterator

  val identifierFeeder: Iterator[Map[String, String]] = Iterator.continually(
    Map(
      "randomIdentifier" -> UUID.randomUUID().toString,
      "repeatIdentifier" -> userIds.next()
    )
  )

  setup(
    "check-user-identifier",
    "Check if a user is on or can be added to the allow list"
  )
    .withActions(feed(identifierFeeder).actionBuilders: _*)
    .withRequests(
      checkUserIdentifierNewUsers,
      checkUserIdentifierReturningUsers
    )

  runSimulation()
}
