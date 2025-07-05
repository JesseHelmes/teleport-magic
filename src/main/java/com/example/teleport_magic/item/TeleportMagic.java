package com.example.teleport_magic.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.HitResult.Type;
import org.jetbrains.annotations.NotNull;

public class TeleportMagic extends Item {
	public TeleportMagic(Properties properties) {
		super(properties);
	}

	@NotNull
	public InteractionResult use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!world.isClientSide) {
			HitResult pos = RayTraceUtil.getNearestPositionWithAir(world, player, 100);
			if (pos != null && (pos.getType() == Type.BLOCK || player.xRotO >= -5.0F)) {
				int side = pos.getType().ordinal();
				if (side != -1) {
					double x = pos.getLocation().x - (side == 4 ? 0.5D : 0.0D) + (side == 5 ? 0.5D : 0.0D);
					double y = pos.getLocation().y - (side == 0 ? 2.0D : 0.0D) + (side == 1 ? 0.5D : 0.0D);
					double z = pos.getLocation().z - (side == 2 ? 0.5D : 0.0D) + (side == 3 ? 0.5D : 0.0D);
					player.stopRiding();
					((ServerPlayer)player).connection.teleport(x, y, z, player.yRotO, player.xRotO);
					world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
					player.getCooldowns().addCooldown(stack, 20);

					return InteractionResult.SUCCESS;
				}
			}
		}

		return InteractionResult.SUCCESS;
	}

//	public InteractionResultHolder<ItemStack> use2(Level level, Player player, InteractionHand hand) {
//		ItemStack itemstack = player.getItemInHand(hand);
//
//		BlockHitResult blockhitresult = TeleportMagic.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
//		player.getCooldowns().addCooldown(this, 20);
//		//BlockPos targetPos = blockhitresult.getBlockPos();
//		//BlockPos targetPos = blockhitresult.getBlockPos().relative(blockhitresult.getDirection());
//		BlockPos targetPos = blockhitresult.getBlockPos();
//		//BlockPos targetPos = blockhitresult.getBlockPos().relative(blockhitresult.getDirection().getOpposite());
//
//
//		// teleports inside of block instead outside of it like the enderpearl
//		this.teleport(level, player, targetPos);
//
//		//this.teleport(level, player, blockhitresult.getLocation());
//
//		player.awardStat(Stats.ITEM_USED.get(this));
//
//		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
//	}

	// copy of item.getPlayerPOVHitResult
//	protected static BlockHitResult getPlayerPOVHitResult(Level level, Player player, ClipContext.Fluid fluid) {
//		float f = player.getXRot();
//		float f1 = player.getYRot();
//		Vec3 vec3 = player.getEyePosition();
//		float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
//		float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
//		float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
//		float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
//		float f6 = f3 * f4;
//		float f7 = f2 * f4;
//		double d0 = 50D; // maxDistance
//		Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
//		return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, fluid, player));
//	}

	// TODO add tp sound
//	protected void teleport(Level level, Player player, BlockPos blockpos) {
//		this.summonParticles(level, player.position(), player);
//
//		// TODO wait some ticks to see particle animations
//		if (!level.isClientSide) {
//			Entity entity = player;
//			if (entity instanceof ServerPlayer) {
//				ServerPlayer serverplayer = (ServerPlayer)entity;
//				if (serverplayer.connection.isAcceptingMessages() && serverplayer.level() == level && !serverplayer.isSleeping()) {
//					if (entity.isPassenger()) {
//						serverplayer.dismountTo(blockpos.getX(), blockpos.getY(), blockpos.getZ());
//					} else {
//						entity.teleportTo(blockpos.getX(), blockpos.getY(), blockpos.getZ());
//					}
//
//					entity.resetFallDistance();
//				}
//			} else if (entity != null) {
//				entity.teleportTo(blockpos.getX(), blockpos.getY(), blockpos.getZ());
//				entity.resetFallDistance();
//			}
//		}
//
//		this.summonParticles(level, player.position(), player);
//	}

	// default ender pearl effect
	// ParticleTypes.PORTAL
//	protected void summonParticles(Level level, Vec3 vec3, Player player) {
//		for(int i = 0; i < 32; ++i) {
//			level.addParticle(ParticleTypes.FLAME,
//					vec3.x(),
//					vec3.y() + player.getRandom().nextDouble() * 2.0D,
//					vec3.z(), player.getRandom().nextGaussian(), 0.0D,
//					player.getRandom().nextGaussian()
//					);
//		}
//	}

	// spiral swirly shape
//	protected void summonParticles(Level level, Vec3 vec3, Player player) {
//		double radius = 2.0; // Set the radius of the tornado
//		int particlesPerRing = 16; // Set the number of particles per ring
//		int rings = 5; // Set the number of rings for the tornado
//
//		for (int i = 0; i < particlesPerRing * rings; ++i) {
//			double angle = i * (2 * Math.PI / particlesPerRing); // Calculate the angle for each particle
//			double x = vec3.x() + radius * Math.cos(angle); // Calculate x position
//			double z = vec3.z() + radius * Math.sin(angle); // Calculate z position
//			double y = vec3.y() + i * (4.0 / particlesPerRing); // Increase y position for each ring
//
//			// Spawn particles
//			level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
//		}
//	}

	// spiral swirly shape in player height
//	protected void summonParticles2(Level level, Vec3 vec3, Player player) {
//		double radius = player.getBbWidth(); // Set the radius of the tornado
//		double height = player.getBbHeight();
//		int particlesPerRing = 16; // Set the number of particles per ring
//		int rings = 3; // Set the number of rings for the tornado
//
//		for (int i = 0; i < particlesPerRing * rings; ++i) {
//			double angle = i * (2 * Math.PI / particlesPerRing); // Calculate the angle for each particle
//			double x = vec3.x() + radius * Math.cos(angle); // Calculate x position
//			double z = vec3.z() + radius * Math.sin(angle); // Calculate z position
//			// Adjusted y position calculation to fit within height and create spiral effect
//			double spiralHeight = (i / particlesPerRing) * (height / rings); // Spiral height
//			double y = vec3.y() + spiralHeight + 0.2 * Math.sin(angle)  + 0.3; // Increase y position for each ring with a sinusoidal variation
//
//
//			// Spawn particles
//			level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
//		}
//	}

	// cylinder shape
//	protected void summonParticles(Level level, Vec3 vec3, Player player) {
//		double radius = player.getBbWidth(); // Set the radius of the tornado
//		double height = player.getBbHeight();
//		int particlesPerRing = 32; // was 16 // Set the number of particles per ring
//		int rings = 5; // Set the number of rings for the tornado
//
//		for (int i = 0; i < particlesPerRing * rings; ++i) {
//			double angle = i * (2 * Math.PI / particlesPerRing); // Calculate the angle for each particle
//			double x = vec3.x() + radius * Math.cos(angle); // Calculate x position
//			double z = vec3.z() + radius * Math.sin(angle); // Calculate z position
//			// 0.2 is offset because it does not start at the ground, was 0
//			double y = vec3.y() + (i / particlesPerRing) * (height / rings) + 0.2; // Increase y position for each ring
//
//			// Spawn particles
//			level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
//		}
//	}
}

