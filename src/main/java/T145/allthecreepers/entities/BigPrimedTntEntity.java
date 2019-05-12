package T145.allthecreepers.entities;

import T145.allthecreepers.api.IElementalTnt;
import T145.allthecreepers.api.immutable.RegistryATC;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.PrimedTntEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class BigPrimedTntEntity extends PrimedTntEntity implements IElementalTnt {

	public static final EntityType<BigPrimedTntEntity> BIG_TNT = Registry.register(Registry.ENTITY_TYPE, new Identifier(RegistryATC.ID, "big_tnt"), FabricEntityTypeBuilder.create(EntityCategory.MISC, BigPrimedTntEntity::new).size(EntitySize.constant(2F, 2F)).build());

	public BigPrimedTntEntity(World world) {
		super(BIG_TNT, world);
	}

	public BigPrimedTntEntity(World world_1, double double_1, double double_2, double double_3, LivingEntity livingEntity_1) {
		this(world_1);
		this.setPosition(double_1, double_2, double_3);
		double double_4 = world_1.random.nextDouble() * 6.2831854820251465D;
		this.setVelocity(-Math.sin(double_4) * 0.02D, 0.20000000298023224D, -Math.cos(double_4) * 0.02D);
		this.setFuse(80);
		this.prevX = double_1;
		this.prevY = double_2;
		this.prevZ = double_3;
		//this.causingEntity = livingEntity_1;
	}

	@Override
	public boolean canDetonate() {
		return true;
	}

	@Override
	public void detonate() {
		System.out.println("Exploding!");
		this.world.createExplosion(this, this.x, this.y + (double)(this.getHeight() / 16.0F), this.z, 6.0F, Explosion.DestructionType.BREAK);
	}
}
