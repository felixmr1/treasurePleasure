package goteborgsuniversitet.maptestapp.model;

public class Item {
    private int value;
    private ItemType type;

    Item(ItemType type, int value){
        this.type = type;
        this.value = value;
    }

    int getValue() {
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
}
