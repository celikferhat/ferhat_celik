/**
 * An adapter class for using turbo payment
 */
public class PaymentAdapter implements ModernPayment{
    TurboPayment turboPayment;

    /**
     *
     * @param turboPayment Takes tubopayment object as parameter
     */
    public PaymentAdapter(TurboPayment turboPayment) {
        this.turboPayment = turboPayment;
    }

    /**
     * For using turbopayment
     * @param cardNo Card number
     * @param amount    Amount of money
     * @param destination   Destination
     * @param installments  Installments
     * @return  Returns tubo payment operation result
     */
    @Override
    public int pay(String cardNo, float amount, String destination, String installments) {
        return turboPayment.payInTurbo(cardNo,amount,destination,installments);
    }
}
