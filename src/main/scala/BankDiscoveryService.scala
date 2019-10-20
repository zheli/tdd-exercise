import java.time.LocalDate

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
          .flatMap {
            case (amount, sameAmountTrans) =>
              val dates = sameAmountTrans.map(_.date)
              val maybeIntervalInDays: Option[Int] = findInterval(dates)
              maybeIntervalInDays.map { interval =>
                Subscription(text, interval, amount * -1)
              }
          }
    }
  }

  def findInterval(dates: Seq[LocalDate]): Option[Int] = {
    val monthly = 30

    def hasMonthlyDates = {
      dates.zipWithIndex.exists {
        case (d, index: Int) =>
          val tail = dates.drop(index + 1)
          tail.contains(d.minusDays(monthly))
      }
    }

    if (hasMonthlyDates)
      Some(monthly)
    else
      None
  }
}
