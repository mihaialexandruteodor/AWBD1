package com.awbd.service;

import com.awbd.model.Info;
import com.awbd.model.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InfoService {
    List<Info> getAllInfos();
    void saveInfo(Info info);
    Info getInfoById(long id);
    void deleteInfoById(long id);
    Page<Info> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    void ChangeProjectInfo(Info info, Project project);
    void RemoveProjectInfo(Info info);
}
