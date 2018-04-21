package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.List;

public class EntitySpiderCreeper extends EntityBaseCreeper {

    private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(EntitySpiderCreeper.class, DataSerializers.BYTE);

    public EntitySpiderCreeper(World world) {
        super(world);
    }

    @Override
    public void createExplosion(int explosionPower, boolean canGrief) {
        int radius = getPowered() ? ModConfig.EXPLOSION_RADII.spider * explosionPower : ModConfig.EXPLOSION_RADII.spider;
        AxisAlignedBB bb = new AxisAlignedBB(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, bb);

        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    pos.setPos(posX + x, posY + y, posZ + z);

                    if (rand.nextInt(100) < 2 && world.isAirBlock(pos)) {
                        world.setBlockState(pos, Blocks.WEB.getDefaultState());
                    }
                }
            }
        }

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                if (entity instanceof EntityLivingBase) {
                    int i = 0;

                    if (world.getDifficulty() == EnumDifficulty.NORMAL) {
                        i = 7;
                    } else if (world.getDifficulty() == EnumDifficulty.HARD) {
                        i = 15;
                    }

                    if (i > 0) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, i * 40, 0));
                    }
                }
            }
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(CLIMBING, (byte) 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!world.isRemote) {
            setBesideClimbableBlock(collidedHorizontally);
        }
    }

    @Override
    public boolean isOnLadder() {
        return isBesideClimbableBlock();
    }

    private boolean isBesideClimbableBlock() {
        return (dataManager.get(CLIMBING) & 1) != 0;
    }

    private void setBesideClimbableBlock(boolean climbing) {
        byte b0 = dataManager.get(CLIMBING);

        if (climbing) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 = (byte) (b0 & -2);
        }

        dataManager.set(CLIMBING, b0);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean isPotionApplicable(PotionEffect effect) {
        return effect.getPotion() != MobEffects.POISON && super.isPotionApplicable(effect);
    }

    @Override
    public void setInWeb() {
    }
}