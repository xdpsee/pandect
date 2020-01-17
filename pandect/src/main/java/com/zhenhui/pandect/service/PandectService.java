package com.zhenhui.pandect.service;

import com.zhenhui.pandect.data.enums.PandectStatus;
import com.zhenhui.pandect.data.repository.PandectRepository;
import com.zhenhui.pandect.service.query.CreatePandectBody;
import com.zhenhui.pandect.service.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/pandect", produces = MediaType.APPLICATION_JSON_VALUE)
public class PandectService {

    @Autowired
    private PandectRepository repository;

    @PostMapping
    @ResponseBody
    public Result<Boolean> createPandect(@RequestBody CreatePandectBody params) {

        try {
            repository.createPandect(params.getType()
                    , params.getTitle()
                    , params.getDescription()
                    , params.getAuthor()
                    , params.getCover()
                    , PandectStatus.PREPARED);

            return Result.<Boolean>builder()
                    .status(200)
                    .message("success")
                    .data(true)
                    .build();
        } catch (Throwable e) {
            log.error("createPandect, exception", e);
            return Result.<Boolean>builder()
                    .status(200)
                    .message("error")
                    .data(false)
                    .build();
        }
    }

}
