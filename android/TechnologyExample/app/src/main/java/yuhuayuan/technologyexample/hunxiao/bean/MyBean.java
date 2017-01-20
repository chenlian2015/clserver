package yuhuayuan.technologyexample.hunxiao.bean;

/**
 * Created by chenlian on 16/10/17.
 */

@HelloAnnotation(color = "红色", value = "如果只有value属性！可以不写属性名和等于号，直接写值即可！", arrayAttr = { 1, 2, 3 })
public class MyBean extends BaseBean {
    public String name;
    public School baseInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(School baseInfo) {
        this.baseInfo = baseInfo;
    }

    public static class School
    {
        public int age;
        public String schoolName;
    }

    public String getAnnotion()
    {
        HelloAnnotation helloAnnotation = (HelloAnnotation) MyBean.class
                .getAnnotation(HelloAnnotation.class);
        return helloAnnotation.color()+helloAnnotation.value()+helloAnnotation.author();
    }
}
