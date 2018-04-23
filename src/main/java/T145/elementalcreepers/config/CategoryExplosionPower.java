package T145.elementalcreepers.config;

import net.minecraftforge.common.config.Config;

public class CategoryExplosionPower {

    @Config.Comment("Sets the Psychic Creeper explosion power")
    public int psychic = 8;

    @Config.Comment("Sets the Wind Creeper explosion power")
    public int wind = 8;

    @Config.Comment("Sets the Spring Creeper explosion power")
    public int spring = 6;
}