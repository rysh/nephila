
import org.scalatest._
import org.scalatest.selenium.WebBrowser
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}

class FirstWebDriverSpec extends FlatSpec with Matchers with WebBrowser {
  "http://server:9000/" should "have the correct title" in {
    val ops = new ChromeOptions
    ops.addArguments("--headless")
    ops.addArguments("--no-sandbox")
    ops.addArguments("--disable-dev-shm-usage")
    implicit val webDriver: WebDriver = new ChromeDriver(ops)
    go to "http://server:9000/"
    pageTitle should be ("Play with Scala.js")
  }
}
