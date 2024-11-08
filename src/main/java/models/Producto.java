package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_producto", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor // Constructor sin argumentos necesario para Hibernate
@ToString
public abstract class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="producto_ID")
	protected long id;

	@NonNull
	//@Column(nullable = false, unique = true)
	protected String nombre;

	@Column
	protected String imagen;

	@Column(nullable = false)
	protected float precio;
	
//	@ManyToOne
//	@JoinColumn(name="pedido_id")
//	protected Pedido pedido;
	
	@ManyToMany(mappedBy="productos")
	private List<Pedido> pedidos = new ArrayList<>();

}
