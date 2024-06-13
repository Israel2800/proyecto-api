package fca.suayed.dto;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class ClientDto {
    private Long id;
    private String name;
    private String lastName1;
    private String lastName2;
    private String rfc;

    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    @ColumnName("nombre")
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    @ColumnName("apellido_paterno")
    public String getLastName1(){
        return lastName1;
    }
    
    public void setLastName1(String lastName1){
        this.lastName1 = lastName1;
    }
    
    @ColumnName("apellido_materno")
    public String getLastName2(){
        return lastName2;
    }

    public void setLastName2(String lastName2){
        this.lastName2 = lastName2;
    }

    @ColumnName("rfc")
    public String getRfc(){
        return rfc;
    }

    public void setRfc(String rfc){
        this.rfc = rfc;
    }
}