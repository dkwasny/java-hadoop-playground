package net.kwas.playground.data.arrow;

import net.kwas.playground.data.fakedata.FakeData;
import net.kwas.playground.data.fakedata.FakeDataIterable;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.ipc.ArrowFileWriter;
import org.apache.arrow.vector.util.Text;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ArrowFileCreatorPgm {

    private static final String OUTPUT_FILE = "/tmp/test-file.arrow";
    private static final int BATCH_SIZE = 500;
    private static final int NUM_ENTRIES = 1000;

    public static void main(String[] args) throws IOException {
        try (
                BufferAllocator allocator = new RootAllocator();
                VarCharVector classificationDictVector = getClassificationDictVector(allocator)
        ) {
            run(allocator, classificationDictVector);
        }
    }

    private static void run(BufferAllocator allocator, VarCharVector classificationDictVector) throws IOException {
        DictionaryProvider.MapDictionaryProvider dictProvider = new DictionaryProvider.MapDictionaryProvider();
        Dictionary classificationDict = new Dictionary(classificationDictVector, FakeDataArrowSchema.getClassificationDictEncoding());
        dictProvider.put(classificationDict);

        Iterable<FakeData> iterable = FakeDataIterable.create(
                NUM_ENTRIES,
                5,
                new Random(13),
                "/usr/share/dict/words"
        );

        try (
                VectorSchemaRoot schemaRoot = VectorSchemaRoot.create(FakeDataArrowSchema.get(), allocator);
                FileOutputStream fos = new FileOutputStream(OUTPUT_FILE);
                ArrowFileWriter writer = new ArrowFileWriter(schemaRoot, dictProvider, fos.getChannel());
        ) {
            writer.start();

            schemaRoot.allocateNew();
            int index = 0;
            int count = 0;
            for (FakeData fakeData : iterable) {
                writeFakeData(schemaRoot, fakeData, index);

                if (++index >= BATCH_SIZE || ++count >= NUM_ENTRIES) {
                    schemaRoot.setRowCount(index);
                    writer.writeBatch();
                    schemaRoot.clear();
                    index = 0;
                }
            }

            writer.end();
        }
    }

    private static void writeFakeData(VectorSchemaRoot schemaRoot, FakeData fakeData, int index) {
        writeString(schemaRoot.getVector("phrase"), fakeData.getPhrase(), index);
        writeInt(schemaRoot.getVector("numWords"), fakeData.getNumWords(), index);
        writeInt(schemaRoot.getVector("numChars"), fakeData.getNumChars(), index);
        writeInt(schemaRoot.getVector("totalWordScore"), fakeData.getTotalWordScore(), index);
        writeInt(schemaRoot.getVector("avgWordScore"), fakeData.getAvgWordScore(), index);
        writeInt(schemaRoot.getVector("minWordScore"), fakeData.getMinWordScore(), index);
        writeInt(schemaRoot.getVector("maxWordScore"), fakeData.getMaxWordScore(), index);
        writeInt(schemaRoot.getVector("avgCharScore"), fakeData.getAvgCharScore(), index);
        writeInt(schemaRoot.getVector("minCharScore"), fakeData.getMinCharScore(), index);
        writeInt(schemaRoot.getVector("maxCharScore"), fakeData.getMaxCharScore(), index);
        writeString(schemaRoot.getVector("classification"), fakeData.getClassification(), index);
        writeString(schemaRoot.getVector("phraseMd5"), fakeData.getPhraseMd5(), index);
    }

    private static void writeString(FieldVector vector, String value, int index) {
        if (vector.getClass() != VarCharVector.class) {
            throw new RuntimeException("Wrong type: " + vector.getClass().getName());
        }
        VarCharVector varCharVector = (VarCharVector) vector;
        varCharVector.setSafe(index, new Text(value));
    }

    private static void writeInt(FieldVector vector, int value, int index) {
        if (vector.getClass() != UInt4Vector.class) {
            throw new RuntimeException("Wrong type: " + vector.getClass().getName());
        }
        UInt4Vector varCharVector = (UInt4Vector) vector;
        varCharVector.setSafe(index, value);
    }

//    private static void writeEncodedDictInt(FieldVector vector, int value, int index) {
//        if (vector.getClass() != UInt4Vector.class) {
//            throw new RuntimeException("Wrong type: " + vector.getClass().getName());
//        }
//        UInt4Vector varCharVector = (UInt4Vector) vector;
//        varCharVector.setSafe(index, value);
//    }

    private static VarCharVector getClassificationDictVector(BufferAllocator allocator) {
        VarCharVector vector = new VarCharVector("classificationDict", allocator);
        int idx = 0;
        vector.setSafe(idx++, new Text("babby"));
        vector.setSafe(idx++, new Text("small"));
        vector.setSafe(idx++, new Text("normal"));
        vector.setSafe(idx++, new Text("large"));
        vector.setSafe(idx++, new Text("chungus"));
        vector.setValueCount(idx);
        return vector;
    }
}
