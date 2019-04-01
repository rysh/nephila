package nephila.shared

import org.specs2.mutable.Specification

class SharedMessagesTest extends Specification {

  "SharedMessagesTest" should {
    "itWorks" in {
      SharedMessages.itWorks must equalTo("It works!")
    }

  }
}
