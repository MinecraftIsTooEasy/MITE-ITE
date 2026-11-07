package net.xiaoyu233.mitemod.miteite.item.recipe;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ForgingTableLevel {
    protected static final List<? extends ForgingTableLevel> VALUES = new ArrayList<>();
    public final String name;
    private final int level;

    public static final ForgingTableLevel IRON = new ForgingTableLevel("iron");
    public static final ForgingTableLevel MITHRIL = new ForgingTableLevel("mithril");
    public static final ForgingTableLevel ADAMANTIUM = new ForgingTableLevel("adamantium");
    public static final ForgingTableLevel VIBRANIUM = new ForgingTableLevel("vibranium");

    public ForgingTableLevel(String name) {
        this.name = name;
        this.level = VALUES.size();
        addValue(this);
    }

    @SuppressWarnings("unchecked")
    protected static <T extends ForgingTableLevel> void addValue(T level) {
        ((List<T>) VALUES).add(level);
    }

    public static void register(ForgingTableLevel level) {
        if (level != null && !VALUES.contains(level)) {
            addValue(level);
        }
    }

    @SuppressWarnings("unchecked")
    public static ForgingTableLevel[] values() {
        return ((List<ForgingTableLevel>) VALUES).toArray(new ForgingTableLevel[0]);
    }

    public static ForgingTableLevel valueOf(String name) {
        for (ForgingTableLevel tableLevel : VALUES) {
            if (tableLevel.name.equalsIgnoreCase(name)) {
                return tableLevel;
            }
        }
        throw new IllegalArgumentException("No forging table level " + name);
    }

    @Nullable
    public static ForgingTableLevel getByLevel(int level) {
        for (ForgingTableLevel tableLevel : VALUES) {
            if (tableLevel.level == level) {
                return tableLevel;
            }
        }
        return null;
    }

    public String name() {
        return this.name;
    }

    public int ordinal() {
        return this.level;
    }

    public int getLevel() {
        return level;
    }
}
