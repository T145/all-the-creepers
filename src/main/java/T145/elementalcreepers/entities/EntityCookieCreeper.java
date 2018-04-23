package T145.elementalcreepers.entities;

import T145.elementalcreepers.config.ModConfig;
import T145.elementalcreepers.entities.base.EntityBaseCreeper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCookieCreeper extends EntityBaseCreeper {

    public EntityCookieCreeper(World world) {
        super(world);
    }

    @Override
    public void explode(boolean canGrief) {
        int cookieAmount = ModConfig.GENERAL.cookieCreeperAmount * (getPowered() ? 2 : 1);

        for (int i = 0; i < cookieAmount; ++i) {
            EntityItem cookie = new EntityItem(world, posX, posY, posZ, new ItemStack(Items.COOKIE));
            cookie.motionY = 0.5D;
            world.spawnEntity(cookie);
        }
    }
}