package com.af5m.elm.service.persistence.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/api/1.0/organizations/{organization_uuid}/localization/bundles/{bundle_uuid}/resourcess/{resource_uuid}/locales/{locale_uuid}/elements")
@RequestMapping("/api/1.0/organizations/{organization_uuid}/localization/bundles/{bundle_uuid}/resources/{resource_uuid}/locales/{locale_uuid}/elements")
public interface ElementRestApi {

}
