package org.lu.hypervisor.android.api;

import org.lu.hypervisor.android.api.model.Attendance;
import org.lu.hypervisor.android.api.model.Course;

import java.util.List;
import java.util.Map;

import feign.HeaderMap;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

interface CourseApi {
    @RequestLine("POST /course")
    void postCourse(@HeaderMap Map<String, Object> headers,@QueryMap Map<String, Object> query);

    @RequestLine("DELETE /course/{id}")
    void delCourse(@HeaderMap Map<String, Object> headers, @Param("id") Long id);

    @RequestLine("GET /course")
    List<Course> getCourse(@HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> query);

    @RequestLine("POST /course/attendance")
    void attendCourse(@HeaderMap Map<String, Object> headers,@QueryMap Map<String, Object> query);

    @RequestLine("DELETE /course/attendance")
    void leaveCourse(@HeaderMap Map<String, Object> headers,@QueryMap Map<String, Object> query);

    @RequestLine("GET /course/attendance")
    List<Attendance> getAttendanceBySubjectId(@HeaderMap Map<String, Object> headers);

    @RequestLine("GET /course/all")
    List<Course> getAllCourse(@HeaderMap Map<String, Object> headers);
}
