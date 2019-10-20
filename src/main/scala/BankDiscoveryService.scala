object BankDiscoveryService {
  def findSubscriptions(transactions: Seq[Transaction]): Seq[Subscription] = {
    val transactionGroups = transactions.groupBy(_.description).toSeq
    transactionGroups.map {
      case (text, trans) if trans.length > 1 => Subscription(text, 30, trans.head.amount * -1)
    }
  }
}
