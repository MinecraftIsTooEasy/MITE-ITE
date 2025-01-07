package net.xiaoyu233.mitemod.miteite.creativetab;

import huix.glacier.api.extension.creativetab.GlacierCreativeTabs;
import net.xiaoyu233.mitemod.miteite.item.Items;

public class CreativeTabsITE extends GlacierCreativeTabs {
    public CreativeTabsITE() {
        super("MITE-ITE");
    }

    @Override
    public int getTabIconItemIndex() {
        return Items.UNIVERSAL_ENHANCE_STONE.itemID;
    }
}
