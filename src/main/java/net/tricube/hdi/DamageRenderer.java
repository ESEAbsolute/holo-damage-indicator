package net.tricube.hdi;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.ray.HologramAPI.Hologram;
import net.ray.HologramAPI.HologramAPI;
import net.tricube.hdi.config.Config;
import net.tricube.hdi.mixin.CameraAccessor;


import java.util.Random;

public class DamageRenderer {
//    private static DamageAnimations currentAnimation = DamageAnimations.NEW_ANIM;
//
//    public static void setAnimation(DamageAnimations animation) {
//        currentAnimation = animation;
//    }
//
//    public static DamageAnimations getCurrentAnimation() {
//        return currentAnimation;
//    }
//
//    public static void cycleAnimation() {
//        DamageAnimations[] animations = {
//                DamageAnimations.FLOATING,
//                DamageAnimations.PARTICLE,
//                DamageAnimations.HYBRID,
//                DamageAnimations.ARCH
//        };
//
//        for (int i = 0; i < animations.length; i++) {
//            if (currentAnimation == animations[i]) {
//                currentAnimation = animations[(i + 1) % animations.length];
//                return;
//            }
//        }
//        currentAnimation = DamageAnimations.FLOATING;
//    }


    public static void renderDamageIndicator(LivingEntity entity, float damage, boolean isCritical) {
        if(!Config.damageEnable.get()) return;
        if(!Config.enableIndicator.get()) return;
        String result;
        if (isCritical) {
            result = Config.critDamageFormat.get().replace("{dmg}",
                    String.format("%." + Config.decimal.get() + "f", damage));
        } else {
            result = Config.damageFormat.get().replace("{dmg}",
                    String.format("%." + Config.decimal.get() + "f", damage));
        }
        Component component = ComponentUtilsParser.parseColorCodes(result);

        Random random = new Random();
        double startX = entity.getX();
        double startY = entity.getY() + entity.getBbHeight() * 0.85;
        double startZ = entity.getZ();

        float radius = 0.2f + random.nextFloat() * 0.3f;

        float u = random.nextFloat();
        float v = random.nextFloat();
        float theta = 2 * (float)Math.PI * u;
        float phi = (float)Math.acos(2 * v - 1);

        double spawnX = startX + Config.damageOffset.get() * (radius * Math.sin(phi) * Math.cos(theta) * 1.5);
        double spawnY = startY + Config.damageOffset.get() * (radius * Math.sin(phi) * Math.sin(theta));
        double spawnZ = startZ + Config.damageOffset.get() * (radius * Math.cos(phi));

		float finalScale = Config.damageScale.get() * (
				Config.dynamicDamageScale.get() ?
				Config.damageScaleFactor.get() * (float) Math.sqrt(
						((CameraAccessor) Minecraft.getInstance().gameRenderer.getMainCamera())
								.holo_damage_indicator$getPosition().distanceTo(
								new Vec3(spawnX, spawnY, spawnZ)
						)
				) : 1
		);

        Hologram holo = HologramAPI.create(component, spawnX, spawnY, spawnZ)
                .shadow(Config.shadow.get())
                .lifetime(Config.damageLifetime.get())
                .scale(finalScale)
                .renderOnTop(Config.renderInfront.get())
                .renderDistance(Config.renderDistance.get())
                .background(Config.background.get());
        Config.damageAnimations.get().apply(holo, random);
    }

    public static void renderHealingIndicator(Entity entity, float heal) {
        if(!Config.healEnable.get()) return;
        if(!Config.enableIndicator.get()) return;

        String result = Config.healFormat.get().replace("{heal}", String.format("%." + Config.decimal.get() + "f", heal));
        Component component = ComponentUtilsParser.parseColorCodes(result);

        Random random = new Random();
        double startX = entity.getX();
        double startY = entity.getY() + entity.getBbHeight() * 0.85;
        double startZ = entity.getZ();

        float radius = 0.2f + random.nextFloat() * 0.3f;

        float u = random.nextFloat();
        float v = random.nextFloat();
        float theta = 2 * (float)Math.PI * u;
        float phi = (float)Math.acos(2 * v - 1);

        double spawnX = startX + Config.healOffset.get() * (radius * Math.sin(phi) * Math.cos(theta) * 1.5);
        double spawnY = startY + Config.healOffset.get() * (radius * Math.sin(phi) * Math.sin(theta));
        double spawnZ = startZ + Config.healOffset.get() * (radius * Math.cos(phi));

		float finalScale = Config.healScale.get() * (
				Config.dynamicHealScale.get() ?
				Config.healScaleFactor.get() * (float) Math.sqrt(
						((CameraAccessor) Minecraft.getInstance().gameRenderer.getMainCamera())
								.holo_damage_indicator$getPosition().distanceTo(
								new Vec3(spawnX, spawnY, spawnZ)
						)
				) : 1
		);

        Hologram holo = HologramAPI.create(component, spawnX, spawnY, spawnZ)
                .shadow(Config.shadow.get())
                .lifetime(Config.healLifetime.get())
                .scale(finalScale)
                .renderOnTop(Config.renderInfront.get())
                .renderDistance(Config.renderDistance.get())
                .background(Config.background.get());;
        Config.healAnimations.get().apply(holo, random);
    }
}
