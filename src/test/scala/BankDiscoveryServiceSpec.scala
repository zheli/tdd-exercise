import org.scalatest._

class BankDiscoveryServiceSpec extends FlatSpec with Matchers {
  "BankDiscoveryService.findSubscriptions" should "not crash if pass in empty transaction sequence" in {
    val testTransactions = Seq.empty[Transaction]
    val result = BankDiscoveryService.findSubscriptions(testTransactions)
    result shouldBe None
  }

  it should "find monthly subscription which withdraws around the same date every month" in {
    val testData = Seq(
      Transaction("20160723", "Video streaming", None, -99),
      Transaction("20160622", "Video streaming", None, -99),
      Transaction("20160523", "Video streaming", None, -99)
    )
    val result = BankDiscoveryService.findSubscriptions(testData)
    result shouldBe Subscription("Video streaming", 30, 99)
  }
}
