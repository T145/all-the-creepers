package T145.elementalcreepers.common.entities;

import T145.elementalcreepers.common.config.ConfigGeneral;
import T145.elementalcreepers.common.entities.explosion.ExplosionWind;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class EntityWindCreeper extends EntityBaseCreeper {

	public EntityWindCreeper(World world) {
		super(world);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		fallDistance = 0.0F;

		if (!onGround && motionY < 0.0D) {
			motionY *= 0.6D;
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	}

	@Override
	public void createExplosion(int explosionPower, boolean griefingEnabled) {
		int exPower = ConfigGeneral.windCreeperRadius * explosionPower;
		createWindGust(this, posX, posY, posZ, exPower, true);
	}

	private ExplosionWind createWindGust(Entity entity, double x, double y, double z, float strength, boolean flag) {
		ExplosionWind explosion = new ExplosionWind(world, entity, x, y, z, strength, ConfigGeneral.windCreeperPower);
		Biome biome = world.getBiome(new BlockPos(x, y, z));
		boolean flammingFlag = biome != null && BiomeDictionary.hasAnyType(biome) && (BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.WASTELAND));
		explosion.isFlaming = flammingFlag;
		explosion.isSmoking = flag;
		explosion.doExplosionA();
		return explosion;
	}
}