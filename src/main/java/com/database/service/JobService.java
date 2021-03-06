package com.database.service;

import com.database.dao.JobDao;
import com.model.Job;

import java.util.List;

public class JobService implements Service<Job>, AdditionalService<Job> {
    private JobDao jobDao = new JobDao();

    @Override
    public List<Job> getAll() {
        return jobDao.getAll();
    }

    @Override
    public boolean remove(Long id) {
        return jobDao.remove(id);
    }

    @Override
    public Job getById(Long id) {
        return jobDao.getById(id);
    }

    @Override
    public Job add(Job job) {
        return jobDao.add(job);//temporary
    }

    @Override
    public Long update(Job job, Long id) {
        return jobDao.update(job, id);
    }

    @Override
    public boolean removeByParameter(String companyName) {
        return jobDao.removeByCompanyName(companyName);
    }

    @Override
    public void addObjectForEmployee(Job job, Long id) {
        jobDao.addJobForEmployee(job, id);
    }
}
