public class TurboMaster implements TurboPayment{
    /**
     * Class for testing turbo payment adapter
     * @param turboCardNo turbo ard no
     * @param turboAmount turbo amount
     * @param destinationTurboOfCourse destination
     * @param installmentsButInTurbo installments
     * @return result
     */
    @Override
    public int payInTurbo(String turboCardNo, float turboAmount, String destinationTurboOfCourse, String installmentsButInTurbo) {
        System.out.println(turboCardNo + " " + turboAmount + " " + destinationTurboOfCourse + " " + installmentsButInTurbo);
        System.out.println("Turbo Payment successful..");
        return 0;
    }
}
