package com.hyperclient.HyperMiner.macros.macros;

import com.hyperclient.HyperMiner.HyperMiner;
import com.hyperclient.HyperMiner.baritone.automine.AutoMineBaritone;
import com.hyperclient.HyperMiner.baritone.automine.config.AutoMineType;
import com.hyperclient.HyperMiner.baritone.automine.config.MineBehaviour;
import com.hyperclient.HyperMiner.handlers.KeybindHandler;
import com.hyperclient.HyperMiner.handlers.MacroHandler;
import com.hyperclient.HyperMiner.macros.Macro;
import com.hyperclient.HyperMiner.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

public class MithrilMacro extends Macro {

    AutoMineBaritone baritone;

    List<Block> priorityBlocks = new ArrayList<Block>(){
        {
            add(Blocks.stained_hardened_clay);
            add(Blocks.prismarine);
            add(Blocks.wool);
        }
    };

    @Override
    protected void onEnable() {
        baritone = new AutoMineBaritone(getMineBehaviour());
    }

    @Override
    public void onTick(TickEvent.Phase phase) {
        baritone.onTickEvent(phase);
        if(phase != TickEvent.Phase.START)
            return;

        if(PlayerUtils.hasPlayerInsideRadius(MightyMiner.config.mithPlayerRad)){
            PlayerUtils.warpBackToIsland();
            MacroHandler.disableScript();
        }

        if(MightyMiner.config.mithShiftWhenMine)
            KeybindHandler.setKeyBindState(KeybindHandler.keyBindShift, true);

        if(!baritone.isEnabled()){
            baritone.enableBaritone(priorityBlocks.get(MightyMiner.config.mithPriority1), priorityBlocks.get(MightyMiner.config.mithPriority2), priorityBlocks.get(MightyMiner.config.mithPriority3));
        }

    }

    @Override
    public void onLastRender() {
        baritone.onRenderEvent();
    }

    @Override
    public void onOverlayRenderEvent(RenderGameOverlayEvent event) {
        baritone.onOverlayRenderEvent(event);
    }



    @Override
    protected void onDisable() {
        baritone.disableBaritone();
        KeybindHandler.resetKeybindState();
    }


    private MineBehaviour getMineBehaviour(){
        return new MineBehaviour(
                AutoMineType.STATIC,
                MightyMiner.config.mithShiftWhenMine,
                true,
                MightyMiner.config.mithRotationTime,
                MightyMiner.config.mithRestartTimeThreshold,
                null,
                null,
               256,
                0
        );
    }
}
