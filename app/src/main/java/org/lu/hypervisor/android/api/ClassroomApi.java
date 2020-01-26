package org.lu.hypervisor.android.api;


import org.lu.hypervisor.android.api.model.Classroom;

import java.util.List;
import java.util.Map;

import feign.HeaderMap;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

interface ClassroomApi {
    @RequestLine("POST /classroom")
    void postClassroom(@HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> query);

    @RequestLine("DELETE /classroom")
    void delClassroom(@HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> query);

    @RequestLine("GET /classroom")
    List<Classroom> getAllClassroom(@HeaderMap Map<String, Object> headers);

}
