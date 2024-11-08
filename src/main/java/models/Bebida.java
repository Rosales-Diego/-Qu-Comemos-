package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Bebida")
@NoArgsConstructor
public class Bebida extends Producto {

}
