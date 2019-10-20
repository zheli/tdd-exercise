object BankDiscoveryService {
  def findSubscriptions(transactions: Seq[Transaction]): Seq[Subscription] = {
    val groupByText = transactions.groupBy(_.description).toSeq
    groupByText.flatMap {
      case (text, sameTextTrans) if sameTextTrans.length > 1 =>
        val groupByAmount = sameTextTrans.groupBy(_.amount).toSeq
        groupByAmount.map {
          case (amount, sameAmountTrans) if sameAmountTrans.length > 1 =>
            Subscription(text, 30, amount * -1)
        }
    }
  }
}
