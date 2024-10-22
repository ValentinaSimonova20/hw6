package org.example;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MorseTranslatorTest {

    MorseTranslator morseTranslator = new HomeWork().morseTranslator();


    @ParameterizedTest
    @MethodSource("testSource")
    void decodeTest(TestObject testObject) {
        assertEquals(testObject.decodedString, morseTranslator.decode(testObject.encodedString));
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void encodeTest(TestObject testObject) {
        assertEquals(testObject.encodedString, morseTranslator.encode(testObject.decodedString));
    }


    private List<TestObject> testSource() {
        return List.of(
                new TestObject("hello world", ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."),
                new TestObject("5 of my legs", "..... / --- ..-. / -- -.-- / .-.. . --. ..."),
                new TestObject(
                        "i study in 9 th grade",
                        ".. / ... - ..- -.. -.-- / .. -. / ----. / - .... / --. .-. .- -.. ."
                ),
                new TestObject("a b v g d h 7", ".- / -... / ...- / --. / -.. / .... / --..."),
                new TestObject(
                        "the cat chased the red ball around the yard with excitement",
                        "- .... . / -.-. .- - / -.-. .... .- ... . -.. " +
                                "/ - .... . / .-. . -.. / -... .- .-.. .-.. / .- .-. --- ..- -. -.. / - .... " +
                                ". / -.-- .- .-. -.. / .-- .. - .... / . -..- -.-. .. - . -- . -. -"
                )
        );
    }

    private static class TestObject{
        String decodedString;
        String encodedString;

        public TestObject(String decodedString, String encodedString) {
            this.decodedString = decodedString;
            this.encodedString = encodedString;
        }
    }
}
