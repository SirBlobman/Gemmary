package com.SirBlobman.gemmary.entity;

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
	private static final DataParameter<Integer> FUSE = EntityDataManager.<Integer>createKey(DiamondTNTPrimed.class, DataSerializers.VARINT);
	private EntityLivingBase placedBy;
	private int fuse;
			
	public DiamondTNTPrimed(World w)
	{
		super(w);
		fuse = 80;
		preventEntitySpawning = true;
		setSize(.98F, .98F);
	}
	
	public DiamondTNTPrimed(World w, double x, double y, double z, EntityLivingBase igniter)
	{
		this(w);
		setPosition(x,y,z);
		float f = (float) (Math.random() * (Math.PI * 2d));
		motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
        motionY = 0.20000000298023224D;
        motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
        setFuse(80);
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;
        placedBy = igniter;
	}

	@Override
	protected void entityInit()
	{
		dataManager.register(FUSE, Integer.valueOf(80));
	}
	
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return !isDead;
	}
	
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
			if(!worldObj.isRemote)
			{
				explode();
			}
		}
		else
		{
			handleWaterMovement();
			worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY + 0.5, posZ, 0.0, 0.0, 0.0, new int[0]);
		}
	}
	
	private void explode()
	{
		float power = 500.F;
		worldObj.createExplosion(this, posX, posY + (height/16.0), posZ, power, true);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		setFuse(compound.getShort("Fuse"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setShort("Fuse", (short)getFuse());
	}
	
	public EntityLivingBase getPlacedBy()
	{
		return placedBy;
	}
	
	@Override
	public float getEyeHeight()
	{
		return 0.0F;
	}
	
	public void setFuse(int f)
	{
		dataManager.set(FUSE, Integer.valueOf(f));
		fuse = f;
	}
	
	@Override
	public void notifyDataManagerChange(DataParameter<?> key)
	{
		if(FUSE.equals(key))
		{
			fuse = getFuseDataManager();
		}
	}
	
	public int getFuseDataManager()
	{
		return ((Integer)dataManager.get(FUSE)).intValue();
	}
	
	public int getFuse()
	{
		return fuse;
	}
}