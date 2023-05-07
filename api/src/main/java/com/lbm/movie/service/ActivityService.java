package com.lbm.movie.service;

import com.lbm.movie.model.entity.Activity;

import java.util.List;

public interface ActivityService {

    void create(Activity activity);

    Activity findById(String id);

    List<Activity> findAll();

    void deleteById(String id);

}
