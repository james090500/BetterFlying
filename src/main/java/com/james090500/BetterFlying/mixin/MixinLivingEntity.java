package com.james090500.BetterFlying.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class MixinLivingEntity {

    private boolean preventedDrift = false;

    @Shadow
    protected abstract boolean isMoving();

    @Inject(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V"))
    public void onMove(MoverType type, Vec3 pos, CallbackInfo ci) {
        LocalPlayer currentInstance = (LocalPlayer) (Object) this;
        if(type == MoverType.SELF && currentInstance.getAbilities().flying) {
            if(!this.isMoving()) {
                if(!this.preventedDrift) {
                    currentInstance.setDeltaMovement(Vec3.ZERO);
                }
                this.preventedDrift = true;
            } else {
                this.preventedDrift = false;
            }
        }
    }
}
