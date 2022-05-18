package effectivejava.ch2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author suzailong
 * @date 2022/4/27-1:28 AM
 */
public abstract class Pizza {
    //    protected Pizza(Set<Topping> toppings) {
    //        this.toppings = toppings;
    //    }
    //
    //    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE,}
    //
    //    final Set<Topping> toppings;
    //
    //    abstract static class Builder<T extends Builder<T>> {
    //        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
    //
    //        public T addTopping(Topping topping) {
    //            toppings.add(Objects.requireNonNull(topping));
    //            return self();
    //        }
    //
    //        abstract Pizza build();
    //
    //    }
    //
    //    Pizza(Builder<?> builder) {
    //        toppings = builder.toppings.clone();
    //    }

}
