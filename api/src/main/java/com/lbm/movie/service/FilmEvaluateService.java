package com.lbm.movie.service;

import com.lbm.movie.model.entity.FilmEvaluate;
import com.lbm.movie.model.vo.FilmEvaluateVO;

import java.util.List;

public interface FilmEvaluateService {

    void save(FilmEvaluate filmEvaluate) throws Exception;

    List<FilmEvaluateVO> findAllByFilmId(String fid);

    void deleteAllByFilmId(String fid);

    void deleteById(String id);

}
