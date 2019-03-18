package com.database.service;

import com.database.dao.AddressDao;
import com.model.Address;

import java.util.List;

public class AddressService  implements Service<Address>,AdditionalService<Address> {
    private AddressDao addressDao;

    @Override
    public List<Address> getAll() {
        return addressDao.getAll();
    }

    @Override
    public boolean remove(Long id) {
        return addressDao.remove(id);
    }

    @Override
    public Address getById(Long id) {
        return addressDao.getById(id);
    }

    @Override
    public Address add(Address address) {
        return addressDao.add(address);//temporary
    }

    @Override
    public Long update(Address address, Long id) {
        return addressDao.update(address,id);
    }

    //Temporary method
    @Override
    public boolean removeByParameter(String street) {
        return addressDao.removeByStreet(street);
    }
    //Temporary method
    @Override
    public void addObjectForEmployee(Address address, Long id) {
         addressDao.addJobForEmployee(address,id);
    }
}
