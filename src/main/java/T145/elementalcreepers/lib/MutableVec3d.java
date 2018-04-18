package T145.elementalcreepers.lib;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class MutableVec3d extends Vec3d {

    /**
     * Mutable X Coordinate
     */
    protected double x;
    /**
     * Mutable Y Coordinate
     */
    protected double y;
    /**
     * Mutable Z Coordinate
     */
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

    /**
     * Add the given coordinates to the coordinates of this Vec3d
     */
    // public Vec3d add(double x, double y, double z)
    // {
    // return super.add(x, y, z).toImmutable();
    // }

    /**
     * Add the given coordinates to the coordinates of this Vec3d
     */
    // public Vec3d add(int x, int y, int z)
    // {
    // return super.add(x, y, z).toImmutable();
    // }

    /**
     * Offsets this Vec3d n blocks in the given direction
     */
    // public Vec3d offset(EnumFacing facing, int n)
    // {
    // return super.offset(facing, n).toImmutable();
    // }

    // public Vec3d rotate(Rotation rotationIn)
    // {
    // return super.rotate(rotationIn).toImmutable();
    // }

    /**
     * Gets the X coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the Y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the Z coordinate.
     */
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

    /**
     * Returns a version of this Vec3d that is guaranteed to be immutable.
     *
     * <p>
     * When storing a Vec3d given to you for an extended period of time, make sure
     * you use this in case the value is changed internally.
     * </p>
     */
    // public Vec3d toImmutable()
    // {
    // return new Vec3d(this);
    // }
}