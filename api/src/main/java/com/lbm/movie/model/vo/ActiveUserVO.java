package com.lbm.movie.model.vo;

import com.lbm.movie.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUserVO {

    private User user;

    private Integer Number;

}
