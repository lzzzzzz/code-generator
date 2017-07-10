package org.openmore.dto.dao.api;

public class PersonDto {

    private Long id;
    private String name;
    private Integer age;
    private List<String> favourite;

    private void setId(Long id){
        this.id=id;
        }

    private Long getId(){
        return this.id
        }
    private void setName(String name){
        this.name=name;
        }

    private String getName(){
        return this.name
        }
    private void setAge(Integer age){
        this.age=age;
        }

    private Integer getAge(){
        return this.age
        }
    private void setFavourite(List<String> favourite){
        this.favourite=favourite;
        }

    private List<String> getFavourite(){
        return this.favourite
        }
}