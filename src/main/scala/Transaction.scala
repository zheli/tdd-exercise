import java.time.LocalDate

case class Transaction(
  date: LocalDate,
  description: String,
  maybeRecipient: Option[String],
  amount: Int
)

object Transaction {
  def apply(
    date: String,
    description: String,
    maybeRecipient: Option[String],
    amount: Int
  ): Transaction = {
    Transaction(
      date= LocalDate.parse(date),
      description= description,
      maybeRecipient= maybeRecipient,
      amount= amount
    )
  }
}
