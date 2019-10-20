object BankDiscoveryService {
  def findSubscriptions(transactions: Seq[Transaction]): Seq[Subscription] = {
    val groupByText = transactions.groupBy(_.description).toSeq
    groupByText.flatMap {
      case (text, sameTextTrans) if sameTextTrans.length > 1 =>
        val groupByAmount = sameTextTrans.groupBy(_.amount).toSeq
        groupByAmount.flatMap {
          case (amount: Int, sameAmountTrans) if sameAmountTrans.length > 1 =>
            Some(Subscription(text, 30, amount * -1))
          case _ =>
            None
        }
    }
  }
}
