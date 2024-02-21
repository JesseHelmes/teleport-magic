package com.example.teleport_magic.item;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RayTraceUtil {
	public static HitResult getNearestPositionWithAir(Level level, Player player, int reach) {
		return getMovingObjectPosWithReachDistance(level, player, (double)reach, false, false, true);
	}

	private static HitResult getMovingObjectPosWithReachDistance(Level level, Player player, double distance, boolean p1, boolean p2, boolean p3) {
		float f = player.getXRot();
		float f1 = player.getYRot();
		double d0 = player.getX();
		double d1 = player.getY() + (double)player.getEyeHeight();
		double d2 = player.getZ();
		Vec3 vec3 = new Vec3(d0, d1, d2);
		float f2 = Mth.cos(-f1 * 0.017453292F - 3.1415927F);
		float f3 = Mth.sin(-f1 * 0.017453292F - 3.1415927F);
		float f4 = -Mth.cos(-f * 0.017453292F);
		float f5 = Mth.sin(-f * 0.017453292F);
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		Vec3 vec31 = vec3.add((double)f6 * distance, (double)f5 * distance, (double)f7 * distance);
		return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, player));
	}
}
