import org.scalatest._

class BankDiscoveryServiceSpec extends FlatSpec with Matchers {
  "BankDiscoveryService.findSubscriptions" should "not crash if pass in empty transaction sequence" in {
    val testTransactions = Seq.empty[Transaction]
    val result = BankDiscoveryService.findSubscriptions(testTransactions)
    result shouldBe Seq.empty
  }

  it should "find monthly subscription which withdraws around the same date every month" in {
    val testData1 = Seq(
      Transaction("2016-07-23", "Video streaming", None, -99),
      Transaction("2016-06-22", "Video streaming", None, -99),
      Transaction("2016-05-23", "Video streaming", None, -99)
    )
    val result1 = BankDiscoveryService.findSubscriptions(testData1)
    result1 shouldBe Seq(Subscription("Video streaming", 30, 99))

    val testData2 = Seq(
      Transaction("2016-07-23", "Video streaming", None, -10),
      Transaction("2016-06-22", "Video streaming", None, -30),
      Transaction("2016-05-23", "Video streaming", None, -99)
    )
    val result2 = BankDiscoveryService.findSubscriptions(testData2)
    result2 shouldBe Seq(Subscription("Video streaming", 30, 99))
  }
}
