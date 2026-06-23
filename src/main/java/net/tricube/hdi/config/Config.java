package net.tricube.hdi.config;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import net.tricube.CraftConfig.api.v1.*;
import net.tricube.CraftConfig.api.controller.*;
import net.tricube.CraftConfig.api.registry.CraftConfigRegistry;
import net.tricube.hdi.DamageTracker;
import net.tricube.hdi.animation.DamageAnimations;

public class Config {


	public static final ConfigOption<Boolean> enableIndicator =
			ConfigOption.booleanOption(Component.literal("Enable Indicator"), true)
					.description(Component.literal("Enable health indicators"));

	public static final ConfigOption<Boolean> renderInfront =
			ConfigOption.booleanOption(Component.literal("Render In Front"), true)
					.description(Component.literal("Renders the damage indicator over everything, including blocks and entities. If enabled, indicator will not show in third person."));

	public static final ConfigOption<Boolean> shadow =
			ConfigOption.booleanOption(Component.literal("Shadow"), true)
					.description(Component.literal("Enables shadow"));

	public static final ConfigOption<Integer> renderDistance =
			ConfigOption.intOption(Component.literal("Render Distance"), 30)
					.description(Component.literal("Changes how far damage indicators will be shown"));

	public static final ConfigOption<Integer> decimal =
			ConfigOption.intOption(Component.literal("Decimal Places"), 1)
					.description(Component.literal("How many trailing decimals"));

	public static final ConfigOption<Boolean> background =
			ConfigOption.booleanOption(Component.literal("Text Background"), false)
					.description(Component.literal("Enable text background"));

	public static final ConfigOption<Boolean> disableSelf =
			ConfigOption.booleanOption(Component.literal("Disable Self"), true)
					.description(Component.literal("Disable indicator for the self damage/healing"));


	public static final ConfigOption<Boolean> damageEnable =
			ConfigOption.booleanOption(Component.literal("Enable Damage Indicator"), true)
					.description(Component.literal("Enable damage indicator"));

	public static final ConfigOption<DamageTracker.DAMAGE_SOURCE> damageSource =
			ConfigOption.enumOption(Component.literal("Damage Source Filter"), DamageTracker.DAMAGE_SOURCE.ALL)
					.description(Component.literal("Damage source filter"));

	public static final ConfigOption<String> damageFormat =
			ConfigOption.stringOption(Component.literal("Damage Format"), "&c-{dmg}")
					.description(Component.literal("Formatting for damage text.\nSupports Minecraft color codes.\nUse {dmg} as damage placeholder."));

	public static final ConfigOption<String> critDamageFormat =
			ConfigOption.stringOption(Component.literal("Crit Damage Format"), "&c&l-{dmg}")
					.description(Component.literal("Formatting for critical hits.\nSupports Minecraft color codes.\nUse {dmg} as damage placeholder."));

	public static final ConfigOption<DamageAnimations> damageAnimations =
			ConfigOption.enumOption(Component.literal("Damage Animation"), DamageAnimations.ZOOM_OUT)
					.description(Component.literal("Damage animation"));

	public static final ConfigOption<Integer> damageLifetime =
			ConfigOption.intOption(Component.literal("Damage Lifetime"), 30)
					.description(Component.literal("How long the indicator will last (in ticks)"));

	public static final ConfigOption<Float> damageOffset =
			ConfigOption.floatOption(Component.literal("Damage Offset"), 1.0f)
					.description(Component.literal("Adjust offset around the entity"));

	public static final ConfigOption<Float> damageScale =
			ConfigOption.floatOption(Component.literal("Damage Scale"), 1.0f)
						.description(Component.literal("How big the damage indicator will be"));

	public static final ConfigOption<Boolean> dynamicDamageScale =
			ConfigOption.booleanOption(Component.literal("Dynamic Damage Scale"), true)
						.description(Component.literal("The scale of damage indicator will be multiplied by distance"));

	public static final ConfigOption<Float> damageScaleFactor =
			ConfigOption.floatOption(Component.literal("Damage Scale Factor"), 1.0f)
						.description(Component.literal("The increment of damage indicator scale per block"));


