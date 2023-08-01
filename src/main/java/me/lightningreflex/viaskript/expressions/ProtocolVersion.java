package me.lightningreflex.viaskript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import javax.annotation.Nullable;

import com.viaversion.viaversion.api.Via;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings({"unused", "unchecked"})
public class ProtocolVersion extends SimpleExpression<Integer> implements Listener {
    private Expression<Player> playerExpression;

    protected Integer @NotNull [] get(@NotNull Event e) {
        return new Integer[]{
            Via.getAPI().getPlayerVersion(
                Objects.requireNonNull(this.playerExpression.getSingle(e)).getUniqueId()
            )
        };
    }

    public boolean isSingle() {
        return true;
    }

    public @NotNull Class<Integer> getReturnType() {
        return Integer.class;
    }

    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "real protocol version";
    }

    public boolean init(
        Expression<?>[] exprs,
        int matchedPattern,
        @NotNull Kleenean isDelayed,
        SkriptParser.@NotNull ParseResult parseResult
    ) {
        this.playerExpression = (Expression<Player>) exprs[0];
        return true;
    }

    static {
        Skript.registerExpression(
            ProtocolVersion.class,
            Integer.class,
            ExpressionType.SIMPLE,
            "%player%'s real protocol version"
        );
    }
}