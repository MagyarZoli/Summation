package mz;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

/**
 * Summation class:
 * with its static methods, the Number class can calculate inheritance classes, return the sum of its values, transform.<br>
 * can handle:
 * <ul>
 *     <li>{@code Array}</li>
 *     <li>{@code Collection}</li>
 *     <li>{@code Map}</li>
 * </ul>
 * It can decompose these types and also calculate the internal values.
 * Class cannot be inherited or instantiated, its methods cannot be overridden.
 * @since 1.0
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public final class Summation {

    /**
     * Cannot be instantiated!
     */
    private Summation(){}

    /**
     * Adds two values of common type Number.<br>
     * <p><i>V extends Number</i>  two values given in 1 common type that come from the Number class.</p>
     * @param a first value.
     * @param b second value.
     * @return sum of two values.
     */
    public static <V extends Number> V summation(V a, V b) {
        return newInstanceofNumber(a, b);
    }

    /**
     * Calculates the sum of the values of the corresponding variable specified.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>T preferred Number, Collection, Map</i> Can accept classes and interfaces derived from Number[], Collection, or classes and interfaces derived from Map.</p>
     * @param x Number, Collection or Map type variable that calculates the sum of its internal values, internal values must be of type Number!
     * @return After deciding on the type, it gives the sum of the values in it.
     */
    public static <V extends Number, T> V summation(T x) {
        if (x instanceof Number[]) {
            return computeSum((V[]) x);
        } else if (x instanceof Collection) {
            return computeSum((Collection<V>) x);
        } else if (x instanceof Map) {
            return computeSum((Map<?,V>) x);
        } else {
            throw new IllegalArgumentException("Unsupported type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * It calculates the sum of the values of the given corresponding variable, and returns it stored in an array.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>T preferred Number, Collection, Map</i> Can accept classes and interfaces derived from Number[], Collection, or classes and interfaces derived from Map.</p>
     * @param x Number, Collection or Map type variable that calculates the sum of its internal values.
     * @return An array containing the internal values of the expanded selected variable. Its type will be the type of the variable's internal values.
     */
    public static <V extends Number, T> V[] summationArray(T x) {
        if (x instanceof Number[][]) {
            return (V[]) summationArray1((V[][]) x);
        } else if (x instanceof Collection[]) {
            return (V[]) summationArray2((Collection<V>[]) x);
        } else if (x instanceof Map[]) {
            return (V[]) summationArray3((Map<?, V>[]) x);
        } else if (x instanceof Collection) {
            Collection<?> collection = (Collection<?>) x;
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Collection cannot be empty");
            } else if (collection.iterator().next() instanceof Number[]) {
                return (V[]) summationArray4((Collection<V[]>) collection);
            } else if (collection.iterator().next() instanceof Collection) {
                return (V[]) summationArray5((Collection<Collection<V>>) collection);
            } else if (collection.iterator().next() instanceof Map) {
                return (V[]) summationArray6((Collection<Map<?, V>>) collection);
            } else {
                throw new IllegalArgumentException("Unsupported collection type");
            }
        } else if (x instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) x;
            if (map.isEmpty()) {
                throw new IllegalArgumentException("Map cannot be empty");
            } else if (map.values().iterator().next() instanceof Number[]) {
                return (V[]) summationArray7((Map<?, V[]>) map);
            } else if (map.values().iterator().next() instanceof Collection) {
                return (V[]) summationArray8((Map<?, Collection<V>>) map);
            } else if (map.values().iterator().next() instanceof Map) {
                return (V[]) summationArray9((Map<?, Map<?, V>>) map);
            } else {
                throw new IllegalArgumentException("Unsupported map value type");
            }
        } else {
            throw new IllegalArgumentException("Unsupported type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Calculates the sum of the values of the given variable, then Collection returns it as a specified derived class.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>C extends Collection</i> Specified Collection type</p>
     * <p><i>T preferred Number, Collection, Map</i> Can accept classes and interfaces derived from Number[], Collection, or classes and interfaces derived from Map.</p>
     * @param x Number, Collection or Map type variable that calculates the sum of its internal values.
     * @param classCollectionNameDotClass Must be specified to define the derived type of Collection.
     * @return Returns values as specified Collection type.
     */
    public static <V extends Number, C extends Collection<Integer>, T> C summationCollection(T x, Class<? extends Collection> classCollectionNameDotClass) {
        if (x instanceof Number[][]) {
            return (C) summationCollection1((V[][]) x, classCollectionNameDotClass);
        } else if (x instanceof Collection[]) {
            return (C) summationCollection2((Collection<V>[]) x, classCollectionNameDotClass);
        } else if (x instanceof Map[]) {
            return (C) summationCollection3((Map<?, V>[]) x, classCollectionNameDotClass);
        } else if (x instanceof Collection) {
            Collection<?> collection = (Collection<?>) x;
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Collection cannot be empty");
            } else if (collection.iterator().next() instanceof Number[]) {
                return (C) summationCollection4((Collection<V[]>) x, classCollectionNameDotClass);
            } else if (collection.iterator().next() instanceof Collection) {
                return (C) summationCollection5((Collection<Collection<V>>) x, classCollectionNameDotClass);
            } else if (collection.iterator().next() instanceof Map) {
                return (C) summationCollection6((Collection<Map<?, V>>) x, classCollectionNameDotClass);
            } else {
                throw new IllegalArgumentException("Unsupported inner collection type");
            }
        } else if (x instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) x;
            if (map.isEmpty()) {
                throw new IllegalArgumentException("Map cannot be empty");
            } else if (map.values().iterator().next() instanceof Number[]) {
                return (C) summationCollection7((Map<?, V[]>) x, classCollectionNameDotClass);
            } else if (map.values().iterator().next() instanceof Collection) {
                return (C) summationCollection8((Map<?, Collection<V>>) x, classCollectionNameDotClass);
            } else if (map.values().iterator().next() instanceof Map) {
                return (C) summationCollection9((Map<?, Map<?, V>>) x, classCollectionNameDotClass);
            } else {
                throw new IllegalArgumentException("Unsupported map value type");
            }
        } else {
            throw new IllegalArgumentException("Unsupported type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Calculates the sum of the values of the given variable and returns it stored as the specified Map class.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>M extends Map</i> Specific Map type.</p>
     * <p><i>T preferred Number, Collection, Map</i> can accept classes and interfaces derived from Number[], Collection, or classes and interfaces derived from Map.</p>
     * @param x Number, Collection or Map type variable that calculates the sum of its internal values.
     * @param classMapNameDotClass Required to specify the Map derived type.
     * @return Map one of the specified derived classes returns two values.
     */
    public static <V extends Number, M extends Map<Integer, V>, T> M summationMap(T x, Class<? extends Map> classMapNameDotClass) {
        return (M) summationMapKey1(x, classMapNameDotClass);
    }

    /**
     * Calculates the sum of the values of the given variable and returns it stored as the specified Map class.<br>
     * <p><i>K preferred Number, Collection, Map</i> Given key can be of Number, Collection or Map type. </p>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>M extends Map</i> Specific Map type.</p>
     * <p><i>T preferred Number, Collection, Map</i> can accept classes and interfaces derived from Number[], Collection, or classes and interfaces derived from Map.</p>
     * @param x Number, Collection or Map type variable that calculates the sum of its internal values.
     * @param classMapNameDotClass Required to specify the Map derived type.
     * @return Map is one of the specified derived classes and returns two values, the key of Map is filled in the specified way.
     * If there is no or the key type or size is not suitable, then the default is to upload.
     */
    public static <K, V extends Number, M extends Map<?, V>, T> M summationMap(T x, Class<? extends Map> classMapNameDotClass, K keys) {
        if (keys == null) {
            return (M) summationMapKey1(x, classMapNameDotClass);
        } else {
            return (M) summationMapKey2(x, classMapNameDotClass, keys);
        }
    }

    /**
     * Counts the elements of the specified array.
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param values array whose sum of values you want.
     * @return sum of values.
     * @see Summation#summation(Object)
     * @see Summation#foreachUpload1(Number[][], Number[])
     * @see Summation#foreachUpload4(Collection, Number[])
     * @see Summation#foreachUpload7(Map, Number[])
     * @see Summation#summationCollection1(Number[][], Class)
     * @see Summation#summationCollection4(Collection, Class)
     * @see Summation#summationCollection7(Map, Class)
     * @see Summation#summationMap1(Number[][], Class)
     * @see Summation#summationMap4(Collection, Class)
     * @see Summation#summationMap7(Map, Class)
     * @see Summation#summationMap1(Number[][], Class, Object)
     * @see Summation#summationMap4(Collection, Class, Object)
     * @see Summation#summationMap7(Map, Class, Object)
     */
    private static <V extends Number> V computeSum(V[] values) {
        V sum = null;
        for (V element : values) {
            if (sum == null) {
                sum = element;
            } else {
                sum = newInstanceofNumber(sum, element);
            }
        }
        return sum;
    }

    /**
     * Counts the elements of the specified Collection.
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param values Collection whose sum of values you want.
     * @return sum of values.
     * @see Summation#summation(Object)
     * @see Summation#foreachUpload2(Collection[], Number[])
     * @see Summation#foreachUpload5(Collection, Number[])
     * @see Summation#foreachUpload8(Map, Number[])
     * @see Summation#summationCollection2(Collection[], Class)
     * @see Summation#summationCollection5(Collection, Class)
     * @see Summation#summationCollection8(Map, Class)
     * @see Summation#summationMap2(Collection[], Class)
     * @see Summation#summationMap5(Collection, Class)
     * @see Summation#summationMap8(Map, Class)
     * @see Summation#summationMap2(Collection[], Class, Object)
     * @see Summation#summationMap5(Collection, Class, Object)
     * @see Summation#summationMap8(Map, Class, Object)
     */
    private static <V extends Number> V computeSum(Collection<V> values) {
        V sum = null;
        for (V element : values) {
            if (sum == null) {
                sum = element;
            } else {
                sum = newInstanceofNumber(sum, element);
            }
        }
        return sum;
    }

    /**
     * Counts the elements of the specified Map.
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param values Map whose sum of values you want.
     * @return sum of values.
     * @see Summation#summation(Object)
     * @see Summation#foreachUpload3(Map[], Number[])
     * @see Summation#foreachUpload6(Collection, Number[])
     * @see Summation#foreachUpload9(Map, Number[])
     * @see Summation#summationCollection3(Map[], Class)
     * @see Summation#summationCollection6(Collection, Class)
     * @see Summation#summationCollection9(Map, Class)
     * @see Summation#summationMap3(Map[], Class)
     * @see Summation#summationMap6(Collection, Class)
     * @see Summation#summationMap9(Map, Class)
     * @see Summation#summationMap3(Map[], Class, Object)
     * @see Summation#summationMap6(Collection, Class, Object)
     * @see Summation#summationMap9(Map, Class, Object)
     */
    private static <V extends Number> V computeSum(Map<?, V> values) {
        V sum = null;
        for (Map.Entry<?,V> element : values.entrySet()) {
            if (sum == null) {
                sum = element.getValue();
            } else {
                sum = newInstanceofNumber(sum, element.getValue());
            }
        }
        return sum;
    }

    /**
     * Determines the type of the variables and adds the two values accordingly.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param a first value.
     * @param b second value.
     * @return returns the sum of two values of the specified type.
     * @see Summation#summation(Number, Number)
     */
    private static <V extends Number> V newInstanceofNumber(V a, V b) {
        if (b instanceof BigDecimal) {
            return (V) BigDecimal.valueOf(a.doubleValue() + b.doubleValue());
        } else if (b instanceof BigInteger) {
            return (V) BigInteger.valueOf(a.longValue() + b.longValue());
        } else if (b instanceof Double) {
            return (V) Double.valueOf(a.doubleValue() + b.doubleValue());
        } else if (b instanceof Float) {
            return (V) Float.valueOf(a.floatValue() + b.floatValue());
        } else if (b instanceof Long) {
            return (V) Long.valueOf(a.longValue() + b.longValue());
        } else if (b instanceof Integer) {
            return (V) Integer.valueOf(a.intValue() + b.intValue());
        } else if (b instanceof Short) {
            return (V) Short.valueOf((short) (a.shortValue() + b.shortValue()));
        } else {
            return (V) Byte.valueOf((byte) (a.byteValue() + b.byteValue()));
        }
    }

    /**
     * Creates an array of type and size.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param value Based on this variable, it decides what type of array should be created.
     * @param size By entering a value, you can control the size of the array it creates.
     * @return returns prepared array as value.
     * @see Summation#createPreferredSizeArray3(Map[], Number[], int)
     * @see Summation#createPreferredSizeArray6(Collection, Number[], int)
     */
    private static <V extends Number> V[] newInstanceofCreateArray(V value, int size) {
        if (value instanceof BigDecimal) {
            return (V[]) new BigDecimal[size];
        } else if (value instanceof BigInteger) {
            return (V[]) new BigInteger[size];
        } else if (value instanceof Double) {
            return (V[]) new Double[size];
        } else if (value instanceof Float) {
            return (V[]) new Float[size];
        } else if (value instanceof Long) {
            return (V[]) new Long[size];
        } else if (value instanceof Integer) {
            return (V[]) new Integer[size];
        } else if (value instanceof Short) {
            return (V[]) new Short[size];
        } else if (value instanceof Byte) {
            return (V[]) new Byte[size];
        } else {
            return (V[]) new Number[size];
        }
    }

    /**
     * Creates an array of type and size.
     * Method takes into account if the specified array size is larger than the specified size, then the new array will be the size of the array specified as a parameter.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param array Creates another array according to the type of element selected from an array.
     * @param size By entering a value, you can control the size of the array it creates.
     * @return returns prepared array as value.
     * @see Summation#createPreferredSizeArray1(Number[][], Number[], int)
     * @see Summation#createPreferredSizeArray4(Collection, Number[], int)
     * @see Summation#createPreferredSizeArray7(Map, Number[], int)
     */
    private static <V extends Number> V[] newInstanceofCreateArray(V[] array, int size) {
        int length = 0;
        if (size <= 0) {
            length = array.length;
        } else {
            length = size;
        }
        return newInstanceofCreateArray(array[0], length);
    }

    /**
     * Creates an array of type and size.
     * Method takes into account if the specified array size is larger than the specified size, then the new array will be the size of the array specified as a parameter.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param array Creates another array according to the type of element selected from an array.
     * @param size By entering a value, you can control the size of the array it creates.
     * @return returns prepared array as value.
     */
    private static <V extends Number> V[] newInstanceofCreateArray(V[][] array, int size) {
        int length = 0;
        if (size <= 0) {
            length = array.length;
        } else {
            length = size;
        }
        if (array[0][0] instanceof BigDecimal) {
            BigDecimal[] c = new BigDecimal[length];
            for (int i = 0; i < length; i++) {
                c[i] = new BigDecimal(0);
            }
            return (V[]) c;
        } else if (array[0][0] instanceof BigInteger) {
            BigInteger[] c = new BigInteger[length];
            for (int i = 0; i < length; i++) {
                c[i] = new BigInteger("0");
            }
            return (V[]) c;
        } else if (array[0][0] instanceof Double) {
            Double[] c = new Double[length];
            for (int i = 0; i < length; i++) {
                c[i] = (double) 0;
            }
            return (V[]) c;
        } else if (array[0][0] instanceof Float) {
            Float[] c = new Float[length];
            for (int i = 0; i < length; i++) {
                c[i] = 0f;
            }
            return (V[]) c;
        } else if (array[0][0] instanceof Long) {
            Long[] c = new Long[length];
            for (int i = 0; i < length; i++) {
                c[i] = 0L;
            }
            return (V[]) c;
        } else if (array[0][0] instanceof Integer) {
            Integer[] c = new Integer[length];
            for (int i = 0; i < length; i++) {
                c[i] = 0;
            }
            return (V[]) c;
        } else if (array[0][0] instanceof Short) {
            Short[] c = new Short[length];
            for (int i = 0; i < length; i++) {
                c[i] = (short) 0;
            }
            return (V[]) c;
        } else {
            Byte[] c = new Byte[length];
            for (int i = 0; i < length; i++) {
                c[i] = (byte) 0;
            }
            return (V[]) c;
        }
    }

    /**
     * Creates an array of type and size.
     * Method takes into account if the size of the specified Collection is larger than the specified size, then the new array will be the size of the Collection specified as a parameter.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param collection Creates another array according to the type of element selected from the collection.
     * @param size By entering a value, you can control the size of the array it creates.
     * @return returns prepared array as value.
     * @see Summation#createPreferredSizeArray2(Collection[], Number[], int)
     * @see Summation#createPreferredSizeArray5(Collection, Number[], int)
     * @see Summation#createPreferredSizeArray8(Map, Number[], int)
     */
    private static <V extends Number> V[] newInstanceofCreateArray(Collection<V> collection, int size) {
        int length = 0;
        if (size <= 0) {
            length = collection.size();
        } else {
            length = size;
        }
        V[] newArray = null;
        for (V value : collection) {
            newArray = newInstanceofCreateArray(value, length);
            break;
        }
        return newArray;
    }

    /**
     * Creates an array of type and size.
     * The method takes into account if the size of the specified Map is larger than the specified size, then the new array will be the size of the Map specified as a parameter.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param map Creates another array according to the type of element selected from the Map value.
     * @param size By entering a value, you can control the size of the array it creates.
     * @return returns prepared array as value.
     */
    private static <V extends Number> V[] newInstanceofCreateArray(Map<?, V[]> map, int size) {
        int length = 0;
        if (size <= 0) {
            length = map.size();
        } else {
            length = size;
        }
        V[] values = null;
        for (Map.Entry<?, V[]> entry : map.entrySet()) {
            values = entry.getValue();
            break;
        }
        return newInstanceofCreateArray(values[0], length);
    }

    /**
     * Creates an array of type and size.
     * The method takes into account if the size of the specified Map is larger than the specified size, then the new array will be the size of the Map specified as a parameter.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * @param map Creates another array according to the type of element selected from the Map value.
     * @param size By entering a value, you can control the size of the array it creates.
     * @return returns prepared array as value.
     * @see Summation#createPreferredSizeArray9(Map, Number[], int)
     */
    private static <V extends Number> V[] newInstanceofCreateArray2(Map<?, V> map, int size) {
        int length = 0;
        if (size <= 0) {
            length = map.size();
        } else {
            length = size;
        }
        V values = null;
        for (Map.Entry<?, V> entry : map.entrySet()) {
            values = entry.getValue();
            break;
        }
        return newInstanceofCreateArray(values, length);
    }

    /**
     * Creating a new Collection derived class.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>C extends Collection</i> Specified Collection type</p>
     * @param classCollectionNameDotClass Required to define the type derived from the Collection class.
     * @return A new descendant Collection is created as the specified Collection class.
     * @see Summation#summationCollection1(Number[][], Class)
     * @see Summation#summationCollection2(Collection[], Class)
     * @see Summation#summationCollection3(Map[], Class)
     * @see Summation#summationCollection4(Collection, Class)
     * @see Summation#summationCollection5(Collection, Class)
     * @see Summation#summationCollection6(Collection, Class)
     * @see Summation#summationCollection7(Map, Class)
     * @see Summation#summationCollection8(Map, Class)
     * @see Summation#summationCollection9(Map, Class)
     */
    private static <V extends Number, C extends Collection<V>> C newCollection(Class<C> classCollectionNameDotClass) {
        try {
            Constructor<C> constructor = classCollectionNameDotClass.getConstructor();
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creating a new Map derived class.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>M extends Map</i> Specified Map type</p>
     * @param classMapNameDotClass Required to define the type derived from the Map class.
     * @return A new descendant Map is created as the specified Map class.
     * @see Summation#summationMap1(Number[][], Class)
     * @see Summation#summationMap2(Collection[], Class)
     * @see Summation#summationMap3(Map[], Class)
     * @see Summation#summationMap4(Collection, Class)
     * @see Summation#summationMap5(Collection, Class)
     * @see Summation#summationMap6(Collection, Class)
     * @see Summation#summationMap7(Map, Class)
     * @see Summation#summationMap8(Map, Class)
     * @see Summation#summationMap9(Map, Class)
     * @see Summation#summationMap1(Number[][], Class, Object)
     * @see Summation#summationMap2(Collection[], Class, Object)
     * @see Summation#summationMap3(Map[], Class, Object)
     * @see Summation#summationMap4(Collection, Class, Object)
     * @see Summation#summationMap5(Collection, Class, Object)
     * @see Summation#summationMap6(Collection, Class, Object)
     * @see Summation#summationMap7(Map, Class, Object)
     * @see Summation#summationMap8(Map, Class, Object)
     * @see Summation#summationMap9(Map, Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> M newMap(Class<M> classMapNameDotClass) {
        try {
            Constructor<M> constructor = classMapNameDotClass.getConstructor();
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks that the size of the selected key is correct.<br>
     * @param keySize Preferred key size.
     * @param targetSize The size of the variable whose internal values will be counted.
     * @throws ArrayIndexOutOfBoundsException Key array size {@code keySize} is less than x array size {@code targetSize}
     * @see Summation#summationMap1(Number[][], Class, Object)
     * @see Summation#summationMap2(Collection[], Class, Object)
     * @see Summation#summationMap3(Map[], Class, Object)
     * @see Summation#summationMap4(Collection, Class, Object)
     * @see Summation#summationMap6(Collection, Class, Object)
     * @see Summation#summationMap7(Map, Class, Object)
     * @see Summation#summationMap8(Map, Class, Object)
     * @see Summation#summationMap9(Map, Class, Object)
     */
    private static void getThrowQuestion(int keySize, int targetSize) {
        if (keySize < targetSize) {
            throw new ArrayIndexOutOfBoundsException("Key array size "+keySize+" is less than x array size "+targetSize);
        }
    }

    /**
     * Calculates the sum of the values of the given variable, then Map returns it as a specified derived class.<br>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>M extends Map</i> Specific Map type.</p>
     * <p><i>T preferred Number, Collection, Map</i> can accept classes and interfaces derived from Number[], Collection, or classes and interfaces derived from Map.</p>
     * @param x Number, Collection or Map type variable that calculates the sum of its internal values.
     * @param classMapNameDotClass Must be specified to define the derived type of Map.
     * @return Returns values as specified Map type.
     * @see Summation#summationMap(Object, Class)
     */
    private static <V extends Number, M extends Map<Integer, V>, T> M summationMapKey1(T x, Class<? extends Map> classMapNameDotClass) {
        if (x instanceof Number[][]) {
            return summationMap1((V[][]) x, classMapNameDotClass);
        } else if (x instanceof Collection[]) {
            return summationMap2((Collection<V>[]) x, classMapNameDotClass);
        } else if (x instanceof Map[]) {
            return summationMap3((Map<?, V>[]) x, classMapNameDotClass);
        } else if (x instanceof Collection) {
            Collection<?> collection = (Collection<?>) x;
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Collection cannot be empty");
            } else if (collection.iterator().next() instanceof Number[]) {
                return summationMap4((Collection<V[]>) x, classMapNameDotClass);
            } else if (collection.iterator().next() instanceof Collection) {
                return summationMap5((Collection<Collection<V>>) x, classMapNameDotClass);
            } else if (collection.iterator().next() instanceof Map) {
                return summationMap6((Collection<Map<?, V>>) x, classMapNameDotClass);
            } else {
                throw new IllegalArgumentException("Unsupported inner collection type");
            }
        } else if (x instanceof Map){
            Map<?, ?> map = (Map<?, ?>) x;
            if (map.isEmpty()) {
                throw new IllegalArgumentException("Map cannot be empty");
            } else if (map.values().iterator().next() instanceof Number[]) {
                return summationMap7((Map<?, V[]>) x, classMapNameDotClass);
            } else if (map.values().iterator().next() instanceof Collection) {
                return summationMap8((Map<?, Collection<V>>) x, classMapNameDotClass);
            } else if (map.values().iterator().next() instanceof Map) {
                return summationMap9((Map<?, Map<?, V>>) x, classMapNameDotClass);
            } else {
                throw new IllegalArgumentException("Unsupported map value type");
            }
        } else {
            throw new IllegalArgumentException("Unsupported type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Calculates the sum of the values of the given variable, then Map returns it as a specified derived class.<br>
     * <p><i>K preferred Number, Collection, Map</i> Given key can be of Number, Collection or Map type. </p>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>M extends Map</i> Specific Map type.</p>
     * <p><i>T preferred Number, Collection, Map</i> can accept classes and interfaces derived from Number[], Collection, or classes and interfaces derived from Map.</p>
     * @param x Number, Collection or Map type variable that calculates the sum of its internal values.
     * @param classMapNameDotClass Must be specified to define the derived type of Map.
     * @return Returns values as specified Map type.
     * If no key is specified or if it is of the wrong type or size, it will be uploaded with the default version.
     * @see Summation#summationMap(Object, Class)
     * @see Summation#summationMap(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<Integer, V>, T> M summationMapKey2(T x, Class<? extends Map> classMapNameDotClass, K keys) {
        if (x instanceof Number[][]) {
            return (M) summationMap1((V[][]) x, classMapNameDotClass, keys);
        } else if (x instanceof Collection[]) {
            return (M) summationMap2((Collection<V>[]) x, classMapNameDotClass, keys);
        } else if (x instanceof Map[]) {
            return (M) summationMap3((Map<?, V>[]) x, classMapNameDotClass, keys);
        } else if (x instanceof Collection) {
            Collection<?> collection = (Collection<?>) x;
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Collection cannot be empty");
            } else if (collection.iterator().next() instanceof Number[]) {
                return (M) summationMap4((Collection<V[]>) x, classMapNameDotClass, keys);
            } else if (collection.iterator().next() instanceof Collection) {
                return (M) summationMap5((Collection<Collection<V>>) x, classMapNameDotClass, keys);
            } else if (collection.iterator().next() instanceof Map) {
                return (M) summationMap6((Collection<Map<?, V>>) x, classMapNameDotClass, keys);
            } else {
                throw new IllegalArgumentException("Unsupported inner collection type");
            }
        } else if (x instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) x;
            if (map.isEmpty()){
                throw new IllegalArgumentException("Map cannot be empty");
            } else if (map.values().iterator().next() instanceof Number[]) {
                return (M) summationMap7((Map<?, V[]>) x, classMapNameDotClass, keys);
            } else if (map.values().iterator().next() instanceof Collection) {
                return (M) summationMap8((Map<?, Collection<V>>) x, classMapNameDotClass, keys);
            } else if (map.values().iterator().next() instanceof Map) {
                return (M) summationMap9((Map<?, Map<?, V>>) x, classMapNameDotClass, keys);
            } else {
                throw new IllegalArgumentException("Unsupported map value type");
            }
        } else {
            throw new IllegalArgumentException("Unsupported type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * With the for each loop, the Map key type is filled with the specified keys.<br>
     * <p><i>K extends Collection</i> Key of type Collection.</p>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>M extends Map</i> Specific Map type.</p>
     * @param sum Map-type variable to load.
     * @param array With which the values of the Map type variable will be filled.
     * @param keys Will upload the values of the specified key to the value of the Map key.
     * @param size How big should the Map be.
     * @return Uploaded Map.
     * @see Summation#summationMap1(Number[][], Class, Object)
     * @see Summation#summationMap2(Collection[], Class, Object)
     * @see Summation#summationMap3(Map[], Class, Object)
     * @see Summation#summationMap4(Collection, Class, Object)
     * @see Summation#summationMap6(Collection, Class, Object)
     * @see Summation#summationMap7(Map, Class, Object)
     * @see Summation#summationMap8(Map, Class, Object)
     * @see Summation#summationMap9(Map, Class, Object)
     */
    private static <K, V extends Number, M extends Map<K, V>> M foreachCollectionKey(M sum, V[] array, Collection<K> keys, int size) {
        int i = 0;
        for (K key : keys) {
            if (i < size) {
                sum.put(key, array[i]);
                i++;
            } else {
                break;
            }
        }
        return sum;
    }

    /**
     * With the for each loop, the Map key type is filled with the specified keys.<br>
     * <p><i>K extends Map</i> Key of type Map.</p>
     * <p><i>V extends Number</i> It can handle classes inherited from the Number class.</p>
     * <p><i>M extends Map</i> Specific Map type.</p>
     * @param sum Map-type variable to load.
     * @param array With which the values of the Map type variable will be filled.
     * @param keys Will upload the values of the specified key to the value of the Map key.
     * @param size How big should the Map be.
     * @return Uploaded Map.
     * @see Summation#summationMap1(Number[][], Class, Object)
     * @see Summation#summationMap2(Collection[], Class, Object)
     * @see Summation#summationMap3(Map[], Class, Object)
     * @see Summation#summationMap4(Collection, Class, Object)
     * @see Summation#summationMap6(Collection, Class, Object)
     * @see Summation#summationMap7(Map, Class, Object)
     * @see Summation#summationMap8(Map, Class, Object)
     * @see Summation#summationMap9(Map, Class, Object)
     */
    private static <K, V extends Number, M extends Map<K, V>> M foreachMapKey(M sum, V[] array, Map<K, ?> keys, int size) {
        int i = 0;
        for (Map.Entry<K, ?> key : keys.entrySet()) {
            if (i < size) {
                sum.put(key.getKey(), array[i]);
            } else {
                break;
            }
        }
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number> V[] summationArray1(V[][] x) {
        V[] sum = null;
        sum = foreachUpload1(x, createPreferredSizeArray1(x, sum, getTargetSize1(x)));
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] summationArray2(C[] x) {
        V[] sum = null;
        sum = foreachUpload2(x, createPreferredSizeArray2(x, sum, getTargetSize2(x)));
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] summationArray3(M[] x) {
        V[] sum = null;
        sum = foreachUpload3(x, createPreferredSizeArray3(x, sum, getTargetSize3(x)));
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number> V[] summationArray4(Collection<V[]> x) {
        V[] sum = null;
        sum = foreachUpload4(x, createPreferredSizeArray4(x, sum, getTargetSize4(x)));
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] summationArray5(Collection<C> x) {
        V[] sum = null;
        sum = foreachUpload5(x, createPreferredSizeArray5(x, sum, getTargetSize5(x)));
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] summationArray6(Collection<M> x) {
        V[] sum = null;
        sum = foreachUpload6(x, createPreferredSizeArray6(x, sum, getTargetSize6(x)));
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number> V[] summationArray7(Map<?, V[]> x) {
        V[] sum = null;
        sum = foreachUpload7(x, createPreferredSizeArray7(x, sum, getTargetSize7(x)));
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] summationArray8(Map<?, C> x) {
        V[] sum = null;
        sum = foreachUpload8(x, createPreferredSizeArray8(x, sum, getTargetSize8(x)));
        return sum;
    }

    /**
     * Creates an appropriately sized, then an array that you fill with the appropriate type, then fills in the desired variable with the for each clause.<br>
     * @param x variable that serves the values.
     * @return Value loaded according to type.
     * @see Summation#summationArray(Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] summationArray9(Map<?, M> x) {
        V[] sum = null;
        sum = foreachUpload9(x, createPreferredSizeArray9(x, sum, getTargetSize9(x)));
        return sum;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray1(Number[][])
     * @see Summation#summationMap1(Number[][], Class)
     * @see Summation#summationMap1(Number[][], Class, Object)
     */
    private static <V extends Number> V[] foreachUpload1(V[][] x, V[] array) {
        int i = 0;
        for (V[] values : x) {
            array[i] = computeSum(values);
            i++;
        }
        return array;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray2(Collection[])
     * @see Summation#summationMap2(Collection[], Class)
     * @see Summation#summationMap2(Collection[], Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] foreachUpload2(C[] x, V[] array) {
        int i = 0;
        for (C values : x) {
            array[i] = computeSum(values);
            i++;
        }
        return array;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray3(Map[])
     * @see Summation#summationMap3(Map[], Class)
     * @see Summation#summationMap3(Map[], Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] foreachUpload3(M[] x, V[] array) {
        int i = 0;
        for (M values : x) {
            array[i] = computeSum(values);
            i++;
        }
        return array;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray4(Collection)
     * @see Summation#summationMap4(Collection, Class)
     * @see Summation#summationMap4(Collection, Class, Object)
     */
    private static <V extends Number> V[] foreachUpload4(Collection<V[]> x, V[] array) {
        int i = 0;
        for (V[] values : x) {
            array[i] = computeSum(values);
            i++;
        }
        return array;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray5(Collection)
     * @see Summation#summationMap5(Collection, Class)
     * @see Summation#summationMap5(Collection, Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] foreachUpload5(Collection<C> x, V[] array) {
        int i = 0;
        for (C values : x) {
            array[i] = computeSum(values);
            i++;
        }
        return array;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray6(Collection)
     * @see Summation#summationMap6(Collection, Class)
     * @see Summation#summationMap6(Collection, Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] foreachUpload6(Collection<M> x, V[] array) {
        int i = 0;
        for (M values : x) {
            array[i] = computeSum(values);
            i++;
        }
        return array;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray7(Map)
     * @see Summation#summationMap7(Map, Class)
     * @see Summation#summationMap7(Map, Class, Object)
     */
    private static <V extends Number> V[] foreachUpload7(Map<?, V[]> x, V[] array) {
        int i = 0;
        for (Map.Entry<?, V[]> values : x.entrySet()) {
            array[i] = computeSum(values.getValue());
            i++;
        }
        return array;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray8(Map)
     * @see Summation#summationMap8(Map, Class)
     * @see Summation#summationMap8(Map, Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] foreachUpload8(Map<?, C> x, V[] array) {
        int i = 0;
        for (Map.Entry<?, C> values : x.entrySet()) {
            array[i] = computeSum(values.getValue());
            i++;
        }
        return array;
    }

    /**
     * Upload specified array.<br>
     * @param x Breaks down the sorted by type and calculates its internal values.
     * @param array Empty array with a specific type into which you fill the values.
     * @return filled array with the right size and the right Number type.
     * @see Summation#summationArray9(Map)
     * @see Summation#summationMap9(Map, Class)
     * @see Summation#summationMap9(Map, Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] foreachUpload9(Map<?, M> x, V[] array) {
        int i = 0;
        for (Map.Entry<?, M> value : x.entrySet()) {
            array[i] = computeSum(value.getValue());
            i++;
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray1(Number[][])
     * @see Summation#summationMap1(Number[][], Class)
     * @see Summation#summationMap1(Number[][], Class, Object)
     */
    private static <V extends Number> V[] createPreferredSizeArray1(V[][] x, V[] array, int targetSize) {
        for (V[] values : x) {
            if (x.length > targetSize) {
                array = newInstanceofCreateArray(values, x.length);
            } else {
                array = newInstanceofCreateArray(values, targetSize);
            }
            break;
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray2(Collection[])
     * @see Summation#summationMap2(Collection[], Class)
     * @see Summation#summationMap2(Collection[], Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] createPreferredSizeArray2(C[] x, V[] array, int targetSize) {
        for (C values : x) {
            if (x.length > targetSize) {
                array = newInstanceofCreateArray(values, x.length);
            } else {
                array = newInstanceofCreateArray(values, targetSize);
            }
            break;
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray3(Map[])
     * @see Summation#summationMap3(Map[], Class)
     * @see Summation#summationMap3(Map[], Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] createPreferredSizeArray3(M[] x, V[] array, int targetSize) {
        for (M values: x) {
            for (Map.Entry<?,V> value : values.entrySet()) {
                if (x.length>targetSize) {
                    array = newInstanceofCreateArray(value.getValue(), x.length);
                } else {
                    array = newInstanceofCreateArray(value.getValue(), targetSize);
                }
                break;
            }
            break;
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray4(Collection)
     * @see Summation#summationMap4(Collection, Class)
     * @see Summation#summationMap4(Collection, Class, Object)
     */
    private static <V extends Number> V[] createPreferredSizeArray4(Collection<V[]> x, V[] array, int targetSize) {
        for (V[] values : x) {
            if (x.size() > targetSize) {
                array = newInstanceofCreateArray(values, x.size());
            } else {
                array = newInstanceofCreateArray(values, targetSize);
            }
            break;
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray5(Collection)
     * @see Summation#summationMap5(Collection, Class)
     * @see Summation#summationMap5(Collection, Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] createPreferredSizeArray5(Collection<C> x, V[] array, int targetSize) {
        for (C values : x) {
            if (x.size() > targetSize) {
                array = newInstanceofCreateArray(values, x.size());
            } else {
                array = newInstanceofCreateArray(values, targetSize);
            }
            break;
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray6(Collection)
     * @see Summation#summationMap6(Collection, Class)
     * @see Summation#summationMap6(Collection, Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] createPreferredSizeArray6(Collection<M> x, V[] array, int targetSize) {
        for (M values: x) {
            for (Map.Entry<?,V> value : values.entrySet()) {
                if (x.size() > targetSize) {
                    array = newInstanceofCreateArray(value.getValue(), x.size());
                } else {
                    array = newInstanceofCreateArray(value.getValue(), targetSize);
                }
                break;
            }
            break;
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray7(Map)
     * @see Summation#summationMap7(Map, Class)
     * @see Summation#summationMap7(Map, Class, Object)
     */
    private static <V extends Number> V[] createPreferredSizeArray7(Map<?, V[]> x, V[] array, int targetSize) {
        for (Map.Entry<?, V[]> entry : x.entrySet()) {
            if (x.size() > targetSize) {
                array = newInstanceofCreateArray(entry.getValue(), x.size());
            } else {
                array = newInstanceofCreateArray(entry.getValue(), targetSize);
            }
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray8(Map)
     * @see Summation#summationMap8(Map, Class)
     * @see Summation#summationMap8(Map, Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> V[] createPreferredSizeArray8(Map<?, C> x, V[] array, int targetSize) {
        for (Map.Entry<?, C> entry : x.entrySet()) {
            if (x.size() > targetSize) {
                array = newInstanceofCreateArray(entry.getValue(), x.size());
            } else {
                array = newInstanceofCreateArray(entry.getValue(), targetSize);
            }
        }
        return array;
    }

    /**
     * From a variable with a foreach loop and decide which of the values in it is more suitable for creating an array.<br>
     * @param x Type that serves the values
     * @param array null array that will now be created with the appropriate size and type.
     * @param targetSize specified size
     * @return empty array created, in the right type and size
     * @see Summation#summationArray9(Map)
     * @see Summation#summationMap9(Map, Class)
     * @see Summation#summationMap9(Map, Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> V[] createPreferredSizeArray9(Map<?, M> x, V[] array, int targetSize) {
        for (Map.Entry<?, M> value : x.entrySet()) {
            if (x.size() > targetSize) {
                array = newInstanceofCreateArray2(value.getValue(), x.size());
            } else {
                array = newInstanceofCreateArray2(value.getValue(), targetSize);
            }
            break;
        }
        return array;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray1(Number[][])
     * @see Summation#summationMap1(Number[][], Class)
     * @see Summation#summationMap1(Number[][], Class, Object)
     */
    private static <V extends Number> int getTargetSize1(V[][] x) {
        int targetSize = 0;
        for (V[] values : x) {
            if (values.length>targetSize) {
                targetSize = values.length;
            }
        }
        return targetSize;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray2(Collection[])
     * @see Summation#summationMap2(Collection[], Class)
     * @see Summation#summationMap2(Collection[], Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> int getTargetSize2(C[] x) {
        int targetSize = 0;
        for (C values : x) {
            if (values.size() > targetSize) {
                targetSize = values.size();
            }
        }
        return targetSize;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray3(Map[])
     * @see Summation#summationMap3(Map[], Class)
     * @see Summation#summationMap3(Map[], Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> int getTargetSize3(M[] x) {
        int targetSize = 0;
        for (M values : x) {
            for (Map.Entry<?, V> value : values.entrySet()) {
                if (values.size() > targetSize) {
                    targetSize = values.size();
                }
            }
        }
        return targetSize;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray4(Collection)
     * @see Summation#summationMap4(Collection, Class)
     * @see Summation#summationMap4(Collection, Class, Object)
     */
    private static <V extends Number> int getTargetSize4(Collection<V[]> x) {
        int targetSize = 0;
        for (V[] values : x) {
            if (values.length > targetSize) {
                targetSize = values.length;
            }
        }
        return targetSize;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray5(Collection)
     * @see Summation#summationMap5(Collection, Class)
     * @see Summation#summationMap5(Collection, Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> int getTargetSize5(Collection<C> x) {
        int targetSize = 0;
        for (C values : x) {
            if (values.size() > targetSize) {
                targetSize = values.size();
            }
        }
        return targetSize;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray6(Collection)
     * @see Summation#summationMap6(Collection, Class)
     * @see Summation#summationMap6(Collection, Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> int getTargetSize6(Collection<M> x) {
        int targetSize = 0;
        for (M values : x) {
            for (Map.Entry<?, V> value : values.entrySet()) {
                if (values.size() > targetSize) {
                    targetSize = values.size();
                }
            }
        }
        return targetSize;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray7(Map)
     * @see Summation#summationMap7(Map, Class)
     * @see Summation#summationMap7(Map, Class, Object)
     */
    private static <V extends Number> int getTargetSize7(Map<?, V[]> x) {
        int targetSize = 0;
        for (Map.Entry<?, V[]> entry : x.entrySet()) {
            if (entry.getValue().length > targetSize) {
                targetSize = entry.getValue().length;
            }
        }
        return targetSize;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray8(Map)
     * @see Summation#summationMap8(Map, Class)
     * @see Summation#summationMap8(Map, Class, Object)
     */
    private static <V extends Number, C extends Collection<V>> int getTargetSize8(Map<?, C> x) {
        int targetSize = 0;
        for (Map.Entry<?, C> entry : x.entrySet()) {
            if (entry.getValue().size() > targetSize) {
                targetSize = entry.getValue().size();
            }
        }
        return targetSize;
    }

    /**
     * Examines which internal value has the largest length.<br>
     * @param x Type that serves the values
     * @return internal largest size value of type x.
     * @see Summation#summationArray9(Map)
     * @see Summation#summationMap9(Map, Class)
     * @see Summation#summationMap9(Map, Class, Object)
     */
    private static <V extends Number, M extends Map<?, V>> int getTargetSize9(Map<?, M> x) {
        int targetSize = 0;
        for (Map.Entry<?, M> value : x.entrySet()) {
            if (x.size() > targetSize) {
                targetSize = x.size();
            }
        }
        return targetSize;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>> C summationCollection1(V[][] x, Class<? extends Collection> classCollectionNameDotClass) {
        C newCollection = (C) newCollection(classCollectionNameDotClass);
        for (V[] collection : x) {
            newCollection.add(computeSum(collection));
        }
        return newCollection;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>, C2 extends  Collection<V>> C2 summationCollection2(C[] x, Class<? extends Collection> classCollectionNameDotClass) {
        C2 newCollection = (C2) newCollection(classCollectionNameDotClass);
        for (C collection : x) {
            newCollection.add(computeSum(collection));
        }
        return newCollection;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>, M extends Map<?, V>> C summationCollection3(M[] x, Class<? extends Collection> classCollectionNameDotClass) {
        C newCollection = (C) newCollection(classCollectionNameDotClass);
        for (M collection: x) {
            newCollection.add(computeSum(collection));
        }
        return newCollection;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>> C summationCollection4(Collection<V[]> x, Class<? extends Collection> classCollectionNameDotClass) {
        C newCollection = (C) newCollection(classCollectionNameDotClass);
        for (V[] collection : x) {
            newCollection.add(computeSum(collection));
        }
        return newCollection;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>, C2 extends  Collection<V>> C2 summationCollection5(Collection<C> x, Class<? extends Collection> classCollectionNameDotClass) {
        C2 newCollection = (C2) newCollection(classCollectionNameDotClass);
        for (C collection : x) {
            newCollection.add(computeSum(collection));
        }
        return newCollection;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>, M extends Map<?, V>> C summationCollection6(Collection<M> x, Class<? extends Collection> classCollectionNameDotClass) {
        C newCollection = (C) newCollection(classCollectionNameDotClass);
        for (M collection : x) {
            newCollection.add(computeSum(collection));
        }
        return newCollection;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>> C summationCollection7(Map<?, V[]> x, Class<? extends Collection> classCollectionNameDotClass) {
        C newCollection = (C) newCollection(classCollectionNameDotClass);
        for (Map.Entry<?, V[]> collection : x.entrySet()) {
            newCollection.add(computeSum(collection.getValue()));
        }
        return newCollection;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>, C2 extends  Collection<V>> C2 summationCollection8(Map<?, C> x, Class<? extends Collection> classCollectionNameDotClass) {
        C2 newCollection = (C2) newCollection(classCollectionNameDotClass);
        for (Map.Entry<?, C> collection : x.entrySet()) {
            newCollection.add(computeSum(collection.getValue()));
        }
        return newCollection;
    }

    /**
     * Loading a Collection derived class.<br>
     * @param x Type that serves the values.
     * @param classCollectionNameDotClass defined Collection derived class.
     * @return Uploaded collection.
     * @see Summation#summationCollection(Object, Class)
     */
    private static <V extends Number, C extends Collection<V>, M extends Map<?, V>> C summationCollection9(Map<?, M> x, Class<? extends Collection> classCollectionNameDotClass) {
        C newCollection = (C) newCollection(classCollectionNameDotClass);
        for (Map.Entry<?, M> collection : x.entrySet()) {
            newCollection.add(computeSum(collection.getValue()));
        }
        return newCollection;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<Integer, V>> M summationMap1(V[][] x, Class<? extends Map> classMapNameDotClass) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload1(x, createPreferredSizeArray1(x, array, getTargetSize1(x)));
        int i = 0;
        for (V[] values : x) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<Integer, V>, C extends Collection<V>> M summationMap2(C[] x, Class<? extends Map> classMapNameDotClass) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload2(x, createPreferredSizeArray2(x, array, getTargetSize2(x)));
        int i = 0;
        for (C values : x) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<?, V>, M2 extends Map<Integer, V>> M2 summationMap3(M[] x, Class<? extends Map> classMapNameDotClass) {
        M2 sum = (M2) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload3(x, createPreferredSizeArray3(x, array, getTargetSize3(x)));
        int i = 0;
        for (M values : x) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<Integer, V>> M summationMap4(Collection<V[]> x, Class<? extends Map> classMapNameDotClass) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload4(x, createPreferredSizeArray4(x, array, getTargetSize4(x)));
        int i = 0;
        for (V[] values : x) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<Integer, V>, C extends Collection<V>> M summationMap5(Collection<C> x, Class<? extends Map> classMapNameDotClass) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload5(x, createPreferredSizeArray5(x, array, getTargetSize5(x)));
        int i = 0;
        for (C values : x) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<?, V>, M2 extends Map<Integer, V>> M2 summationMap6(Collection<M> x, Class<? extends Map> classMapNameDotClass) {
        M2 sum = (M2) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload6(x, createPreferredSizeArray6(x, array, getTargetSize6(x)));
        int i = 0;
        for (M values : x) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<Integer, V>> M summationMap7(Map<?, V[]> x, Class<? extends Map> classMapNameDotClass) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload7(x, createPreferredSizeArray7(x, array, getTargetSize7(x)));
        int i = 0;
        for (Map.Entry<?, V[]> value : x.entrySet()) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<Integer, V>, C extends Collection<V>> M summationMap8(Map<?, C> x, Class<? extends Map> classMapNameDotClass) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload8(x, createPreferredSizeArray8(x, array, getTargetSize8(x)));
        int i = 0;
        for (Map.Entry<?, C> value : x.entrySet()) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey1(Object, Class)
     */
    private static <V extends Number, M extends Map<?, V>, M2 extends Map<Integer, V>> M2 summationMap9(Map<?, M> x, Class<? extends Map> classMapNameDotClass) {
        M2 sum = (M2) newMap(classMapNameDotClass);
        V[] array = null;
        array = foreachUpload9(x, createPreferredSizeArray9(x, array, getTargetSize9(x)));
        int i = 0;
        for (Map.Entry<?, M> value : x.entrySet()) {
            sum.put(i, array[i]);
            i++;
        }
        return sum;
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<K, V>> M summationMap1(V[][] x, Class<? extends Map> classMapNameDotClass, K keys) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.length);
            array = foreachUpload1(x, createPreferredSizeArray1(x, array, getTargetSize1(x)));
            int i = 0;
            for (V[] values : x) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.length);
            array = foreachUpload1(x, createPreferredSizeArray1(x, array, getTargetSize1(x)));
            return foreachCollectionKey(sum, array, newKeys, x.length);
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.length);
            array = foreachUpload1(x, createPreferredSizeArray1(x, array, getTargetSize1(x)));
            return foreachMapKey(sum, array, newKeys, x.length);
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<K, V>, C extends Collection<V>> M summationMap2(C[] x, Class<? extends Map> classMapNameDotClass, K keys) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.length);
            array = foreachUpload2(x, createPreferredSizeArray2(x, array, getTargetSize2(x)));
            int i = 0;
            for (C values : x) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.length);
            array = foreachUpload2(x, createPreferredSizeArray2(x, array, getTargetSize2(x)));
            return foreachCollectionKey(sum, array, newKeys, x.length);
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.length);
            array = foreachUpload2(x, createPreferredSizeArray2(x, array, getTargetSize2(x)));
            return foreachMapKey(sum, array, newKeys, x.length);
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<?, V>, M2 extends Map<K, V>> M2 summationMap3(M[] x, Class<? extends Map> classMapNameDotClass, K keys) {
        M2 sum = (M2) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.length);
            array = foreachUpload3(x, createPreferredSizeArray3(x, array, getTargetSize3(x)));
            int i = 0;
            for (M values : x) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.length);
            array = foreachUpload3(x, createPreferredSizeArray3(x, array, getTargetSize3(x)));
            return foreachCollectionKey(sum, array, newKeys, x.length);
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.length);
            array = foreachUpload3(x, createPreferredSizeArray3(x, array, getTargetSize3(x)));
            return foreachMapKey(sum, array, newKeys, x.length);
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<K, V>> M summationMap4(Collection<V[]> x, Class<? extends Map> classMapNameDotClass, K keys) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.size());
            array = foreachUpload4(x, createPreferredSizeArray4(x, array, getTargetSize4(x)));
            int i = 0;
            for (V[] values : x) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload4(x, createPreferredSizeArray4(x, array, getTargetSize4(x)));
            return foreachCollectionKey(sum, array, newKeys, x.size());
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload4(x, createPreferredSizeArray4(x, array, getTargetSize4(x)));
            return foreachMapKey(sum, array, newKeys, x.size());
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<K, V>, C extends Collection<V>> M summationMap5(Collection<C> x, Class<? extends Map> classMapNameDotClass, K keys) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.size());
            array = foreachUpload5(x, createPreferredSizeArray5(x, array, getTargetSize5(x)));
            int i = 0;
            for (C values : x) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload5(x, createPreferredSizeArray5(x, array, getTargetSize5(x)));
            return foreachCollectionKey(sum, array, newKeys, x.size());
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload5(x, createPreferredSizeArray5(x, array, getTargetSize5(x)));
            return foreachMapKey(sum, array, newKeys, x.size());
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<?, V>, M2 extends Map<K, V>> M2 summationMap6(Collection<M> x, Class<? extends Map> classMapNameDotClass, K keys) {
        M2 sum = (M2) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.size());
            array = foreachUpload6(x, createPreferredSizeArray6(x, array, getTargetSize6(x)));
            int i = 0;
            for (M values : x) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload6(x, createPreferredSizeArray6(x, array, getTargetSize6(x)));
            return foreachCollectionKey(sum, array, newKeys, x.size());
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload6(x, createPreferredSizeArray6(x, array, getTargetSize6(x)));
            return foreachMapKey(sum, array, newKeys, x.size());
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<K, V>> M summationMap7(Map<?, V[]> x, Class<? extends Map> classMapNameDotClass, K keys) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.size());
            array = foreachUpload7(x, createPreferredSizeArray7(x, array, getTargetSize7(x)));
            int i = 0;
            for (Map.Entry<?, V[]> value : x.entrySet()) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload7(x, createPreferredSizeArray7(x, array, getTargetSize7(x)));
            return foreachCollectionKey(sum, array, newKeys, x.size());
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload7(x, createPreferredSizeArray7(x, array, getTargetSize7(x)));
            return foreachMapKey(sum, array, newKeys, x.size());
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<K, V>, C extends Collection<V>> M summationMap8(Map<?, C> x, Class<? extends Map> classMapNameDotClass, K keys) {
        M sum = (M) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.size());
            array = foreachUpload8(x, createPreferredSizeArray8(x, array, getTargetSize8(x)));
            int i = 0;
            for (Map.Entry<?, C> value : x.entrySet()) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload8(x, createPreferredSizeArray8(x, array, getTargetSize8(x)));
            return foreachCollectionKey(sum, array, newKeys, x.size());
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload8(x, createPreferredSizeArray8(x, array, getTargetSize8(x)));
            return foreachMapKey(sum, array, newKeys, x.size());
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }

    /**
     * Create and upload a specified Map descendant class.
     * @param x Type that serves the values.
     * @param classMapNameDotClass Map descendant class name.
     * @param keys Key that will be uploaded to the value of the Map key.
     * @return Uploaded Map class, its key is an increasing sequence of numbers.
     * @see Summation#summationMapKey2(Object, Class, Object)
     */
    private static <K, V extends Number, M extends Map<?, V>, M2 extends Map<K, V>> M2 summationMap9(Map<?, M> x, Class<? extends Map> classMapNameDotClass, K keys) {
        M2 sum = (M2) newMap(classMapNameDotClass);
        V[] array = null;
        if (keys instanceof Number[]) {
            V[] newKeys = (V[]) keys;
            getThrowQuestion(newKeys.length, x.size());
            array = foreachUpload9(x, createPreferredSizeArray9(x, array, getTargetSize9(x)));
            int i = 0;
            for (Map.Entry<?, M> value : x.entrySet()) {
                sum.put((K) newKeys[i], array[i]);
                i++;
            }
            return sum;
        } else if (keys instanceof Collection) {
            Collection<K> newKeys = (Collection<K>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload9(x, createPreferredSizeArray9(x, array, getTargetSize9(x)));
            return foreachCollectionKey(sum, array, newKeys, x.size());
        } else if (keys instanceof Map) {
            Map<K, ?> newKeys = (Map<K, ?>) keys;
            getThrowQuestion(newKeys.size(), x.size());
            array = foreachUpload9(x, createPreferredSizeArray9(x, array, getTargetSize9(x)));
            return foreachMapKey(sum, array, newKeys, x.size());
        } else {
            throw new IllegalArgumentException("Unsupported keys type! Supported type: Number, Collection, Map");
        }
    }
}