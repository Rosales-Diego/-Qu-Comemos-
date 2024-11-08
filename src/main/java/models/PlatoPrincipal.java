package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PlatoPrincipal")
@NoArgsConstructor
public class PlatoPrincipal extends Producto {

}
