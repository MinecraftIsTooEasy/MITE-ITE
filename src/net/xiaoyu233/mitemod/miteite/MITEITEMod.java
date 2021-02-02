package net.xiaoyu233.mitemod.miteite;

import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.fml.asm.annotations.Mod;
import net.xiaoyu233.fml.util.ModInfo;
import net.xiaoyu233.mitemod.miteite.events.EventListeners;
import net.xiaoyu233.mitemod.miteite.util.Config;
import net.xiaoyu233.mitemod.miteite.util.Constant;

import javax.swing.*;
import java.io.File;

@Mod
public class MITEITEMod extends AbstractMod {
    private static final String MOD_LOADER_MIN_VER_STRING = "B0.1.4";
    private static final int MOD_LOADER_MIN_VER_NUM = 5;
    private static final File CFG_FILE = new File(System.getProperty("user.dir"),"mite-ite-cfg.json");
    public static Config CONFIG;
    @Override
    public void preInit() {

    }

    @Override
    public void postInit() {
        ModInfo modLoader = FishModLoader.getModsMap().get("FishModLoader");
        int modLoaderNum = modLoader.getModVerNum();
        if (modLoaderNum < MOD_LOADER_MIN_VER_NUM){
            JFrame diaFrame = new JFrame();
            diaFrame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(diaFrame,"MITE-ITE加载错误: 模组加载器版本过低,FishModLoader模组加载器需要" + MOD_LOADER_MIN_VER_STRING + "\n当前版本:" + modLoader.getModVerStr(),"错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        EventListeners.registerAllEvents();
        if (!CFG_FILE.exists()) {
            MITEITEMod.CONFIG = new Config(CFG_FILE);
            MITEITEMod.CONFIG.saveDefault();
            MITEITEMod.CONFIG.load();
        }else {
            MITEITEMod.CONFIG = new Config(CFG_FILE);
            MITEITEMod.CONFIG.load();
        }
    }

    @Override
    public String modId() {
        return "MITE-ITE";
    }

    @Override
    public int modVerNum() {
        return Constant.MITE_ITE_VER_NUM;
    }

    @Override
    public String modVerStr() {
        return Constant.MITE_ITE_VERSION;
    }

    @Override
    public String transformPkg() {
        return "net.xiaoyu233.mitemod.miteite.trans";
    }
}
