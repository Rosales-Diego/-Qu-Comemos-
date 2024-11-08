package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("Admin")
@NoArgsConstructor // Constructor sin argumentos requerido por Hibernate
@Getter
@Setter
@ToString(callSuper = true)
public class Admin extends Usuario {

}
