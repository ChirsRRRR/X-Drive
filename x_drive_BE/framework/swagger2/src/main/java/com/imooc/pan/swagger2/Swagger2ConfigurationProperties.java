package com.imooc.pan.swagger2;


import com.imooc.pan.core.constants.RPanConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * swagger2配置属性实体
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2ConfigurationProperties {

    private boolean show = true;

    private String groupName = "x-drive";

    private String basePackage = RPanConstants.BASE_COMPONENT_SCAN_PATH;

    private String title = "x-drive-server";

    private String description = "x-drive-server";

    private String termsofserviceurl = "http://127.0.0.1:${server.port}";

    private String concatname = "Jason Ran";

    private String concaturl = "http://www.baidu.com";

    private String concatEmail = "xir16@pitt.edu";

    private String version = "1.0";


}
