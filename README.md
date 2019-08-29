# paramcheck
springboot对外接口参数校验工具<br>

使用方法是：<br>
1、下载paramcheck/classes/artifacts/paramcheck_jar/paramcheck.jar 。<br>
2、在项目中新建一个与src同级的目录libs, 其实名字随意，我这里用libs。<br>
3、在把paramcheck.jar粘贴到libs下。<br>
4、在pom.xml中加入如下配置：<br>
         \<dependency><br>
            \<groupId>com.lanfangyi</groupId><br>
            \<artifactId>paramcheck</artifactId><br>
            \<version>0.0.1-SNAPSHOT</version><br>
            \<scope>system</scope><br>
            \<systemPath>${project.basedir}/libs/paramcheck.jar</systemPath><br>
        \</dependency><br>


 说明：gav三个参数随便写，但尽量按照规范来。<br>
 
 使用示例<br>
 @PostMapping("/registerOrLogin")<br>
 @Valid<br>
 public IMResponse<User> registerOrLogin(@NotBlank(among = {"lan", "fang", "yi"}) String username, @NotBlank(startWith = "123") String password) {<br>
   ....<br>
  }<br>
  
  像上面这么写的华，username不能为空并且必须是lan、fang、yi三个中的某一个，password只能以123开头<br>
  项目中还有更多好玩的注解，喜欢的可以看看。<br>
  
