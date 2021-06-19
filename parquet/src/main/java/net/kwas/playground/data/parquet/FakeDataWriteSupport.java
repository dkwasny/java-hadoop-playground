package net.kwas.playground.data.parquet;

import net.kwas.playground.data.fakedata.FakeData;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;

import java.util.Collections;

public class FakeDataWriteSupport extends WriteSupport<FakeData> {

    private RecordConsumer consumer;

    @Override
    public WriteSupport.WriteContext init(Configuration configuration) {
        return new WriteSupport.WriteContext(FakeDataParquetSchema.get(), Collections.emptyMap());
    }

    @Override
    public void prepareForWrite(RecordConsumer recordConsumer) {
        this.consumer = recordConsumer;
    }

    @Override
    public void write(FakeData record) {
        int idx = 0;
        consumer.startMessage();

        addField("phrase", idx++, () -> consumer.addBinary(Binary.fromCharSequence(record.getPhrase())));
        addField("numWords", idx++, () -> consumer.addInteger(record.getNumWords()));
        addField("numChars", idx++, () -> consumer.addInteger(record.getNumChars()));
        addField("totalWordScore", idx++, () -> consumer.addInteger(record.getTotalWordScore()));
        addField("avgWordScore", idx++, () -> consumer.addInteger(record.getAvgWordScore()));
        addField("minWordScore", idx++, () -> consumer.addInteger(record.getMinWordScore()));
        addField("maxWordScore", idx++, () -> consumer.addInteger(record.getMaxWordScore()));
        addField("avgCharScore", idx++, () -> consumer.addInteger(record.getAvgCharScore()));
        addField("minCharScore", idx++, () -> consumer.addInteger(record.getMinCharScore()));
        addField("maxCharScore", idx++, () -> consumer.addInteger(record.getMaxCharScore()));
        addField("classification", idx++, () -> consumer.addBinary(Binary.fromCharSequence(record.getClassification())));
        addField("phraseMd5", idx++, () -> consumer.addBinary(Binary.fromCharSequence(record.getPhraseMd5())));

        consumer.endMessage();
    }

    private void addField(String name, int idx, Runnable function) {
        consumer.startField(name, idx);
        function.run();
        consumer.endField(name, idx);
    }

}
