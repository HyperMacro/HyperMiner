package com.hyperclient.HyperMiner;

import com.hyperclient.HyperMiner.config.Config;
import com.hyperclient.HyperMiner.handlers.KeybindHandler;
import com.hyperclient.HyperMiner.handlers.MacroHandler;
import com.hyperclient.HyperMiner.macros.macros.GemstoneMacro;
import com.hyperclient.HyperMiner.render.BlockRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import rosegoldaddons.Main;

@Mod(modid = HyperMiner.MODID, version = HyperMiner.VERSION)
public class HyperMiner {
    public static final String MODID = "hyperminer";
    public static final String VERSION = "1.0";

    public static Config config;

    //thx pizza for fixing this
    public static void onStartGame(){
        config = new Config();
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new MacroHandler());
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
        MinecraftForge.EVENT_BUS.register(new BlockRenderer());
        KeybindHandler.initializeCustomKeybindings();
        MacroHandler.initializeMacro();

        Minecraft.getMinecraft().gameSettings.gammaSetting = 100;
    }





}
