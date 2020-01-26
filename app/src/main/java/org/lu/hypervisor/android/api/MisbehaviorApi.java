package org.lu.hypervisor.android.api;

import org.lu.hypervisor.android.api.model.Misbehavior;

import java.util.List;
import java.util.Map;

import feign.HeaderMap;
import feign.QueryMap;
import feign.RequestLine;

interface MisbehaviorApi {
    @RequestLine("GET /misbehavior")
    List<Misbehavior> getMisbehavior(@HeaderMap Map<String, Object> headers, @QueryMap Map<String, Object> query);

    @RequestLine("GET /misbehavior/all")
    List<Misbehavior> getAllMisbehavior(@HeaderMap Map<String, Object> headers);
}
