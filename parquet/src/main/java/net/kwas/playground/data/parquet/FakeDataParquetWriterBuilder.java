package net.kwas.playground.data.parquet;

import net.kwas.playground.data.fakedata.FakeData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.api.WriteSupport;

public class FakeDataParquetWriterBuilder extends ParquetWriter.Builder<FakeData, FakeDataParquetWriterBuilder>{

    public FakeDataParquetWriterBuilder(Path path) {
        super(path);
    }

    @Override
    protected FakeDataParquetWriterBuilder self() {
        return this;
    }

    @Override
    protected WriteSupport<FakeData> getWriteSupport(Configuration conf) {
        return new FakeDataWriteSupport();
    }

}
