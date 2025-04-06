package net.xiaoyu233.mitemod.miteite.creativetab;

import huix.glacier.api.extension.creativetab.GlacierCreativeTabs;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class CreativeTabsITE extends GlacierCreativeTabs {
    public CreativeTabsITE() {
        super("MITE-ITE");
    }

    @Override
    public int getTabIconItemIndex() {
        return MITEITEItemRegistryInit.UNIVERSAL_ENHANCE_STONE.itemID;
    }
}
