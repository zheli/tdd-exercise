/**
 *
 * @param description
 * @param intervalDays subscription interval in days
 * @param amount
 */
case class Subscription(
  description: String,
  intervalDays: Int,
  amount: Int
)
