package ModelandStores;

import Factory.PhoneIngredientFactory;
import Factory.TurkeyFactory;

public class TurkeyPhoneStore extends PhoneStore{
    /**
     *
     * @param model Taking phone model as parameter
     * @return Returns  an incomplete phone customized for Turkey
     */
    @Override
    protected Phone createPhone(String model) {
        Phone phone = null;
        PhoneIngredientFactory phoneIngredientFactory = new TurkeyFactory();
        if(model.equals("MaximumEfford")){
            phone = new MaximumEffort(phoneIngredientFactory);
            phone.prepare();
        }
        else if(model.equals("IflasDeluxe")){
            phone = new IflasDeluxe(phoneIngredientFactory);
            phone.prepare();
        }
        else if(model.equals("I_I_Aman_Iflas")){
            phone = new I_I_Aman_Iflas(phoneIngredientFactory);
            phone.prepare();
        }

        return phone;
    }
}
