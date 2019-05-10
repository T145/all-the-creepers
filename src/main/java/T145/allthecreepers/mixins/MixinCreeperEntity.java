package T145.allthecreepers.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import T145.allthecreepers.api.IElementalCreeper;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.explosion.Explosion;

@Mixin(CreeperEntity.class)
public abstract class MixinCreeperEntity implements IElementalCreeper {

	@Override
	public boolean canDetonate() {
		return false;
	}

	@Shadow
	private void spawnEffectsCloud() {}

	@Inject(method = "explode", at = @At("HEAD"), cancellable = true)
	private void explode(CallbackInfo info) {
		CreeperEntity creeper = (CreeperEntity) (Object) this;

		if (this.canDetonate() && !creeper.world.isClient) {
			Explosion.DestructionType destructionType = creeper.world.getGameRules().getBoolean("mobGriefing") ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
			byte radius = (byte) (6 * (creeper.isCharged() ? 2 : 1));
			this.detonate(destructionType, radius, new Explosion(creeper.world, creeper, creeper.x, creeper.y, creeper.z, 1F, false, Explosion.DestructionType.DESTROY));
			creeper.remove();
			spawnEffectsCloud();
			info.cancel();
		}
	}
}
