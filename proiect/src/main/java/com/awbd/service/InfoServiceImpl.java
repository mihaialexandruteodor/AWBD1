package com.awbd.service;

import com.awbd.model.Info;
import com.awbd.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoServiceImpl implements InfoService{
    
    @Autowired
    private InfoRepository infoRepository;

    @Override
    public List<Info> getAllInfos() { return infoRepository.findAll(); }

    @Override
    public void saveInfo(Info info) { this.infoRepository.save(info); }

    @Override
    public Info getInfoById(long id) {
        Optional<Info> optional = infoRepository.findById(id);
        Info info = null;
        if (optional.isPresent()) {
            info = optional.get();
        } else {
            throw new RuntimeException(" Info not found for id :: " + id);
        }
        return info;
    }

    @Override
    public void deleteInfoById(long id) { this.infoRepository.deleteById(id); }

    @Override
    public Page<Info> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.infoRepository.findAll(pageable);
    }
}
