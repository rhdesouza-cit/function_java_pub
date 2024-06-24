package org.bullla;

import com.google.api.core.ApiFuture;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class HolidayFunction implements HttpFunction {

    private static final String EXTERNAL_SERVICE = "http://localhost:3000";
    private static final String EMULATOR_HOST = "localhost:8085";
    private static final String PROJECT_ID = "localstack";
    private static final String TOPIC_ID = "topic1";

    private static Logger logger = LoggerFactory.getLogger(HolidayFunction.class);

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        HolidayClient holidayClient = FeignConfig.createClient(HolidayClient.class, EXTERNAL_SERVICE);
        List<Holiday> holidays = holidayClient.getHoliday();

        for (Holiday holiday : holidays) {
            BufferedWriter writer = response.getWriter();
            writer.write("Holiday: " + holiday.getName() + "\n");
            writer.write("Date: " + holiday.getDate() + "\n");
            writer.write("Description: " + holiday.getDescription() + "\n");
            publisher(holiday.toString());
        }
    }

    private void publisher(String message) throws InterruptedException {
        var channel = ManagedChannelBuilder.forTarget(EMULATOR_HOST).usePlaintext().build();
        var channelProvider = FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel));
        var credentialsProvider = NoCredentialsProvider.create();

        TopicName topicName = TopicName.of(PROJECT_ID, TOPIC_ID);
        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName)
                .setChannelProvider(channelProvider)
                .setCredentialsProvider(credentialsProvider)
                .build();

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();

            logger.info("messageId: " + messageId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }

}
