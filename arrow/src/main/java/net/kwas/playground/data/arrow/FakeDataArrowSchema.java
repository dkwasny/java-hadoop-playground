package net.kwas.playground.data.arrow;

import org.apache.arrow.vector.types.pojo.*;

import java.util.Arrays;

public class FakeDataArrowSchema {

    public static Schema get() {
        return new Schema(Arrays.asList(
                field("phrase", utf8Type()),
                field("numWords", intType()),
                field("numChars", intType()),
                field("totalWordScore", intType()),
                field("avgWordScore", intType()),
                field("minWordScore", intType()),
                field("maxWordScore", intType()),
                field("avgCharScore", intType()),
                field("minCharScore", intType()),
                field("maxCharScore", intType()),
                // TODO: This is still writing the unencoded values.
                // I may have to restructure the entire program to properly use dictionaries...
                field("classification", utf8DictType()),
                field("phraseMd5", utf8Type())
        ));
    }

    private static Field field(String name, FieldType type) {
        return new Field(name, type, null);
    }

    private static FieldType intType() {
        return new FieldType(false, new ArrowType.Int(32, false), null);
    }

    private static FieldType utf8Type() {
        return new FieldType(false, new ArrowType.Utf8(), null);
    }

    private static FieldType utf8DictType() {
        return new FieldType(false, new ArrowType.Utf8(), getClassificationDictEncoding());
    }

    public static DictionaryEncoding getClassificationDictEncoding() {
        return new DictionaryEncoding(1L, false, null);
    }

}
