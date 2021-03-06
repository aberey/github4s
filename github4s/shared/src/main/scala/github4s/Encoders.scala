/*
 * Copyright 2016-2018 47 Degrees, LLC. <http://www.47deg.com>
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

package github4s

import github4s.free.domain._
import io.circe._
import io.circe.syntax._
import io.circe.generic.auto._

object Encoders {

  implicit val encodeTreeData: Encoder[TreeData] = Encoder.instance {
    case d: TreeDataSha  => d.asJson
    case d: TreeDataBlob => d.asJson
  }

  implicit val encodeNewPullRequest: Encoder[CreatePullRequest] = Encoder.instance {
    case d: CreatePullRequestData  => d.asJson
    case d: CreatePullRequestIssue => d.asJson
  }

  implicit val encodePrrStatus: Encoder[PullRequestReviewState] = Encoder.encodeString.contramap(_.value)
}
