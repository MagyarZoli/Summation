import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import mz.Summation;

public class SummationTest {
    
    private int testSize;
    private Number sum;
    private Number result;
    private Number[] arrayNumbers;
    private BigDecimal[] arrayBigDecimals;
    private BigInteger[] arrayBigIntegers;
    private Double[] arrayDoubles;
    private Float[] arrayFloats;
    private Long[] arrayLongs;
    private Integer[] arrayIntegers;
    private Short[] arrayShorts;
    private Byte[] arrayBytes;
    private Collection<Number> collectionNumbers;
    private Collection<BigDecimal> collectionBigDecimals;
    private Collection<BigInteger> collectionBigIntegers;
    private Collection<Double> collectionDoubles;
    private Collection<Float> collectionFloats;
    private Collection<Long> collectionLongs;
    private Collection<Integer> collectionIntegers;
    private Collection<Short> collectionShorts;
    private Collection<Byte> collectionBytes;

    @BeforeEach
    void testField() {
        testSize = 100;
        arrayNumbers = new Number[testSize];
        arrayBigDecimals = new BigDecimal[testSize];
        arrayBigIntegers = new BigInteger[testSize];
        arrayDoubles = new Double[testSize];
        arrayFloats = new Float[testSize];
        arrayLongs = new Long[testSize];
        arrayIntegers = new Integer[testSize];
        arrayShorts = new Short[testSize];
        arrayBytes = new Byte[testSize];
        collectionNumbers = new Stack<>();
        collectionBigDecimals = new Vector<>(); 
        collectionBigIntegers = new ArrayList<>();
        collectionDoubles = new TreeSet<>();
        collectionFloats = new HashSet<>();
        collectionLongs = new LinkedHashSet<>();
        collectionIntegers = new LinkedList<>(); 
        collectionShorts = new LinkedBlockingQueue<>();
        collectionBytes = new ArrayDeque<>(); 
    }

    @RepeatedTest(5)
    @DisplayName(value = "Investigation of throw cases.")
    void throwsTest() {
        assertThrows(IllegalArgumentException.class, () -> Summation.summation(null));
        assertThrows(IllegalArgumentException.class, () -> Summation.summation(0));
        assertThrows(IllegalArgumentException.class, () -> Summation.summation("a"));
        assertThrows(IllegalArgumentException.class, () -> Summation.summation(new String[]{"ab", "cd", "ef"}));
        assertDoesNotThrow(() -> Summation.summation(1.1f, 2.2f));
        assertDoesNotThrow(() -> Summation.summation(arrayNumbers));
        assertDoesNotThrow(() -> Summation.summation(arrayBigDecimals));
        assertDoesNotThrow(() -> Summation.summation(arrayBigIntegers));
        assertDoesNotThrow(() -> Summation.summation(arrayDoubles));
        assertDoesNotThrow(() -> Summation.summation(arrayFloats));
        assertDoesNotThrow(() -> Summation.summation(arrayLongs));
        assertDoesNotThrow(() -> Summation.summation(arrayIntegers));
        assertDoesNotThrow(() -> Summation.summation(arrayShorts));
        assertDoesNotThrow(() -> Summation.summation(arrayBytes));
        assertDoesNotThrow(() -> Summation.summation(collectionNumbers));
        assertDoesNotThrow(() -> Summation.summation(collectionBigDecimals));
        assertDoesNotThrow(() -> Summation.summation(collectionBigIntegers));
        assertDoesNotThrow(() -> Summation.summation(collectionDoubles));
        assertDoesNotThrow(() -> Summation.summation(collectionFloats));
        assertDoesNotThrow(() -> Summation.summation(collectionLongs));
        assertDoesNotThrow(() -> Summation.summation(collectionIntegers));
        assertDoesNotThrow(() -> Summation.summation(collectionShorts));
        assertDoesNotThrow(() -> Summation.summation(collectionBytes));
    }

