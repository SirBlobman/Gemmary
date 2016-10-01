package com.SirBlobman.gemmary.entity;

import com.SirBlobman.gemmary.Gemmary;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class DiamondTNTPrimed extends Entity
{
	private final DataParameter<Integer> FUSE = EntityDataManager.createKey(DiamondTNTPrimed.class, DataSerializers.VARINT);
	private EntityLivingBase placer;
	private int fuse;
	
	public DiamondTNTPrimed(World w)
	{
		super(w);
		fuse = 80;
		preventEntitySpawning = true;
		setSize(0.98F, 0.98F);
	}
	
	public DiamondTNTPrimed(World w, double x, double y, double z, EntityLivingBase placer)
	{
		this(w);
		setPosition(x,y,z);
		float f = (float) (Math.random() * (Math.PI * 2.0D));
		motionX = -(Math.sin(f) * 0.02F);
		motionY = 0.20000000298023224D;
		motionZ = -(Math.cos(f) * 0.02F);
		setFuse(80);
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
		this.placer = placer;
	}
	
	@Override
	public void entityInit() {dataManager.register(FUSE, 80);}
	@Override
	public boolean canTriggerWalking() {return false;}
	@Override
	public boolean canBeCollidedWith() {return !isDead;}
	
	@Override
	public void onUpdate()
	{
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY -= 0.03999999910593033D;
		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.9800000190734863D;
		motionY *= 0.9800000190734863D;
		motionZ *= 0.9800000190734863D;
		
		if(onGround)
		{
			motionX *= 0.699999988079071D;
			motionY *= 0.699999988079071D;
			motionZ *= -0.5D;
		}
		
		--fuse;
		
		if(fuse <= 0)
		{
			setDead();
			if(!worldObj.isRemote) explode();
		}
		else
		{
			handleWaterMovement();
			worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY, posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}
	
	private void explode()
	{
		float power = Gemmary.diamond_tnt_explosion_size;
		worldObj.createExplosion(this, posX, posY + (height / 16.0D), posZ, power, true);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {setFuse(tag.getShort("Fuse"));}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound old) {old.setShort("Fuse", (short) getFuse());}
	
	public EntityLivingBase getPlacer() {return placer;}
	
	@Override
	public float getEyeHeight() {return 0.0F;}
	
	public void setFuse(int f)
	{
		dataManager.set(FUSE, f);
		fuse = f;
	}
	
	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {if(FUSE.equals(key)) fuse = getFuseDataManager();}
	
	public int getFuseDataManager() {return dataManager.get(FUSE);}
	public int getFuse() {return fuse;}
}