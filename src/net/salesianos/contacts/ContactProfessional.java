package net.salesianos.contacts;

public class ContactProfessional extends Contact {
    private String empresa;

    public ContactProfessional(String nombre, String telefono, String email, String empresa) {
        super(nombre, telefono, email);
        this.empresa = empresa;
    }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    

    @Override
    public String toString() {
        return super.toString() + " | " + empresa;
    }
    
}