	public static final ConfigOption<Boolean> healEnable =
			ConfigOption.booleanOption(Component.literal("Enable Healing Indicator"), true)
					.description(Component.literal("Enable healing indicator"));

	public static final ConfigOption<String> healFormat =
			ConfigOption.stringOption(Component.literal("Heal Format"), "§a+{heal}")
					.description(Component.literal("Formatting for healing text.\nSupports Minecraft color codes.\nUse {heal} as heal placeholder."));

	public static final ConfigOption<DamageAnimations> healAnimations =
			ConfigOption.enumOption(Component.literal("Heal Animation"), DamageAnimations.RISE)
					.description(Component.literal("Healing animation"));

	public static final ConfigOption<Integer> healLifetime =
			ConfigOption.intOption(Component.literal("Heal Lifetime"), 30)
					.description(Component.literal("How long the indicator will last (in ticks)"));

	public static final ConfigOption<Float> healOffset =
			ConfigOption.floatOption(Component.literal("Heal Offset"), 1.0f)
					.description(Component.literal("Adjust offset around the entity"));

	public static final ConfigOption<Float> healScale =
			ConfigOption.floatOption(Component.literal("Heal Scale"), 1.0f)
					.description(Component.literal("How big the healing indicator will be"));

	public static final ConfigOption<Boolean> dynamicHealScale =
			ConfigOption.booleanOption(Component.literal("Dynamic Heal Scale"), true)
						.description(Component.literal("The scale of healing indicator will be multiplied by distance"));

	public static final ConfigOption<Float> healScaleFactor =
			ConfigOption.floatOption(Component.literal("Heal Scale Factor"), 1.0f)
						.description(Component.literal("The increment of healing indicator scale per block"));


	public static final CraftConfig config = CraftConfig.create("holo_damage_indicator")
			.title(Component.literal("Holo Damage Indicator Config"))

			.category(ConfigCategory.builder(Component.literal("General"))
					.section(ConfigSection.builder(Component.literal("Holograms"))
							.option(enableIndicator.controller(new BooleanController()))
							.option(renderInfront.controller(new BooleanController()))
							.option(shadow.controller(new BooleanController()))
							.option(background.controller(new BooleanController()))
							.option(disableSelf.controller(new BooleanController()))
							.option(renderDistance.controller(new SliderController<>(1, 320)))
							.option(decimal.controller(new SliderController<>(0, 5)))
							.build())
					.build())
			.category(ConfigCategory.builder(Component.literal("Damage"))
					.section(ConfigSection.builder(Component.literal("Damage Appearance"))
							.option(damageEnable.controller(new BooleanController()))
							.option(damageSource.controller(new EnumController<>()))
							.option(damageAnimations.controller(new EnumController<>()))
							.option(damageFormat.controller(new InputFieldController<>()))
							.option(critDamageFormat.controller(new InputFieldController<>()))
							.option(damageLifetime.controller(new SliderController<>(0, 200)))
							.option(damageOffset.controller(new SliderController<>(0.0f, 5.0f)))
						    .option(damageScale.controller(new SliderController<>(0.0f, 5.0f)))
						    .option(dynamicDamageScale.controller(new BooleanController()))
						    .option(damageScaleFactor.controller(new SliderController<>(0.1f, 2.0f)))
							.build())
					.build())

			.category(ConfigCategory.builder(Component.literal("Healing"))
					.section(ConfigSection.builder(Component.literal("Heal Appearance"))
							.option(healEnable.controller(new BooleanController()))
							.option(healAnimations.controller(new EnumController<>()))
							.option(healFormat.controller(new InputFieldController<>()))
							.option(healLifetime.controller(new SliderController<>(0, 200)))
							.option(healOffset.controller(new SliderController<>(0.0f, 5.0f)))
							.option(healScale.controller(new SliderController<>(0.0f, 5.0f)))
						    .option(dynamicHealScale.controller(new BooleanController()))
						    .option(healScaleFactor.controller(new SliderController<>(0.1f, 2.0f)))
							.build())
					.build())

			.build();

	public static void init() {
		config.load();
		CraftConfigRegistry.register("holo_damage_indicator", config, "Holo Damage Indicator")
				.setModMenuEnabled(true)
				.setCommandEnabled(true)
				.setCustomCommand("damageindicator")
				.build();
	}
}