    @RepeatedTest(5)
    @DisplayName(value = "Check for null and non-null return array value.")
    void arrayNullAndNotNullTest() {
        assertNull(Summation.summation(arrayDoubles));
        result = Upload.upload(arrayDoubles);
        assertNotNull(Summation.summation(arrayDoubles));
        assertNull(Summation.summation(arrayFloats));
        result = Upload.upload(arrayFloats);
        assertNotNull(Summation.summation(arrayFloats));
        assertNull(Summation.summation(arrayLongs));
        result = Upload.upload(arrayLongs);
        assertNotNull(Summation.summation(arrayLongs));
        assertNull(Summation.summation(arrayIntegers));
        result = Upload.upload(arrayIntegers);
        assertNotNull(Summation.summation(arrayIntegers));
        assertNull(Summation.summation(arrayShorts));
        result = Upload.upload(arrayShorts);
        assertNotNull(Summation.summation(arrayShorts));
        assertNull(Summation.summation(arrayBytes));
        result = Upload.upload(arrayBytes);
        assertNotNull(Summation.summation(arrayBytes));
    }

    @RepeatedTest(5)
    @DisplayName(value = "Check for null and non-null return collection value.")
    void collectionNullAndNotNullTest() {
        assertNull(Summation.summation(collectionDoubles));
        result = Upload.uploadDouble(collectionDoubles, testSize);
        assertNotNull(Summation.summation(collectionDoubles));
        assertNull(Summation.summation(collectionFloats));
        result = Upload.uploadFloat(collectionFloats, testSize);
        assertNotNull(Summation.summation(collectionFloats));
        assertNull(Summation.summation(collectionLongs));
        result = Upload.uploadLong(collectionLongs, testSize);
        assertNotNull(Summation.summation(collectionLongs));
        assertNull(Summation.summation(collectionIntegers));
        result = Upload.uploadInt(collectionIntegers, testSize);
        assertNotNull(Summation.summation(collectionIntegers));
        assertNull(Summation.summation(collectionShorts));
        result = Upload.uploadShort(collectionShorts, testSize);
        assertNotNull(Summation.summation(collectionShorts));
        assertNull(Summation.summation(collectionBytes));
        result = Upload.uploadByte(collectionBytes, testSize);
        assertNotNull(Summation.summation(collectionBytes));
    }

    @RepeatedTest(5)
    @DisplayName(value = "Array addition test.")
    void arrayTest() {
        result = Upload.upload(arrayDoubles);
        sum = Summation.summation(arrayDoubles);
        assertEquals(result, sum);      
        result = Upload.upload(arrayFloats);
        sum = Summation.summation(arrayFloats);
        assertEquals(result, sum);      
        result = Upload.upload(arrayLongs);
        sum = Summation.summation(arrayLongs);
        assertEquals(result, sum);       
        result = Upload.upload(arrayIntegers);
        sum = Summation.summation(arrayIntegers);
        assertEquals(result, sum);
        result = Upload.upload(arrayShorts);
        sum = Summation.summation(arrayShorts);
        assertEquals(result, sum);
        result = Upload.upload(arrayBytes);
        sum = Summation.summation(arrayBytes);
        assertEquals(result, sum);
    }

    @RepeatedTest(5)
    @DisplayName(value = "Collection addition test.")
    void collectionTest() {
        result = Upload.uploadDouble(collectionDoubles, testSize);
        sum = Summation.summation(collectionDoubles);
        assertEquals(result, sum);      
        result = Upload.uploadFloat(collectionFloats, testSize);
        sum = Summation.summation(collectionFloats);
        assertEquals(result, sum);      
        result = Upload.uploadLong(collectionLongs, testSize);
        sum = Summation.summation(collectionLongs);
        assertEquals(result, sum);       
        result = Upload.uploadInt(collectionIntegers, testSize);
        sum = Summation.summation(collectionIntegers);
        assertEquals(result, sum);
        result = Upload.uploadShort(collectionShorts, testSize);
        sum = Summation.summation(collectionShorts);
        assertEquals(result, sum);
        result = Upload.uploadByte(collectionBytes, testSize);
        sum = Summation.summation(collectionBytes);
        assertEquals(result, sum);
    }
}