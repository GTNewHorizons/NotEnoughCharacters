package net.moecraft.nechar;

import java.io.File;
import java.util.function.BiConsumer;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.vfyjxf.nechar.NechCommand;
import net.vfyjxf.nechar.NechConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.towdium.pinin.DictLoader;
import me.towdium.pinin.PinIn;

@Mod(modid = NotEnoughCharacters.ID, acceptableRemoteVersions = "*", useMetadata = true)
public class NotEnoughCharacters {

    public static final String ID = "nechar";
    public static final String VERSION = NotEnoughCharacters.class.getPackage()
        .getImplementationVersion();
    public static final Logger logger = LogManager.getLogger("NotEnoughCharacters");

    public static final PinIn CONTEXT = new PinIn(new CustomDictLoader()).config()
        .accelerate(true)
        .commit();

    private static class CustomDictLoader extends DictLoader.Default {

        @Override
        public void load(BiConsumer<Character, String[]> feed) {
            super.load(feed);
            // 钅卢
            feed.accept('\ue900', new String[] { "lu2", "jinlu" });
            feed.accept('\udf3b', new String[] { "lu2", "jinlu" });
            // 钅杜
            feed.accept('\ue901', new String[] { "du4", "jindu" });
            feed.accept('\udf4a', new String[] { "du4", "jindu" });
            // 钅喜
            feed.accept('\ue902', new String[] { "xi3", "jinxi" });
            feed.accept('\udf73', new String[] { "xi3", "jinxi" });
            // 钅波
            feed.accept('\ue903', new String[] { "bo1", "jinbo" });
            feed.accept('\udf5b', new String[] { "bo1", "jinbo" });
            // 钅黑
            feed.accept('\ue904', new String[] { "hei1", "jinhei" });
            feed.accept('\udf76', new String[] { "hei1", "jinhei" });
            // 钅麦
            feed.accept('\u9fcf', new String[] { "mai4", "jinmai" });
            // feed.accept('\u9fcf', new String[] { "mai4", "jinmai" });
            // 钅达
            feed.accept('\ue906', new String[] { "da2", "jinda" });
            feed.accept('\udffc', new String[] { "da2", "jinda" });
            // 钅仑
            feed.accept('\ue907', new String[] { "lun2", "jinlun" });
            feed.accept('\udf2d', new String[] { "lun2", "jinlun" });
            // 钅哥
            feed.accept('\u9fd4', new String[] { "ge1", "jinge" });
            // feed.accept('\u9fd4', new String[] { "ge1", "jinge" });
            // 钅尔
            feed.accept('\u9fed', new String[] { "ni3", "jiner" });
            // feed.accept('\u9fed', new String[] { "ni3", "jiner" });
            // 钅夫
            feed.accept('\ue90a', new String[] { "fu1", "jinfu" });
            feed.accept('\udce7', new String[] { "fu1", "jinfu" });
            // 钅立
            feed.accept('\ue90c', new String[] { "li4", "jinli" });
            feed.accept('\udff7', new String[] { "li4", "jinli" });
            // 石田
            feed.accept('\u9fec', new String[] { "tian2", "shitian" });
            // feed.accept('\u9fec', new String[] { "tian2", "shitian" });
            // 气奥
            feed.accept('\u9feb', new String[] { "ao4", "qiao", "aoqi" });
            // feed.accept('\u9feb', new String[] { "ao4", "qiao", "aoqi" });
        }
    }

    public static void onConfigChange() {
        CONTEXT.config()
            .fZh2Z(NechConfig.EnableFZh2Z)
            .fSh2S(NechConfig.EnableFSh2S)
            .fCh2C(NechConfig.EnableFCh2C)
            .fAng2An(NechConfig.EnableFAng2An)
            .fIng2In(NechConfig.EnableFIng2In)
            .fEng2En(NechConfig.EnableFEng2En)
            .fU2V(NechConfig.EnableFU2V)
            .keyboard(NechConfig.KeyboardType.get())
            .commit();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("Not Enough Characters - v" + VERSION);
        NechConfig.loadConfig(new File(Minecraft.getMinecraft().mcDataDir, "config/NotEnoughCharacters.cfg"));
        onConfigChange();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new NechCommand());
    }
}
