public class Main {
    public static void main(String [] args){
        /**
         * Turbo payment
         */
        TurboMaster turboMaster = new TurboMaster();
        /**
         * Adapter
         */
        ModernPayment modernPayment = new PaymentAdapter(turboMaster);
        /**
         * modern payment to turbo payment
         */
        modernPayment.pay("11111",999,"22222","12");
    }
}
