package dev.turtywurty.tutorialmod.entity;

import dev.turtywurty.tutorialmod.init.EntityTypeInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExampleEntity extends TameableEntity {
    public ExampleEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        setTamed(false, false);
    }

    public ExampleEntity(World world) {
        this(EntityTypeInit.EXAMPLE_ENTITY, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new TameableEntity.TameableEscapeDangerGoal(1.5, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1, 10, 2));
        this.goalSelector.add(6, new WanderAroundGoal(this, 1, 120, false));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 10f));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!getWorld().isClient) {
            if (!isTamed() && itemStack.isOf(ItemInit.EXAMPLE_FOOD) && !isBaby()) {
                itemStack.decrementUnlessCreative(1, player);

                setOwner(player);
                this.navigation.stop();
                setTarget(null);
                setSitting(true);
                getWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
                return ActionResult.SUCCESS;
            } else if (isTamed()) {
                ActionResult result = super.interactMob(player, hand);
                if (!result.isAccepted() && isOwner(player)) {
                    setSitting(!isSitting());
                    this.jumping = false;
                    this.navigation.stop();
                    setTarget(null);
                    return ActionResult.SUCCESS_NO_ITEM_USED;
                } else {
                    return result;
                }
            }
        }

        return isOwner(player) || isTamed() || itemStack.isOf(ItemInit.EXAMPLE_FOOD) ? ActionResult.CONSUME : ActionResult.PASS;
    }

    public static DefaultAttributeContainer.Builder createDefaultAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }
}
