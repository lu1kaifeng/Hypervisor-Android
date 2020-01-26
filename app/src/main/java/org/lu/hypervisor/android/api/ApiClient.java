package org.lu.hypervisor.android.api;

import org.lu.hypervisor.android.api.model.Photo;
import org.lu.hypervisor.android.api.model.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ApiClient {
    private static Map<String,Object> toKeyPair(String[] k, Object[] v){
        HashMap<String,Object> map = new HashMap<>();
        for(int i = 0;i < k.length;i++)
            map.put(k[i],v[i]);
        return map;
    }
    public static abstract class Classroom{
        public static void postNew(String token,String identifier){
            FeignClient.classroomApi.postClassroom(toKeyPair(new String[]{"x-api-key"},new Object[]{token}),toKeyPair(new String[]{"identifier"},new Object[]{identifier}));
        }
        public static void del(String token,String identifier){
            FeignClient.classroomApi.delClassroom(toKeyPair(new String[]{"x-api-key"},new Object[]{token}),toKeyPair(new String[]{"identifier"},new Object[]{identifier}));
        }
        public static List<org.lu.hypervisor.android.api.model.Classroom> getAll(String token){
            return FeignClient.classroomApi.getAllClassroom(toKeyPair(new String[]{"x-api-key"},new Object[]{token}));
        }
    }
    public static abstract class Course{
        public static void postNew(String token,String name,Long teacherId,String classroom,Short weekday,Short timeHr,Short timeMin,Short durationHr){
            FeignClient.courseApi.postCourse(toKeyPair(new String[]{"x-api-key"},new Object[]{token}),toKeyPair(new String[]{"name","teacherId","classroom","weekday","timeHr","timeMin","durationHr"},new Object[]{name,teacherId,classroom,weekday,timeHr,timeMin,durationHr}));
        }
        public static void del(String token,Long courseId){
            FeignClient.courseApi.delCourse(toKeyPair(new String[]{"x-api-key"},new Object[]{token}),courseId);
        }
    }
    public static abstract class Face{

    }
    public static abstract class Misbehavior{

    }
    public static abstract class Subject{
        public static String login(String password,String name){
            return FeignClient.subjectApi.getLogin(toKeyPair(new String[]{"name","password"},new Object[]{name,password}));
        }
        public static org.lu.hypervisor.android.api.model.Subject getInfo(String token){
            return FeignClient.subjectApi.getSubjectInfo(toKeyPair(new String[]{"x-api-key"},new Object[]{token}));
        }
        public static List<org.lu.hypervisor.android.api.model.Subject> getAll(String token){
            return FeignClient.subjectApi.getAllSubject(toKeyPair(new String[]{"x-api-key"},new Object[]{token}));
        }
        public static Photo getPhoto(String token){
            return FeignClient.subjectApi.getSubjectPhoto(toKeyPair(new String[]{"x-api-key"},new Object[]{token}));
        }
    }
}
