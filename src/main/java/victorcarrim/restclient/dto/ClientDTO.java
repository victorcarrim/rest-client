package victorcarrim.restclient.dto;

import victorcarrim.restclient.entities.Client;

import java.util.Date;
import java.util.Objects;

public class ClientDTO {

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Date birthDate;
    private Integer children;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String cpf, Double income, Date birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientDTO clientDTO)) return false;
        return Objects.equals(getId(), clientDTO.getId()) && Objects.equals(getName(), clientDTO.getName()) && Objects.equals(getCpf(), clientDTO.getCpf()) && Objects.equals(getIncome(), clientDTO.getIncome()) && Objects.equals(getBirthDate(), clientDTO.getBirthDate()) && Objects.equals(getChildren(), clientDTO.getChildren());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
