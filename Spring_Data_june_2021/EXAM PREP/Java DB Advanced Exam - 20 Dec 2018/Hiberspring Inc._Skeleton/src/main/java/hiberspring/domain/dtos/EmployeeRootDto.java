package hiberspring.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRootDto {


    @XmlElement(name = "employee")
    private Set<EmployeeDto> employeeDtos;

    public EmployeeRootDto() {
    }

    public Set<EmployeeDto> getEmployeeDtos() {
        return employeeDtos;
    }

    public void setEmployeeDtos(Set<EmployeeDto> employeeDtos) {
        this.employeeDtos = employeeDtos;
    }
}
