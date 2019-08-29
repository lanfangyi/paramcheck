# paramcheck
springboot对外接口参数校验工具

使用方法是：
1、下载paramcheck/classes/artifacts/paramcheck_jar/paramcheck.jar 。<br>
2、在项目中新建一个与src同级的目录libs, 其实名字随意，我这里用libs。
3、在把paramcheck.jar粘贴到libs下。
4、在pom.xml中加入如下配置：
        <dependency>
            <groupId>com.lanfangyi</groupId>
            <artifactId>paramcheck</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/libs/paramcheck.jar</systemPath>
        </dependency>
 说明：gav三个参数随便写，但尽量按照规范来。
 
 使用示例
 @PostMapping("/registerOrLogin")
 @Valid
 public IMResponse<User> registerOrLogin(@NotBlank(among = {"lan", "fang", "yi"}) String username, @NotBlank(startWith = "123") String password) {
   ....
  }
  
  像上面这么写的华，username不能为空并且必须是lan、fang、yi三个中的某一个，password只能以123开头
  项目中还有更多好玩的注解，喜欢的可以看看。
  
