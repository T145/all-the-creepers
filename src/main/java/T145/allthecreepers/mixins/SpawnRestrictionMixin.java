package T145.allthecreepers.mixins;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import T145.allthecreepers.init.ModInit;
import T145.allthecreepers.utils.GenericUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

@Mixin(SpawnRestriction.class)
abstract class SpawnRestrictionMixin {

	@SuppressWarnings("rawtypes")
	@Shadow
	@Final
	private static Map mapping;

	@SuppressWarnings("unchecked")
	@Inject(method = "register", at = @At("HEAD"))
	private static void register(EntityType<?> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, CallbackInfo ci) {
		if (type == EntityType.CREEPER) {
			for (Class<?> targetClass : SpawnRestriction.class.getDeclaredClasses()) {
				if (targetClass.isEnum()) {
					continue;
				}

				Object entry = GenericUtils.construct(targetClass, heightmapType, location);

				// these checks are for if the player disables a creeper in the config & doesn't restart

				if (ModInit.config.ballisticCreeperSpawnWeight > 0)
					mapping.put(ModInit.BALLISTIC_CREEPER, entry);
				if (ModInit.config.cakeCreeperSpawnWeight > 0)
					mapping.put(ModInit.CAKE_CREEPER, entry);
				if (ModInit.config.cookieCreeperSpawnWeight > 0)
					mapping.put(ModInit.COOKIE_CREEPER, entry);
				if (ModInit.config.darkCreeperSpawnWeight > 0)
					mapping.put(ModInit.DARK_CREEPER, entry);
				if (ModInit.config.demolitionCreeperSpawnWeight > 0)
					mapping.put(ModInit.DEMOLITION_CREEPER, entry);
				if (ModInit.config.earthCreeperSpawnWeight > 0)
					mapping.put(ModInit.EARTH_CREEPER, entry);
				if (ModInit.config.fireCreeperSpawnWeight > 0)
					mapping.put(ModInit.FIRE_CREEPER, entry);
				if (ModInit.config.fireworkCreeperSpawnWeight > 0)
					mapping.put(ModInit.FIREWORK_CREEPER, entry);
				if (ModInit.config.furnaceCreeperSpawnWeight > 0)
					mapping.put(ModInit.FURNACE_CREEPER, entry);
				if (ModInit.config.lavaCreeperSpawnWeight > 0)
					mapping.put(ModInit.LAVA_CREEPER, entry);
				if (ModInit.config.lightningCreeperSpawnWeight > 0)
					mapping.put(ModInit.LIGHTNING_CREEPER, entry);
				if (ModInit.config.luminousCreeperSpawnWeight > 0)
					mapping.put(ModInit.LUMINOUS_CREEPER, entry);
				if (ModInit.config.natureCreeperSpawnWeight > 0)
					mapping.put(ModInit.NATURE_CREEPER, entry);
				if (ModInit.config.partyCreeperSpawnWeight > 0)
					mapping.put(ModInit.PARTY_CREEPER, entry);
				if (ModInit.config.waterCreeperSpawnWeight > 0)
					mapping.put(ModInit.WATER_CREEPER, entry);
				break;
			}
		}
	}
}
