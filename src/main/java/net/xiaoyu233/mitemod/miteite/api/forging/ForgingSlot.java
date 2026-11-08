package net.xiaoyu233.mitemod.miteite.api.forging;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ForgingSlot {
    private static final List<ForgingSlot> VALUES = new ArrayList<>();
    public final String name;
    private final int ordinal;

    public static final ForgingSlot AXE = new ForgingSlot("axe");
    public static final ForgingSlot HAMMER = new ForgingSlot("hammer");
    public static final ForgingSlot TOOL = new ForgingSlot("tool");
    public static final ForgingSlot MATERIAL = new ForgingSlot("material");
    public static final ForgingSlot OUTPUT = new ForgingSlot("output");

    public ForgingSlot(String name) {
        this.name = name;
        this.ordinal = VALUES.size();
        register(this);
    }

    public static void register(@Nullable ForgingSlot slot) {
        if (slot != null && !VALUES.contains(slot)) {
            VALUES.add(slot);
        }
    }

    @Nonnull
    public static ForgingSlot[] values() {
        return VALUES.toArray(new ForgingSlot[0]);
    }

    @Nonnull
    public static ForgingSlot valueOf(String name) {
        for (ForgingSlot slot : VALUES) {
            if (slot.name.equalsIgnoreCase(name)) {
                return slot;
            }
        }
        throw new IllegalArgumentException("No forging slot " + name);
    }

    @Nullable
    public static ForgingSlot getByOrdinal(int ordinal) {
        for (ForgingSlot slot : VALUES) {
            if (slot.ordinal == ordinal) {
                return slot;
            }
        }
        return null;
    }

    public String name() {
        return this.name;
    }

    public int ordinal() {
        return this.ordinal;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
