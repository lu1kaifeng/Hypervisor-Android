package org.lu.hypervisor.android.api;

import org.lu.hypervisor.android.api.model.Course;
import org.lu.hypervisor.android.api.model.Misbehavior;

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
    }
    private static final String apiRootUrl = "http://10.0.0.130:2233";
    private static Feign.Builder builder;
    static ClassroomApi classroomApi;
    static CourseApi courseApi;
    static FaceApi faceApi;
    static MisbehaviorApi misbehaviorApi;
    static SubjectApi subjectApi;
    private static Feign.Builder getBuilder(){
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder());
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
}
