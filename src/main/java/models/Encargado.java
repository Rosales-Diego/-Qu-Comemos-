package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("Encargado") // Valor discriminador para esta subclase
@RequiredArgsConstructor // Constructor solo con campos @NonNull
@NoArgsConstructor // Constructor sin argumentos requerido por Hibernate
@Getter
@Setter
@ToString(callSuper = true)
public class Encargado extends Usuario {

	@NonNull
	private String nombre;

	@NonNull
	private String apellido;

	private String foto;

	@Enumerated(EnumType.STRING)
	private Turno turno;

	private boolean habilitado = false;

}
