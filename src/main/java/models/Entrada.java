package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Entrada")
@NoArgsConstructor
public class Entrada extends Producto {

}
