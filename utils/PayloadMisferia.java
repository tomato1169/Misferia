package azerot.azerot.utils;

public class PayloadMisferia extends CustomPayload {
    private final short subId;

    private final int[] data;

    public PayloadMisferia(short subId, int... data) {
        this.subId = subId;
        this.data = data;
    }

    public short getId() {
        return 9;
    }

    public void write(PacketBuffer buffer) {
        buffer.writeShort(this.subId);
        for (int i : this.data)
            buffer.writeInt(i);
    }
}
