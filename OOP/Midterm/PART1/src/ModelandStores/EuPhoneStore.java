package ModelandStores;

import Factory.EuFactory;
import Factory.PhoneIngredientFactory;

public class EuPhoneStore extends PhoneStore{
    /**
     *
     * @param model Taking phone model as parameter
     * @return Returns  an incomplete phone customized for EU
     */
    @Override
    protected Phone createPhone(String model) {
        Phone phone = null;
        PhoneIngredientFactory phoneIngredientFactory = new EuFactory();
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
