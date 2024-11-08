package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@DiscriminatorValue("Menu")
@NoArgsConstructor
@Getter
@Setter
public class Menu extends Producto {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id") // Crea una columna "menu_id" en los productos que pertenecen a un menú
	private List<Producto> componentes = new ArrayList<Producto>();

	@Enumerated(EnumType.STRING)
	@NonNull
	private TipoMenu tipo;
	
//	@Override
//	public float getPrecio() {
//		return (float) (this.componentes.stream().mapToDouble(componente -> componente.getPrecio()).sum());
//	}

}
