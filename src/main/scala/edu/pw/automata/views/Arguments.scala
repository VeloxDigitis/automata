package edu.pw.automata.views

import fr.iscpif.scaladget.api.{BootstrapTags => bs}
import org.scalajs.dom.Element

object Arguments extends Section{

  import scalatags.JsDom.all._

  lazy val template = div(
    h1("Hello world")
  )

  def getTemplate(): Element = template.render

}
