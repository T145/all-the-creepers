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

				mapping.put(ModInit.BALLISTIC_CREEPER, entry);
				mapping.put(ModInit.CAKE_CREEPER, entry);
				mapping.put(ModInit.COOKIE_CREEPER, entry);
				mapping.put(ModInit.DARK_CREEPER, entry);
				mapping.put(ModInit.DEMOLITION_CREEPER, entry);
				mapping.put(ModInit.EARTH_CREEPER, entry);
				mapping.put(ModInit.FIRE_CREEPER, entry);
				mapping.put(ModInit.FIREWORK_CREEPER, entry);
				mapping.put(ModInit.FURNACE_CREEPER, entry);
				mapping.put(ModInit.LAVA_CREEPER, entry);
				mapping.put(ModInit.LIGHTNING_CREEPER, entry);
				mapping.put(ModInit.LUMINOUS_CREEPER, entry);
				mapping.put(ModInit.NATURE_CREEPER, entry);
				mapping.put(ModInit.PARTY_CREEPER, entry);
				mapping.put(ModInit.WATER_CREEPER, entry);
				break;
			}
		}
	}
}
