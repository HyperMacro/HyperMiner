package com.hyperclient.HyperMiner.macros.macros;

import com.hyperclient.HyperMiner.baritone.autowalk.WalkBaritone;
import com.hyperclient.HyperMiner.macros.Macro;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommissionMacro extends Macro {
    WalkBaritone baritone = new WalkBaritone();

    @Override
    protected void onEnable() {
        baritone.onEnable(new BlockPos(2, 64, 2));
    }

    @Override
    public void onTick(TickEvent.Phase phase) {
        baritone.onTickEvent(phase);
    }

    @Override
    public void onLastRender() {
        baritone.onRenderEvent();
    }

    @Override
    protected void onDisable() {
        baritone.disableBaritone();

    }
}
