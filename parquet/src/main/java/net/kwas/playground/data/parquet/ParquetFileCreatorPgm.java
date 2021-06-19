package net.kwas.playground.data.parquet;

import net.kwas.playground.data.fakedata.FakeData;
import net.kwas.playground.data.fakedata.FakeDataIterable;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import java.io.IOException;
import java.util.Random;

public class ParquetFileCreatorPgm {

    private static final String OUTPUT_FILE = "/tmp/test-file.parquet";

    public static void main(String[] args) throws IOException {
        Iterable<FakeData> iterable = FakeDataIterable.create(
                1000,
                5,
                new Random(13),
                "/usr/share/dict/words"
        );

        try(ParquetWriter<FakeData> writer = getWriter()) {
            for (FakeData fakeData : iterable) {
                writer.write(fakeData);
            }
        }
    }

    private static ParquetWriter<FakeData> getWriter() throws IOException {
        return new FakeDataParquetWriterBuilder(new Path(OUTPUT_FILE))
                .withCompressionCodec(CompressionCodecName.GZIP)
                .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
                .build();
    }

}
