package net.kwas.playground.data.parquet;

import net.kwas.playground.data.fakedata.FakeData;
import net.kwas.playground.data.fakedata.FakeDataIterable;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import java.io.IOException;
import java.util.Random;

public class ParquetFileCreatorPgm {

    public static void main(String[] args) throws IOException {
        Iterable<FakeData> iterable = FakeDataIterable.create(
                1000,
                5,
                new Random(13),
                "/usr/share/dict/words"
        );

        ParquetWriter<FakeData> writer = new FakeDataParquetWriterBuilder(new Path("/tmp/test-file"))
                .withCompressionCodec(CompressionCodecName.GZIP)
                .build();

        for (FakeData fakeData : iterable) {
            writer.write(fakeData);
        }

        writer.close();
    }

}
