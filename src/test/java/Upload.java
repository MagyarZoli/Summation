import java.util.Collection;
import java.util.Random;

public class Upload {
    
    private static Random random = new Random();

    static Double upload(Double[] array) {
        Double result = 0.0;
        for (int i = 0; i < array.length; i++) {
            int r = random.nextInt();
            array[i] = Double.valueOf(r);
            result = Double.valueOf(r + result);
        }
        return result;
    }

    static Float upload(Float[] array) {
        Float result = 0f;
        for (int i = 0; i < array.length; i++) {
            int r = random.nextInt();
            array[i] = Float.valueOf(r);
            result = Float.valueOf(r + result);
        }
        return result;
    }

    static Long upload(Long[] array) {
        Long result = 0l;
        for (int i = 0; i < array.length; i++) {
            int r = random.nextInt();
            array[i] = Long.valueOf(r);
            result = Long.valueOf(r + result);
        }
        return result;
    }

    static Integer upload(Integer[] array) {
        Integer result = 0;
        for (int i = 0; i < array.length; i++) {
            int r = random.nextInt();
            array[i] = Integer.valueOf(r);
            result = Integer.valueOf(r + result);
        }
        return result;
    }

    static Short upload(Short[] array) {
        Short result = 0;
        for (int i = 0; i < array.length; i++) {
            int r = random.nextInt();
            array[i] = Short.valueOf((short) r);
            result = Short.valueOf((short) (r + result.shortValue()));
        }
        return result;
    }

    static Byte upload(Byte[] array) {
        Byte result = 0;
        for (int i = 0; i < array.length; i++) {
            int r = random.nextInt();
            array[i] = Byte.valueOf((byte) r);
            result = Byte.valueOf((byte) (r + result.shortValue()));
        }
        return result;
    }

    static Double uploadDouble(Collection<Double> collection, int size) {
        Double result = 0.0;
        for (int i = 0; i < size; i++) {
            int r = random.nextInt();
            collection.add(Double.valueOf(r));
            result = Double.valueOf(r + result);
        }
        return result;
    }

    static Float uploadFloat(Collection<Float> collection, int size) {
        Float result = 0.0f;
        for (int i = 0; i < size; i++) {
            float r = random.nextFloat();
            collection.add(r);
            result = r + result;
        }
        return result;
    }

    static Long uploadLong(Collection<Long> collection, int size) {
        Long result = 0l;
        for (int i = 0; i < size; i++) {
            int r = random.nextInt();
            collection.add(Long.valueOf(r));
            result = Long.valueOf(r + result);
        }
        return result;
    }

    static Integer uploadInt(Collection<Integer> collection, int size) {
        Integer result = 0;
        for (int i = 0; i < size; i++) {
            int r = random.nextInt();
            collection.add(Integer.valueOf(r));
            result = Integer.valueOf(r + result);
        }
        return result;
    }

    static Short uploadShort(Collection<Short> collection, int size) {
        Short result = 0;
        for (int i = 0; i < size; i++) {
            int r = random.nextInt();
            collection.add(Short.valueOf((short) r));
            result = Short.valueOf((short) (r + result.shortValue()));
        }
        return result;
    }

    static Byte uploadByte(Collection<Byte> collection, int size) {
        Byte result = 0;
        for (int i = 0; i < size; i++) {
            int r = random.nextInt();
            collection.add(Byte.valueOf((byte) r));
            result = Byte.valueOf((byte) (r + result.shortValue()));
        }
        return result;
    }
}