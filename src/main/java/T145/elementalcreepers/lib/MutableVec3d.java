package T145.elementalcreepers.lib;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class MutableVec3d extends Vec3d {

    protected double x;
    protected double y;
    protected double z;

    public MutableVec3d() {
        this(0.0D, 0.0D, 0.0D);
    }

    public MutableVec3d(Vec3d pos) {
        this(pos.x, pos.y, pos.y);
    }

    public MutableVec3d(double x_, double y_, double z_) {
        super(0.0D, 0.0D, 0.0D);
        this.x = x_;
        this.y = y_;
        this.z = z_;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public MutableVec3d setPos(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public MutableVec3d setFlooredPos(double x, double y, double z) {
        return setPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
    }

    public MutableVec3d setPos(Entity entity) {
        return setPos(entity.posX, entity.posY, entity.posZ);
    }

    public MutableVec3d setPos(Vec3i vec) {
        return setPos(vec.getX(), vec.getY(), vec.getZ());
    }

    public MutableVec3d move(EnumFacing facing) {
        return move(facing, 1);
    }

    public MutableVec3d move(EnumFacing facing, int n) {
        return setPos(x + facing.getFrontOffsetX() * n, y + facing.getFrontOffsetY() * n, z + facing.getFrontOffsetZ() * n);
    }
}