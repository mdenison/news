package org.unsane.spirit.news
package snippet

import net.liftweb.http.S
import org.unsane.spirit.news.lib.Config._

/**
 * Routing the user either to the New cool schedule or to the old uncool schedule.
 * Depends on if the panic switch was used.
 * @todo Don't know if this is the way to do it, but it works?!
 */
class ScheduleDispatch {

  loadChangeableProps("schedule") match {
    case "new" => S.redirectTo("/schedule")
    case "old" => S.redirectTo("/stundenplan/index")
    case _ => S.redirectTo("/")
  }

  def render = {

    <div></div>
  }

}

class OldScheduleDispatch {

  loadChangeableProps("schedule") match {
    case "new" => S.redirectTo("/schedule")
    case "old" =>
    case _ => S.redirectTo("/")
  }

  def render = {

    <div></div>
  }

}
