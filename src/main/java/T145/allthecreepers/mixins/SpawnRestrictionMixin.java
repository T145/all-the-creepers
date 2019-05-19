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
public abstract class SpawnRestrictionMixin {

	@SuppressWarnings("rawtypes")
	@Shadow
	@Final
	private static Map mapping;

	@SuppressWarnings("unchecked")
	@Inject(method = "register", at = @At("HEAD"))
	private static void register(EntityType<?> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, CallbackInfo ci) {
		if (type == EntityType.CREEPER) {
			for (Class<?> c : SpawnRestriction.class.getDeclaredClasses()) {
				if (c.isEnum()) {
					continue;
				}

				mapping.put(ModInit.CAKE_CREEPER, GenericUtils.construct(c, heightmapType, location));
				mapping.put(ModInit.COOKIE_CREEPER, GenericUtils.construct(c, heightmapType, location));
				mapping.put(ModInit.FIREWORK_CREEPER, GenericUtils.construct(c, heightmapType, location));
				mapping.put(ModInit.PARTY_CREEPER, GenericUtils.construct(c, heightmapType, location));
				break;
			}
		}
	}
}
