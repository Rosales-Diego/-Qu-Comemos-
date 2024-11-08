package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Postre")
@NoArgsConstructor
public class Postre extends Producto {

}
