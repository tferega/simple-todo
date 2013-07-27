package com.ferega.todo
package lift

import net.liftweb.sitemap.Loc.Hidden
import net.liftweb.http.LiftRules
import net.liftweb.sitemap.{ Menu, SiteMap }
import net.liftweb.http.LiftRulesMocker.toLiftRules
import net.liftweb.sitemap.LocPath.stringToLocPath
import net.liftweb.http.Req
import net.liftweb.http.Html5Properties
import net.liftweb.util.Vendor.valToVender
import net.liftweb.http.Bootable

class Boot extends Bootable {
  def boot() {
    LiftRules.addToPackages("com.ferega.todo.lift")
    LiftRules.setSiteMap(SiteMap(
        Menu("Home") / "index" >> Hidden,
        Menu("Auth") / "auth" >> Hidden)
    )

    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))
  }
}