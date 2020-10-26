package com.ast.ejecutorws.model;

public class Respueta {
	private Boolean flag;
	private String mensaje;
	private Integer cod;
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	
	@Override
	public String toString() {
		return "Respueta [flag=" + flag + ", mensaje=" + mensaje + ", cod=" + cod + ", getFlag()=" + getFlag()
				+ ", getMensaje()=" + getMensaje() + ", getCod()=" + getCod() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
