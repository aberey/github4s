package com.fortysevendeg.github4s

import com.fortysevendeg.github4s.GithubResponses.{GHResponse, GHIO}
import com.fortysevendeg.github4s.app._
import com.fortysevendeg.github4s.free.algebra.{AuthOps, RepositoryOps, UserOps}
import com.fortysevendeg.github4s.free.domain._

class GHUsers(implicit O : UserOps[GitHub4s]){

  def get(username : String): GHIO[GHResponse[Collaborator]] = O.getUser(username)

  def getAuth: GHIO[GHResponse[Collaborator]] = O.getAuthUser

  def getUsers(since : Int, pagination: Option[Pagination] = None): GHIO[GHResponse[List[Collaborator]]] = O.getUsers(since, pagination)

}

class GHRepos(implicit O : RepositoryOps[GitHub4s]){

  def get(owner : String, repo: String): GHIO[GHResponse[Repository]] = O.getRepo(owner, repo)

  def listCommits(
      owner : String,
      repo: String,
      sha: Option[String] = None,
      path: Option[String] = None,
      author: Option[String] = None,
      since: Option[String] = None,
      until: Option[String] = None,
      pagination: Option[Pagination] = None): GHIO[GHResponse[List[Commit]]] =
    O.listCommits(owner, repo, sha, path, author, since, until, pagination)

}

class GHAuth(implicit O : AuthOps[GitHub4s]){

  def newAuth(
      username: String,
      password: String,
      scopes: List[String],
      note: String,
      client_id: String,
      client_secret: String): GHIO[GHResponse[Authorization]] =
    O.newAuth(username, password, scopes, note, client_id, client_secret)

  def authorizeUrl(
      client_id: String,
      redirect_uri: String,
      scopes: List[String]): GHIO[GHResponse[Authorize]] =
    O.authorizeUrl(client_id, redirect_uri, scopes)

  def getAccessToken(
      client_id: String,
      client_secret: String,
      code: String,
      redirect_uri: String,
      state: String): GHIO[GHResponse[OAuthToken]] =
    O.getAccessToken(client_id, client_secret, code, redirect_uri, state)
}