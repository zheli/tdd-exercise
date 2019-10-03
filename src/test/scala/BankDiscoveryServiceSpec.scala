import org.scalatest._

class BankDiscoveryServiceSpec extends FlatSpec with Matchers {
    val mockTransactions = Seq.empty[Transaction]
  "BankDiscoveryService.findSubscriptions" should "not crash if pass in empty transaction sequence" in {
    val result = BankDiscoveryService.findSubscriptions(mockTransactions)
    result shouldBe None
  }
}
