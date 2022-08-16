package swp.charite;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.utils.Utils;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.transforms.Transformation;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Map;

public class CustomTransformation<R extends ConnectRecord<R>> implements Transformation<R> {

    private static ZooKeeper zk;
    private static final NullZooKeeperWatcher zkWatcher = new NullZooKeeperWatcher();

    @Override
    public R apply(R record) {
        // Ignoring tombstones just in case
        if (record.value() == null) {
            return record;
        }

        Struct struct = (Struct) record.value();
        String op = struct.getString("op");

        // ignoring deletions in the outbox table
        if (op.equals("d")) {
            return null;
        }
        else if (op.equals("c") || op.equals("r")) {
            Long timestamp = struct.getInt64("ts_ms");
            Struct after = struct.getStruct("after");

            String key = after.getString("aggregate_id");
            String topic = after.getString("aggregate_type");

            String eventId = after.getString("id");
            String eventType = after.getString("type");
            String payload = after.getString("payload");

            Schema valueSchema = SchemaBuilder.struct()
                    .field("eventType", after.schema().field("type").schema())
                    .field("ts_ms", struct.schema().field("ts_ms").schema())
                    .field("payload", after.schema().field("payload").schema())
                    .build();

            Struct value = new Struct(valueSchema)
                    .put("eventType", eventType)
                    .put("ts_ms", timestamp)
                    .put("payload", payload);

            Headers headers = record.headers();
            headers.addString("eventId", eventId);

            try {
                initZooKeeper();
                Integer numberOfPartition = zk
                        .getChildren(String.format("/brokers/topics/%s/partitions", topic), zkWatcher).size();
                Integer partition = Utils.toPositive(Utils.murmur2(key.getBytes())) % numberOfPartition;
                // Build the event to be published.

                record = record.newRecord(topic, partition, Schema.STRING_SCHEMA, key, valueSchema,
                        value, record.timestamp(), headers);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return record;
        }
        // not expecting update events, as the outbox table is "append only",
        // i.e. event records will never be updated
        else {
            throw new IllegalArgumentException("Record of unexpected op type: " + record);
        }
    }

    public static void initZooKeeper() throws IOException {
        if (zk == null) {
            synchronized (CustomTransformation.class) {
                CustomTransformation.zk = new ZooKeeper(System.getenv("ZOOKEEPER_CONNECTION"), 1000, zkWatcher);
            }
        }
    }

    public static void closeZooKeeper() throws InterruptedException {
        CustomTransformation.zk.close();
        CustomTransformation.zk = null;
    }

    @Override
    public ConfigDef config() {
        return new ConfigDef();
    }

    @Override
    public void close() {
        try {
            CustomTransformation.closeZooKeeper();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
