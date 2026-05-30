// mixin/LivingEntityMixin.java
package net.tricube.hdi.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.tricube.hdi.DamageTracker;
import net.tricube.hdi.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class EntityHealthMixin {

    @Inject(method = "baseTick", at = @At("HEAD"))
    private void onBaseTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if(Config.disableSelf.get() && entity instanceof LocalPlayer){
        }
        else{
            DamageTracker.updateEntity(entity);
        }
    }
}
