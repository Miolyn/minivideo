package cn.tju.minivideo.core.constants;

public class ProjectConstant {

    // 项目基础包名称
    public static final String BASE_PACKAGE = "cn.tju.minivideo";

    // Entity所在包
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";

    // Mapper所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";

    // Service所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    // ServiceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    // Controller所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    // Mapper插件基础接口的完全限定名
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.universal.Mapper";

    public static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    public static final Integer PageSize = 12;
    public static final Integer SmallPageSize = 3;

    // redis 过期时间 5分钟=300秒
    public static final Integer ExpireTime = 300;

    // 社区后缀
    public static final String LabelUpMasterSuffix = "的社区";


}
