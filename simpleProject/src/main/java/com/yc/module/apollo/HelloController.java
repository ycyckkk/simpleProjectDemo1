package com.yc.module.apollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author yuche
 * @since 2019/12/22 18:32
 */
@RestController
@RequestMapping("/apollo")
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger( HelloController.class );

    @Value( "${name}" )
    String name;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hi(@RequestParam("name") String name) {

        logger.debug( "debug log..." );
        logger.info( "info log..." );
        logger.warn( "warn log..." );

        return "hi " + name + " ,i am from name:" + name;
    }
}
