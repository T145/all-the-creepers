package T145.allthecreepers.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import T145.allthecreepers.api.IElementalTnt;
import net.minecraft.entity.PrimedTntEntity;

@Mixin(PrimedTntEntity.class)
abstract class PrimedTntEntityMixin implements IElementalTnt {

	final PrimedTntEntity tnt = (PrimedTntEntity) (Object) this;

	@Override
	public boolean canDetonate() {
		return false;
	}

	@Inject(method = "explode", at = @At("HEAD"), cancellable = true)
	private void allthecreepers$explode(CallbackInfo info) {
		if (canDetonate()) {
			detonate();
			info.cancel();
		}
	}
}
