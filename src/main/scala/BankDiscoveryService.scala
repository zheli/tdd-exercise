object BankDiscoveryService {
  def findSubscriptions(transactions: Seq[Transaction]): Seq[Subscription] = {
    val groupByText = transactions.groupBy(_.description).toSeq
    groupByText.flatMap {
      case (text, sameTextTrans) if sameTextTrans.length > 1 =>
        val groupByAmount = sameTextTrans.groupBy(_.amount).toSeq
        groupByAmount
          .filter{
            case (_, sameAmountTrans) => sameAmountTrans.length > 1
          }
          .map {
            case (amount, _) =>
              Subscription(text, 30, amount * -1)
          }
    }
  }
}
