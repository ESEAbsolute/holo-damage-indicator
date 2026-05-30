package net.tricube.hdi.mixin;

import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.tricube.hdi.DamageTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleEngine.class)
public class CritMixin {

    @Inject(
            method = "createTrackingEmitter(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/particles/ParticleOptions;)V",
            at = @At("HEAD")
    )
    private void onCreateTrackingEmitter(Entity entity, ParticleOptions particleOptions, CallbackInfo ci) {
        if (particleOptions == ParticleTypes.CRIT || particleOptions == ParticleTypes.ENCHANTED_HIT) {
            DamageTracker.markCriticalHit(entity.getId());
        }
    }
}
