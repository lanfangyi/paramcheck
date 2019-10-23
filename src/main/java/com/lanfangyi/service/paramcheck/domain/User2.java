package com.lanfangyi.service.paramcheck.domain;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User2 extends BaseUser2{

    @Min(2)
    private long a;

}
