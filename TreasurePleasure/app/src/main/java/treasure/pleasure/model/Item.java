package treasure.pleasure.model;
import java.text.DecimalFormat;

public class Item implements ItemCallBack {
    private final DecimalFormat dm = new DecimalFormat("##.#");
    private double value;
    private ItemType type;

    Item(ItemType type, int value){
        this.type = type;
        this.value = value;
    }

    double getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    ItemType getType() {
        return type;
    }

    void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public double getValueCallBack() {

        String val = dm.format(Math.round(value));

        return  Double.parseDouble(val);


    }
}
