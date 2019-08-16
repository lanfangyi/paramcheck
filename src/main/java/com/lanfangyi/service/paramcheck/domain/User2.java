package com.lanfangyi.service.paramcheck.domain;

import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User2 {

    @Min(2)
    private long a;

}
