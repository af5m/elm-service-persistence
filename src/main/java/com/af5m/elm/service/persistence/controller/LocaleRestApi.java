package com.af5m.elm.service.persistence.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/api/1.0/localization/locales")
@RequestMapping("/api/1.0/locales")
public interface LocaleRestApi {

}
