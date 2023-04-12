package xyz.sirblobman.mod.gemmary.item;

import java.util.function.Supplier;

import net.minecraft.world.item.Item;

import xyz.sirblobman.mod.gemmary.GemmaryMod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public final class GemmaryItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GemmaryMod.MOD_ID);
    public static final DeferredRegister<Item> ELEMENTS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GemmaryMod.MOD_ID);

    public static void registerItems(IEventBus eventBus) {
        registerGemsAndParts();
        ITEMS.register(eventBus);

        registerPeriodicElements();
        ELEMENTS.register(eventBus);
    }

    private static RegistryObject<Item> registerItem(Supplier<Item> supplier, String id) {
        return ITEMS.register(id, supplier);
    }

    private static void registerElement(Supplier<ElementItem> supplier, String id) {
        ELEMENTS.register("element_" + id, supplier);
    }

    @ObjectHolder(registryName = "minecraft:item", value = "gemmary:ruby")
    public static Item RUBY;
    @ObjectHolder(registryName = "minecraft:item", value = "gemmary:element_oxygen")
    public static Item OXYGEN;

    public static void registerGemsAndParts() {
        registerItem(() -> new GemItem(9.0D), "ruby");
        registerItem(() -> new GemItem(9.0D), "sapphire");
        registerItem(() -> new GemItem(1.0D), "talc");
        registerItem(() -> new GemItem(6.75D), "tanzanite");
        registerItem(() -> new GemItem(8.0D), "topaz");
        registerItem(() -> new GemItem(5.5D), "turquoise");

        registerItem(SimpleItem::new, "diamond_part");
        registerItem(SimpleItem::new, "emerald_part");
    }

    public static void registerPeriodicElements() {
        // Row 1
        registerElement(() -> new ElementItem("H", 1, 1.008D), "hydrogen");
        registerElement(() -> new ElementItem("He", 2, 4.0026D), "helium");

        // Row 2
        registerElement(() -> new ElementItem("Li", 3, 6.94D), "lithium");
        registerElement(() -> new ElementItem("Be", 4, 9.0122D), "beryllium");
        registerElement(() -> new ElementItem("B", 5, 10.81D), "boron");
        registerElement(() -> new ElementItem("C", 6, 12.011D), "carbon");
        registerElement(() -> new ElementItem("N", 7, 14.007D), "nitrogen");
        registerElement(() -> new ElementItem("O", 8, 15.999D), "oxygen");
        registerElement(() -> new ElementItem("F", 9, 18.998D), "fluorine");
        registerElement(() -> new ElementItem("Ne", 10, 20.180D), "neon");

        // Row 3
        registerElement(() -> new ElementItem("Na", 11, 22.990D), "sodium");
        registerElement(() -> new ElementItem("Mg", 12, 23.305D), "magnesium");
        registerElement(() -> new ElementItem("Al", 13, 26.982D), "aluminium");
        registerElement(() -> new ElementItem("Si", 14, 28.085D), "silicon");
        registerElement(() -> new ElementItem("P", 15, 30.974D), "phosphorus");
        registerElement(() -> new ElementItem("S", 16, 32.06D), "sulfer");
        registerElement(() -> new ElementItem("Cl", 17, 35.45D), "chlorine");
        registerElement(() -> new ElementItem("Ar", 18, 39.948D), "argon");

        // Row 4
        registerElement(() -> new ElementItem("K", 19, 39.098D), "potassium");
        registerElement(() -> new ElementItem("Ca", 20, 40.078D), "calcium");
        registerElement(() -> new ElementItem("Sc", 21, 44.956D), "scandium");
        registerElement(() -> new ElementItem("Ti", 22, 47.867D), "titanium");
        registerElement(() -> new ElementItem("V", 23, 50.942D), "vanadium");
        registerElement(() -> new ElementItem("Cr", 24, 51.996D), "chromium");
        registerElement(() -> new ElementItem("Mn", 25, 54.938D), "manganese");
        registerElement(() -> new ElementItem("Fe", 26, 55.845D), "iron");
        registerElement(() -> new ElementItem("Co", 27, 58.933D), "cobalt");
        registerElement(() -> new ElementItem("Ni", 28, 58.693D), "nickel");
        registerElement(() -> new ElementItem("Cu", 29, 63.546D), "copper");
        registerElement(() -> new ElementItem("Zn", 30, 65.38D), "zinc");
        registerElement(() -> new ElementItem("Ga", 31, 69.723D), "gallium");
        registerElement(() -> new ElementItem("Ge", 32, 72.630D), "germanium");
        registerElement(() -> new ElementItem("As", 33, 74.922D), "arsenic");
        registerElement(() -> new ElementItem("Se", 34, 78.971D), "selenium");
        registerElement(() -> new ElementItem("Br", 35, 79.904D), "bromine");
        registerElement(() -> new ElementItem("Kr", 36, 83.798D), "krypton");

        // Row 5
        registerElement(() -> new ElementItem("Rb", 37, 85.468D), "rubidium");
        registerElement(() -> new ElementItem("Sr", 38, 87.62D), "strontium");
        registerElement(() -> new ElementItem("Y", 39, 88.906D), "yttrium");
        registerElement(() -> new ElementItem("Zr", 40, 91.224D), "zirconium");
        registerElement(() -> new ElementItem("Nb", 41, 92.906D), "niobium");
        registerElement(() -> new ElementItem("Mo", 42, 95.95D), "molybdenum");
        registerElement(() -> new ElementItem("Tc", 43, 98.0D), "technetium");
        registerElement(() -> new ElementItem("Ru", 44, 101.07D), "ruthenium");
        registerElement(() -> new ElementItem("Rh", 45, 102.91D), "rhodium");
        registerElement(() -> new ElementItem("Pd", 46, 106.42D), "palladium");
        registerElement(() -> new ElementItem("Ag", 47, 107.87D), "silver");
        registerElement(() -> new ElementItem("Cd", 48, 112.41D), "cadmium");
        registerElement(() -> new ElementItem("In", 49, 114.82D), "indium");
        registerElement(() -> new ElementItem("Sn", 50, 118.71D), "tin");
        registerElement(() -> new ElementItem("Sb", 51, 121.76D), "antimony");
        registerElement(() -> new ElementItem("Te", 52, 127.60D), "tellurium");
        registerElement(() -> new ElementItem("I", 53, 126.90D), "iodine");
        registerElement(() -> new ElementItem("Xe", 54, 131.29D), "xenon");

        // Row 6 + Lanthanoids
        registerElement(() -> new ElementItem("Cs", 55, 132.91D), "caesium");
        registerElement(() -> new ElementItem("Ba", 56, 137.33D), "barium");
        registerElement(() -> new ElementItem("La", 57, 138.91D), "lanthanum");
        registerElement(() -> new ElementItem("Ce", 58, 140.12D), "cerium");
        registerElement(() -> new ElementItem("Pr", 59, 140.91D), "praseodymium");
        registerElement(() -> new ElementItem("Nd", 60, 144.24D), "neodymium");
        registerElement(() -> new ElementItem("Pm", 59, 145.0D), "promethium");
        registerElement(() -> new ElementItem("Sm", 62, 150.36D), "samarium");
        registerElement(() -> new ElementItem("Eu", 63, 151.96D), "europium");
        registerElement(() -> new ElementItem("Gd", 64, 157.25D), "gadolinium");
        registerElement(() -> new ElementItem("Tb", 65, 158.93D), "terbium");
        registerElement(() -> new ElementItem("Dy", 66, 162.50D), "dysprosium");
        registerElement(() -> new ElementItem("Ho", 67, 164.93D), "holmium");
        registerElement(() -> new ElementItem("Er", 68, 167.26D), "erbium");
        registerElement(() -> new ElementItem("Tm", 69, 168.93D), "thulium");
        registerElement(() -> new ElementItem("Yb", 70, 173.05D), "ytterbium");
        registerElement(() -> new ElementItem("Lu", 71, 174.97D), "lutetium");
        registerElement(() -> new ElementItem("Hf", 72, 178.49D), "hafnium");
        registerElement(() -> new ElementItem("Ta", 73, 180.95D), "tantalum");
        registerElement(() -> new ElementItem("W", 74, 183.84D), "tungsten");
        registerElement(() -> new ElementItem("Re", 75, 186.21D), "rhenium");
        registerElement(() -> new ElementItem("os", 76, 190.23D), "osmium");
        registerElement(() -> new ElementItem("Ir", 77, 192.22D), "iridium");
        registerElement(() -> new ElementItem("Pt", 78, 195.08D), "platinum");
        registerElement(() -> new ElementItem("Au", 79, 196.97D), "gold");
        registerElement(() -> new ElementItem("Hg", 80, 200.59D), "mercury");
        registerElement(() -> new ElementItem("Tl", 81, 204.38D), "thallium");
        registerElement(() -> new ElementItem("Pb", 82, 207.2D), "lead");
        registerElement(() -> new ElementItem("Bi", 83, 208.98D), "bismuth");
        registerElement(() -> new ElementItem("Po", 84, 209.0D), "polonium");
        registerElement(() -> new ElementItem("At", 85, 210.0D), "astatine");
        registerElement(() -> new ElementItem("Rn", 86, 222.0D), "radon");

        // Row 7 + Actinoids
        registerElement(() -> new ElementItem("Fr", 87, 223.0D), "francium");
        registerElement(() -> new ElementItem("Ra", 88, 226.0D), "radium");
        registerElement(() -> new ElementItem("Ac", 89, 227.0D), "actinium");
        registerElement(() -> new ElementItem("Th", 90, 232.04D), "thorium");
        registerElement(() -> new ElementItem("Pa", 91, 231.04D), "protactinium");
        registerElement(() -> new ElementItem("U", 92, 238.03D), "uranium");
        registerElement(() -> new ElementItem("Np", 93, 237.0D), "neptunium");
        registerElement(() -> new ElementItem("Pu", 94, 244.0D), "plutonium");
        registerElement(() -> new ElementItem("Am", 95, 243.0D), "americium");
        registerElement(() -> new ElementItem("Cm", 96, 247.0D), "curium");
        registerElement(() -> new ElementItem("Bk", 97, 247.0D), "berkelium");
        registerElement(() -> new ElementItem("Cf", 98, 251.0D), "californium");
        registerElement(() -> new ElementItem("Es", 99, 252.0D), "einsteinium");
        registerElement(() -> new ElementItem("Fm", 100, 257.0D), "fermium");
        registerElement(() -> new ElementItem("Md", 101, 258.0D), "mendelevium");
        registerElement(() -> new ElementItem("No", 102, 259.0D), "nobelium");
        registerElement(() -> new ElementItem("Lr", 103, 266.0D), "lawrencium");
        registerElement(() -> new ElementItem("Rf", 104, 267.0D), "rutherfordium");
        registerElement(() -> new ElementItem("Db", 105, 268.0D), "dubnium");
        registerElement(() -> new ElementItem("Sg", 106, 269.0D), "seaborgium");
        registerElement(() -> new ElementItem("Bh", 107, 270.0D), "bohrium");
        registerElement(() -> new ElementItem("Hs", 108, 277.0D), "hassium");
        registerElement(() -> new ElementItem("Mt", 109, 278.0D), "meitnerium");
        registerElement(() -> new ElementItem("Ds", 110, 281.0D), "darmstadium");
        registerElement(() -> new ElementItem("Rg", 111, 282.0D), "roentgenium");
        registerElement(() -> new ElementItem("Cn", 112, 285.0D), "copernicium");
        registerElement(() -> new ElementItem("Uut", 113, 286.0D), "ununtrium");
        registerElement(() -> new ElementItem("Uuq", 114, 289.0D), "ununquadium");
        registerElement(() -> new ElementItem("Uup", 115, 290.0D), "ununpentium");
        registerElement(() -> new ElementItem("Uuh", 116, 293.0D), "ununhexium");
        registerElement(() -> new ElementItem("Uus", 117, 294.0D), "ununseptium");
        registerElement(() -> new ElementItem("Uuo", 118, 294.0D), "ununoctium");
    }

    public static final RegistryObject<Item> CLOTH = registerItem(ClothItem::new, "cloth");
}
