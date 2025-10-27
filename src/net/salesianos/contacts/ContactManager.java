package net.salesianos.contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private static final ArrayList<Contact> contactos = new ArrayList<>();

    public static void añadirContacto(Contact c) {
        contactos.add(c);
    }

    public static boolean eliminarContacto(int index) {
        if (index >= 0 && index < contactos.size()) {
            contactos.remove(index);
            return true;
        }
        return false;
    }

    public static boolean editarContacto(int index, Contact c) {
        if (index >= 0 && index < contactos.size()) {
            contactos.set(index, c);
            return true;
        }
        return false;
    }

    public static List<Contact> listarContactos() {
        return new ArrayList<>(contactos); // devuelve copia
    }
}

