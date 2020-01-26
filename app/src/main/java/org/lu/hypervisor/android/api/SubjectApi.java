package org.lu.hypervisor.android.api;

import org.lu.hypervisor.android.api.model.Photo;
import org.lu.hypervisor.android.api.model.Subject;

import java.util.List;
import java.util.Map;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

interface SubjectApi {
    @RequestLine("GET /subject")
    Subject getSubject(String photoBase64);

    @RequestLine("POST /subject/signIn")
    Subject postSignIn(String photoBase64, @QueryMap Map<String, Object> query);

    @RequestLine("POST /subject/engagement")
    Void postEngagement(String photoBase64);

    @RequestLine("GET /subject/all")
    List<Subject> getAllSubject(@HeaderMap Map<String, Object> headers);

    @RequestLine("GET /subject/logIn")
    String getLogin( @QueryMap Map<String, Object> query);

    @RequestLine("GET /subject/info")
    Subject getSubjectInfo(@HeaderMap Map<String, Object> headers);

    @RequestLine("GET /subject/photo")
    Photo getSubjectPhoto(@HeaderMap Map<String, Object> headers);
}
