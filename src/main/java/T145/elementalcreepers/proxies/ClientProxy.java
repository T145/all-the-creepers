package T145.elementalcreepers.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFirework;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

	@Override
	public void spawnCustomFireworks(World world, double x, double y, double z, NBTTagCompound fireworkData) {
		Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFirework.Starter(world, x, y, z, 0, 0, 0, Minecraft.getMinecraft().effectRenderer, fireworkData));
	}
}