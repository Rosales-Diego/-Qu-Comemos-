package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor // Constructor solo con campos @NonNull
@NoArgsConstructor // Constructor sin argumentos requerido por Hibernate
@Getter
@Setter
@ToString
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pedido_ID")
	private long id;
	
	@NonNull
	//@Column(nullable = false, unique = true)
	private String codigo;
	
	@Enumerated(EnumType.STRING)
	@NonNull
	@Column(nullable = false)
	private EstadoPedido estado = EstadoPedido.PENDIENTE;
	
	private float monto = 0.0f;
	
	private LocalDate fecha = LocalDate.now();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="Pedido_Producto",
	joinColumns=@JoinColumn(name="ped_ID",
	referencedColumnName="pedido_ID"),
	inverseJoinColumns=@JoinColumn(name="pro_ID",
	referencedColumnName="producto_ID"))
	private List<Producto> productos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	@NonNull
	private Cliente clientePedido;
	
	
	public float getMonto() {
		if (this.monto == 0.0f) {
			this.monto = (float) (this.productos.stream().mapToDouble(producto -> producto.getPrecio()).sum());
		} 
		return this.monto;
		
	}

}
