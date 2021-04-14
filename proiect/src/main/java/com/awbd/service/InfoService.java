package com.awbd.service;

import com.awbd.model.Info;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InfoService {
    List<Info> getAllInfos();
    void saveInfo(Info info);
    Info getInfoById(long id);
    void deleteInfoById(long id);
    Page<Info> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
