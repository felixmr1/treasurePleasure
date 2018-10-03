package goteborgsuniversitet.maptestapp.core;

enum ItemType {
    DIAMOND,
    GOLD,
    STONE
}

public class Item {
    private int value;
    private ItemType type;

    Item(ItemType type, int value){
        this.type = type;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
