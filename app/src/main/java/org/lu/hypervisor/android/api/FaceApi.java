package org.lu.hypervisor.android.api;

import org.lu.hypervisor.android.api.model.FacePair;

import feign.Body;
import feign.RequestLine;

interface FaceApi {
    @RequestLine("POST /face/comparison")
    String fetchComparison(FacePair facePair);
}
