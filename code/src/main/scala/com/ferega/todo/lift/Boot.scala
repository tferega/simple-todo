package com.ferega.todo
package lift

import net.liftweb.common.{ Empty, Full }
import net.liftweb.http.{ Bootable, Html5Properties, LiftRules }
import net.liftweb.http.{ Req, S }
import net.liftweb.sitemap.Loc.{ EarlyResponse, Hidden }
import net.liftweb.sitemap.{ Menu, SiteMap }

class Boot extends Bootable {
  def redirectIfFalse(predicate: => Boolean, destination: String) =
    () => {
      predicate match {
        case true  => Empty
        case false => Full(S.redirectTo(destination))
      }
    }

  def isLoggedIn  = redirectIfFalse(UserTools.isLoggedIn,  "/auth")
  def notLoggedIn = redirectIfFalse(UserTools.notLoggedIn, "/")

  def boot() {
    LiftRules.addToPackages("com.ferega.todo.lift")
    LiftRules.setSiteMap(SiteMap(
        Menu("Home") / "index" >> Hidden >> EarlyResponse(isLoggedIn),
        Menu("Auth") / "auth"  >> Hidden >> EarlyResponse(notLoggedIn)
    ))

    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))
  }
}