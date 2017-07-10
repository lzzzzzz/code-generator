package org.openmore.dto.service.impl;

import org.openmore.dto.dto.service.PersonService;

import org.openmore.dto.dto.api.PersonDto;

@Service
public interface PersonServiceImpl implements PersonService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PersonMapper PersonDao;

    public Person getById(){
        return PersonDao.getPersonById();
        }

    /**增*/
    public void create(Person Person){
        PersonDao.insertPerson(Person );
        }
    /**删*/
    public void deleteById(Long id){
        PersonDao.deleteById(id);
        }
    /**改*/
    public void update(Long id, Person Person){
        PersonDao.update(id , Person);
        }
    /**查*/
    public PersonDto getPersonProfile(long uid){
        Person Person=PersonDao.getById(uid);
        PersonDto dto= new PersonDto();
        BeanUtils.copyProperties(Person, dto);
        return dto;
        }
}