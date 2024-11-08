package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor // Crea un constructor solo con los campos @NonNull
@NoArgsConstructor // Necesario para Hibernate
@Getter
@Setter
@ToString
public class Sugerencia implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sugerencia_id")
	private Long id;

	@NonNull
	@Column(nullable = false)
	private String contenido;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoSugerencia tipo;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@NonNull
	private Cliente clienteSugerencia;
}
