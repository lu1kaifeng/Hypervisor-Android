package org.lu.hypervisor.android.api;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.lu.hypervisor.android.api.model.Course;
import org.lu.hypervisor.android.api.model.Misbehavior;
import org.lu.hypervisor.android.api.model.Notification;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

abstract class FeignClient {
    static {
        builder = getBuilder();
        classroomApi = getClassroomApi();
        courseApi = getCourseApi();
        faceApi = getFaceApi();
        misbehaviorApi =getMisbehaviorApi();
        subjectApi = getSubjectApi();
        notificationApi = getNotificationApi();
    }
    private static final String apiRootUrl = "http://10.0.0.130:2233";
    private static Feign.Builder builder;
    static ClassroomApi classroomApi;
    static CourseApi courseApi;
    static FaceApi faceApi;
    static MisbehaviorApi misbehaviorApi;
    static SubjectApi subjectApi;
    static NotificationApi notificationApi;
    private static Feign.Builder getBuilder(){
        List<TypeAdapter<?>> adapters = new ArrayList<>();
        adapters.add(new TypeAdapter<LocalTime>() {
            @Override
            public void write(JsonWriter out, LocalTime value) throws IOException {
                out.value(value.toString());
            }

            @Override
            public LocalTime read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                return LocalTime.parse(in.nextString());
            }
        });
        adapters.add(new TypeAdapter<Duration>() {
            @Override
            public void write(JsonWriter out, Duration value) throws IOException {
                out.value(value.toString());
            }

            @Override
            public Duration read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                return Duration.parse(in.nextString());
            }
        });
        adapters.add(new TypeAdapter<LocalDateTime>() {
            @Override
            public void write(JsonWriter out, LocalDateTime value) throws IOException {
                out.value(value.toString());
            }

            @Override
            public LocalDateTime read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                return LocalDateTime.parse(in.nextString());
            }
        });
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder(adapters));
    }
    private static ClassroomApi getClassroomApi(){
        return builder.target(ClassroomApi.class,apiRootUrl);
    }
    private static CourseApi getCourseApi(){
        return builder.target(CourseApi.class,apiRootUrl);
    }
    private static FaceApi getFaceApi(){
        return builder.target(FaceApi.class,apiRootUrl);
    }
    private static MisbehaviorApi getMisbehaviorApi(){
        return builder.target(MisbehaviorApi.class,apiRootUrl);
    }
    private static SubjectApi getSubjectApi(){
        return builder.target(SubjectApi.class,apiRootUrl);
    }
    private static NotificationApi getNotificationApi(){
        return builder.target(NotificationApi.class,apiRootUrl);
    }
}
