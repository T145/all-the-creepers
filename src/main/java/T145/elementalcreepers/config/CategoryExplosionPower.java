package T145.elementalcreepers.config;

import net.minecraftforge.common.config.Config;

public class CategoryExplosionPower {

    @Config.Comment("Sets the Psychic Creeper explosion power")
    public int psychicCreeperPower = 8;

    @Config.Comment("Sets the Wind Creeper explosion power")
    public int windCreeperPower = 3;

    @Config.Comment("Sets the Spring Creeper explosion radius")
    public int springCreeperPower = 3;
}