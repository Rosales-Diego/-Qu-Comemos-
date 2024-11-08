package models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "ROL")
@RequiredArgsConstructor // Crea un constructor solo con los campos @NonNull
@NoArgsConstructor // Necesario para Hibernate
@Getter
@Setter
@ToString
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usuario_id")
	protected Long id;

	@NonNull
	@Column(nullable = false, unique = true)
	protected String email;

	@NonNull
	@Column(nullable = false)
	protected String dni;

	@NonNull
	@Column(nullable = false)
	protected String clave;

}
