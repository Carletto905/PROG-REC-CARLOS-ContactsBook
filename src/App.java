import java.util.List;
import java.util.Scanner;
import net.salesianos.contacts.Contact;
import net.salesianos.contacts.ContactProfessional;
import net.salesianos.contacts.ContactManager;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Contact Book");
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    añadirContacto();
                    break;
                case "2":
                    editarContacto();
                    break;
                case "3":
                    eliminarContacto();
                    break;
                case "4":
                    listarContactos();
                    break;
                case "5":
                    System.out.println("Saliendo del programa");
                    salir = true;
                    break;
                default:
                    System.out.println(" Opción no válida. Intentalo de nuevo.");
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Añadir contacto");
        System.out.println("2. Editar contacto");
        System.out.println("3. Eliminar contacto");
        System.out.println("4. Listar contactos");
        System.out.println("5. Salir");
    }

    private static void añadirContacto() {
        System.out.print("¿Es un contacto profesional?: ");
        String tipo = sc.nextLine().trim().toLowerCase();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        if (tipo.equals("s")) {
            System.out.print("Empresa: ");
            String empresa = sc.nextLine();
            ContactProfessional cp = new ContactProfessional(nombre, telefono, email, empresa);
            ContactManager.añadirContacto(cp);
        } else {
            Contact c = new Contact(nombre, telefono, email);
            ContactManager.añadirContacto(c);
        }

        System.out.println("Contacto añadido correctamente.");
    }

    private static void editarContacto() {
        listarContactos();
        System.out.print("Escribe el número del contacto a editar: ");
        int index = leerEnteroSeguro();

        List<Contact> lista = ContactManager.listarContactos();
        if (index < 0 || index >= lista.size()) {
            System.out.println(" Número no válido.");
            return;
        }

        Contact actual = lista.get(index);
        System.out.println("Editando: " + actual);

        System.out.print("Nuevo nombre (deja vacío para mantener): ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo teléfono (deja vacío para mantener): ");
        String telefono = sc.nextLine();
        System.out.print("Nuevo email (deja vacío para mantener): ");
        String email = sc.nextLine();

        if (actual instanceof ContactProfessional) {
            System.out.print("Nueva empresa (deja vacío para mantener): ");
            String empresa = sc.nextLine();
            ContactProfessional cp = (ContactProfessional) actual;
            if (!nombre.isEmpty()) cp.setNombre(nombre);
            if (!telefono.isEmpty()) cp.setTelefono(telefono);
            if (!email.isEmpty()) cp.setEmail(email);
            if (!empresa.isEmpty()) cp.setEmpresa(empresa);
            ContactManager.editarContacto(index, cp);
        } else {
            if (!nombre.isEmpty()) actual.setNombre(nombre);
            if (!telefono.isEmpty()) actual.setTelefono(telefono);
            if (!email.isEmpty()) actual.setEmail(email);
            ContactManager.editarContacto(index, actual);
        }

        System.out.println("Contacto actualizado correctamente.");
    }

    private static void eliminarContacto() {
        listarContactos();
        System.out.print("Introduce el número del contacto a eliminar: ");
        int index = leerEnteroSeguro();

        if (ContactManager.eliminarContacto(index)) {
            System.out.println("Contacto eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el contacto.");
        }
    }

    private static void listarContactos() {
        List<Contact> lista = ContactManager.listarContactos();
        if (lista.isEmpty()) {
            System.out.println("No hay contactos guardados.");
            return;
        }
        System.out.println("\n--- Lista de Contactos ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + ") " + lista.get(i));
        }
    }

    private static int leerEnteroSeguro() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

  
}
