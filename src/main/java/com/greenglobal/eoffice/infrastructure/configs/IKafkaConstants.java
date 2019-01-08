package com.greenglobal.eoffice.infrastructure.configs;

public interface IKafkaConstants {
    public static String KAFKA_BROKERS = "192.168.1.233:9092";
    public static Integer MESSAGE_COUNT=1000;
    public static String CLIENT_ID="congvandi_client";
    public static String GROUP_ID="congvandi_group";
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
    public static String OFFSET_RESET_LATEST="latest";
    public static String OFFSET_RESET_EARLIER="earliest";
    public static Integer MAX_POLL_RECORDS=1;
    public static String TOPIC_CONGVANDI="cong-van-di";
}