package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityWaterCreeper extends EntityBaseCreeper {

    public EntityWaterCreeper(World world) {
        super(world);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAICreeperSwell(this));
        this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (world.isRemote) {
            return;
        }

        createPlatform(this, world, getPosition(), Blocks.COBBLESTONE, Blocks.FLOWING_LAVA, Blocks.FLOWING_LAVA);
        createPlatform(this, world, getPosition(), Blocks.OBSIDIAN, Blocks.LAVA, Blocks.LAVA);
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.explosionRadii.waterCreeperRadius * explosionPower : ModConfig.explosionRadii.waterCreeperRadius;
        specialExplosion(radius, Blocks.WATER.getDefaultState());
    }
}