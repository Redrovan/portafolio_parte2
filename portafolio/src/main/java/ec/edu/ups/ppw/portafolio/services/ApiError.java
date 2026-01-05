package ec.edu.ups.ppw.portafolio.services;

public class ApiError {

	private int codigo;
	private String name;
	private String Descripcion;
	
	public ApiError() {
		
	}
	
	public ApiError(int codigo, String name, String Descripcion) {
		this.codigo=codigo;
		this.name=name;
		this.Descripcion=Descripcion;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
}
