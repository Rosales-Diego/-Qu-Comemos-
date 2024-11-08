package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("Cliente") // Valor discriminador para esta subclase
@RequiredArgsConstructor // Constructor solo con campos @NonNull
@NoArgsConstructor // Constructor sin argumentos requerido por Hibernate
@Getter
@Setter
@ToString(callSuper = true)
public class Cliente extends Usuario {

	@NonNull
	private String nombre;

	@NonNull
	private String apellido;

	private String foto;

	private boolean habilitado = false;

	@OneToMany(mappedBy = "clienteSugerencia", cascade = CascadeType.REMOVE)
	private List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
	
	@OneToMany(mappedBy="clientePedido", cascade = CascadeType.REMOVE)
	private List<Pedido> pedidos = new ArrayList<Pedido>();

}
