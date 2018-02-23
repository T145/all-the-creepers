package T145.elementalcreepers.entities.ai;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import T145.elementalcreepers.entities.EntityEnderCreeper;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer> {

	private final EntityEnderCreeper creeper;
	private EntityPlayer player;
	private int aggroTime;
	private int teleportTime;

	public EntityAIFindPlayer(EntityEnderCreeper creeper) {
		super(creeper, EntityPlayer.class, false);
		this.creeper = creeper;
	}

	@Override
	public boolean shouldExecute() {
		double d0 = getTargetDistance();
		player = creeper.world.getNearestAttackablePlayer(creeper.posX, creeper.posY, creeper.posZ, d0, d0, (Function) null, new Predicate<EntityPlayer>() {
			public boolean apply(@Nullable EntityPlayer p_apply_1_) {
				return p_apply_1_ != null && EntityAIFindPlayer.this.creeper.shouldAttackPlayer(p_apply_1_);
			}
		});
		return player != null;
	}

	@Override
	public void startExecuting() {
		aggroTime = 5;
		teleportTime = 0;
	}

	@Override
	public void resetTask() {
		player = null;
		super.resetTask();
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (player != null) {
			if (!creeper.shouldAttackPlayer(player)) {
				return false;
			} else {
				creeper.faceEntity(player, 10.0F, 10.0F);
				return true;
			}
		} else {
			return targetEntity != null && ((EntityPlayer) targetEntity).isEntityAlive() ? true : super.shouldContinueExecuting();
		}
	}

	@Override
	public void updateTask() {
		if (player != null) {
			if (--aggroTime <= 0) {
				targetEntity = player;
				player = null;
				super.startExecuting();
			}
		} else {
			if (targetEntity != null) {
				if (creeper.shouldAttackPlayer((EntityPlayer) targetEntity)) {
					if (((EntityPlayer) targetEntity).getDistanceSq(creeper) < 16.0D) {
						creeper.teleportRandomly();
					}

					teleportTime = 0;
				} else if (((EntityPlayer) targetEntity).getDistanceSq(creeper) > 256.0D && teleportTime++ >= 30 && creeper.teleportToEntity(targetEntity)) {
					teleportTime = 0;
				}
			}

			super.updateTask();
		}
	}
}